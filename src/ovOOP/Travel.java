package ovOOP;

import java.util.Scanner;

public class Travel {

    static double calculateCost(boolean businessClass, int distanceTraveling, double conversionRate) {
        if (Main.userID == 0) {
            return 0;
        }

        double totalCost = 0;

        double fuelCostPerLiter = 2.17;

        double totalFuelCost = (distanceTraveling / 500.0) * fuelCostPerLiter;

        totalCost += totalFuelCost;

        double randomFactor = Math.random() * 0.3 + 1; // Random increase

        totalCost *= randomFactor * (randomFactor / 2);

        if (businessClass) {
            totalCost *= 1.7;
        } else {
            totalCost *= 0.9;
        }

        totalCost *= 1.09; // taxes

        totalCost *= 1.20; // Profit margin

        totalCost += 2; //Base price

        totalCost *= conversionRate; // Conversion between currencies

        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void travelMenu(Scanner scanner) {
        Menu.clear();

        int target = Option.showOption(scanner, "To destination,Lines,Map");
        switch (target) {
            case 1: break;
            case 2: break;
            case 3: break;
            default: System.out.println(Color.RED + "That is not a valid option" + Color.RESET); Menu.startMenu(scanner);
        }
    }

    static void toDestinationMenu(Scanner scanner) {
        System.out.println("Enter your destination or type 'map' to go to the map");
        String input = scanner.nextLine();
        int i = Data.CITIES.length();
        System.out.println(i);
    }
}
