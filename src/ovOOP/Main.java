package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run classes here

        OvTime time = new OvTime();

        time.getTime();

        

        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(calculateTravelCost.totalTravelCost, 1000);
        double roundedCost = Math.round(calculateTravelCost.totalTravelCost * 100.0) / 100.0;

        System.out.println(roundedCost);
    }
}
