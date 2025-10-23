package ovOOP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.management.monitor.MonitorNotification;

public class TravelSystem {

    static double calculateCost(boolean businessClass, int distanceTraveling, double conversionRate) {
        if (Main.userID == 0) {
            return 0;
        }

        double totalCost = 0;
        double fuelCostPerLiter = 2.17;
        double totalFuelCost = (distanceTraveling / 500.0) * fuelCostPerLiter;
        totalCost += totalFuelCost; // total fuel cost
        double randomFactor = Math.random() * 0.3 + 1;
        totalCost *= randomFactor * (randomFactor / 2); // random factor

        if (businessClass) {
            totalCost *= 1.7;
        } else {
            totalCost *= 0.9;
        }

        totalCost *= 1.09; // taxes
        totalCost *= 1.20; // margin
        totalCost += 2; // addition for minimal cost
        totalCost *= conversionRate; // conversion rate for different currencies
        totalCost = Math.round(totalCost * 100) / 100.0;

        return totalCost;
    }

    static void CreateInvoice(Scanner scanner, double totalCost, boolean isFirstClass, String origin,
            String destination, String trainCompany) {
        System.out.println(ColorSystem.BRIGHT_BLUE + "Would you like to print your invoice?" + ColorSystem.RESET);
        boolean willPrint = (OptionsSystem.showOption(scanner, "Yes,No") == 1);

        if (!willPrint) {
            MenuSystem.startMenu(scanner);
            return;
        }
        int invoiceId = (int) (Math.random() * 900000) + 100000; // 100000 to 999999

        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================");
        System.out.println(
                ColorSystem.BRIGHT_BLUE + ColorSystem.BOLD + trainCompany + " Transport - INVOICE #" + invoiceId + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================");
        System.out.println(ColorSystem.CYAN + "Thank you for using " + trainCompany + " Transport for your traveling!" + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        DataSystem data = new DataSystem(Main.userID);
        System.out.println(ColorSystem.BRIGHT_CYAN + "Invoice to: " + ColorSystem.BRIGHT_CYAN + data.getUsername() + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(ColorSystem.BRIGHT_BLUE + "From: " + ColorSystem.BRIGHT_CYAN + origin + ColorSystem.RESET + 
                           ColorSystem.BRIGHT_BLUE + "  To: " + ColorSystem.BRIGHT_CYAN + destination + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");

        // Base fare
        double baseFare = 2.00;
        System.out.println(ColorSystem.BRIGHT_CYAN + "Base fare      : " + ColorSystem.BRIGHT_BLUE + ColorSystem.withLargeIntegers(baseFare) + ColorSystem.RESET);

        // Fixed VAT and profit
        double vat = 0.03;
        double profitMargin = 0.07;
        System.out.println(ColorSystem.BRIGHT_CYAN + "VAT (9%)       : " + ColorSystem.BRIGHT_BLUE + ColorSystem.withLargeIntegers(vat) + ColorSystem.RESET);
        System.out.println(ColorSystem.BRIGHT_CYAN + "Profit Margin  : " + ColorSystem.BRIGHT_BLUE + ColorSystem.withLargeIntegers(profitMargin) + ColorSystem.RESET);

        // Travel price
        double travelPrice = 0.37;
        System.out.println(ColorSystem.BRIGHT_CYAN + "Travel price   : " + ColorSystem.BRIGHT_BLUE + ColorSystem.withLargeIntegers(travelPrice) + ColorSystem.RESET);

        // Total cost
        totalCost = baseFare + vat + profitMargin + travelPrice;
        System.out.println(ColorSystem.BRIGHT_CYAN + "----------------------------------------------------");
        System.out.println(ColorSystem.BRIGHT_CYAN + ColorSystem.BOLD + "Total price    : " + ColorSystem.BRIGHT_CYAN + ColorSystem.withLargeIntegers(totalCost) + ColorSystem.RESET);

        System.out.println(ColorSystem.BRIGHT_CYAN + "====================================================" + ColorSystem.RESET);

    }

    static void travelMenu(Scanner scanner) {
        MenuSystem.clear();

        int target = OptionsSystem.showOption(scanner, "To destination,Lines,Map");
        switch (target) {
            case 1: toDestinationMenu(scanner);
            case 2: break;
            case 3: break;
            default: System.out.println(ColorSystem.RED + "That is not a valid option" + ColorSystem.RESET); MenuSystem.startMenu(scanner);
        }
    }

    static void toDestinationMenu(Scanner scanner) {
        MenuSystem.clear();

        DataSystem data = new DataSystem(Main.userID);

        System.out.println(ColorSystem.BRIGHT_CYAN + "╔════════════════════════════════════════════════════╗");
        System.out.println(ColorSystem.BRIGHT_BLUE + "  You are currently at: " + ColorSystem.BRIGHT_CYAN + data.getLocation());
        System.out.println(ColorSystem.BRIGHT_CYAN + "╚════════════════════════════════════════════════════╝" + ColorSystem.RESET);

        System.out.println(ColorSystem.CYAN + "Please select a country you want to go to:" + ColorSystem.RESET);



        String origin = data.getLocation();

        List<String> cities = new ArrayList<>();

         for (String i : data.CITIES) {
             cities.add(i);
        }

        

        int target = OptionsSystem.showOption(scanner, String.join(",", cities)) - 1;

        

        System.out.println(ColorSystem.BRIGHT_BLUE + "Selected destination: " + ColorSystem.BRIGHT_CYAN + cities.get(target) + ColorSystem.RESET);

        int distance = 100;

        // get distance here and set distance variable to it

        boolean isFirstClass = (OptionsSystem.showOption(scanner, "First class,Second class") == 1);

        double conversionRate = 1;

        double totalCost = TravelSystem.calculateCost(isFirstClass, distance, conversionRate);

        TravelSystem.CreateInvoice(scanner, totalCost, isFirstClass, origin, cities.get(target), "OVOOP");


        data.setLocation(cities.get(target));

        MenuSystem.startMenu(scanner);

    }
}
