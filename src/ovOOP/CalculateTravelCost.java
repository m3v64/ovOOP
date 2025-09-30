package ovOOP;

import java.util.Random;

public class CalculateTravelCost {
    static double totalTravelCost;

    public void travelCost(double totalTravelCost, int distance) {

        double fuelCost;
        double totalFuel;
        double peopleInTrain;
        double travelCost;
        Random random = new Random();

        peopleInTrain = random.nextInt(20, 75);
        totalFuel = random.nextInt(125, 200);
        fuelCost = (double) totalFuel / peopleInTrain / 3;

        travelCost = distance * 0.00098;

        CalculateTravelCost.totalTravelCost = fuelCost + travelCost;

    }
}
