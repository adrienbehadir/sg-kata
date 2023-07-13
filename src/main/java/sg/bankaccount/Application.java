package sg.bankaccount;

import sg.bankaccount.model.Client;
import sg.bankaccount.model.Money;

import java.math.BigDecimal;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        app();
    }

    public static void app() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter client firstName");
        String clientFirstName = scanner.nextLine();

        System.out.println("Enter client lastName");
        String clientLastName = scanner.nextLine();

        System.out.println("Enter initial account balance");
        Money accountInitBalance = new Money(new BigDecimal(scanner.nextLine()));

        Client client = new Client(clientFirstName, clientLastName, accountInitBalance);

        boolean exit = false;
        while (!exit) {
            System.out.println(System.lineSeparator() + client);
            System.out.println("Choose an action:");
            System.out.println("- Statement printing (Enter S)");
            System.out.println("- Deposit (Enter D)");
            System.out.println("- Withdrawal (Enter W)");
            System.out.println("- Disconnect (Enter any character)");
            String option = scanner.nextLine();
            switch (option) {
                case "S" -> System.out.println(client.getAccount());
                case "D" -> {
                    try {
                        Money depositAmount = new Money(new BigDecimal(scanner.nextLine()));
                        client.getAccount().deposit(depositAmount);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                case "W" -> {
                    try {
                        Money withdrawAmount = new Money(new BigDecimal(scanner.nextLine()));
                        client.getAccount().debit(withdrawAmount);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                }
                default -> exit = true;
            }
        }
        System.exit(0);
    }
}
