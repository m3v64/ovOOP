package ovOOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

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
        System.out.println(ANSI_CYAN + "Please choose from the following list");

        int accounts = Option.showOption(scanner, "Login,Sign up,Return,Logout");

        if (accounts == 1) {
            loginSystem(scanner);
        }
        if (accounts == 2) {
            signupSystem(scanner);
        } if (accounts == 3){
            Travel.startMenu(scanner);
        } if (accounts == 4) {
            logoutSystem(scanner);
        }
    }
    static void logoutSystem(Scanner scanner) {
        Main.currentUsername = null;
        Main.currentBalance = 0.0;
        Main.clear();
        Travel.startMenu(scanner);
    }
    static void loginSystem(Scanner scanner) {
        System.out.println();
        System.out.println(ANSI_CYAN + "Username:");

        String username = scanner.next();

        System.out.println(ANSI_CYAN + "\nPassword:");
        String password = scanner.next();
        File file = new File("data/Accounts.json");

        Gson gson = new Gson();
        List<Account> accounts = new ArrayList<>();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type accountListType = new TypeToken<List<Account>>() {
                }.getType();
                accounts = gson.fromJson(reader, accountListType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Optional<Account> accountOpt = Optional.empty();
        double balance = 0;
        try {
            accountOpt = accounts.stream()
                    .filter(acc -> acc.username.equalsIgnoreCase(username) && acc.password.equals(password))
                    .findFirst();

            if (accountOpt.isPresent()) {
                balance = accountOpt.get().balance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (accountOpt.isPresent()) {
            System.out.println(ANSI_GREEN + "Logged in!");

            Main.currentUsername = username;

            Main.currentBalance = balance;

            Travel.startMenu(scanner);
        } else {
            System.out.println(ANSI_RED + "No account matching those credentials could be found");
            Travel.startMenu(scanner);
        }
    }

    static void signupSystem(Scanner scanner) {
        System.out.println(ANSI_CYAN + "Please enter the name of the account you want to create:");
        String username = scanner.next().toLowerCase();
        System.out.println(ANSI_CYAN + "Please enter the password of the account you want to create");
        String password = scanner.next();

        File file = new File("data/Accounts.json");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Account> accounts = new ArrayList<>();

        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Type accountListType = new TypeToken<List<Account>>() {
                }.getType();
                accounts = gson.fromJson(reader, accountListType);
                if (accounts == null) {
                    accounts = new ArrayList<>();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        accounts.add(new Account(username, password, 0.00));
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(accounts, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(ANSI_GREEN + "Account created successfully!");
        Travel.startMenu(scanner);
    }

    static class Account {
        String username;
        String password;
        double balance;

        Account(String username, String password, double balance) {
            this.username = username;
            this.password = password;
            this.balance = balance;
        }
    }

}
