package ovOOP;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;

public class Data {
    private String username;
    private String password;
    private double balance;

    public void Account(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public double getBalance() {return balance;}
    public void setBalance(double balance) {this.balance = balance;}

}
