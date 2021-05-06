package src.validator;

import src.model.Writer;
import src.service.Service;

public class WriterValidator extends AbstractValidator {
    private final Writer writer;

    public WriterValidator(Writer writer, Service service) {
        super(service);
        this.writer = writer;
    }

    @Override
    public boolean validate() {
        if (writer == null || writer.getName() == null || writer.getName().isEmpty()) {
            errorMessages.add("Writer name cannot be empty!");
        }
        return errorMessages.isEmpty();
    }
}
