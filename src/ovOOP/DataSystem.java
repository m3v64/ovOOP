package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import java.lang.reflect.Type;

import java.util.Scanner;

public class DataSystem {

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

    // private Map<String, Map<String, Object>> connections;
    // private int line;
    // private String start;

    public DataSystem(int userID) {
        this.userID = userID;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

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

        } catch (Exception e) {
            e.printStackTrace();
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
        List<DataSystem> dataList;

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            dataList = gson.fromJson(reader, dataListType);
            if (dataList == null)
                dataList = new ArrayList<>();
        } catch (Exception e) {
            dataList = new ArrayList<>();
        }

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        Main.userID = i;

        MenuSystem.startMenu();
    }

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance, this.defaultClass,
                this.defaultCurrencyConversionRate, this.mainPalette, this.secondaryPalette);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation,
            double newBalance, int newDefaultClass, double newConversionRate, String newMainPalette,
            String newSecondaryPalette) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/AccountInfo.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

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
                // System.out.println("User " + userID + " updated successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> listPossibleLines(String currentLocation) {
        List<Integer> foundLines = new ArrayList<>();

        try (Reader reader = new FileReader("data/TrainLines.json")) {
            com.google.gson.JsonArray companies = com.google.gson.JsonParser.parseReader(reader).getAsJsonArray();

            for (com.google.gson.JsonElement companyEl : companies) {
                if (!companyEl.isJsonObject())
                    continue;
                com.google.gson.JsonObject companyObj = companyEl.getAsJsonObject();
                if (!companyObj.has("lines") || !companyObj.get("lines").isJsonArray())
                    continue;
                com.google.gson.JsonArray lines = companyObj.getAsJsonArray("lines");

                for (com.google.gson.JsonElement lineEl : lines) {
                    if (!lineEl.isJsonObject())
                        continue;
                    com.google.gson.JsonObject lineObj = lineEl.getAsJsonObject();

                    if (!lineObj.has("start") && !lineObj.has("connections"))
                        continue;

                    boolean foundMatch = false;
                    String start = lineObj.has("start") ? lineObj.get("start").getAsString() : null;
                    if (start != null && start.equalsIgnoreCase(currentLocation)) {
                        foundMatch = true;
                    }

                    if (!foundMatch && lineObj.has("connections") && lineObj.get("connections").isJsonObject()) {
                        com.google.gson.JsonObject connections = lineObj.getAsJsonObject("connections");
                        for (java.util.Map.Entry<String, com.google.gson.JsonElement> entry : connections.entrySet()) {
                            String placeName = entry.getKey();
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
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading TrainLines.json: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Malformed JSON in TrainLines.json: " + e.getMessage());
        }

        return new ArrayList<>(foundLines);
    }

    public static String getStart(int line) {
        String start = null;

        try (Reader reader = new FileReader("data/TrainLines.json")) {
            JsonArray companies = JsonParser.parseReader(reader).getAsJsonArray();

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
                    } catch (Exception ex) {
                        // skip malformed
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading TrainLines.json: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Malformed JSON in TrainLines.json: " + e.getMessage());
        }

        return start;
    }

    public static String[] getLine(int line) {
        List<String> lineStations = new ArrayList<>();

        try (FileReader reader = new FileReader("data/TrainLines.json")) {
            JsonArray companies = JsonParser.parseReader(reader).getAsJsonArray();

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
                        continue;
                    }

                    if (lineObj.has("start") && !lineObj.get("start").isJsonNull()) {
                        lineStations.add(lineObj.get("start").getAsString());
                    }

                    if (lineObj.has("connections") && lineObj.get("connections").isJsonObject()) {
                        JsonObject connections = lineObj.getAsJsonObject("connections");
                        for (Entry<String, JsonElement> entry : connections.entrySet()) {
                            String cityName = entry.getKey();
                            lineStations.add(cityName);
                        }
                    }

                    return lineStations.toArray(new String[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineStations.toArray(new String[0]);
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
                monthName = "";
                break;
        }
        return monthName;
    }
}
