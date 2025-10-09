package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;

    public static void main(String[] args) {

        // double travelTotalCost = Travel.calculateCost(1000000000, true, 1000000000, 1000000000);
        // System.out.println(travelTotalCost);
        Scanner scanner = new Scanner(System.in);

        Menu.clear();

        Menu.startMenu(scanner);
    }
}