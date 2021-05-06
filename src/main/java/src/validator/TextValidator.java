package src.validator;

import src.model.Text;
import src.service.Service;

import java.util.Optional;

public class TextValidator extends AbstractValidator {

    private final Text text;

    public TextValidator(Text text, Service service) {
        super(service);
        this.text = text;
    }

    public boolean validate() {
        if (text == null) {
            errorMessages.add("Content cannot be empty!");
        }
        Optional.ofNullable(text)
                .filter(t -> t.getWriter() != null)
                .filter(t -> !t.getWriter().isEmpty())
                .filter(t -> !service.exist(t.getWriter()))
                .ifPresent(t -> errorMessages.add("There is no writer named " + t.getWriter()));
        Optional.ofNullable(text)
                .filter(t -> t.getWriter() != null)
                .filter(t -> !t.getWriter().isEmpty())
                .filter(t -> service.existingSubject(t.getWriter(), t.getSubject()))
                .ifPresent(t -> errorMessages.add("There is a different post by "+ text.getWriter() +" author on"+ text.getSubject() ));
        return errorMessages.isEmpty();
    }

}
