package ovOOP;

public class Main {
    public static void main(String[] args) {
        // Passenger details
        String passengerName = "Morris";
        int seatClass = 1;
        int ticketAmount = 3;

        // Trip details
        String origin = "Dryard";
        AskDestination askDestination = new AskDestination(origin);
        String destination = askDestination.getDestination();

        // Calculate distance
        TravelTo travelTo = new TravelTo(origin, destination);
        int distance = travelTo.distance;

        // Calculate travel cost
        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(distance, seatClass, ticketAmount);

        // Clear screen (prints 50 empty lines)
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        // Check if tickets were booked
        if (CalculateTravelCost.totalTravelCost == 0) {
            System.out.println("No tickets booked");
            return;
        }

        // Create coupon
        CreateCoupon createCoupon = new CreateCoupon();
        createCoupon.createCoupon(
            CalculateTravelCost.totalTravelCost,
            seatClass,
            ticketAmount,
            passengerName,
            destination,
            origin
        );

        // Update origin for next trip
        origin = destination;
    }
}
