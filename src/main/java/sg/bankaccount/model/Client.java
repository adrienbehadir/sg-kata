package sg.bankaccount.model;

import java.security.InvalidParameterException;

public class Client {
    private final Account account;
    private final String firstName;
    private final String lastName;

    public Client(String firstName, String lastName, Money initBalance) {
        if (firstName.length() < 1 || lastName.length() < 1) {
            throw new InvalidParameterException("Client FirstName and LastName must contain at least one character!");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        account = new Account(initBalance);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Account getAccount() {
        return account;
    }
}
