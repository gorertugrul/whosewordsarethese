package src.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import src.analyser.Analyser;
import src.model.Text;
import src.model.Writer;
import src.parser.TextRatioParser;
import src.sample.Sample;
import src.service.Service;
import src.validator.AbstractValidator;
import src.validator.TextValidator;
import src.validator.WriterValidator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;


@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    Service service;

    @GetMapping("writers/all")
    private List<String> writers() {
        return service.writers();
    }

    @PostMapping(path = "/whoseWords")
    private List<String> whoseWords(@RequestBody String searchText) {
        return Analyser.analyse(searchText, service);
    }

    @PostMapping(path = "{writerName}/writings")
    private String addText(@PathVariable String writerName) {
        Map<String, Text> writings = service.writings(writerName);
        return writings.toString();
    }

    @PostMapping(path = "text/add", consumes = "application/json")
    private String addText(@RequestBody Text text) {
        Optional<String> errorMessages = Stream.of(new TextValidator(text, service),
                new WriterValidator(new Writer(text == null ? null : text.getWriter()), service))
                .filter(validator -> !validator.validate())
                .map(AbstractValidator::getErrorMessages)
                .flatMap(Collection::stream)
                .findFirst();
        if (!errorMessages.isPresent()) {
            Writer writer = service.getWriter(text.getWriter());
            writer.addWritings(text);
            writer.updateRatio(TextRatioParser.parse(text.getContent()));
            service.saveOrUpdateWriter(writer);
            return "Yazı başarı ile kayıt edildi.";
        } else {
            return "Hata: " + String.join(", ", errorMessages.get());
        }
    }

    @PostMapping(path = "writer/add", consumes = "application/json")
    private String addWriter(@RequestBody Writer writer) {
        if (!service.exist(writer.getName())) {
            WriterValidator validator = new WriterValidator(writer, service);
            if (validator.validate()) {
                service.saveOrUpdateWriter(writer);
                return writer.getName() + " saved";
            } else {
                return "Hata: " + String.join(", ", validator.getErrorMessages());
            }
        } else {
            return "Yazar mevcut";
        }
    }

    @GetMapping("/runSample")
    private void runSample() {
        Sample.generateSample().forEach(writer -> {
            writer.getWritings().forEach((key, value) -> writer.updateRatio(TextRatioParser.parse(value.getContent())));
            service.saveOrUpdateWriter(writer);
        });
    }
}
