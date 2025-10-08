package ovOOP;

import java.util.Scanner;

public class Main {
    public static int userID = 0;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Menu.clear();

        Menu.startMenu(scanner);
    }
}