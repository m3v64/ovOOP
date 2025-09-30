package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run classes here

        OvTime time = new OvTime();

        time.getTime();

        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(1000, 2, 1);

        System.out.println(CalculateTravelCost.totalTravelCost);
    }
}
