package ovOOP;

import java.util.Scanner;

public class BalanceSystem {
    static void manageBalance(Scanner scanner) {
        DataSystem data = new DataSystem(Main.userID);
        System.out.println(ColorSystem.colorPalette[0] + "---------------------------------------");
        System.out.println(ColorSystem.colorPalette[1] + "Please choose from the following options");
        System.out.println(ColorSystem.colorPalette[1] + "the balance of the user " + data.getUsername() + " is "
                + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(data.getBalance()));
        int balanceMenu = OptionsSystem.showOption(scanner, "Balance Menu", "Deposit,Withdraw,Main Menu");

        if (balanceMenu == 1) {
            // Deposit code here
            System.out.println(ColorSystem.colorPalette[0] + "Please choose how much you would like to deposit");
            double deposit;
            try {
                deposit = scanner.nextDouble();
                double balance = data.getBalance();

                if (deposit > 0) {
                    balance = balance + deposit;
                    data.setBalance(balance);
                    System.out.println("Successfully deposited " + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(deposit) + ColorSystem.colorPalette[0]
                            + " to balance (" + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(data.getBalance()) + ColorSystem.colorPalette[0] + ")");
                    manageBalance(scanner);
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.colorPalette[0] + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 2) {
            // Withdraw code here
            System.out.println(ColorSystem.colorPalette[0] + "Please choose how much you would like to withdraw");
            double withdraw;
            try {
                withdraw = scanner.nextDouble();
                double balance = data.getBalance();

                if (withdraw > 0) {
                    if (balance - withdraw >= 0) {
                        balance = balance - withdraw;
                        data.setBalance(balance);
                        System.out.println("Successfully withdrawn " + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(withdraw) + ColorSystem.colorPalette[0]
                                + " to balance (" + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(data.getBalance()) + ColorSystem.colorPalette[0] + ")");
                        manageBalance(scanner);
                    } else {
                        System.out
                                .println("You do not have enough balance to withdraw " + ColorSystem.colorPalette[1] + ColorSystem.withLargeIntegers(withdraw));
                        manageBalance(scanner);
                    }
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.colorPalette[0] + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 3) {
            MenuSystem.startMenu();
        }
    }
}
