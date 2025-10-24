package ovOOP;

import java.util.Scanner;

public class MenuSystem {
    static void startMenu(Scanner scanner) {
        System.out.println(ColorSystem.BLUE + "+------------------------------------------------+");
        System.out.println(ColorSystem.BLUE + "|                                                |");
        System.out.println(ColorSystem.CYAN + "|   _______ ___    _________ _______ ________    |");
        System.out.println(ColorSystem.CYAN + "|   __  __ \\__ |  / /__  __ \\__  __ \\___  __ \\   |");
        System.out.println(ColorSystem.CYAN + "|   _  / / /__ | / / _  / / /_  / / /__  /_/ /   |");
        System.out.println(ColorSystem.CYAN + "|   / /_/ / __ |/ /  / /_/ / / /_/ / _  ____/    |");
        System.out.println(ColorSystem.CYAN + "|   \\____/  _____/   \\____/  \\____/  /_/         |");
        System.out.println(ColorSystem.BLUE + "|                                                |");
        System.out.println(ColorSystem.BLUE + "+------------------------------------------------+");
        System.out.println(ColorSystem.BLUE + "=======================================");
        int input = 0;
        DataSystem data = new DataSystem(Main.userID);
        int currentUser = data.getUserID();
        if (currentUser != 0) {
            System.out.println(
                ColorSystem.BRIGHT_CYAN + String.format("Logged in as: %-20s", data.getUsername()) +
                ColorSystem.CYAN + " | Balance: " + ColorSystem.withLargeIntegers(data.getBalance())
            );
                System.out.println(ColorSystem.BLUE + "---------------------------------------");
                input = OptionsSystem.showOption(scanner,
                        "Start traveling,Account Settings,Manage balance,Exit system,Credits");
            } else {
                System.out.println(ColorSystem.BRIGHT_CYAN + "You are not currently logged in.");
                System.out.println(ColorSystem.BRIGHT_CYAN + "Please log in before using any traveling features.");
                System.out.println(ColorSystem.BLUE + "---------------------------------------");
                input = OptionsSystem.showOption(scanner, "Account Settings,Credits");
            }
            if (currentUser != 0) {
                if (input == 5) {
                    // Credits go here
                    showCredits(scanner);
                } else if (input == 1) {
                    // Travel Menu
                    TravelSystem.travelMenu(scanner);
                } else if (input == 2) {
                    AccountSystem.displayAccounts(scanner);
                    // Accounts system goes here
                } else if (input == 3) {
                    BalanceSystem.manageBalance(scanner);
                } else if (input == 4) {
                    System.out.println(ColorSystem.RED + "Exiting System");
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
        System.out.println(ColorSystem.BLUE + "==========================================");
        System.out.println(ColorSystem.BRIGHT_BLUE + "                CREDITS                   ");
        System.out.println(ColorSystem.BLUE + "==========================================");
        System.out.println(ColorSystem.CYAN + " Developers      : Morris van Uden, Max Viehöfer");
        System.out.println(ColorSystem.CYAN + " Teacher         : Erik Seldenthuis");
        System.out.println(ColorSystem.CYAN + " Class           : TIA4V1B");
        System.out.println(ColorSystem.CYAN + " Web Page        : https://ovoop.m3v.dev (Soon)");
        System.out.println(ColorSystem.CYAN + " GitHub Repo     : https://github.com/m3v64/ovOOP");
        System.out.println(ColorSystem.BLUE + "==========================================");
        System.out.println();
        System.out.print(ColorSystem.BRIGHT_CYAN + "Press Enter to return to the menu...");
        scanner.nextLine();
        startMenu(scanner);
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
