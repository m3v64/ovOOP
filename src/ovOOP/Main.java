package ovOOP;

public class Main {
    public static void main(String[] args) {
        // Passenger details
        String passengerName = "Daan";
        int seatClass = 1;
        int ticketAmount = 5;

        // Trip details
        String origin = "Dryard";
        AskDestination askDestination = new AskDestination(origin);
        String destination = askDestination.getDestination();

        // Calculate distance
        TravelTo travelTo = new TravelTo(origin, destination);
        int distance = travelTo.distance;

        // Calculate travel cost
        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        totalCost = calculateTravelCost.travelCost(distance, seatClass, ticketAmount);

        // Clear screen (prints 50 empty lines)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        // Check if tickets were booked
        if (totalCost == 0) {
            System.out.println("No tickets booked");
            return;
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

        // Update origin for next trip
        origin = destination;
    }
}
