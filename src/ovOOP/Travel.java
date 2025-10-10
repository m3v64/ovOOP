package ovOOP;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

        totalCost += 2; // Base price

        totalCost *= conversionRate; // Conversion between currencies

        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void askDestination(Scanner scanner) {
        // traveling system goes here
        Menu.clear();

        Data data = new Data(Main.userID);

        System.out.println(Color.BRIGHT_CYAN + "╔════════════════════════════════════════════════════╗");
        System.out.println(Color.BRIGHT_BLUE + "  You are currently at: " + Color.BRIGHT_CYAN + data.getLocation());
        System.out.println(Color.BRIGHT_CYAN + "╚════════════════════════════════════════════════════╝" + Color.RESET);

        System.out.println(Color.CYAN + "Please select a country you want to go to:" + Color.RESET);

        // get list of countries here

        String origin = data.getLocation();

        List<String> countries;

        countries = new ArrayList<>();

        countries.add("Country1");
        countries.add("Country2");

        int target = Option.showOption(scanner, String.join(",", countries));

        System.out.println(Color.BRIGHT_BLUE + "Selected destination: " + Color.BRIGHT_CYAN + countries.get(target - 1) + Color.RESET);

        int distance = 100;

        // get distance here and set distance variable to it

        boolean isFirstClass = (Option.showOption(scanner, "First class,Second class") == 1);

        double conversionRate = 1;

        double totalCost = Travel.calculateCost(isFirstClass, distance, conversionRate);

        Travel.CreateInvoice(scanner, totalCost, isFirstClass, origin, countries.get(target - 1), "OVOOP");

    }

    static void CreateInvoice(Scanner scanner, double totalCost, boolean isFirstClass, String origin,
            String destination, String trainCompany) {
        System.out.println(Color.BRIGHT_BLUE + "Would you like to print your invoice?" + Color.RESET);
        boolean willPrint = (Option.showOption(scanner, "Yes,No") == 1);

        if (!willPrint) {
            Menu.startMenu(scanner);
            return;
        }
        int invoiceId = (int) (Math.random() * 900000) + 100000; // 100000 to 999999

        System.out.println(Color.BRIGHT_CYAN + "====================================================");
        System.out.println(
                Color.BRIGHT_BLUE + Color.BOLD + trainCompany + " Transport - INVOICE #" + invoiceId + Color.RESET);
        System.out.println(Color.BRIGHT_CYAN + "====================================================");
        System.out.println(Color.CYAN + "Thank you for using " + trainCompany + " Transport for your traveling!" + Color.RESET);
        System.out.println(Color.BRIGHT_CYAN + "----------------------------------------------------");
        Data data = new Data(Main.userID);
        System.out.println(Color.BRIGHT_CYAN + "Invoice to: " + Color.BRIGHT_CYAN + data.getUsername() + Color.RESET);
        System.out.println(Color.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(Color.BRIGHT_BLUE + "From: " + Color.BRIGHT_CYAN + origin + Color.RESET + 
                           Color.BRIGHT_BLUE + "  To: " + Color.BRIGHT_CYAN + destination + Color.RESET);
        System.out.println(Color.BRIGHT_CYAN + "----------------------------------------------------");

        // Base fare
        double baseFare = 2.00;
        System.out.println(Color.BRIGHT_CYAN + "Base fare      : " + Color.BRIGHT_BLUE + Color.withLargeIntegers(baseFare) + Color.RESET);

        // Fixed VAT and profit
        double vat = 0.03;
        double profitMargin = 0.07;
        System.out.println(Color.BRIGHT_CYAN + "VAT (9%)       : " + Color.BRIGHT_BLUE + Color.withLargeIntegers(vat) + Color.RESET);
        System.out.println(Color.BRIGHT_CYAN + "Profit Margin  : " + Color.BRIGHT_BLUE + Color.withLargeIntegers(profitMargin) + Color.RESET);

        // Travel price
        double travelPrice = 0.37;
        System.out.println(Color.BRIGHT_CYAN + "Travel price   : " + Color.BRIGHT_BLUE + Color.withLargeIntegers(travelPrice) + Color.RESET);

        // Total cost
        totalCost = baseFare + vat + profitMargin + travelPrice;
        System.out.println(Color.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(Color.BRIGHT_CYAN + Color.BOLD + "Total price    : " + Color.BRIGHT_CYAN + Color.withLargeIntegers(totalCost) + Color.RESET);

        System.out.println(Color.BRIGHT_CYAN + "====================================================" + Color.RESET);

    }

}
