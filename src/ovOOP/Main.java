package ovOOP;

public class Main {
    public static void main(String[] args) {
        // run classes here
        String name = "Morris";

        int seatClass = 1;

        int ticketAmount = 20;

        String destination = "Amsterdam";

        String origin = "groningen";

        askDestination askDestination = new askDestination();
        askDestination.askDestination();

        TravelTo travelTo = new TravelTo(origin, destination);
        int distance = travelTo.distance;

        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(distance, seatClass, ticketAmount);

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        if (CalculateTravelCost.totalTravelCost == 0) {
            System.out.println("No tickets booked");
            return;
        }
        CreateCoupon createCoupon = new CreateCoupon();

        createCoupon.createCoupon(CalculateTravelCost.totalTravelCost, seatClass, ticketAmount, name, destination,
                origin);

        origin = destination;
    }
}
