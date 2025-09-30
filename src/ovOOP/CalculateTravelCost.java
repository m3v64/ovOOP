package ovOOP;

import java.util.Random;

public class CalculateTravelCost {
    static int distance;
    static double totalTravelCost;

    public void main(String[] args, double totalTravelCost, int distance) {
        double fuelCost;
        int totalFuel;
        int peopleInTrain;
        double travelCost;
        Random random = new Random();

        peopleInTrain = random.nextInt(20, 75);
        totalFuel = random.nextInt(125, 200);
        fuelCost = totalFuel / peopleInTrain;


        travelCost = distance * 0.0098;

        totalTravelCost = fuelCost + travelCost;

    }
}
