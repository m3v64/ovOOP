package ovOOP;

import java.util.Scanner;

public class OptionsSystem {

    private static void printDivider() {
        System.out.println(ColorSystem.BLUE + "========================================");
    }

    private static void printOption(int index, String option) {
        System.out.printf(ColorSystem.BRIGHT_BLUE + "  %2d. %s%n", index, option.trim());
    }

    public static int showOption(Scanner scanner, String header, String options) {
        String regex = "[,]";
        String[] optionArray = options.split(regex);
        int i = 0;
        printDivider();
        System.out.println(ColorSystem.CYAN + header);
        System.out.println(ColorSystem.CYAN + "Please select an option:");
        printDivider();
        for (String option : optionArray) {
            i++;
            printOption(i, option);
        }
        printDivider();
        System.out.print(ColorSystem.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");

        int chosen = 0;
        while (chosen == 0) {
            try {
                chosen = scanner.nextInt();
                scanner.nextLine();
                if (chosen > i || chosen < 1) {
                    System.out.println(ColorSystem.BRIGHT_BLUE + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                    System.out.print(ColorSystem.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");
                    chosen = 0;
                }
            } catch (Exception e) {
                System.out.println(ColorSystem.BRIGHT_BLUE + "  [!] Invalid input! Please choose a number between 1 and " + i + ".");
                System.out.print(ColorSystem.BRIGHT_CYAN + "Enter your choice (1-" + i + "): ");
                scanner.nextLine(); // consume invalid input
                chosen = 0;
            }
        }
        printDivider();
        return chosen;
    }
}
