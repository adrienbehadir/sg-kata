package sg.bankaccount.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Transaction")
class TransactionTest {

    @Test
    @DisplayName("should instanced")
    void testConstructor() {
        Transaction transaction = new Transaction(new Money(1000), OperationType.DEBIT);
        assertNotNull(transaction);
    }

    @Test
    @DisplayName("should correctly printed")
    void testToString() {
        Transaction transaction = new Transaction(new Money(1000), OperationType.DEBIT);
        assertTrue(transaction.toString().contains("DEBIT EUR 1000"));
    }
}