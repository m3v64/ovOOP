package ovOOP;


public class TravelTo {
    String origin;
    String destination;
    int distance;

    public TravelTo(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;

        int[][] distances = {
                // Amsterdam, Rotterdam, Utrecht, Eindhoven, Groningen
                {0, 57, 35, 125, 180},      // Amsterdam
                {57, 0, 40, 115, 200},     // Rotterdam
                {35, 40, 0, 100, 160},     // Utrecht
                {125, 115, 100, 0, 220},   // Eindhoven
                {180, 200, 160, 220, 0}    // Groningen
        };
        int originIndex = -1;
        int destinationIndex = -1;
        String[] cities = {"Amsterdam", "Rotterdam", "Utrecht", "Eindhoven", "Groningen"};
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(origin)) {
                originIndex = i;
            }
            if (cities[i].equalsIgnoreCase(destination)) {
                destinationIndex = i;
            }
        }
        if (originIndex == -1 || destinationIndex == -1) {
            System.out.println("Error: Unknown city");
            return;
        }
        this.distance = distances[originIndex][destinationIndex];
        System.out.println("Distance from " + origin + " to " + destination + " is " + distance + " km.");
    }
}
    