package az.growlab.LoanApp.exception;

import org.webjars.NotFoundException;

public class ReasonNotFoundException extends NotFoundException {
    public ReasonNotFoundException(String message) {
        super(message);
    }
}
