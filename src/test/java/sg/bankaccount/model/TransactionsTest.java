package sg.bankaccount.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sg.bankaccount.exception.ForbiddenTransactionException;
import sg.bankaccount.exception.NegativeBalanceException;

@DisplayName("Transactions")
class TransactionsTest {
    Transactions transactions;

    @BeforeEach
    void initTransactions() {
        transactions = new Transactions();
    }

    @Test
    @DisplayName("should have only one initial transaction")
    void testUniqueInitialTransactionException() {
        ForbiddenTransactionException thrown = Assertions.assertThrows(ForbiddenTransactionException.class, () -> {
            transactions.initDeposit(new Money(1000));
            transactions.initDeposit(new Money(1000));
        });
        Assertions.assertEquals("The initial transaction is only allowed on the first transaction!", thrown.getMessage());
    }

    @Test
    @DisplayName("should prevent negative balance on initial transaction")
    void testNegavtiveBalanceException() {
        NegativeBalanceException thrown = Assertions.assertThrows(NegativeBalanceException.class, () -> {
            transactions.initDeposit(new Money(-1));
        });
        Assertions.assertEquals("Balance cannot be less than 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("should have an initial transaction as first transaction")
    void testFirstTransactionException() {
        ForbiddenTransactionException thrown = Assertions.assertThrows(ForbiddenTransactionException.class, () -> {
            transactions.deposit(new Money(1000));
        });
        Assertions.assertEquals("Only initial transaction is allowed as first transaction!", thrown.getMessage());
    }

    @Test
    @DisplayName("should disallow transactions less than 0 after the initial transaction")
    void testNegativeTransactionException() {
        ForbiddenTransactionException thrown = Assertions.assertThrows(ForbiddenTransactionException.class, () -> {
            transactions.initDeposit(new Money(0));
            transactions.deposit(new Money(-1));
        });
        Assertions.assertEquals("Transaction amount cannot be less than or equal to 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("should disallow transactions equal to 0 after the initial transaction")
    void testNullTransactionException() {
        ForbiddenTransactionException thrown = Assertions.assertThrows(ForbiddenTransactionException.class, () -> {
            transactions.initDeposit(new Money(0));
            transactions.deposit(new Money(0));
        });
        Assertions.assertEquals("Transaction amount cannot be less than or equal to 0!", thrown.getMessage());
    }

    @Test
    @DisplayName("can create debited transaction")
    void debit() {
        transactions.initDeposit(new Money(0));
        transactions.debit(new Money(1000));
        Assertions.assertEquals(2, transactions.getTransactions().size());
    }

    @Test
    @DisplayName("can create deposited transaction")
    void deposit() {
        transactions.initDeposit(new Money(1000));
        transactions.deposit(new Money(1000));
        Assertions.assertEquals(2, transactions.getTransactions().size());
    }

    @Test
    @DisplayName("can create initial deposited transaction")
    void initDeposit() {
        transactions.initDeposit(new Money(1000));
        Assertions.assertEquals(1, transactions.getTransactions().size());
    }

    @Test
    @DisplayName("can store many transactions")
    void getTransactions() {
        transactions.initDeposit(new Money(1000));
        transactions.debit(new Money(1200));
        transactions.deposit(new Money(300));
        Assertions.assertEquals(3, transactions.getTransactions().size());
    }

    @Test
    @DisplayName("should correctly print transactions")
    void testToString() {
        transactions.initDeposit(new Money(1000));
        transactions.debit(new Money(1200));
        transactions.deposit(new Money(300));
        String[] strTransactions = transactions.toString().split("\n");
        Assertions.assertTrue(strTransactions[0].contains("DEPOSIT EUR 300"));
        Assertions.assertTrue(strTransactions[1].contains("DEBIT EUR 1200"));
        Assertions.assertTrue(strTransactions[2].contains("INIT_DEPOSIT EUR 1000"));
    }
}