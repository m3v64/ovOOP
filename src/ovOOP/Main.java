package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;

    public static void main(String[] args) {

        Scanner initialScanner = new Scanner(System.in);

        // MenuSystem.clear();

        // MenuSystem.startMenu(initialScanner);

        // Map generation

        MapGenerationSystem mapGenerator = new MapGenerationSystem();

        mapGenerator.initializeMap(100, 35, '.');

        // Paint the text "Test" at position (userID, userID)

        // Load city data
        DataSystem data = new DataSystem(userID);
        String[] cityList = data.CITIES;

        mapGenerator.generateCity(13, 30, "Dryard", true);

        mapGenerator.generateCity(25, 20, "Brittle", true);

        mapGenerator.paintLineLayer(mapGenerator.getRoadLayer(), 13, 30, 25, 20, '#');

        // Display the map
        mapGenerator.displayMap(initialScanner, false);

        // Data

        // DataSystem data = new DataSystem(userID);
        // for (String city : data.CITIES) {
        // System.out.println("Route from giad to " + city + ": " +
        // TravelSystem.findRoute(city));
        // }
    }
}