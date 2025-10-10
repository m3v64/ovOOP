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
    private static final String PREFIX = Color.BRIGHT_CYAN + "[AccountSystem] " + Color.RESET;
    private static final String SEPARATOR = Color.BRIGHT_BLUE + "----------------------------------------" + Color.RESET;

    private static void prettyPrint(String message) {
        System.out.println(PREFIX + message);
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    static void displayAccounts(Scanner scanner) {
        printSeparator();
        prettyPrint(Color.BRIGHT_CYAN + "Please choose from the following list");
        printSeparator();

        int accounts = Option.showOption(scanner, "Login,Logout,Sign up,Main Menu");

        if (accounts == 1) {
            loginSystem(scanner);
        }
        if (accounts == 3) {
            signupSystem(scanner);
        }
        if (accounts == 4) {
            Menu.startMenu(scanner);
        }
        if (accounts == 2) {
            logoutSystem(scanner);
        }
    }

    static void signupSystem(Scanner scanner) {
        printSeparator();
        prettyPrint(Color.BRIGHT_CYAN + "Please enter the name of the account you want to create:");
        String username = scanner.next().toLowerCase();
        prettyPrint(Color.BRIGHT_CYAN + "Please enter the password of the account you want to create");
        String password = scanner.next();

        Data.addAccount(username, password, scanner);
    }

    static void logoutSystem(Scanner scanner) {
        Main.userID = 0;
        Menu.clear();
        printSeparator();
        prettyPrint(Color.CYAN + "You have been logged out successfully.");
        printSeparator();
        Menu.startMenu(scanner);
    }

    static void loginSystem(Scanner scanner) {
        printSeparator();
        prettyPrint(Color.BRIGHT_CYAN + "Username:");

        String username = scanner.next();

        if (username.equalsIgnoreCase("guest")) {
            prettyPrint(Color.BRIGHT_BLUE + "You cannot use Guest to log in");
            printSeparator();
            Menu.startMenu(scanner);
            return;
        }

        prettyPrint(Color.BRIGHT_CYAN + "Password:");
        String password = scanner.next();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<Data>>() {
            }.getType();
            List<Data> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (Data d : dataList) {
                if (d.getUsername().equalsIgnoreCase(username) && d.getPassword().equals(password)) {
                    Main.userID = d.getUserID();
                    prettyPrint(Color.CYAN + "You have logged in!");
                    break;
                }
            }

            if (Main.userID == 0) {
                prettyPrint(Color.BRIGHT_BLUE + "That account does not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        printSeparator();
        Menu.startMenu(scanner);
    }
}
