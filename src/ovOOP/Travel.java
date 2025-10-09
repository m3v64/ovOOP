package ovOOP;

public class Travel {

    private static double totalCost;

    static double calculateCost(int ticketAmount, boolean businessClass, int distanceTravelling,
            double conversionRate) {

        // if (Main.userID == 0){
        // return 0;
        // }

        Travel.totalCost = 0;

        double fuelCostPerLiter = 2.17;

        double totalFuelCost = (distanceTravelling / 500) * fuelCostPerLiter;

        Travel.totalCost += totalFuelCost;

        totalCost *= ticketAmount; // The more people that buy a ticket the pricier it becomes

        double randomFactor = Math.random() * 0.3 + 1; // Random increase

        totalCost *= randomFactor * (randomFactor / 2);

        if (ticketAmount > 4) {
            totalCost *= 0.9;
        }
        if (ticketAmount > 8) {
            totalCost *= 0.9;
        }

        if (businessClass == true) {
            totalCost *= 1.7;
        } else {
            totalCost *= 0.9;
        }

        totalCost *= 1.09; // taxes

        totalCost *= 1.20; // Profit margin

        Travel.totalCost *= conversionRate; // Conversion between currencies

        Travel.totalCost = Math.round(Travel.totalCost * 100) / 100.0;

        return totalCost;
    }

}
