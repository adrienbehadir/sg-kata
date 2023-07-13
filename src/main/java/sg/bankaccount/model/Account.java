package sg.bankaccount.model;

import sg.bankaccount.exception.NegativeBalanceException;

public final class Account {

    private Money balance;
    private final Transactions transactions = new Transactions();

    public Account(Money initBalance) {
        transactions.initDeposit(initBalance);
        balance = initBalance;
    }

    public void debit(Money amount) {
        if (balance.compareTo(amount) < 0) {
            throw new NegativeBalanceException();
        }
        transactions.debit(amount);
        balance = balance.subtract(amount);
    }

    public void deposit(Money amount) {
        transactions.deposit(amount);
        balance = balance.add(amount);
    }

    public Money getBalance() {
        return balance;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        return "Balance: " + balance + System.lineSeparator() + transactions.toString();
    }
}
