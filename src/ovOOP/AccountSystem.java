package ovOOP;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

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
        if (accounts == 2) {
            signupSystem(scanner);
        }
    }

    static void loginSystem(Scanner scanner) {
        System.out.println();
        System.out.println(ANSI_CYAN + "Username:");

        String username = scanner.next();

        File file = new File("data/Accounts.json");

        JSONObject JSON = new JSONObject(file);
        

        System.out.println(JSON.getString("username"));

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

    static void signupSystem(Scanner scanner) {
        System.out.println(ANSI_CYAN + "Please enter the name of the account you want to create:");
        String username = scanner.next().toLowerCase();
        System.out.println(ANSI_CYAN + "Please enter the password of the account you want to create");
        String password = scanner.next();

        File file = new File("data/Accounts.json");
        JSONArray accountSystem = new JSONArray();

        // Read existing accounts if file exists
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                StringBuilder jsonStr = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    jsonStr.append(fileScanner.nextLine());
                }
                if (!jsonStr.toString().isEmpty()) {
                    accountSystem = new JSONArray(jsonStr.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Add new account
        JSONObject account = new JSONObject();
        account.put("username", username);
        account.put("password", password);
        accountSystem.put(account);

        // Write updated array back to file (overwrite)
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(accountSystem.toString(4)); // pretty print with 4-space indentation
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ANSI_GREEN + "Account created successfully!");
    }

}
