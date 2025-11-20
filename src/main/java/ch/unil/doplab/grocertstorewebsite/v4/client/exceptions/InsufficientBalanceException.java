package ch.unil.doplab.grocertstorewebsite.v4.client.exceptions;

/**
 * Software Architectures | DOPLab | UniL
 *
 * @author Melike Geçer
 */
public class InsufficientBalanceException extends Exception {

    private String message;

    public InsufficientBalanceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
