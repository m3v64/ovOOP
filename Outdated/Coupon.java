package ovOOP;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Coupon {

    private String name;
    private int seatClass;
    private int ticketAmount;
    private String origin;
    private String destination;
    private double distance;
    private String time;
    private boolean peak;
    private double totalPrice;

    // Constructor
    public Coupon(String name, int seatClass, int ticketAmount, String origin, String destination, double price) {
        this.name = name;
        this.seatClass = seatClass;
        this.ticketAmount = ticketAmount;
        this.origin = origin;
        this.destination = destination;
        TravelTo travelTo = new TravelTo(origin, destination);
        this.distance = travelTo.distance;
        this.time = OvTime.hours + ":" + OvTime.minutes;
        this.peak = OvTime.peakStarted;
        this.totalPrice = price;
    }

    // Convert this coupon to JSON
    public JSONObject toJson() {
        return new JSONObject()
                .put("name", name)
                .put("seatclass", seatClass)
                .put("tickets", ticketAmount)
                .put("origin", origin)
                .put("destination", destination)
                .put("distance", distance)
                .put("time", time)
                .put("peak", peak)
                .put("total_price", totalPrice);
    }

    // Save coupon to file
    public void saveToFile(String filename) {
        JSONArray couponsArray;

        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            couponsArray = new JSONArray(content);
        } catch (IOException e) {
            couponsArray = new JSONArray();
        }

        couponsArray.put(this.toJson());

        try (FileWriter file = new FileWriter(filename)) {
            file.write(couponsArray.toString(4));
            System.out.println("Coupon saved to " + filename);
            System.out.println(name + ", have a nice trip!\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
