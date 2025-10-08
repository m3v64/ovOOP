package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class Data {
    private int userID;
    private String username;
    private String password;
    private String location;
    private double balance;

    public Data(int userID) {
        this.userID = userID;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/Accounts.json")) {
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
            this.location = "Dryard";
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

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation, double newBalance) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/Accounts.json")) {
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

            try (FileWriter writer = new FileWriter("data/Accounts.json")) {
                gson.toJson(dataList, writer);
                System.out.println("User " + userID + " updated successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
