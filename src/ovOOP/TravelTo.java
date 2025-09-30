package ovOOP;

public class TravelTo {
    private static final String[] CITIES = {
        "Dryard", "TimerGulch", "Brittle", "Staglenhold", "Eldyard", "Trasin",
        "Swiftlec", "Lirongrale", "Ghostle", "Pearllows", "Irehole", "Lighthgro",
        "Stormwall", "Linere"
    };

    private static final int[][] DISTANCES = {
        // Dryard, TimerGulch, Brittle, StaglenHold, EldYard, Trasin, SwiftLec, LironGrale, Ghostle, Pearllows, Irehole, Lighthgro, Stormwall, Linere
        {0,    50,   150,   0,    400,  200,  500,  0,    0,    0,    0,    0,    0,    300},   // Dryard
        {50,   0,    130,   0,    0,    0,    0,    0,    0,    0,    0,    0,    0,    0},     // TimerGulch
        {150,  0,    0,     100,  0,    0,    0,    0,    0,    0,    0,    0,    0,    0},     // Brittle
        {0,    0,    100,   0,    0,    0,    0,    0,    0,    0,    100,  100,  0,    0},     // StaglenHold
        {400,  0,    0,     0,    0,    300,  500,  0,    0,    0,    0,    0,    75,   300},   // EldYard
        {200,  0,    0,     0,    300,  0,    200,  100,  0,    0,    0,    0,    0,    0},     // Trasin
        {500,  0,    0,     0,    500,  200,  0,    0,    75,   0,    0,    0,    0,    200},   // SwiftLec
        {0,    0,    0,     0,    0,    100,  0,    0,    0,    50,   0,    0,    0,    0},     // LironGrale
        {0,    0,    0,     0,    0,    0,    75,   0,    0,    0,    0,    0,    0,    0},     // Ghostle
        {0,    0,    0,     0,    0,    0,    0,    50,   0,    0,    0,    0,    0,    0},     // Pearllows
        {0,    0,    0,     100,  0,    0,    0,    0,    0,    0,    0,    0,    0,    0},     // Irehole
        {0,    0,    0,     100,  0,    0,    0,    0,    0,    0,    0,    0,    0,    0},     // Lighthgro
        {0,    0,    0,     0,    75,   0,    0,    0,    0,    0,    0,    0,    0,    0},     // Stormwall
        {300,  0,    0,     0,    300,  0,    200,  0,    0,    0,    0,    0,    0,    0}      // Linere
    };

    private String origin;
    private String destination;
    public int distance;

    public TravelTo(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;

        int originIndex = getCityIndex(origin);
        int destinationIndex = getCityIndex(destination);

        if (originIndex == -1 || destinationIndex == -1) {
            System.out.println("Error: Unknown city");
            return;
        }

        this.distance = DISTANCES[originIndex][destinationIndex];
        System.out.println("Distance from " + origin + " to " + destination + " is " + distance + " km.");
    }

    private int getCityIndex(String city) {
        for (int i = 0; i < CITIES.length; i++) {
            if (CITIES[i].equalsIgnoreCase(city)) {
                return i;
            }
        }
        return -1;
    }
}