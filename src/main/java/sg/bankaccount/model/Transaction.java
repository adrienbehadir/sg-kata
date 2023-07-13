package sg.bankaccount.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public final class Transaction {
    private final OperationType operationType;
    private final LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);
    private final Money amount;

    public Transaction(Money money, OperationType operationType) {
        amount = money;
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return createdAt.format(ISO_LOCAL_DATE_TIME) + " " + operationType + " " + amount.toString();
    }
}
