package ovOOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class AccountSystem {
    private static final String PREFIX = ColorSystem.BRIGHT_CYAN + "[AccountSystem] " + ColorSystem.RESET;
    private static final String SEPARATOR = ColorSystem.BRIGHT_BLUE + "----------------------------------------" + ColorSystem.RESET;

    private static void prettyPrint(String message) {
        System.out.println(PREFIX + message);
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    static void displayAccounts(Scanner scanner) {
        printSeparator();
        prettyPrint(ColorSystem.BRIGHT_CYAN + "Please choose from the following list");
        printSeparator();

        int accounts = OptionsSystem.showOption(scanner, "Login,Logout,Sign up,Main Menu,Debug");

        if (accounts == 1) {
            loginSystem(scanner);
        }
        if (accounts == 3) {
            signupSystem(scanner);
        }
        if (accounts == 4) {
            MenuSystem.startMenu(scanner);
        }
        if (accounts == 2) {
            logoutSystem(scanner);
        }
        if (accounts == 5) {
            Main.userID = 2;
            MenuSystem.startMenu(scanner);
        }
    }

    static void signupSystem(Scanner scanner) {
        printSeparator();
        prettyPrint(ColorSystem.BRIGHT_CYAN + "Please enter the name of the account you want to create:");
        String username = scanner.next().toLowerCase();
        prettyPrint(ColorSystem.BRIGHT_CYAN + "Please enter the password of the account you want to create");
        String password = scanner.next();

        DataSystem.addAccount(username, password, scanner);
    }

    static void logoutSystem(Scanner scanner) {
        Main.userID = 0;
        MenuSystem.clear();
        printSeparator();
        prettyPrint(ColorSystem.CYAN + "You have been logged out successfully.");
        printSeparator();
        MenuSystem.startMenu(scanner);
    }

    static void loginSystem(Scanner scanner) {
        printSeparator();
        prettyPrint(ColorSystem.BRIGHT_CYAN + "Username:");

        String username = scanner.next();

        if (username.equalsIgnoreCase("guest")) {
            prettyPrint(ColorSystem.BRIGHT_BLUE + "You cannot use Guest to log in");
            printSeparator();
            MenuSystem.startMenu(scanner);
            return;
        }

        prettyPrint(ColorSystem.BRIGHT_CYAN + "Password:");
        String password = scanner.next();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (DataSystem d : dataList) {
                if (d.getUsername().equalsIgnoreCase(username) && d.getPassword().equals(password)) {
                    Main.userID = d.getUserID();
                    prettyPrint(ColorSystem.CYAN + "You have logged in!");
                    break;
                }
            }

            if (Main.userID == 0) {
                prettyPrint(ColorSystem.BRIGHT_BLUE + "That account does not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        printSeparator();
        MenuSystem.startMenu(scanner);
    }
}
