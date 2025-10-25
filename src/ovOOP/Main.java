package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;

    public static void main(String[] args) {

        Scanner initialScanner = new Scanner(System.in);

        MenuSystem.clear();

        MenuSystem.startMenu(initialScanner);

        //Map generation

        // MapGenerationSystem mapGenerator = new MapGenerationSystem();

        // int[][] map = mapGenerator.createEmptyMap(35, 100, 0);

        // DataSystem data = new DataSystem(userID);

        // String[] cityList = data.CITIES;

        // mapGenerator.initializeCities(cityList, true);

        // mapGenerator.displayMap(map, initialScanner, false);

        //Data

        // DataSystem data = new DataSystem(userID);
        // for (String city : data.CITIES) {
        // System.out.println("Route from giad to " + city + ": " +
        // TravelSystem.findRoute(city));
        // }
    }
}