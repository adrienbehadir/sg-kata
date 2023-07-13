package sg.bankaccount.exception;

public class NegativeBalanceException extends RuntimeException {
    public NegativeBalanceException() {
        super("Balance cannot be less than 0!");
    }
}