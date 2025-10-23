package ovOOP;

import java.util.Scanner;

public class Menu {
    static void startMenu(Scanner scanner) {
        System.out.println(Color.BLUE + "=======================================");
        System.out.println(Color.BRIGHT_BLUE + "           Welcome to OVOOP            ");
        System.out.println(Color.BLUE + "=======================================");
        System.out.println(Color.CYAN + "Please choose from the following options:");
        int input = 0;
        Data data = new Data(Main.userID);
        int currentUser = data.getUserID();
        if (currentUser != 0) {
            System.out.println(
                Color.BRIGHT_CYAN + String.format("Logged in as: %-20s", data.getUsername()) +
                Color.CYAN + " | Balance: " + Color.withLargeIntegers(data.getBalance())
            );
            System.out.println(Color.BLUE + "---------------------------------------");
            input = Option.showOption(scanner,
                    "Start traveling,Account Settings,Manage balance,Exit system,Credits");
        } else {
            System.out.println(Color.BRIGHT_CYAN + "You are not currently logged in.");
            System.out.println(Color.BRIGHT_CYAN + "Please log in before using any traveling features.");
            System.out.println(Color.BLUE + "---------------------------------------");
            input = Option.showOption(scanner, "Account Settings,Credits");
        }
        if (currentUser != 0) {
            if (input == 5) {
                // Credits go here
                showCredits(scanner);
            } else if (input == 1) {
                // Travel Menu
                Travel.travelMenu(scanner);
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
            switch (input) {
                case 1 -> AccountSystem.displayAccounts(scanner);
                case 2 -> showCredits(scanner);
            }
        }
    }

    static void showCredits(Scanner scanner) {
        System.out.println(Color.BLUE + "==========================================");
        System.out.println(Color.BRIGHT_BLUE + "                CREDITS                   ");
        System.out.println(Color.BLUE + "==========================================");
        System.out.println(Color.CYAN + " Developers      : Morris van Uden, Max Viehöfer");
        System.out.println(Color.CYAN + " Teacher         : Erik Seldenthuis");
        System.out.println(Color.CYAN + " Class           : TIA4V1B");
        System.out.println(Color.CYAN + " Web Page        : https://ovoop.m3v.dev (Soon)");
        System.out.println(Color.CYAN + " GitHub Repo     : https://github.com/m3v64/ovOOP");
        System.out.println(Color.BLUE + "==========================================");
        System.out.println();
        System.out.print(Color.BRIGHT_CYAN + "Press Enter to return to the menu...");
        scanner.nextLine();
        startMenu(scanner);
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
