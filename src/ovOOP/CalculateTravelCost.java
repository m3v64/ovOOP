package ovOOP;

import java.util.Random;

public class CalculateTravelCost {
    static double totalTravelCost;

    public void travelCost(int distance, int seatClass, int ticketAmount) {
        Random random = new Random();

        double fuelCost;
        double totalFuel;

        int peopleInTrain;

        double travelCost;

        int trainCarts;

        int seatsPerCart;

        int remainingSeats;

        peopleInTrain = random.nextInt(30, 50);

        seatsPerCart = random.nextInt(10, 20);

        trainCarts = random.nextInt(5, 10);

        remainingSeats = trainCarts * seatsPerCart - peopleInTrain;

        if (remainingSeats < ticketAmount) {
            System.out.println("Not enough seats available");
            return;
        }

        int trainSpeed;

        totalFuel = random.nextInt(125, 200);

        double globalFuelPrice = random.nextDouble(1.8, 2.3);

        trainSpeed = random.nextInt(80, 200);
        fuelCost = totalFuel * globalFuelPrice / peopleInTrain / 3;

        travelCost = distance * 0.00098;

        travelCost = travelCost + trainSpeed / 100;

        CalculateTravelCost.totalTravelCost = fuelCost + travelCost;

        if (OvTime.peakStarted) {
            CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * 1.2;
        }
        if (OvTime.hours >= 22 || OvTime.hours < 6) {
            CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * 1.2;
        }

        if (seatClass == 2) {
            CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * 0.8;
        } else if (seatClass == 1) {
            CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * 1.5;
        }
        // taxes
        CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * 1.09;

        CalculateTravelCost.totalTravelCost = CalculateTravelCost.totalTravelCost * ticketAmount;

        CalculateTravelCost.totalTravelCost += 2; // service fee

        CalculateTravelCost.totalTravelCost *= 0.5; // discount

        CalculateTravelCost.totalTravelCost = Math.round(CalculateTravelCost.totalTravelCost * 100.0) / 100.0;

    }
}
