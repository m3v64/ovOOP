package ovOOP.extras;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class GameSystem {

    private static int winMoney = 0;

    static int playGame(int playGame) {
        boolean wonGame = false;

        Scanner scanner = new Scanner(System.in);

        switch (playGame) {

            case 1:
                wonGame = playMastermind(scanner);
                break;
            case 2:
                wonGame = playTicTacToe(scanner);
                break;
            case 3:
                wonGame = playUno(scanner);
                break;
            case 4:
                wonGame = playLuckyEights(scanner);
                break;
            case 5:
                wonGame = playConnectFour(scanner);
                break;
            case 6:
                wonGame = playWordle(scanner);
                break;
            case 7:
                wonGame = playMinesweeper(scanner);
                break;
            case 8:
                wonGame = playBattleship(scanner);
                break;
            case 9:
                wonGame = playBlackJack(scanner);
                break;
            case 10:
                wonGame = playRockPaperScissors(scanner);
                break;
            case 11:
                wonGame = playGuessTheNumber(scanner);
                break;

        }
        if (wonGame == true) {
            return winMoney;
        } else {
            return 0;
        }
    }

    static boolean playMastermind(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playTicTacToe(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static String translateUnoCardToReadable(String cardCode) {
        String output = "";

        char[] untranslatedCode = cardCode.toCharArray();

        String color = "";

        String type = "";

        switch (untranslatedCode[0]) {
            case 'b':
                color = "Blue";
                break;
            case 'y':
                color = "Yellow";
                break;
            case 'r':
                color = "Red";
                break;
            case 'g':
                color = "Green";
                break;
            case 'w':
                color = "Wildcard";
                break;
        }
        switch (untranslatedCode[1]) {
            case 'r':
                type = "Reverse turns";
                break;
            case 'x':
                type = "Turn block";
                break;
            case '+':
                type = "Pull cards";
                break;
            case 'c':
                type = "Choose color";
                break;
            default:
                type = Character.toString(untranslatedCode[1]);
                break;
        }

        output = color + " " + type;

        return output;
    }

    static boolean playUno(Scanner scanner) {

        boolean wonGame = false;

        int playerCardAmount = 7;

        int botCardAmount = 7;

        int turn = 1;

        ArrayList<String> playerCards = new ArrayList<>();

        ArrayList<String> botCards = new ArrayList<>();

        ArrayList<String> cardsInDeck = new ArrayList<>();

        String[] possibleCards = { "b01", "b12", "b22", "b32", "b42", "b52", "b62", "b72", "b82", "b92", "bx2", "br2",
                "b+2",
                "y01", "y12", "y22", "y32", "y42", "y52", "y62", "y72", "y82", "y92", "yx2", "yr2", "y+2",
                "r01", "r12", "r22", "r32", "r42", "r52", "r62", "r72", "r82", "r92", "rx2", "rr2", "r+2",
                "g01", "g12", "g22", "g32", "g42", "g52", "g62", "g72", "g82", "g92", "gx2", "gr2", "g+2",
                "wc4", "w+4"
        };
        for (String i : possibleCards) {
            char[] charArray = i.toCharArray();

            int count = 0;
            if (charArray.length > 2 && Character.isDigit(charArray[2])) {
                count = Character.getNumericValue(charArray[2]);
            }
            for (int j = 0; j < count; j++) {
                cardsInDeck.add(i);
            }
        }

        Random r = new Random();

        for (int i = 0; i < playerCardAmount; i++) {
            int randomCardIndex = r.nextInt(cardsInDeck.size());
            playerCards.add(cardsInDeck.get(randomCardIndex));
            cardsInDeck.remove(randomCardIndex);
        }
        for (int i = 0; i < botCardAmount; i++) {
            int randomCardIndex = r.nextInt(cardsInDeck.size());
            botCards.add(cardsInDeck.get(randomCardIndex));
            cardsInDeck.remove(randomCardIndex);
        }

        System.out.println("You have the following cards:");
        int index = 1;
        for (String i : playerCards) {
            System.out.println(translateUnoCardToReadable(i));
            index++;
        }

        return wonGame;
    }

    static boolean playLuckyEights(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playConnectFour(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playWordle(Scanner scanner) {
        boolean wonGame = false;

        ArrayList<String> words = new ArrayList<>();
        File file = new File("data/Words.txt");

        // Read words from file
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                words.add(fileReader.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (words.isEmpty()) {
            System.out.println("No words available in data/Words.txt");
            return wonGame;
        }

        // Select a random word
        Random r = new Random();
        String randomWord = words.get(r.nextInt(words.size())).toLowerCase();
        int wordLength = randomWord.length();

        // For testing: remove in real gameplay
        System.out.println("DEBUG: The word is " + randomWord);

        // Player has 6 attempts
        for (int attempt = 0; attempt < 6; attempt++) {
            System.out.println("\nAttempt " + (attempt + 1) + " of 6. Enter a word of length " + wordLength + ":");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.length() != wordLength) {
                System.out.println("Word must be exactly " + wordLength + " letters.");
                attempt--; // repeat this attempt
                continue;
            }

            // Check for exact matches first
            char[] inputChars = input.toCharArray();
            char[] wordChars = randomWord.toCharArray();
            int[] result = new int[wordLength]; // 0=wrong, 1=correct letter wrong place, 2=correct letter & place
            boolean[] letterUsed = new boolean[wordLength]; // track letters used for yellow

            // First pass: correct position (green)
            for (int i = 0; i < wordLength; i++) {
                if (inputChars[i] == wordChars[i]) {
                    result[i] = 2;
                    letterUsed[i] = true;
                }
            }

            // Second pass: correct letter, wrong position (yellow)
            for (int i = 0; i < wordLength; i++) {
                if (result[i] == 0) { // only if not already green
                    for (int j = 0; j < wordLength; j++) {
                        if (!letterUsed[j] && inputChars[i] == wordChars[j]) {
                            result[i] = 1;
                            letterUsed[j] = true;
                            break;
                        }
                    }
                }
            }

            // Display result
            for (int i = 0; i < wordLength; i++) {
                if (result[i] == 2) {
                    System.out.print(ColorSystem.GREEN_BG + inputChars[i]);
                } else if (result[i] == 1) {
                    System.out.print(ColorSystem.YELLOW_BG + inputChars[i]);
                } else {
                    System.out.print(ColorSystem.RED_BG + inputChars[i]);
                }
            }
            System.out.println(ColorSystem.RESET);

            // Check win
            if (input.equals(randomWord)) {
                System.out.println("Congratulations! You guessed the word!");
                wonGame = true;
                winMoney = 25;
                break;
            }
        }

        if (!wonGame) {
            System.out.println("Sorry! The word was: " + randomWord);
        }

        return wonGame;
    }

    static boolean playMinesweeper(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playBattleship(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playBlackJack(Scanner scanner) {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playRockPaperScissors(Scanner scanner) {
        boolean wonGame = false;

        int wins = 0;
        int losses = 0;

        String[] options = { "Rock", "Paper", "Scissors" };

        Random r = new Random();

        for (int i = 0; i < 3; i++) {

            System.out
                    .println(ColorSystem.colorPalette[0] + "Rock Paper Scissors Wins " + wins + " : Losses " + losses);
            System.out.println();
            System.out.println("Round " + ColorSystem.colorPalette[1] + (i + 1) + ColorSystem.colorPalette[0] + "/3");

            int userChoiceIndex = OptionsSystem.showOption(scanner, "Please input what you want to use",
                    "Rock,Paper,Scissors");
            int userIdx = userChoiceIndex - 1;
            String userChoice = options[userIdx];

            int botIdx = r.nextInt(options.length);
            String botChoice = options[botIdx];

            boolean wonRound = false;

            if (userChoice.equals("Rock") && botChoice.equals("Scissors")) {
                wonRound = true;
            }
            if (userChoice.equals("Paper") && botChoice.equals("Rock")) {
                wonRound = true;
            }
            if (userChoice.equals("Scissors") && botChoice.equals("Paper")) {
                wonRound = true;
            }

            if (userChoice.equals(botChoice)) {
                System.out.println(ColorSystem.colorPalette[1] + "Tie! (You chose " + userChoice + ", Bot chose "
                        + botChoice + ")");
                i--;
            } else if (wonRound) {
                System.out.println(ColorSystem.colorPalette[1] + "You won! (You chose " + userChoice + ", Bot chose "
                        + botChoice + ")");
                wins++;
            } else {
                System.out.println(ColorSystem.colorPalette[1] + "You lost! (You chose " + userChoice + ", Bot chose "
                        + botChoice + ")");
                losses++;
            }

            System.out.println("Press enter to continue");

            scanner.nextLine();

        }

        wonGame = wins > losses;

        winMoney = 5 * wins;

        return wonGame;
    }

    static boolean playGuessTheNumber(Scanner scanner) {
        boolean wonGame = false;

        int guessNumber = (int) (Math.random() * 10) + 1;

        int roundsUsed = 0;

        for (int i = 0; i < 5; i++) {
            System.out.println("Guess " + ColorSystem.colorPalette[1] + (i + 1) + ColorSystem.colorPalette[0] + "/5");

            System.out.println("Please input the number you want to guess:");
            int guess;
            try {
                guess = scanner.nextInt();
            } catch (Exception e) {
                i--;
                System.out.println("That is an invalid input! Please choose a number between 1 and 10");
                continue;
            }
            if (guess == guessNumber) {
                wonGame = true;
                System.out.println("Correct! It was " + guessNumber);
                break;
            } else if (guess > guessNumber) {
                System.out.println("That number is too high!");
            } else if (guess < guessNumber) {
                System.out.println("That number is too low!");
            }
            roundsUsed = i;
        }

        winMoney = -5 * roundsUsed + 25;

        return wonGame;
    }
}
