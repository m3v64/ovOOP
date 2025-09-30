package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run classes here
        String name = "Morris";

        int seatClass = 1;

        int distance = 3000;

        int ticketAmount = 15;
        
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

        createCoupon.createCoupon(CalculateTravelCost.totalTravelCost, seatClass, ticketAmount, name);
    }
}
