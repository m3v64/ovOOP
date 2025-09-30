package ovOOP;

import java.io.File;
import java.io.FileWriter;

public class CreateCoupon {
    public void createCoupon(double price, int seatClass, int ticketAmount, String name) {
        // create coupon code here

        try {
            FileWriter coupon = new FileWriter("coupon.txt", true);
            coupon.write("----- COUPON -----\n");
            coupon.write("Coupon for: " + name + "\n");
            coupon.write("Class: " + seatClass + "\n");
            coupon.write("Tickets: " + ticketAmount + "\n");
            coupon.write("at " + OvTime.hours + ":" + OvTime.minutes + "\n");
            coupon.write("------------------\n");
            coupon.write("Total price: " + price + " euros\n");
            coupon.write("------------------\n");

            System.out.println("----- COUPON -----");
            System.out.println("Coupon for: " + name);
            System.out.println("Class: " + seatClass);
            System.out.println("Tickets: " + ticketAmount);
            System.out.println("at " + OvTime.hours + ":" + OvTime.minutes);
            System.out.println("------------------");
            System.out.println("Total price: " + price + " euros");
            System.out.println("------------------");

            coupon.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
