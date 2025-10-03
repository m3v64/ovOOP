package ovOOP;

import java.util.Scanner;

public class AccountSystem {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static void displayAccounts(Scanner scanner) {
        System.out.println();
        System.out.println(ANSI_CYAN + "Do you want to: 1. Login, or 2. Sign up");

        int accounts = 0;

        while (accounts == 0) {
            try {
                accounts = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(ANSI_RED + "That is not a valid input! Please input a 1 or a 2");
                scanner.nextLine();
                continue;
            }
            if (accounts > 2 || accounts < 1) {
                accounts = 0;
                System.out.println(ANSI_RED + "That is not a valid input! Please input a 1 or a 2");
                scanner.nextLine();
            }
        }
        if (accounts == 1) {
            loginSystem(scanner);
        }
    }

    static void loginSystem(Scanner scanner) {
        System.out.println();
        System.out.println(ANSI_CYAN + "Username:");
        String username = scanner.next();
        // check against valid usernames here
        System.out.println(ANSI_CYAN + "\nPassword:");
        String password = scanner.next();
        // check if password is equal to username's password here

        boolean valid = false;
        if (valid) {
            System.out.println(ANSI_GREEN + "Logged in!");
        } else {
            System.out.println(ANSI_RED + "No account matching those credentials could be found");
        }
    }
}
