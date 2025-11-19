package ovOOP.model;

/**
 * POJO model representing a station connection with distance.
 * Used for JSON serialization/deserialization with Gson.
 */
public class StationConnection {
    private int distance;

    public StationConnection() {
        // Default constructor for Gson
    }

    public StationConnection(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
