package ovOOP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

import java.util.Scanner;

public class DataSystem {
    public final String CITIES[] = { "Dryard", "TimerGulch", "Brittle", "StaglenHold", "EldYard", "Trasin", "SwiftLec",
            "LironGrale", "Ghostle", "Pearllows", "Irehole", "Lighthgro", "Stormwall", "Linere", "Giad", "Portal",
            "Heete Birch", "Arcs Styrie", "Charité", "Liberté et Égalité", "Kreutzbeck", "Sankt Jeder", "Hesturn",
            "Capella", "Elektra" };

    private int userID;
    private String username;
    private String password;
    private String location;
    private double balance;

    private Map<String, Map<String, Object>> connections;
    private int line;
    private String start;

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

    public int getUserID() {
        return userID;
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

        dataList.add(newUser);

        try (FileWriter writer = new FileWriter("data/AccountInfo.json")) {
            gson.toJson(dataList, writer);
            System.out.println("New user added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Main.userID = i;

        MenuSystem.startMenu(scanner);
    }

    private void updateJson() {
        updateJson(this.userID, this.username, this.password, this.location, this.balance);
    }

    public static void updateJson(int userID, String newUsername, String newPassword, String newLocation,
            double newBalance) {
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

    public static List<Integer> listPossibleLines(String currentLocation) {
        List<Integer> foundLines = new ArrayList<>();
        Gson gson = new Gson();

        try (Reader reader = new FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                return foundLines;

            for (DataSystem lineData : dataList) {
                if (lineData == null || lineData.connections == null)
                    continue;

                boolean foundMatch = false;

                if (lineData.start.equalsIgnoreCase(currentLocation)) {
                    foundMatch = true;
                } else {
                    for (String placeName : lineData.connections.keySet()) {
                        if (placeName != null && placeName.equalsIgnoreCase(currentLocation)) {
                            foundMatch = true;
                            break;
                        }
                    }
                }

                if (foundMatch) {
                    foundLines.add(lineData.line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading TrainLines.json: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Malformed JSON in TrainLines.json: " + e.getMessage());
        }

        return new ArrayList<>(foundLines);
    }

    public static String[] getLine(int line, String currentLocation) {
        List<String> comingStations = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (DataSystem lineData : dataList) {
                if (lineData.line == line && lineData.connections != null) {
                    boolean foundCurrent = false;
                    for (Map.Entry<String, Map<String, Object>> entry : lineData.connections.entrySet()) {
                        Map<String, Object> destMap = entry.getValue();
                        for (String city : destMap.keySet()) {
                            if (foundCurrent) {
                                comingStations.add(city);
                            }
                            if (city.equalsIgnoreCase(currentLocation)) {
                                foundCurrent = true;
                            }
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comingStations.toArray(new String[0]);
    }

    public static String[] getLine(int line) {
        List<String> comingStations = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (DataSystem lineData : dataList) {
                if (lineData.line == line && lineData.connections != null) {
                    for (Map.Entry<String, Map<String, Object>> entry : lineData.connections.entrySet()) {
                        Map<String, Object> destMap = entry.getValue();
                        for (String city : destMap.keySet()) {
                            comingStations.add(city);
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comingStations.toArray(new String[0]);
    }
}
