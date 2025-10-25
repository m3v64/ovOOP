package ovOOP;

import java.util.Scanner;

public class BalanceSystem {
    static void manageBalance(Scanner scanner) {
        DataSystem data = new DataSystem(Main.userID);
        System.out.println(ColorSystem.BLUE + "---------------------------------------");
        System.out.println(ColorSystem.BRIGHT_CYAN + "Please choose from the following options");
        System.out.println(ColorSystem.BRIGHT_CYAN + "the balance of the user " + data.getUsername() + " is "
                + ColorSystem.CYAN + ColorSystem.withLargeIntegers(data.getBalance()));
        int balanceMenu = OptionsSystem.showOption(scanner, "Balance Menu", "Deposit,Withdraw,Main Menu");

        if (balanceMenu == 1) {
            // Deposit code here
            System.out.println(ColorSystem.BLUE + "Please choose how much you would like to deposit");
            double deposit;
            try {
                deposit = scanner.nextDouble();
                double balance = data.getBalance();

                if (deposit > 0) {
                    balance = balance + deposit;
                    data.setBalance(balance);
                    System.out.println("Successfully deposited " + ColorSystem.CYAN + ColorSystem.withLargeIntegers(deposit) + ColorSystem.BLUE
                            + " to balance (" + ColorSystem.CYAN + ColorSystem.withLargeIntegers(data.getBalance()) + ColorSystem.BLUE + ")");
                    manageBalance(scanner);
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.BRIGHT_BLUE + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 2) {
            // Withdraw code here
            System.out.println(ColorSystem.BLUE + "Please choose how much you would like to withdraw");
            double withdraw;
            try {
                withdraw = scanner.nextDouble();
                double balance = data.getBalance();

                if (withdraw > 0) {
                    if (balance - withdraw >= 0) {
                        balance = balance - withdraw;
                        data.setBalance(balance);
                        System.out.println("Successfully withdrawn " + ColorSystem.CYAN + ColorSystem.withLargeIntegers(withdraw) + ColorSystem.BLUE
                                + " to balance (" + ColorSystem.CYAN + ColorSystem.withLargeIntegers(data.getBalance()) + ColorSystem.BLUE + ")");
                        manageBalance(scanner);
                    } else {
                        System.out
                                .println("You do not have enough balance to withdraw " + ColorSystem.CYAN + ColorSystem.withLargeIntegers(withdraw));
                        manageBalance(scanner);
                    }
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.BRIGHT_BLUE + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 3) {
            MenuSystem.startMenu(scanner);
        }
    }
}
