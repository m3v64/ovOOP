package ovOOP;

import java.util.Scanner;

public class Balance {
    static void manageBalance(Scanner scanner) {
        Data data = new Data(Main.userID);
        System.out.println(Color.BLUE + "---------------------------------------");
        System.out.println(Color.CYAN + "Please choose from the following options");
        System.out.println(Color.CYAN + "the balance of the user " + data.getUsername() + " is "
                + Color.GREEN + data.getBalance());
        int balanceMenu = Option.showOption(scanner, "Deposit,Withdraw,Main Menu");

        if (balanceMenu == 1) {
            // Deposit code here
            System.out.println(Color.BLUE + "Please choose how much you would like to deposit");
            double deposit;
            try {
                deposit = scanner.nextDouble();
                double balance = data.getBalance();

                if (deposit > 0) {
                    balance = balance + deposit;
                    data.setBalance(balance);
                    System.out.println("Succesfully deposited " + Color.GREEN + deposit + Color.BLUE
                            + " to balance (" + Color.GREEN + balance + Color.BLUE + ")");
                    manageBalance(scanner);
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(Color.RED + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 2) {
            // Withdraw code here
            System.out.println(Color.BLUE + "Please choose how much you would like to withdraw");
            double withdraw;
            try {
                withdraw = scanner.nextDouble();
                double balance = data.getBalance();

                if (withdraw > 0) {
                    if (balance - withdraw >= 0) {
                        balance = balance - withdraw;
                        data.setBalance(balance);
                        System.out.println("Succesfully withdrawn " + Color.GREEN + withdraw + Color.BLUE
                                + " to balance (" + Color.GREEN + balance + Color.BLUE + ")");
                        manageBalance(scanner);
                    } else {
                        System.out
                                .println("You do not have enough balance to withdraw " + Color.GREEN + withdraw);
                        manageBalance(scanner);
                    }
                } else {
                    System.out.println("You cannot deposit numbers smaller than 1");
                    manageBalance(scanner);
                }
            } catch (Exception e) {
                System.out.println(Color.RED + "That is not a valid input!");
                manageBalance(scanner);
            }
        } else if (balanceMenu == 3) {
            Menu.startMenu(scanner);
        }
    }
}
