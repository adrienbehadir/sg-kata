package sg.bankaccount.model;

import sg.bankaccount.exception.ForbiddenTransactionException;
import sg.bankaccount.exception.NegativeBalanceException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Transactions {

    private final List<Transaction> transactions = new ArrayList<>();

    private void createTransaction(Money amount, OperationType operationType) {
        if (transactions.size() == 0) {
            throw new ForbiddenTransactionException("Only initial transaction is allowed as first transaction!");
        }
        if (amount.compareTo(0) <= 0) {
            throw new ForbiddenTransactionException("Transaction amount cannot be less than or equal to 0!");
        }
        transactions.add(0, new Transaction(amount, operationType));
    }

    public void deposit(Money amount) {
        createTransaction(amount, OperationType.DEPOSIT);
    }

    public void debit(Money amount) {
        createTransaction(amount, OperationType.DEBIT);
    }

    public void initDeposit(Money amount) {
        if (amount.compareTo(0) < 0) {
            throw new NegativeBalanceException();
        }
        if (transactions.size() > 0) {
            throw new ForbiddenTransactionException("The initial transaction is only allowed on the first transaction!");
        }
        transactions.add(0, new Transaction(amount, OperationType.INIT_DEPOSIT));
    }

    public Collection<Transaction> getTransactions() {
        return Collections.unmodifiableCollection(transactions);
    }

    @Override
    public String toString() {
        return transactions.stream()
                .map(Transaction::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
