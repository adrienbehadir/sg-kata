package sg.bankaccount.exception;

public class ForbiddenTransactionException extends RuntimeException {
    public ForbiddenTransactionException(String msg) {
        super(msg);
    }
}