package ovOOP;

import java.io.FileWriter;
import java.io.IOException;

public class CreateCoupon {

    public void createCoupon(double price, int seatClass, int ticketAmount, String name, String destination, String origin) {
        TravelTo travelTo = new TravelTo(origin, destination);
        String couponDetails = buildCouponDetails(price, seatClass, ticketAmount, name, destination, origin, travelTo);

        // Write coupon to file
        try (FileWriter coupon = new FileWriter("coupon.txt", true)) {
            coupon.write(couponDetails);
        } catch (IOException e) {
            System.err.println("Error writing coupon: " + e.getMessage());
            return;
        }

        // Print coupon to console
        System.out.print(couponDetails);
        System.out.println("Coupon saved to coupon.txt");
        System.out.println(name + ", have a nice trip!\n");
    }

    private String buildCouponDetails(double price, int seatClass, int ticketAmount, String name, String destination, String origin, TravelTo travelTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("----- COUPON -----\n");
        sb.append("Coupon for: ").append(name).append("\n");
        sb.append("Class: ").append(seatClass).append("\n");
        sb.append("Tickets: ").append(ticketAmount).append("\n");
        sb.append("From ").append(origin).append(" to ").append(destination)
          .append(" with a distance of ").append(travelTo.distance).append(" km\n");
        sb.append("at ").append(OvTime.hours).append(":").append(OvTime.minutes)
          .append(", at peak: ").append(OvTime.peakStarted).append("\n");
        sb.append("------------------\n");
        sb.append("Total price: ").append(price).append(" euros\n");
        sb.append("------------------\n\n");
        return sb.toString();
    }
}
