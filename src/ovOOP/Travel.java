package ovOOP;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Travel {

    static double calculateCost(boolean businessClass, int distanceTraveling, double conversionRate) {
        if (Main.userID == 0) {
            return 0;
        }

        double totalCost = 0;

        double fuelCostPerLiter = 2.17;

        double totalFuelCost = (distanceTraveling / 500.0) * fuelCostPerLiter;

        totalCost += totalFuelCost;

        double randomFactor = Math.random() * 0.3 + 1; // Random increase

        totalCost *= randomFactor * (randomFactor / 2);

        if (businessClass) {
            totalCost *= 1.7;
        } else {
            totalCost *= 0.9;
        }

        totalCost *= 1.09; // taxes

        totalCost *= 1.20; // Profit margin

        totalCost += 2; //Base price

        totalCost *= conversionRate; // Conversion between currencies

        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void askDestination(Scanner scanner) {
        // traveling system goes here
        Menu.clear();

        System.out.println("Please select a country you want to go to:");

        // get list of countries here

        List<String> countries;

        countries = new ArrayList<>();

        countries.add("Country1");

        countries.add("Country2");

        int target = Option.showOption(scanner, String.join(",", countries));

        System.out.println(countries.get(target - 1));

        int distance = 100;

        // get distance here and set distance variable to it

        boolean isFirstClass = (Option.showOption(scanner, "First class,Second class") == 1);

        double conversionRate = 1;

        System.out.println(Travel.calculateCost(isFirstClass, distance, conversionRate));

    }

}
