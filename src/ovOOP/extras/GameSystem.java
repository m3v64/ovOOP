package ovOOP.extras;

public class GameSystem {

    static boolean playGame(int playGame) {
        boolean wonGame = false;

        switch (playGame) {
            case 1:
                wonGame = playMastermind();
                break;
            case 2:
                wonGame = playTicTacToe();
                break;
            case 3:
                wonGame = playUno();
                break;
            case 4:
                wonGame = playLuckyEights();
                break;
            case 5:
                wonGame = playConnectFour();
                break;
            case 6:
                wonGame = playWorlde();
                break;
            case 7:
                wonGame = playMinesweeper();
                break;
            case 8:
                wonGame = playBattleship();
                break;
            case 9:
                wonGame = playBlackJack();
                break;
            case 10:
                wonGame = playRockPaperScissors();
                break;
            case 11:
                wonGame = playGuessTheNumber();
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

    static boolean playWorlde() {
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
