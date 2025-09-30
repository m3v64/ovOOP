package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run classes here


        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(1000, 2, 9);

        System.out.println(CalculateTravelCost.totalTravelCost);
    }
}
