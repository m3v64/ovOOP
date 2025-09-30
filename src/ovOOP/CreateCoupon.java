package ovOOP;

import java.io.FileWriter;

public class CreateCoupon {
    public void createCoupon(double price, int seatClass, int ticketAmount, String name, String destination, String origin ) {
        // create coupon code here

        try {
            FileWriter coupon = new FileWriter("coupon.txt", true);
            coupon.write("----- COUPON -----\n");
            coupon.write("Coupon for: " + name + "\n");
            coupon.write("Class: " + seatClass + "\n");
            coupon.write("Tickets: " + ticketAmount + "\n");
            TravelTo travelTo = new TravelTo(origin, destination);
            coupon.write("From " + origin + " to " + destination + " with a distance of " + travelTo.distance + " km\n");
            coupon.write("at " + OvTime.hours + ":" + OvTime.minutes + " with peak: " + OvTime.peakStarted + "\n");
            coupon.write("------------------\n");
            coupon.write("Total price: " + price + " euros\n");
            coupon.write("------------------\n");

            System.out.println("----- COUPON -----");
            System.out.println("Coupon for: " + name);
            System.out.println("Class: " + seatClass);
            System.out.println("Tickets: " + ticketAmount);
            System.out.println("From " + origin + " to " + destination + " with a distance of " + travelTo.distance + " km");
            System.out.println("at " + OvTime.hours + ":" + OvTime.minutes + " with peak on: " + OvTime.peakStarted + "\n");
            System.out.println("------------------");
            System.out.println("Total price: " + price + " euros");
            System.out.println("------------------");
            System.out.println("\n");
            System.out.println("Coupon saved to coupon.txt");
            System.out.println(name + ", have a nice trip!");
            System.out.println("\n");

            coupon.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
