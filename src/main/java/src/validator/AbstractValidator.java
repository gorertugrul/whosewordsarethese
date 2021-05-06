package src.validator;

import src.service.Service;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractValidator {

    protected Service service;

    protected List<String> errorMessages = new ArrayList<>();

    public AbstractValidator(Service service) {
        this.service = service;
    }

    public abstract boolean validate();

    public List<String> getErrorMessages() {
        return errorMessages;
    }


}
