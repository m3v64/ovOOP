package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //run classes here
        
        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.main(args, CalculateTravelCost.totalTravelCost, 0);
        System.out.println("Total travel cost: " + CalculateTravelCost.totalTravelCost);

        scanner.close();
    }
}
