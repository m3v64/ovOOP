package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // run classes here

        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(calculateTravelCost.totalTravelCost, 1000);
        double roundedCost = Math.round(calculateTravelCost.totalTravelCost * 100.0) / 100.0;

        System.out.println(roundedCost);
        scanner.close();
    }
}
