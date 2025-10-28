package ovOOP;

import java.util.Scanner;

public class MenuSystem {

    public static int mode = 1;

    static void startMenu(Scanner scanner) {
        System.out.println(ColorSystem.colorPalette[0] + "+------------------------------------------------+");
        System.out.println(ColorSystem.colorPalette[0] + "|                                                |");
        System.out.println(ColorSystem.colorPalette[1] + "|   _______ ___    _________ _______ ________    |");
        System.out.println(ColorSystem.colorPalette[1] + "|   __  __ \\__ |  / /__  __ \\__  __ \\___  __ \\   |");
        System.out.println(ColorSystem.colorPalette[1] + "|   _  / / /__ | / / _  / / /_  / / /__  /_/ /   |");
        System.out.println(ColorSystem.colorPalette[1] + "|   / /_/ / __ |/ /  / /_/ / / /_/ / _  ____/    |");
        System.out.println(ColorSystem.colorPalette[1] + "|   \\____/  _____/   \\____/  \\____/  /_/         |");
        System.out.println(ColorSystem.colorPalette[1] + "|                                                |");
        System.out.println(ColorSystem.colorPalette[0] + "+------------------------------------------------+");
        System.out.println(ColorSystem.colorPalette[0] + "=======================================");
        int input = 0;
        DataSystem data = new DataSystem(Main.userID);

        ColorSystem.colorPalette[0] = data.getMainPalette();
        ColorSystem.colorPalette[1] = data.getSecondaryPalette();
        MenuSystem.mode = data.getMode();

        int currentUser = data.getUserID();
        if (currentUser != 0) {
            System.out.println(
                    ColorSystem.colorPalette[1] + String.format("Logged in as: %-20s", data.getUsername()) +
                            ColorSystem.colorPalette[1] + " | Balance: "
                            + ColorSystem.withLargeIntegers(data.getBalance()));
            System.out.println(ColorSystem.colorPalette[0] + "---------------------------------------");
            input = OptionsSystem.showOption(scanner, "Main Menu",
                    "Start traveling,Settings,Manage balance,Exit system,Credits");
        } else {
            System.out.println(ColorSystem.colorPalette[1] + "You are not currently logged in.");
            System.out.println(ColorSystem.colorPalette[1] + "Please log in before using any traveling features.");
            System.out.println(ColorSystem.colorPalette[0] + "---------------------------------------");
            input = OptionsSystem.showOption(scanner, "Main Menu", "Account Settings,Credits");
        }
        if (currentUser != 0) {
            if (input == 5) {
                // Credits go here
                showCredits(scanner);
            } else if (input == 1) {
                // Travel Menu
                TravelSystem.travelMenu(scanner);
            } else if (input == 2) {
                MenuSystem.showSettingsScreen(scanner);
                // Accounts system goes here
            } else if (input == 3) {
                BalanceSystem.manageBalance(scanner);
            } else if (input == 4) {
                System.out.println(ColorSystem.RED + "Exiting System");
                System.exit(0);
                // Exits the system
            }
        } else {
            switch (input) {
                case 1 -> AccountSystem.displayAccounts(scanner);
                case 2 -> showCredits(scanner);
            }
        }
    }

    static void changeColorPalette(Scanner scanner) {
        int setPalette = OptionsSystem.showOption(scanner, "What color palette do you want?",
                "BLACK - WHITE,BLACK - RED,BLACK - GREEN,BLACK - YELLOW,BLACK - BLUE,BLACK - PURPLE,BLACK - CYAN,"
                        + "RED - WHITE,RED - YELLOW,RED - BLUE,RED - PURPLE,RED - CYAN,"
                        + "GREEN - WHITE,GREEN - YELLOW,GREEN - BLUE,GREEN - PURPLE,GREEN - CYAN,"
                        + "YELLOW - BLUE,YELLOW - PURPLE,"
                        + "BLUE - WHITE,BLUE - PURPLE,BLUE - CYAN,"
                        + "PURPLE - WHITE,PURPLE - CYAN,"
                        + "CYAN - WHITE,CUSTOM");

        switch (setPalette) {
            case 1:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 2:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.RED;
                break;
            case 3:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.GREEN;
                break;
            case 4:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.YELLOW;
                break;
            case 5:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.BLUE;
                break;
            case 6:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.PURPLE;
                break;
            case 7:
                ColorSystem.colorPalette[0] = ColorSystem.BLACK;
                ColorSystem.colorPalette[1] = ColorSystem.CYAN;
                break;
            case 8:
                ColorSystem.colorPalette[0] = ColorSystem.RED;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 9:
                ColorSystem.colorPalette[0] = ColorSystem.RED;
                ColorSystem.colorPalette[1] = ColorSystem.YELLOW;
                break;
            case 10:
                ColorSystem.colorPalette[0] = ColorSystem.RED;
                ColorSystem.colorPalette[1] = ColorSystem.BLUE;
                break;
            case 11:
                ColorSystem.colorPalette[0] = ColorSystem.RED;
                ColorSystem.colorPalette[1] = ColorSystem.PURPLE;
                break;
            case 12:
                ColorSystem.colorPalette[0] = ColorSystem.RED;
                ColorSystem.colorPalette[1] = ColorSystem.CYAN;
                break;
            case 13:
                ColorSystem.colorPalette[0] = ColorSystem.GREEN;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 14:
                ColorSystem.colorPalette[0] = ColorSystem.GREEN;
                ColorSystem.colorPalette[1] = ColorSystem.YELLOW;
                break;
            case 15:
                ColorSystem.colorPalette[0] = ColorSystem.GREEN;
                ColorSystem.colorPalette[1] = ColorSystem.BLUE;
                break;
            case 16:
                ColorSystem.colorPalette[0] = ColorSystem.GREEN;
                ColorSystem.colorPalette[1] = ColorSystem.PURPLE;
                break;
            case 17:
                ColorSystem.colorPalette[0] = ColorSystem.GREEN;
                ColorSystem.colorPalette[1] = ColorSystem.CYAN;
                break;
            case 18:
                ColorSystem.colorPalette[0] = ColorSystem.YELLOW;
                ColorSystem.colorPalette[1] = ColorSystem.BLUE;
                break;
            case 19:
                ColorSystem.colorPalette[0] = ColorSystem.YELLOW;
                ColorSystem.colorPalette[1] = ColorSystem.PURPLE;
                break;
            case 20:
                ColorSystem.colorPalette[0] = ColorSystem.BLUE;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 21:
                ColorSystem.colorPalette[0] = ColorSystem.BLUE;
                ColorSystem.colorPalette[1] = ColorSystem.PURPLE;
                break;
            case 22:
                ColorSystem.colorPalette[0] = ColorSystem.BLUE;
                ColorSystem.colorPalette[1] = ColorSystem.CYAN;
                break;
            case 23:
                ColorSystem.colorPalette[0] = ColorSystem.PURPLE;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 24:
                ColorSystem.colorPalette[0] = ColorSystem.PURPLE;
                ColorSystem.colorPalette[1] = ColorSystem.CYAN;
                break;
            case 25:
                ColorSystem.colorPalette[0] = ColorSystem.CYAN;
                ColorSystem.colorPalette[1] = ColorSystem.WHITE;
                break;
            case 26:
                int color1 = OptionsSystem.showOption(scanner, "Choose the first color!",
                        "Black,Red,Green,Yellow,Blue,Purple,Cyan,White");
                int color2 = OptionsSystem.showOption(scanner, "Choose the second color!",
                        "Black,Red,Green,Yellow,Blue,Purple,Cyan,White");
                String[] Colors = { ColorSystem.BLACK, ColorSystem.RED, ColorSystem.GREEN, ColorSystem.YELLOW,
                        ColorSystem.BLUE, ColorSystem.PURPLE, ColorSystem.CYAN, ColorSystem.WHITE };
                ColorSystem.colorPalette[0] = Colors[color1 - 1];
                ColorSystem.colorPalette[1] = Colors[color2 - 1];
        }

        DataSystem data = new DataSystem(Main.userID);
        data.setMainPalette(ColorSystem.colorPalette[0]);
        data.setSecondaryPalette(ColorSystem.colorPalette[1]);

        startMenu(scanner);
    }

    static void showVisualSettingsScreen(Scanner scanner) {
        int target = OptionsSystem.showOption(scanner, "Please choose the setting you want to change",
                "Mode,Color Palette");

        switch (target) {
            case 1:
                mode = OptionsSystem.showOption(scanner, "Please choose the mode you want", "Dark mode, Light mode");
                updateMode(scanner, mode);
                break;
            case 2:
                changeColorPalette(scanner);
                break;
        }
    }

    static void updateMode(Scanner scanner, int mode) {
        String mainPalette = ColorSystem.colorPalette[0];
        String secondaryPalette = ColorSystem.colorPalette[1];
        int mainIndex = 0, secondaryIndex = 0;

        switch (mode) {
            case 1: // Dark mode
                for (int i = 0; i < ColorSystem.lightColorPaletteOptions.length; i++) {
                    if (ColorSystem.lightColorPaletteOptions[i].equals(mainPalette)) {
                        mainIndex = i;
                        mainPalette = ColorSystem.darkColorPaletteOptions[i];
                    }
                    if (ColorSystem.lightColorPaletteOptions[i].equals(secondaryPalette)) {
                        secondaryIndex = i;
                        secondaryPalette = ColorSystem.darkColorPaletteOptions[i];
                    }
                }
                break;
            case 2: // Light mode
                for (int i = 0; i < ColorSystem.darkColorPaletteOptions.length; i++) {
                    if (ColorSystem.darkColorPaletteOptions[i].equals(mainPalette)) {
                        mainIndex = i;
                        mainPalette = ColorSystem.lightColorPaletteOptions[i];
                    }
                    if (ColorSystem.darkColorPaletteOptions[i].equals(secondaryPalette)) {
                        secondaryIndex = i;
                        secondaryPalette = ColorSystem.lightColorPaletteOptions[i];
                    }
                }
                break;
        }

        // Apply to ColorSystem
        ColorSystem.colorPalette[0] = mainPalette;
        ColorSystem.colorPalette[1] = secondaryPalette;

        // Save to user JSON
        DataSystem data = new DataSystem(Main.userID);
        data.setMainPalette(mainPalette);
        data.setSecondaryPalette(secondaryPalette);
        data.setMode(mode);

        startMenu(scanner);
    }

    static void showSettingsScreen(Scanner scanner) {
        int target = OptionsSystem.showOption(scanner, "Settings", "Account settings,Visual settings,Debug settings");

        switch (target) {
            case 1:
                AccountSystem.displayAccounts(scanner);
                break;
            case 2:
                showVisualSettingsScreen(scanner);
                break;
            case 3:
                break;
        }
    }

    static void showCredits(Scanner scanner) {
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println(ColorSystem.colorPalette[0] + "                CREDITS                   ");
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println(ColorSystem.colorPalette[1] + " Developers      : Morris van Uden, Max Viehöfer");
        System.out.println(ColorSystem.colorPalette[1] + " Teacher         : Erik Seldenthuis");
        System.out.println(ColorSystem.colorPalette[1] + " Class           : TIA4V1B");
        System.out.println(ColorSystem.colorPalette[1] + " Web Page        : https://ovoop.m3v.dev (Soon)");
        System.out.println(ColorSystem.colorPalette[1] + " GitHub Repo     : https://github.com/m3v64/ovOOP");
        System.out.println(ColorSystem.colorPalette[0] + "==========================================");
        System.out.println();
        System.out.println(ColorSystem.BRIGHT_PURPLE + "Press enter to continue...");
        scanner.nextLine();
        startMenu(scanner);
    }

    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
