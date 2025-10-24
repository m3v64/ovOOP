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

    public static String getStart(int line) {
        Gson gson = new Gson();
        String start = null;

        try (Reader reader = new FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList != null) {
                for (DataSystem lineData : dataList) {
                    if (lineData == null || lineData.connections == null)
                        continue;

                    if (lineData.line == line) {
                        start = lineData.start;
                        break;
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileReader reader = new FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null)
                dataList = new ArrayList<>();

            for (DataSystem lineData : dataList) {
                if (lineData.line == line && lineData.connections != null) {
                    // include the line start as the first station
                    if (lineData.start != null) {
                        lineStations.add(lineData.start);
                    }
                    for (Map.Entry<String, Map<String, Object>> entry : lineData.connections.entrySet()) {
                        // The JSON structure is: "connections": { "CityName": { "distance": 123 } }
                        // entry.getKey() is the city name; entry.getValue() is the inner map with distance.
                        String cityName = entry.getKey();
                        lineStations.add(cityName);
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lineStations.toArray(new String[0]);
    }

    public static java.util.Map<String, java.util.Map<String, Integer>> buildGraph() {
        java.util.Map<String, java.util.Map<String, Integer>> graph = new java.util.HashMap<>();
        
        try (java.io.Reader reader = new java.io.FileReader("data/TrainLines.json")) {
            com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseReader(reader).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                com.google.gson.JsonObject lineData = jsonArray.get(i).getAsJsonObject();
                
                if (!lineData.has("start") || !lineData.has("connections"))
                    continue;

                String start = lineData.get("start").getAsString();
                com.google.gson.JsonObject connections = lineData.get("connections").getAsJsonObject();
                
                String prev = start;

                // Iterate through connections in order (JsonObject maintains insertion order in newer Gson)
                for (java.util.Map.Entry<String, com.google.gson.JsonElement> entry : connections.entrySet()) {
                    String city = entry.getKey();
                    com.google.gson.JsonObject destData = entry.getValue().getAsJsonObject();
                    int distance = destData.get("distance").getAsInt();

                    // add edge between prev and city (sequential stations along the line)
                    if (!graph.containsKey(prev)) {
                        graph.put(prev, new java.util.HashMap<String, Integer>());
                    }
                    graph.get(prev).put(city, distance);

                    if (!graph.containsKey(city)) {
                        graph.put(city, new java.util.HashMap<String, Integer>());
                    }
                    graph.get(city).put(prev, distance);

                    prev = city;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return graph;
    }

    public static int findLineBetween(String a, String b) {
        if (a == null || b == null) return -1;
        Gson gson = new Gson();

        try (java.io.Reader reader = new java.io.FileReader("data/TrainLines.json")) {
            Type dataListType = new TypeToken<List<DataSystem>>() {
            }.getType();
            List<DataSystem> dataList = gson.fromJson(reader, dataListType);

            if (dataList == null) return -1;

            for (DataSystem lineData : dataList) {
                if (lineData == null || lineData.connections == null)
                    continue;

                boolean hasA = false;
                boolean hasB = false;

                if (lineData.start != null && lineData.start.equalsIgnoreCase(a)) hasA = true;
                if (lineData.start != null && lineData.start.equalsIgnoreCase(b)) hasB = true;

                for (String placeName : lineData.connections.keySet()) {
                    if (placeName != null && placeName.equalsIgnoreCase(a)) hasA = true;
                    if (placeName != null && placeName.equalsIgnoreCase(b)) hasB = true;
                }

                if (hasA && hasB) {
                    return lineData.line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static List<String> getSequentialPathOnLine(int lineNum, String start, String destination) {
        List<String> path = new ArrayList<>();
        
        try (java.io.Reader reader = new java.io.FileReader("data/TrainLines.json")) {
            com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseReader(reader).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                com.google.gson.JsonObject lineData = jsonArray.get(i).getAsJsonObject();
                
                if (lineData.get("line").getAsInt() != lineNum)
                    continue;

                String lineStart = lineData.get("start").getAsString();
                com.google.gson.JsonObject connections = lineData.get("connections").getAsJsonObject();
                
                // Build ordered list of all stations on this line
                List<String> allStations = new ArrayList<>();
                allStations.add(lineStart);
                for (java.util.Map.Entry<String, com.google.gson.JsonElement> entry : connections.entrySet()) {
                    allStations.add(entry.getKey());
                }

                // Find indices of start and destination
                int startIdx = -1;
                int destIdx = -1;
                for (int j = 0; j < allStations.size(); j++) {
                    if (allStations.get(j).equalsIgnoreCase(start)) {
                        if (startIdx == -1) startIdx = j; // Take first occurrence
                    }
                    if (allStations.get(j).equalsIgnoreCase(destination)) {
                        if (destIdx == -1) destIdx = j; // Take first occurrence
                    }
                }

                if (startIdx == -1 || destIdx == -1) return path;

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
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }

    public static int getSequentialDistanceOnLine(int lineNum, String start, String destination) {
        int totalDistance = 0;
        
        try (java.io.Reader reader = new java.io.FileReader("data/TrainLines.json")) {
            com.google.gson.JsonArray jsonArray = com.google.gson.JsonParser.parseReader(reader).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                com.google.gson.JsonObject lineData = jsonArray.get(i).getAsJsonObject();
                
                if (lineData.get("line").getAsInt() != lineNum)
                    continue;

                String lineStart = lineData.get("start").getAsString();
                com.google.gson.JsonObject connections = lineData.get("connections").getAsJsonObject();
                
                // Build ordered list of all stations with distances
                List<String> allStations = new ArrayList<>();
                List<Integer> distances = new ArrayList<>();
                allStations.add(lineStart);
                
                for (java.util.Map.Entry<String, com.google.gson.JsonElement> entry : connections.entrySet()) {
                    allStations.add(entry.getKey());
                    distances.add(entry.getValue().getAsJsonObject().get("distance").getAsInt());
                }

                // Find indices - take first occurrence only
                int startIdx = -1;
                int destIdx = -1;
                for (int j = 0; j < allStations.size(); j++) {
                    if (allStations.get(j).equalsIgnoreCase(start)) {
                        if (startIdx == -1) startIdx = j; // Take first occurrence
                    }
                    if (allStations.get(j).equalsIgnoreCase(destination)) {
                        if (destIdx == -1) destIdx = j; // Take first occurrence
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
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalDistance;
    }
}
