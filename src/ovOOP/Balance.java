package ovOOP;

import java.util.Scanner;

public class Balance {
    static void manageBalance(Scanner scanner) {
        Data data = new Data(Main.userID);
        System.out.println(Color.ANSI_BLUE + "---------------------------------------");
        System.out.println(Color.ANSI_CYAN + "Please choose from the following options");
        System.out.println(Color.ANSI_CYAN + "the balance of the user " + data.getUsername() + " is "
                + Color.ANSI_GREEN + data.getBalance());
        int balanceMenu = Option.showOption(scanner, "Deposit,Withdraw,Main Menu");

        if (balanceMenu == 1) {
            // Deposit code here
            System.out.println(Color.ANSI_BLUE + "Please choose how much you would like to deposit");
            double deposit;
            try {
                deposit = scanner.nextDouble();
                double balance = data.getBalance();

                if (deposit > 0) {
                    balance = balance + deposit;
                    data.setBalance(balance);
                    System.out.println("Succesfully deposited " + Color.ANSI_GREEN + deposit + Color.ANSI_BLUE
                            + " to balance (" + Color.ANSI_GREEN + balance + Color.ANSI_BLUE + ")");
                    manageBalance(scanner);
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(Color.ANSI_RED + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 2) {
            // Withdraw code here
            System.out.println(Color.ANSI_BLUE + "Please choose how much you would like to withdraw");
            double withdraw;
            try {
                withdraw = scanner.nextDouble();
                double balance = data.getBalance();

                if (withdraw > 0) {
                    if (balance - withdraw >= 0) {
                        balance = balance - withdraw;
                        data.setBalance(balance);
                        System.out.println("Succesfully withdrawn " + Color.ANSI_GREEN + withdraw + Color.ANSI_BLUE
                                + " to balance (" + Color.ANSI_GREEN + balance + Color.ANSI_BLUE + ")");
                        manageBalance(scanner);
                    } else {
                        System.out
                                .println("You do not have enough balance to withdraw " + Color.ANSI_GREEN + withdraw);
                        manageBalance(scanner);
                    }
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(Color.ANSI_RED + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 3) {
            Menu.startMenu(scanner);
        }
    }
}
