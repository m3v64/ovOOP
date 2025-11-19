package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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

    // private int line;
    // private String start;

    // Cache for JSON data to avoid repeated file loading
    private static List<DataSystem> accountInfoCache = null;
    private static JsonArray trainLinesCache = null;
    private static long accountInfoLastModified = 0;
    private static long trainLinesLastModified = 0;

    public DataSystem(int userID) {
        this.userID = userID;

        List<DataSystem> dataList = loadAccountInfo();

        for (DataSystem d : dataList) {
            if (d.getUserID() == userID) {
                this.username = d.username;
                this.password = d.password;
                this.location = d.location;
                this.balance = d.balance;
                this.defaultClass = d.defaultClass;
                this.defaultCurrencyConversionRate = d.defaultCurrencyConversionRate;
                this.mainPalette = d.mainPalette;
                this.secondaryPalette = d.secondaryPalette;
                break;
            }
        }

        if (this.username == null) {
            this.username = "guest";
            this.password = "guest";
            this.location = "Giad";
            this.balance = 0.0;
            this.defaultClass = 2;
            this.defaultCurrencyConversionRate = 1.0;
            this.mainPalette = ColorSystem.BLUE;
            this.secondaryPalette = ColorSystem.CYAN;
        }
    }

    /**
     * Centralized method to load AccountInfo.json with caching.
     * Reloads only if file has been modified since last load.
     */
    public static List<DataSystem> loadAccountInfo() {
        try {
            java.io.File file = new java.io.File("data/AccountInfo.json");
            long currentModified = file.lastModified();
            
            // Return cached data if file hasn't changed
            if (accountInfoCache != null && currentModified == accountInfoLastModified) {
                return accountInfoCache;
            }
            
            // Load fresh data
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileReader reader = new FileReader(file)) {
                Type dataListType = new TypeToken<List<DataSystem>>() {}.getType();
                List<DataSystem> dataList = gson.fromJson(reader, dataListType);
                
                if (dataList == null) {
                    dataList = new ArrayList<>();
                }
                
                // Update cache
                accountInfoCache = dataList;
                accountInfoLastModified = currentModified;
                
                return dataList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return accountInfoCache != null ? accountInfoCache : new ArrayList<>();
        }
    }

    /**
     * Centralized method to load TrainLines.json with caching.
     * Reloads only if file has been modified since last load.
     */
    public static JsonArray loadTrainLines() {
        try {
            java.io.File file = new java.io.File("data/TrainLines.json");
            long currentModified = file.lastModified();
            
            // Return cached data if file hasn't changed
            if (trainLinesCache != null && currentModified == trainLinesLastModified) {
                return trainLinesCache;
            }
            
            // Load fresh data
            try (Reader reader = new FileReader(file)) {
                JsonArray companies = JsonParser.parseReader(reader).getAsJsonArray();
                
                // Update cache
                trainLinesCache = companies;
                trainLinesLastModified = currentModified;
                
                return companies;
            }
        } catch (IOException e) {
            System.err.println("Error reading TrainLines.json: " + e.getMessage());
            e.printStackTrace();
            return trainLinesCache != null ? trainLinesCache : new JsonArray();
        } catch (JsonSyntaxException e) {
            System.err.println("Malformed JSON in TrainLines.json: " + e.getMessage());
            e.printStackTrace();
            return trainLinesCache != null ? trainLinesCache : new JsonArray();
        }
    }

    /**
     * Invalidates the AccountInfo cache to force reload on next access.
     * Should be called after modifying AccountInfo.json.
     */
    private static void invalidateAccountInfoCache() {
        accountInfoCache = null;
        accountInfoLastModified = 0;
    }

    /**
     * Preloads all JSON data at application startup.
     * Call this method once from Main.main() to load all data upfront.
     */
    public static void preloadAllData() {
        loadAccountInfo();
        loadTrainLines();
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
        boolean isFirstClass = false;
        if (this.defaultClass == 1) {
            isFirstClass = true;
        }
        return isFirstClass;
    }

    public static void addAccount(String username, String password, Scanner scanner) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<DataSystem> dataList = loadAccountInfo();

        int i = 0;
        for (DataSystem d : dataList) {
            i++;

            if (username.equalsIgnoreCase(d.username)) {
                System.out.println("That username is already taken, Please choose another");
                AccountSystem.displayAccounts(scanner);
                return;
            }
        }

        DataSystem newUser = new DataSystem(i);
        newUser.username = username;
        newUser.password = password;
        newUser.location = "Giad";
        newUser.balance = 0.0;
        newUser.defaultClass = 2;
        newUser.defaultCurrencyConversionRate = 1.0;
        newUser.mainPalette = ColorSystem.BLUE;
        newUser.secondaryPalette = ColorSystem.CYAN;

        dataList.add(newUser);

        try (FileWriter writer = new FileWriter("data/AccountInfo.json")) {
            gson.toJson(dataList, writer);
            System.out.println("New user added successfully!");
            invalidateAccountInfoCache(); // Invalidate cache after write
        } catch (Exception e) {
            e.printStackTrace();
        }

        Main.userID = i;

        MenuSystem.startMenu(scanner);
    }

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance, this.defaultClass,
                this.defaultCurrencyConversionRate, this.mainPalette, this.secondaryPalette);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation,
        double newBalance, int newDefaultClass, double newConversionRate, String newMainPalette,
        String newSecondaryPalette) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<DataSystem> dataList = loadAccountInfo();

        for (DataSystem d : dataList) {
            if (d.getUserID() == userID) {
                d.username = newUsername;
                d.password = newPassword;
                d.location = newLocation;
                d.balance = newBalance;
                d.defaultClass = newDefaultClass;
                d.defaultCurrencyConversionRate = newConversionRate;
                d.mainPalette = newMainPalette;
                d.secondaryPalette = newSecondaryPalette;
                break;
            }
        }

        try (FileWriter writer = new FileWriter("data/AccountInfo.json")) {
            gson.toJson(dataList, writer);
            invalidateAccountInfoCache(); // Invalidate cache after write
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("imagine giving an error...");
        }
    }

    public static List<Integer> listPossibleLines(String currentLocation) {
        List<Integer> foundLines = new ArrayList<>();

        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;
            JsonArray lines = companyObj.getAsJsonArray("lines");

            for (JsonElement lineEl : lines) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineObj = lineEl.getAsJsonObject();

                if (!lineObj.has("start") && !lineObj.has("connections"))
                    continue;

                boolean foundMatch = false;
                String start = lineObj.has("start") ? lineObj.get("start").getAsString() : null;
                if (start != null && start.equalsIgnoreCase(currentLocation)) {
                    foundMatch = true;
                }

                if (!foundMatch && lineObj.has("connections") && lineObj.get("connections").isJsonObject()) {
                    JsonObject connections = lineObj.getAsJsonObject("connections");
                    for (String placeName : connections.keySet()) {
                        if (placeName != null && placeName.equalsIgnoreCase(currentLocation)) {
                            foundMatch = true;
                            break;
                        }
                    }
                }

                if (foundMatch && lineObj.has("line")) {
                    try {
                        foundLines.add(lineObj.get("line").getAsInt());
                    } catch (Exception ex) {
                        // ignore malformed line id
                        ex.printStackTrace();
                    }
                }
            }
        }

        return new ArrayList<>(foundLines);
    }

    public static String getStart(int line) {
        String start = null;

        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;
            for (JsonElement lineEl : companyObj.getAsJsonArray("lines")) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineObj = lineEl.getAsJsonObject();
                if (!lineObj.has("line"))
                    continue;
                try {
                    if (lineObj.get("line").getAsInt() == line) {
                        if (lineObj.has("start"))
                            start = lineObj.get("start").getAsString();
                        return start;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return start;
    }

    public static String[] getLine(int line) {
        List<String> lineStations = new ArrayList<>();

        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;
            for (JsonElement lineEl : companyObj.getAsJsonArray("lines")) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineObj = lineEl.getAsJsonObject();
                if (!lineObj.has("line"))
                    continue;
                try {
                    if (lineObj.get("line").getAsInt() != line)
                        continue;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }

                if (lineObj.has("start") && !lineObj.get("start").isJsonNull()) {
                    lineStations.add(lineObj.get("start").getAsString());
                }

                if (lineObj.has("connections") && lineObj.get("connections").isJsonObject()) {
                    JsonObject connections = lineObj.getAsJsonObject("connections");
                    for (String cityName : connections.keySet()) {
                        lineStations.add(cityName);
                    }
                }

                return lineStations.toArray(new String[0]);
            }
        }

        return lineStations.toArray(new String[0]);
    }

    public static int findLineBetween(String a, String b) {
        if (a == null || b == null)
            return -1;
        
        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;

            for (JsonElement lineEl : companyObj.getAsJsonArray("lines")) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineData = lineEl.getAsJsonObject();

                if (!lineData.has("line"))
                    continue;

                boolean hasA = false;
                boolean hasB = false;

                if (lineData.has("start") && lineData.get("start").getAsString().equalsIgnoreCase(a))
                    hasA = true;
                if (lineData.has("start") && lineData.get("start").getAsString().equalsIgnoreCase(b))
                    hasB = true;

                if (lineData.has("connections") && lineData.get("connections").isJsonObject()) {
                    JsonObject connections = lineData.getAsJsonObject("connections");
                    for (String placeName : connections.keySet()) {
                        if (placeName != null && placeName.equalsIgnoreCase(a))
                            hasA = true;
                        if (placeName != null && placeName.equalsIgnoreCase(b))
                            hasB = true;
                    }
                }

                if (hasA && hasB) {
                    try {
                        return lineData.get("line").getAsInt();
                    } catch (Exception ex) {
                        // malformed
                        ex.printStackTrace();
                    }
                }
            }
        }

        return -1;
    }

    public static List<String> getSequentialPathOnLine(int lineNum, String start, String destination) {
        List<String> path = new ArrayList<>();

        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;

            for (JsonElement lineEl : companyObj.getAsJsonArray("lines")) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineData = lineEl.getAsJsonObject();
                if (!lineData.has("line") || !lineData.has("start") || !lineData.has("connections"))
                    continue;
                try {
                    if (lineData.get("line").getAsInt() != lineNum)
                        continue;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }

                String lineStart = lineData.get("start").getAsString();
                JsonObject connections = lineData.get("connections").getAsJsonObject();

                // Build ordered list of all stations on this line
                List<String> allStations = new ArrayList<>();
                allStations.add(lineStart);
                for (String stationName : connections.keySet()) {
                    allStations.add(stationName);
                }

                // Find indices of start and destination
                int startIdx = -1;
                int destIdx = -1;
                for (int j = 0; j < allStations.size(); j++) {
                    if (allStations.get(j).equalsIgnoreCase(start)) {
                        if (startIdx == -1)
                            startIdx = j; // Take first occurrence
                    }
                    if (allStations.get(j).equalsIgnoreCase(destination)) {
                        if (destIdx == -1)
                            destIdx = j; // Take first occurrence
                    }
                }

                if (startIdx == -1 || destIdx == -1)
                    return path;

                // Extract sequential path
                if (startIdx <= destIdx) {
                    for (int j = startIdx; j <= destIdx; j++) {
                        path.add(allStations.get(j));
                    }
                } else {
                    // going backwards on the line
                    for (int j = startIdx; j >= destIdx; j--) {
                        path.add(allStations.get(j));
                    }
                }
                return path;
            }
        }

        return path;
    }

    public static int getSequentialDistanceOnLine(int lineNum, String start, String destination) {
        int totalDistance = 0;

        JsonArray companies = loadTrainLines();

        for (JsonElement companyEl : companies) {
            if (!companyEl.isJsonObject())
                continue;
            JsonObject companyObj = companyEl.getAsJsonObject();
            if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                continue;

            for (JsonElement lineEl : companyObj.getAsJsonArray("lines")) {
                if (!lineEl.isJsonObject())
                    continue;
                JsonObject lineData = lineEl.getAsJsonObject();
                if (!lineData.has("line") || !lineData.has("start") || !lineData.has("connections"))
                    continue;
                try {
                    if (lineData.get("line").getAsInt() != lineNum)
                        continue;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }

                String lineStart = lineData.get("start").getAsString();
                JsonObject connections = lineData.get("connections").getAsJsonObject();

                // Build ordered list of all stations with distances
                List<String> allStations = new ArrayList<>();
                List<Integer> distances = new ArrayList<>();
                allStations.add(lineStart);

                for (String stationName : connections.keySet()) {
                    allStations.add(stationName);
                    distances.add(connections.get(stationName).getAsJsonObject().get("distance").getAsInt());
                }

                // Find indices - take first occurrence only
                int startIdx = -1;
                int destIdx = -1;
                for (int j = 0; j < allStations.size(); j++) {
                    if (allStations.get(j).equalsIgnoreCase(start)) {
                        if (startIdx == -1)
                            startIdx = j; // Take first occurrence
                    }
                    if (allStations.get(j).equalsIgnoreCase(destination)) {
                        if (destIdx == -1)
                            destIdx = j; // Take first occurrence
                    }
                }

                if (startIdx == -1 || destIdx == -1)
                    return 0;

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
