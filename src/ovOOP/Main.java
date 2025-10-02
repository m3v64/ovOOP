package ovOOP;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu();

        // placeholder for ticket info
        String passengerName = "Daan";
        int seatClass = 1;
        int ticketAmount = 5;

        SaveOrigin saveOrigin = new SaveOrigin();
        String origin = saveOrigin.loadLastOrigin(passengerName, "origin.json");

        // If no previous origin, use default
        if (origin == null) {
            origin = "Dryard";
        }
        boolean running = true;
        while (running) {
            int select = menu.displayMenu();
            if (select == 1) {
                origin = bookTicket(passengerName, seatClass, ticketAmount, origin);
                saveOrigin.saveOrigin(origin, passengerName, "origin.json");
            } else if (select == 5) {
                System.out.println("Exiting the system. Goodbye!");
                System.exit(0);
            }
        }
    }

    public static String bookTicket(String passengerName, int seatClass, int ticketAmount, String origin) {

        // Trip details
        AskDestination askDestination = new AskDestination(origin);
        String destination = askDestination.getDestination();

        // Calculate distance
        TravelTo travelTo = new TravelTo(origin, destination);
        int distance = travelTo.distance;

        // Calculate travel cost
        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        double totalCost = calculateTravelCost.travelCost(distance, seatClass, ticketAmount);

        // Clear screen (prints 50 empty lines)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        // Check if tickets were booked
        if (totalCost == 0) {
            System.out.println("No tickets booked");
            return origin;
        }

        // Create coupon using OOP style
        Coupon coupon = new Coupon(
                passengerName,
                seatClass,
                ticketAmount,
                origin,
                destination,
                totalCost);

        // Save coupon to JSON file
        coupon.saveToFile("coupon.json");
        System.out.println("Thank you for using the MVU train company!");

        // Update origin for next trip
        origin = destination;

        return origin;

    }
}
