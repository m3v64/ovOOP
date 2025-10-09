package ovOOP;

import java.util.Scanner;

public class Menu {

    static void askDestination(Scanner scanner) {
        // travelling system goes here
    }

    static void startMenu(Scanner scanner) {
        System.out.println(Color.BLUE + "---------------------------------------");
        System.out.println(Color.CYAN + "Welcome to OVOOP");
        System.out.println(Color.CYAN + "Please choose from the following options");
        int input = 0;
        Data data = new Data(Main.userID);
        int currentUser = data.getUserID();
        if (currentUser != 0) {
            System.out.println(
                    Color.BLACK + "You are currently logged in as " + data.getUsername() + " with a balance of "
                            +  Color.GREEN + Color.withLargeIntegers(data.getBalance()));
            input = Option.showOption(scanner,
                    "Start travelling,Account Settings,Manage balance,Exit system,Credits");
        } else {
            System.out.println(Color.BLACK + "You are not currently logged in, please log in before using any travelling features");
            input = Option.showOption(scanner, "Account Settings,Credits");
        }
        if (currentUser != 0) {
            if (input == 5) {
                // Credits go here
                showCredits(scanner);
            } else if (input == 1) {
                Menu.askDestination(scanner);
                // Travelling system
            } else if (input == 2) {
                AccountSystem.displayAccounts(scanner);
                // Accounts system goes here
            } else if (input == 3) {
                Balance.manageBalance(scanner);
            } else if (input == 4) {
                System.out.println(Color.RED + "Exiting System");
                System.exit(0);
                // Exits the system
            }
        } else {
            if (input == 2) {
                // Credits go here
                showCredits(scanner);
            } else if (input == 1) {
                AccountSystem.displayAccounts(scanner);
            }
        }
    }

    static void showCredits(Scanner scanner) {
        System.out.println(Color.BLACK + "------------------------------------------");
        System.out.println(Color.GREEN + "Developers : Morris van Uden, Max Viehöfer");
        System.out.println(Color.BLACK + "------------------------------------------");
        System.out.println(Color.GREEN + "Teacher : Erik Seldenthuis");
        System.out.println(Color.BLACK + "------------------------------------------");
        System.out.println(Color.GREEN + "Class : TIA4V1B");
        System.out.println(Color.BLACK + "------------------------------------------");
        System.out.println(Color.GREEN + "web page : https://ovoop.m3v.dev (Soon)");
        System.out.println(Color.BLACK + "------------------------------------------");
        System.out.println(Color.GREEN + "Github Repository : https://github.com/m3v64/ovOOP");
        System.out.println(Color.BLACK + "------------------------------------------");
        startMenu(scanner);
    }

    public static void clear() {
        for (int i = 0; i < 5000; i++) {
            System.out.println("\n");
        }
    }
}
