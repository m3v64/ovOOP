package ovOOP;

import java.util.Scanner;

public class AskDestination {
    private String destination;

    public String getDestination() {
        return destination;
    }

    public AskDestination() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter destination city:");
        String input = scanner.nextLine();
        System.out.println("Destination city set to: " + input);
        this.destination = input.toLowerCase();
    }
}
