package tictactoe;

import tictactoe.player.EasyAI;
import tictactoe.player.HardAI;
import tictactoe.player.MediumAI;
import tictactoe.player.Player;
import tictactoe.player.User;

import java.util.Scanner;

public class Game {

    Player[] players;
    private MatrixBoard matrix;
    private CheckState gameStatus;

    public Game() {
        gameStatus = CheckState.NOT_FINISHED;
    }

    public void startGame() {
        String playerTypes = "^(user|easy|medium|hard)";
        boolean validCommands = false;
        while (!validCommands) {
            players = new Player[2];
            matrix = new MatrixBoard();
            System.out.print("Input command: ");
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().toLowerCase().split("\\s+");

            if ("exit".equals(input[0])) {
                System.exit(0);
            } else if (input.length == 3 && input[1].matches(playerTypes) &&
                    input[2].matches(playerTypes)) {
                players[0] = getNewPlayer(input[1]);
                players[1] = getNewPlayer(input[2]);
                validCommands = true;
            } else {
                System.out.println("Bad parameters");
            }
        }
        loopGame(players[0], players[1]);
    }

    public Player getNewPlayer(String text) {
        switch (text) {
            case "easy":
                return new EasyAI();
            case "medium":
                return new MediumAI();
            case "hard":
                return new HardAI();
            case "user":
                return new User();
        }
        return null;
    }

    private void loopGame(Player playerOne, Player playerTwo) {
        while (gameStatus == CheckState.NOT_FINISHED) {
            playerOne.setMatrixBySymbol(matrix);
            matrix.drawMatrix();
            matrix.findWinner();
            playerTwo.setMatrixBySymbol(matrix);
            matrix.drawMatrix();
            matrix.findWinner();
        }
        startGame();
    }
}