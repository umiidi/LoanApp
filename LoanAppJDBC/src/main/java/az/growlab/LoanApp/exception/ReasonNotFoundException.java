package az.growlab.LoanApp.exception;

public class ReasonNotFoundException extends RuntimeException {
    public ReasonNotFoundException(String message) {
        super(message);
    }
}
