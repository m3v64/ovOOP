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
    private double balance;

    public Data() {
        this.userID = 0;
        this.username = "guest";
        this.password = "guest";
        this.balance = 0.0;
    }

    public Data(int userID, String username, String password, double balance) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getUserID() {return userID;}
    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public double getBalance() {return balance;}

    public void setUsername(String username) {this.username = username; updateJson(this.userID, this.username, this.password, this.balance);}
    public void setPassword(String password) {this.password = password; updateJson(this.userID, this.username, this.password, this.balance);}
    public void setBalance(double balance) {this.balance = balance; updateJson(this.userID, this.username, this.password, this.balance);}

    public static void updateJson(int userID, String newUsername, String newPassword, double newBalance) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/Accounts.json")) {
            Type dataListType = new TypeToken<List<Data>>(){}.getType();
            List<Data> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null) dataList = new ArrayList<>();

            // find the user and update
            for (Data d : dataList) {
                if (d.getUserID() == userID) {
                    d.username = newUsername;
                    d.password = newPassword;
                    d.balance = newBalance;
                    break;
                }
            }

            // write it back to file
            try (FileWriter writer = new FileWriter("data/Accounts.json")) {
                gson.toJson(dataList, writer);
                System.out.println("User " + userID + " updated successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
