package ovOOP;

import java.util.Scanner;


public class Travel {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static void askDestination(Scanner scanner) {
        //travelling system goes here
    }

    static void startMenu(Scanner scanner) {
        System.out.println(ANSI_BLUE + "---------------------------------------");
        System.out.println(ANSI_CYAN + "Welcome to OVOOP");
        System.out.println(ANSI_CYAN + "Please choose from the following options");
        System.out.println(ANSI_BLUE + "---------------------------------------" + ANSI_CYAN);
        System.out.println("1. Credits");
        System.out.println("2. Start travelling");
        System.out.println("3. Manage accounts");
        System.out.println("4. Manage balance");
        System.out.println("5. Exit system");
        System.out.println(ANSI_BLUE + "---------------------------------------");
        int input = 0;
        while (input == 0) {
            try {
                input = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(ANSI_RED + "That is not a valid number!");
                scanner.nextLine();
                input = 0;
            }
        }
        if (input == 1) {
            //Credits go here
            showCredits(scanner);
        } else if (input == 2) {
            Travel.askDestination(scanner);
            // Travelling system
        } else if (input == 3) {
            AccountSystem.displayAccounts(scanner);
            // Accounts system goes here
        } else if (input == 4) {
            // Balance system goes here
        } else if (input == 5) {
            System.out.println(ANSI_RED + "Exiting System");
            System.exit(0);
            // Exits the system
        }
    }
    static void showCredits(Scanner scanner) {
        System.out.println(ANSI_BLACK + "------------------------------------------");
        System.out.println(ANSI_GREEN + "Developers : Morris van Uden, Max Viehöfer");
        System.out.println(ANSI_BLACK + "------------------------------------------");
        System.out.println(ANSI_GREEN + "Teacher : Erik Seldenthuis");
        System.out.println(ANSI_BLACK + "------------------------------------------");
        System.out.println(ANSI_GREEN + "Class : TIA4V1B");
        System.out.println(ANSI_BLACK + "------------------------------------------");
        startMenu(scanner);
    }
}
