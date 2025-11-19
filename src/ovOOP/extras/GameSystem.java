package ovOOP.extras;

import java.util.Random;
import java.util.Scanner;

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

    static boolean playUno(Scanner scanner) {
        boolean wonGame = false;

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
        System.out.println(guessNumber);

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
