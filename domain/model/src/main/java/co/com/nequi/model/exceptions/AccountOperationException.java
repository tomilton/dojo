package co.com.nequi.model.exceptions;

public class AccountOperationException extends RuntimeException{
    public AccountOperationException(String message) {
        super(message);
    }
}
