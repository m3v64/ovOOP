package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import java.util.Scanner;

public class Data {
    private int userID;
    private String username;
    private String password;
    private String location;
    private double balance;

    public Data(int userID) {
        this.userID = userID;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<Data>>(){}.getType();
            List<Data> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null) dataList = new ArrayList<>();

            for (Data d : dataList) {
                if (d.getUserID() == userID) {
                    this.username = d.username;
                    this.password = d.password;
                    this.location = d.location;
                    this.balance = d.balance;
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.username == null) {
            this.username = "guest";
            this.password = "guest";
            this.location = "Giad";
            this.balance = 0.0;
        }
    }

    public int getUserID() {return userID;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getLocation() {return location;}
    public double getBalance() {return balance;}

    public void setUserID(int userID) {this.userID = userID; updateJson();}
    public void setUsername(String username) {this.username = username; updateJson();}
    public void setPassword(String password) {this.password = password; updateJson();}
    public void setLocation(String location) {this.location = location; updateJson();}
    public void setBalance(double balance) {this.balance = balance; updateJson();}


public static void addAccount(String username, String password, Scanner scanner) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    List<Data> dataList;

    try (FileReader reader = new FileReader("data/AccountInfo.json")) {
        Type dataListType = new TypeToken<List<Data>>() {}.getType();
        dataList = gson.fromJson(reader, dataListType);
        if (dataList == null) dataList = new ArrayList<>();
    } catch (Exception e) {
        // If file doesn't exist or can't be read, start fresh
        dataList = new ArrayList<>();
    }

    // get highest user id
    int i = 0;
    for (Data d : dataList) {
        i ++;

        if (username.equalsIgnoreCase(d.username)) {
            System.out.println("That username is already taken, Please choose another");
            AccountSystem.displayAccounts(scanner);
            return;
        }
    }

    // Create and add new user
    Data newUser = new Data(i);
    newUser.username = username;
    newUser.password = password;
    newUser.location = "Giad";
    newUser.balance = 0.0;

    dataList.add(newUser);

    // Write updated list to file
    try (FileWriter writer = new FileWriter("data/AccountInfo.json")) {
        gson.toJson(dataList, writer);
        System.out.println("New user added successfully!");
    } catch (Exception e) {
        e.printStackTrace();
    }

    Main.userID = i;

    Menu.startMenu(scanner);
}

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation, double newBalance) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<Data>>(){}.getType();
            List<Data> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null) dataList = new ArrayList<>();

            for (Data d : dataList) {
                if (d.getUserID() == userID) {
                    d.username = newUsername;
                    d.password = newPassword;
                    d.location = newLocation;
                    d.balance = newBalance;
                    break;
                }
            }

            try (FileWriter writer = new FileWriter("data/AccountInfo.json")) {
                gson.toJson(dataList, writer);
                System.out.println("User " + userID + " updated successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
