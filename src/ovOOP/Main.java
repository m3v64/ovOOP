package ovOOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // run classes here
        String name = "Morris";

        int seatClass = 2;

        int distance = 300;

        int ticketAmount = 9;
        
        CalculateTravelCost calculateTravelCost = new CalculateTravelCost();
        calculateTravelCost.travelCost(distance, seatClass, ticketAmount);

        for (int i = 0; i < 50; i++) {
            System.out.println();
        }

        CreateCoupon createCoupon = new CreateCoupon();

        createCoupon.createCoupon(CalculateTravelCost.totalTravelCost, seatClass, ticketAmount, name);
    }
}
