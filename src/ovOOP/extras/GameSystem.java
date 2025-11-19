package ovOOP.extras;

public class GameSystem {

    static int playGame(int playGame) {
        boolean wonGame = false;
        int winMoney = 0;

        switch (playGame) {
            case 1:
                wonGame = playMastermind();
                if (wonGame = true) {
                    winMoney = 10;
                }
                break;
            case 2:
                wonGame = playTicTacToe();
                winMoney = 5;
                break;
            case 3:
                wonGame = playUno();
                winMoney = 25;
                break;
            case 4:
                wonGame = playLuckyEights();
                winMoney = 25;
                break;
            case 5:
                wonGame = playConnectFour();
                winMoney = 20;
                break;
            case 6:
                wonGame = playWordle();
                winMoney = 35;
                break;
            case 7:
                wonGame = playMinesweeper();
                winMoney = 35;
                break;
            case 8:
                wonGame = playBattleship();
                winMoney = 25;
                break;
            case 9:
                wonGame = playBlackJack();
                winMoney = 15;
                break;
            case 10:
                wonGame = playRockPaperScissors();
                winMoney = 10;
                break;
            case 11:
                wonGame = playGuessTheNumber();
                winMoney = 15;
                break;

        }

        return wonGame;
    }

    static boolean playMastermind() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playTicTacToe() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playUno() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playLuckyEights() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playConnectFour() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playWordle() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playMinesweeper() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playBattleship() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playBlackJack() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playRockPaperScissors() {
        boolean wonGame = false;

        return wonGame;
    }

    static boolean playGuessTheNumber() {
        boolean wonGame = false;

        return wonGame;
    }
}
