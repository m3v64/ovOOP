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
    static void displayAccounts(Scanner scanner) {
        System.out.println();
        System.out.println(Color.CYAN + "Please choose from the following list");

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
        System.out.println(Color.CYAN + "Please enter the name of the account you want to create:");
        String username = scanner.next().toLowerCase();
        System.out.println(Color.CYAN + "Please enter the password of the account you want to create");
        String password = scanner.next();

        Data.addAccount(username, password, scanner);
    }

    static void logoutSystem(Scanner scanner) {
        Main.userID = 0;
        Menu.clear();
        System.out.println(Color.GREEN + "You have been logged out successfully.");
        Menu.startMenu(scanner);
    }

    static void loginSystem(Scanner scanner) {
        System.out.println();
        System.out.println(Color.CYAN + "Username:");

        String username = scanner.next();

        System.out.println(Color.CYAN + "\nPassword:");
        String password = scanner.next();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/Accounts.json")) {
            Type dataListType = new TypeToken<List<Data>>() {
            }.getType();
            List<Data> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (Data d : dataList) {
                if (d.getUsername().equalsIgnoreCase(username) && d.getPassword().equals(password)) {
                    Main.userID = d.getUserID();
                    System.out.println(Color.GREEN + "you have logged in!");
                    break;
                }
            }

            if (Main.userID == 0) {
                System.out.println(Color.RED + "that account does not exist.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Menu.startMenu(scanner);
    }
}
