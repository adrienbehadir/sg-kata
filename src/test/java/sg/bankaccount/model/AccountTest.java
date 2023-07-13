package sg.bankaccount.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.bankaccount.exception.NegativeBalanceException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Account")
class AccountTest {
    Account account;

    @BeforeEach
    void initAccount() {
        account = new Account(new Money(1000));
    }

    @Test
    @DisplayName("should not have a negative initial balance")
    void testConstructorException() {
        NegativeBalanceException thrown = assertThrows(NegativeBalanceException.class, () -> {
            Account account = new Account(new Money(-1));
        });
        assertEquals("Balance cannot be less than 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("should have an initial balance")
    void testConstructor() {
        assertTrue(account.toString().contains("Balance: EUR 1000"));
    }

    @Test
    @DisplayName("should have a balance")
    void testBalance() {
        assertNotNull(account.getBalance());
    }

    @Test
    @DisplayName("should have a initial transaction")
    void testTransaction() {
        assertNotNull(account.getTransactions());
    }

    @Test
    @DisplayName("can be debited")
    void testDebit() {
        account.debit(new Money(500));
        assertTrue(account.toString().contains("Balance: EUR 500"));
    }

    @Test
    @DisplayName("cannot be debited above 0")
    void testForbiddenDebit() {
        NegativeBalanceException thrown = assertThrows(NegativeBalanceException.class, () -> {
            account.debit(new Money(1001));
        });
        assertEquals("Balance cannot be less than 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("can be credited")
    void testCredit() {
        account.deposit(new Money(1000));
        assertTrue(account.toString().contains("Balance: EUR 2000"));
    }
}