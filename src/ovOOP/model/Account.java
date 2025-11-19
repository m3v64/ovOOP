package ovOOP.model;

/**
 * POJO model representing a user account.
 * Used for JSON serialization/deserialization with Gson.
 */
public class Account {
    private int userID;
    private String username;
    private String mainPalette;
    private String secondaryPalette;
    private String password;
    private String location;
    private double balance;
    private int defaultClass;
    private double defaultCurrencyConversionRate;

    public Account() {
        // Default constructor for Gson
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMainPalette() {
        return mainPalette;
    }

    public void setMainPalette(String mainPalette) {
        this.mainPalette = mainPalette;
    }

    public String getSecondaryPalette() {
        return secondaryPalette;
    }

    public void setSecondaryPalette(String secondaryPalette) {
        this.secondaryPalette = secondaryPalette;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getDefaultClass() {
        return defaultClass;
    }

    public void setDefaultClass(int defaultClass) {
        this.defaultClass = defaultClass;
    }

    public double getDefaultCurrencyConversionRate() {
        return defaultCurrencyConversionRate;
    }

    public void setDefaultCurrencyConversionRate(double defaultCurrencyConversionRate) {
        this.defaultCurrencyConversionRate = defaultCurrencyConversionRate;
    }
}
