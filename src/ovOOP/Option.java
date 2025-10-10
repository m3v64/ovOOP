package ovOOP;

import java.util.Scanner;

public class Option {

    private static void printDivider() {
        System.out.println(Color.BLUE + "========================================");
    }

    private static void printOption(int index, String option) {
        System.out.printf(Color.BRIGHT_BLUE + "  %2d. %s%n", index, option.trim());
    }

    public static int showOption(Scanner scanner, String options) {
        String regex = "[,]";
        String[] optionArray = options.split(regex);
        int i = 0;
        printDivider();
        System.out.println(Color.CYAN + "         Please select an option:");
        printDivider();
        for (String option : optionArray) {
            i++;
            printOption(i, option);
        }
        printDivider();
        System.out.print(Color.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");

        int chosen = 0;
        while (chosen == 0) {
            try {
                chosen = scanner.nextInt();
                scanner.nextLine();
                if (chosen > i || chosen < 1) {
                    System.out.println(Color.BRIGHT_BLUE + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                    System.out.print(Color.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");
                    chosen = 0;
                }
            } catch (Exception e) {
                System.out.println(Color.BRIGHT_BLUE + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                System.out.print(Color.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");
                scanner.nextLine(); // consume invalid input
                chosen = 0;
            }
        }
        printDivider();
        return chosen;
    }
}
