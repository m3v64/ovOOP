package ovOOP;

import java.util.Scanner;

public class askDestination {
    public void askDestination() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter destination city:");
        String destination = scanner.nextLine();
        System.out.println("Destination city set to: " + destination);
        destination = destination.toLowerCase();
    }
}
