package ovOOP;

import java.util.Random;

public class CalculateTravelCost {
    static double totalTravelCost;

    public double travelCost(int distance, int seatClass, int ticketAmount) {
        OvTime time = new OvTime();
        time.updateTime();

        Random random = new Random();

        // Train and Seat Calculation
        int peopleInTrain = random.nextInt(30, 50);
        int seatsPerCart = random.nextInt(10, 20);
        int trainCarts = random.nextInt(5, 10);
        int remainingSeats = trainCarts * seatsPerCart - peopleInTrain;

        if (!hasEnoughSeats(remainingSeats, ticketAmount)) {
            System.out.println("Not enough seats available");
            return 0;
        }

        // Fuel and Speed Calculation
        double totalFuel = random.nextInt(125, 200);
        double globalFuelPrice = random.nextDouble(1.8, 2.3);
        int trainSpeed = random.nextInt(80, 200);

        // Cost Calculations
        double fuelCost = calculateFuelCost(totalFuel, globalFuelPrice, peopleInTrain);
        double baseTravelCost = calculateBaseTravelCost(distance, trainSpeed);

        double cost = fuelCost + baseTravelCost;
        cost = applyTimeModifiers(cost);
        cost = applySeatClassModifier(cost, seatClass);
        cost = applyTaxes(cost);
        cost = applyTicketAmount(cost, ticketAmount);
        cost = applyServiceFee(cost);
        cost = applyDiscounts(cost, ticketAmount);

        return totalTravelCost = Math.round(cost * 100.0) / 100.0;
    }

    // --- Helper Methods ---

    private boolean hasEnoughSeats(int remainingSeats, int ticketAmount) {
        return remainingSeats >= ticketAmount;
    }

    private double calculateFuelCost(double totalFuel, double globalFuelPrice, int peopleInTrain) {
        return (totalFuel * globalFuelPrice) / (peopleInTrain * 3);
    }

    private double calculateBaseTravelCost(int distance, int trainSpeed) {
        double baseCost = distance * 25 * 0.00098;
        return baseCost * trainSpeed / 100;
    }

    private double applyTimeModifiers(double cost) {
        if (OvTime.peakStarted || OvTime.hours >= 22 || OvTime.hours < 6) {
            cost *= 1.2;
        }
        return cost;
    }

    private double applySeatClassModifier(double cost, int seatClass) {
        switch (seatClass) {
            case 2: return cost * 0.8; // Economy
            case 1: return cost * 1.5; // First class
            default: return cost;      // Standard
        }
    }

    private double applyTaxes(double cost) {
        return cost * 1.09;
    }

    private double applyTicketAmount(double cost, int ticketAmount) {
        return cost * ticketAmount;
    }

    private double applyServiceFee(double cost) {
        return cost + 2;
    }

    private double applyDiscounts(double cost, int ticketAmount) {
        cost *= 0.5; // General discount
        if (ticketAmount >= 4) {
            cost *= 0.75; // Group discount
        }
        return cost;
    }
}
