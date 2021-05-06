package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import src.model.Ratio;
import src.model.Text;
import src.model.Writer;
import src.repository.TextRepository;
import src.repository.WriterRepository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    TextRepository textRepository;

    @Autowired
    WriterRepository writerRepository;

    public Map<String, Text> writings(String name) {
        return writerRepository.writings(name).stream()
                .collect(Collectors.toMap(Text::getSubject, Function.identity()));
    }

    public Map<String, Ratio> wordRatios(String name) {
        return writerRepository.wordRatio(name).stream()
                .collect(Collectors.toMap(Ratio::getText, Function.identity()));
    }

    public boolean exist(String name) {
        return writerRepository.existsById(name);
    }

    public List<String> allWriterNames() {
        return writerRepository.writers();
    }

    public Writer getWriter(String name) {
        return writerRepository.findById(name).get();
    }

    public void saveOrUpdate(Text text) {
        textRepository.save(text);
    }

    public List<String> writers() {
        return writerRepository.writers();
    }

    public void saveOrUpdateWriter(Writer writer) {
        writerRepository.save(writer);
    }

    public boolean existingSubject(String writer, String subject) {
        return !textRepository.findWriterNames(subject, writer).isEmpty();
    }
}
