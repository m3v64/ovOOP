package ovOOP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class AskDestination {
    private static final Set<String> VALID_CITIES = new HashSet<>(Arrays.asList(
        "dryard", "timergulch", "brittle", "staglenhold", "eldyard", "trasin",
        "swiftlec", "lirongrale", "ghostle", "pearllows", "irehole", "lighthgro",
        "stormwall", "linere"
    ));

    private String destination;

    public AskDestination(String origin) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter destination city: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase(origin)) {
            System.out.println("Error: Origin and destination cannot be the same.");
            return;
        }

        if (!VALID_CITIES.contains(input.toLowerCase())) {
            System.out.println("Error: Unknown city");
            return;
        }

        this.destination = input;
        System.out.println("Destination city set to: " + input);
    }

    public String getDestination() {
        return destination;
    }
}
