package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ovOOP.model.Account;
import ovOOP.model.TrainCompany;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class DataSystem {
    public final String CITIES[] = { "Dryard", "TimerGulch", "Brittle", "StaglenHold", "EldYard", "Trasin", "SwiftLec",
        "LironGrale", "Ghostle", "Pearllows", "Irehole", "Lighthgro", "Stormwall", "Linere", "Giad", "Portal",
        "Heete Birch", "Arcs Styrie", "Charite", "Liberte et Egalite", "Kreutzbeck", "Sankt Jeder", "Hesturn",
        "Capella", "Elektra" };

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String ACCOUNT_FILE = "data/AccountInfo.json";
    private static final String TRAIN_LINES_FILE = "data/TrainLines.json";

    // Cached data to avoid repeated file reads
    private static List<Account> accountsCache;
    private static List<TrainCompany> trainLinesCache;

    private int userID;
    private String username;
    private String mainPalette;
    private String secondaryPalette;
    private String password;
    private String location;
    private double balance;
    private int defaultClass;
    private double defaultCurrencyConversionRate;

    private static int hour;
    private static int minutes;
    private static int month;
    private static int day;

    public DataSystem(int userID) {
        this.userID = userID;
        loadAccountData(userID);
    }

    /**
     * Load account data from cache or file system.
     */
    private void loadAccountData(int userID) {
        List<Account> accounts = loadAccounts();
        
        for (Account account : accounts) {
            if (account.getUserID() == userID) {
                this.username = account.getUsername();
                this.password = account.getPassword();
                this.location = account.getLocation();
                this.balance = account.getBalance();
                this.defaultClass = account.getDefaultClass();
                this.defaultCurrencyConversionRate = account.getDefaultCurrencyConversionRate();
                this.mainPalette = account.getMainPalette();
                this.secondaryPalette = account.getSecondaryPalette();
                return;
            }
        }

        // Default guest values if account not found
        this.username = "guest";
        this.password = "guest";
        this.location = "Giad";
        this.balance = 0.0;
        this.defaultClass = 2;
        this.defaultCurrencyConversionRate = 1.0;
        this.mainPalette = ColorSystem.BLUE;
        this.secondaryPalette = ColorSystem.CYAN;
    }

    /**
     * Load all accounts from file with caching.
     */
    private static List<Account> loadAccounts() {
        if (accountsCache != null) {
            return accountsCache;
        }

        try (FileReader reader = new FileReader(ACCOUNT_FILE)) {
            Type accountListType = new TypeToken<List<Account>>() {}.getType();
            accountsCache = GSON.fromJson(reader, accountListType);
            if (accountsCache == null) {
                accountsCache = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            accountsCache = new ArrayList<>();
        }
        return accountsCache;
    }

    /**
     * Load all train lines from file with caching.
     */
    private static List<TrainCompany> loadTrainLines() {
        if (trainLinesCache != null) {
            return trainLinesCache;
        }

        try (FileReader reader = new FileReader(TRAIN_LINES_FILE)) {
            Type trainCompanyListType = new TypeToken<List<TrainCompany>>() {}.getType();
            trainLinesCache = GSON.fromJson(reader, trainCompanyListType);
            if (trainLinesCache == null) {
                trainLinesCache = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            trainLinesCache = new ArrayList<>();
        }
        return trainLinesCache;
    }

    /**
     * Invalidate the accounts cache to force reload on next access.
     */
    private static void invalidateAccountsCache() {
        accountsCache = null;
    }

    /**
     * Save accounts list to file and invalidate cache.
     */
    private static void saveAccounts(List<Account> accounts) {
        try (FileWriter writer = new FileWriter(ACCOUNT_FILE)) {
            GSON.toJson(accounts, writer);
            invalidateAccountsCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Find a specific train line by line number.
     * Helper method to reduce code duplication.
     */
    private static ovOOP.model.TrainLine findTrainLine(int lineNumber) {
        List<TrainCompany> companies = loadTrainLines();
        
        for (TrainCompany company : companies) {
            if (company.getLines() == null) continue;
            
            for (ovOOP.model.TrainLine trainLine : company.getLines()) {
                if (trainLine.getLine() == lineNumber) {
                    return trainLine;
                }
            }
        }
        
        return null;
    }

    public int getUserID() {
        return userID;
    }

    public String getMainPalette() {
        return mainPalette;
    }

    public String getSecondaryPalette() {
        return secondaryPalette;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public double getBalance() {
        return balance;
    }

    public int getDefaultClass() {
        return defaultClass;
    }

    public double getConversionRate() {
        return defaultCurrencyConversionRate;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        updateJson();
    }

    public void setUsername(String username) {
        this.username = username;
        updateJson();
    }

    public void setPassword(String password) {
        this.password = password;
        updateJson();
    }

    public void setLocation(String location) {
        this.location = location;
        updateJson();
    }

    public void setBalance(double balance) {
        this.balance = balance;
        updateJson();
    }

    public void setDefaultClass(int defaultClass) {
        this.defaultClass = defaultClass;
        updateJson();
    }

    public void setConversionRate(double defaultCurrencyConversionRate) {
        this.defaultCurrencyConversionRate = defaultCurrencyConversionRate;
        updateJson();
    }

    public void setMainPalette(String color) {
        this.mainPalette = color;
        updateJson();
    }

    public void setSecondaryPalette(String color) {
        this.secondaryPalette = color;
        updateJson();
    }

    public boolean isFirstClass() {
        return this.defaultClass == 1;
    }

    public static void addAccount(String username, String password, Scanner scanner) {
        List<Account> accounts = loadAccounts();

        int newUserID = 0;
        for (Account account : accounts) {
            newUserID++;
            if (username.equalsIgnoreCase(account.getUsername())) {
                System.out.println("That username is already taken, Please choose another");
                AccountSystem.displayAccounts(scanner);
                return;
            }
        }

        Account newAccount = new Account();
        newAccount.setUserID(newUserID);
        newAccount.setUsername(username);
        newAccount.setPassword(password);
        newAccount.setLocation("Giad");
        newAccount.setBalance(0.0);
        newAccount.setDefaultClass(2);
        newAccount.setDefaultCurrencyConversionRate(1.0);
        newAccount.setMainPalette(ColorSystem.BLUE);
        newAccount.setSecondaryPalette(ColorSystem.CYAN);

        accounts.add(newAccount);
        saveAccounts(accounts);
        System.out.println("New user added successfully!");

        Main.userID = newUserID;
        MenuSystem.startMenu(scanner);
    }

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance, this.defaultClass,
                this.defaultCurrencyConversionRate, this.mainPalette, this.secondaryPalette);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation,
        double newBalance, int newDefaultClass, double newConversionRate, String newMainPalette,
        String newSecondaryPalette) {
        List<Account> accounts = loadAccounts();

        for (Account account : accounts) {
            if (account.getUserID() == userID) {
                account.setUsername(newUsername);
                account.setPassword(newPassword);
                account.setLocation(newLocation);
                account.setBalance(newBalance);
                account.setDefaultClass(newDefaultClass);
                account.setDefaultCurrencyConversionRate(newConversionRate);
                account.setMainPalette(newMainPalette);
                account.setSecondaryPalette(newSecondaryPalette);
                break;
            }
        }

        saveAccounts(accounts);
    }

    public static List<Integer> listPossibleLines(String currentLocation) {
        List<Integer> foundLines = new ArrayList<>();
        List<TrainCompany> companies = loadTrainLines();

        for (TrainCompany company : companies) {
            if (company.getLines() == null) continue;

            for (ovOOP.model.TrainLine line : company.getLines()) {
                boolean foundMatch = false;

                if (line.getStart() != null && line.getStart().equalsIgnoreCase(currentLocation)) {
                    foundMatch = true;
                }

                if (!foundMatch && line.getConnections() != null) {
                    for (String stationName : line.getConnections().keySet()) {
                        if (stationName != null && stationName.equalsIgnoreCase(currentLocation)) {
                            foundMatch = true;
                            break;
                        }
                    }
                }

                if (foundMatch) {
                    foundLines.add(line.getLine());
                }
            }
        }

        return foundLines;
    }

    public static String getStart(int line) {
        ovOOP.model.TrainLine trainLine = findTrainLine(line);
        return trainLine != null ? trainLine.getStart() : null;
    }

    public static String[] getLine(int line) {
        List<String> lineStations = new ArrayList<>();
        ovOOP.model.TrainLine trainLine = findTrainLine(line);

        if (trainLine != null) {
            if (trainLine.getStart() != null) {
                lineStations.add(trainLine.getStart());
            }

            if (trainLine.getConnections() != null) {
                lineStations.addAll(trainLine.getConnections().keySet());
            }
        }

        return lineStations.toArray(new String[0]);
    }

    public static int findLineBetween(String a, String b) {
        if (a == null || b == null) return -1;

        List<TrainCompany> companies = loadTrainLines();

        for (TrainCompany company : companies) {
            if (company.getLines() == null) continue;

            for (ovOOP.model.TrainLine trainLine : company.getLines()) {
                boolean hasA = false;
                boolean hasB = false;

                if (trainLine.getStart() != null) {
                    if (trainLine.getStart().equalsIgnoreCase(a)) hasA = true;
                    if (trainLine.getStart().equalsIgnoreCase(b)) hasB = true;
                }

                if (trainLine.getConnections() != null) {
                    for (String stationName : trainLine.getConnections().keySet()) {
                        if (stationName != null && stationName.equalsIgnoreCase(a)) hasA = true;
                        if (stationName != null && stationName.equalsIgnoreCase(b)) hasB = true;
                    }
                }

                if (hasA && hasB) {
                    return trainLine.getLine();
                }
            }
        }

        return -1;
    }

    public static List<String> getSequentialPathOnLine(int lineNum, String start, String destination) {
        List<String> path = new ArrayList<>();
        ovOOP.model.TrainLine trainLine = findTrainLine(lineNum);

        if (trainLine == null || trainLine.getStart() == null || trainLine.getConnections() == null) {
            return path;
        }

        // Build ordered list of all stations on this line
        List<String> allStations = new ArrayList<>();
        allStations.add(trainLine.getStart());
        allStations.addAll(trainLine.getConnections().keySet());

        // Find indices of start and destination
        int startIdx = -1;
        int destIdx = -1;
        for (int j = 0; j < allStations.size(); j++) {
            if (allStations.get(j).equalsIgnoreCase(start) && startIdx == -1) {
                startIdx = j;
            }
            if (allStations.get(j).equalsIgnoreCase(destination) && destIdx == -1) {
                destIdx = j;
            }
        }

        if (startIdx == -1 || destIdx == -1) return path;

        // Extract sequential path
        if (startIdx <= destIdx) {
            for (int j = startIdx; j <= destIdx; j++) {
                path.add(allStations.get(j));
            }
        } else {
            for (int j = startIdx; j >= destIdx; j--) {
                path.add(allStations.get(j));
            }
        }
        
        return path;
    }

    public static int getSequentialDistanceOnLine(int lineNum, String start, String destination) {
        int totalDistance = 0;
        ovOOP.model.TrainLine trainLine = findTrainLine(lineNum);

        if (trainLine == null || trainLine.getStart() == null || trainLine.getConnections() == null) {
            return 0;
        }

        // Build ordered list of all stations with distances
        List<String> allStations = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();
        allStations.add(trainLine.getStart());

        for (java.util.Map.Entry<String, ovOOP.model.StationConnection> entry : trainLine.getConnections().entrySet()) {
            allStations.add(entry.getKey());
            distances.add(entry.getValue().getDistance());
        }

        // Find indices - take first occurrence only
        int startIdx = -1;
        int destIdx = -1;
        for (int j = 0; j < allStations.size(); j++) {
            if (allStations.get(j).equalsIgnoreCase(start) && startIdx == -1) {
                startIdx = j;
            }
            if (allStations.get(j).equalsIgnoreCase(destination) && destIdx == -1) {
                destIdx = j;
            }
        }

        if (startIdx == -1 || destIdx == -1) return 0;

        // Sum distances along the sequential path
        if (startIdx <= destIdx) {
            for (int j = startIdx; j < destIdx; j++) {
                totalDistance += distances.get(j);
            }
        } else {
            for (int j = startIdx - 1; j >= destIdx; j--) {
                totalDistance += distances.get(j);
            }
        }
        
        return totalDistance;
    }

    public static void updateTime() {
        Calendar date = Calendar.getInstance();
        hour = date.get(Calendar.HOUR_OF_DAY);
        minutes = date.get(Calendar.MINUTE);

        day = date.get(Calendar.DAY_OF_MONTH);
        month = date.get(Calendar.MONTH);
    }

    public static int getHour() {
        return hour;
    }

    public static int getMinute() {
        return minutes;
    }

    public static int getDay() {
        return day;
    }

    public static int getMonth() {
        return month;
    }

    public static String getMonthName(int monthIndex) {
        String monthName;
        switch (monthIndex) {
            case 0:
                monthName = "January";
                break;
            case 1:
                monthName = "February";
                break;
            case 2:
                monthName = "March";
                break;
            case 3:
                monthName = "April";
                break;
            case 4:
                monthName = "May";
                break;
            case 5:
                monthName = "June";
                break;
            case 6:
                monthName = "July";
                break;
            case 7:
                monthName = "August";
                break;
            case 8:
                monthName = "September";
                break;
            case 9:
                monthName = "October";
                break;
            case 10:
                monthName = "November";
                break;
            case 11:
                monthName = "December";
                break;
            default:
                monthName = null;
                break;
        }
        return monthName;
    }
}
