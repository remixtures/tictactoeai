package tictactoe;

import java.util.Arrays;
import java.util.Random;

public class MatrixBoard {

    private final char[] matrix = new char[9];
    private char symbol;
    private Game game;
    private CheckState gameStatus;

   public MatrixBoard() {
       this.symbol = 'X';
       gameStatus = CheckState.NOT_FINISHED;
       Arrays.fill(matrix, ' ');
       game = new Game();
    }

    public CheckState getGameStatus() {
        return gameStatus;
    }

    private final int[][] victory = {
            // Horizontals
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            // Verticals
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            // Diagonals
            {0, 4, 8},
            {2, 4, 6}
    };

    public void drawMatrix() {
        System.out.println("---------");
        System.out.println("| " + matrix[0] + " " + matrix[1] + " " + matrix[2] + " |");
        System.out.println("| " + matrix[3] + " " + matrix[4] + " " + matrix[5] + " |");
        System.out.println("| " + matrix[6] + " " + matrix[7] + " " + matrix[8] + " |");
        System.out.println("---------");
    }

    private char otherPlayer() {
        return (symbol == 'X') ? 'O' : 'X';
    }

    public boolean isCellEmpty(int index) {
        return matrix[index] == ' ';

    }

    public void opponentPlayer() {
        if (symbol == 'X') {
            symbol = 'O';
        } else {
            symbol = 'X';
        }
    }

    public void setCell(int index) {
        this.matrix[index] = symbol;
    }

    public void randomMove(MatrixBoard matrix) {
        Random randomNumber = new Random();
        int index;
        do {
            index = randomNumber.nextInt(9);
            if (isCellEmpty(index)) {
                this.matrix[index] = symbol;
                opponentPlayer();
                break;
            }
        } while (true);
    }

    public static int coordinatesToBoardIndex(int column, int row) {
        int x = column - 1;
        int y = 3 - row;

        return y * 3 + x;
    }

    public void findWinner() {
        for (int[] line : victory) {
            if (matrix[line[0]] == matrix[line[1]] && matrix[line[1]] == matrix[line[2]]) {
                if (matrix[line[0]] == 'X') {
                    gameStatus = CheckState.X_WINS;
                } else if (matrix[line[0]] == 'O') {
                    gameStatus = CheckState.O_WINS;
                } else {
                    gameStatus = CheckState.NOT_FINISHED;
                }
            }
        }

        if (!isAnyMoveLeft(matrix)) {
            gameStatus = CheckState.DRAW;
        }

        if (gameStatus == CheckState.X_WINS || gameStatus == CheckState.O_WINS || gameStatus == CheckState.DRAW) {
            System.out.println(gameStatus.getMessage());
            game.startGame();
        }
    }

    public boolean likelyToWin() {

        for (int i = 0; i < 9; i += 3) {
            if (symbol == matrix[i] && symbol == matrix[i + 1] && (isCellEmpty(i + 2))) {
                matrix[i + 2] = symbol;
                return true;
            } else if (symbol == matrix[i]   && symbol == matrix[i + 2] && (isCellEmpty(i + 1))) {
                matrix[i + 1] = symbol;
                return true;
            } else if (symbol == matrix[i + 1]   && symbol == matrix[i + 2]  && (isCellEmpty(i))) {
                matrix[i] = symbol;
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (symbol == matrix[i]   && symbol == matrix[i + 3] && (isCellEmpty(i + 6))) {
                matrix[i + 6] = symbol;
                return true;
            } else if (symbol == matrix[i] && symbol == matrix[i + 6] && (isCellEmpty(i + 3))) {
                matrix[i + 3] = symbol;
                return true;
            } else if (symbol == matrix[i + 3] && symbol == matrix[i + 6] && (isCellEmpty(i))) {
                matrix[i] = symbol;
                return true;
            }
        }
        int i = 0;
        if (symbol == matrix[i] && symbol == matrix[i + 4] && (isCellEmpty(i + 8))) {
            matrix[i + 8] = symbol;
            return true;
        } else if (symbol == matrix[i] && symbol == matrix[i + 8] && (isCellEmpty(i + 4))) {
            matrix[i + 4] = symbol;
            return true;
        } else if (symbol == matrix[i + 4]  && symbol == matrix[i + 8] && (isCellEmpty(i))) {
            matrix[i] = symbol;
            return true;
        }
        i = 2;
        if (symbol == matrix[i] && symbol == matrix[i + 2] && (isCellEmpty(i + 4))) {
            matrix[i + 4] = symbol;
            return true;
        } else if (symbol == matrix[i] && symbol == matrix[i + 4] && (isCellEmpty(i + 2))) {
            matrix[i + 2] = symbol;
            return true;
        } else if (symbol == matrix[i + 2] && symbol == matrix[i + 4]  && (isCellEmpty(i))) {
            matrix[i] = symbol;
            return true;
        }
        return false;
    }

    public boolean likelyToLoose() {
        opponentPlayer();
        for (int i = 0; i < 9; i += 3) {
            if (symbol == matrix[i] && symbol == matrix[i + 1]  && (isCellEmpty(i + 2))) {
                opponentPlayer();
                matrix[i + 2] = symbol;
                return true;
            } else if (symbol == matrix[i] && symbol == matrix[i + 2] && (isCellEmpty(i + 1))) {
                opponentPlayer();
                matrix[i + 1] = symbol;
                return true;
            } else if (symbol == matrix[i + 1] && symbol == matrix[i + 2] && (isCellEmpty(i))) {
                opponentPlayer();
                matrix[i] = symbol;
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (symbol == matrix[i] && symbol == matrix[i + 3] && (isCellEmpty(i + 6))) {
                opponentPlayer();
                matrix[i + 6] = symbol;
                return true;
            } else if (symbol == matrix[i] && symbol == matrix[i + 6] && (isCellEmpty(i + 3))) {
                opponentPlayer();
                matrix[i + 3] = symbol;
                return true;
            } else if (symbol == matrix[i + 3] && symbol == matrix[i + 6] && (isCellEmpty(i))) {
                opponentPlayer();
                matrix[i] = symbol;
                return true;
            }
        }
        int i = 0;
        if (symbol == matrix[i] && symbol == matrix[i + 4] && (isCellEmpty(i + 8))) {
            opponentPlayer();
            matrix[i + 8] = symbol;
            return true;
        } else if (symbol == matrix[i] && symbol == matrix[i + 8] && !(isCellEmpty(i + 4))) {
            opponentPlayer();
            matrix[i + 4] = symbol;
            return true;
        } else if (symbol == matrix[i + 4] && symbol == matrix[i + 8] && (isCellEmpty(i))) {
            opponentPlayer();
            matrix[i] = symbol;
            return true;
        }
        i = 2;
        if (symbol == matrix[i] && symbol == matrix[i + 2] && (isCellEmpty(i + 4))) {
            opponentPlayer();
            matrix[i + 4] = symbol;
            return true;
        } else if (symbol == matrix[i] && symbol == matrix[i + 4] && (isCellEmpty(i + 2))) {
            opponentPlayer();
            matrix[i + 2] = symbol;
            return true;
        } else if (symbol == matrix[i + 2] && symbol == matrix[i + 4] && (isCellEmpty(i))) {
            opponentPlayer();
            matrix[i] = symbol;
            return true;
        }
        opponentPlayer();
        return false;
    }

    public static boolean isAnyMoveLeft(char[] matrix) {
        for (int i = 0; i < 9; i++) {
            if ('X' == matrix[i] ^ 'O' == matrix[i]) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    public int bestMove() {
        int bestVal = -1000;
        int bestMove = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.

        for (int i = 0; i < 9; i++) {
            // Check if cell is empty
            if (isCellEmpty(i)) {

                // Make the move
                matrix[i] = symbol;

                // compute evaluation function for this
                // move.
                int moveVal = minimax(0, false);

                // Undo the move
                matrix[i] = ' ';

                // If the value of the current move is
                // more than the best value, then update
                // best/
                if (moveVal > bestVal) {
                    bestMove = i;
                    bestVal = moveVal;
                }
            }
        }
        return bestMove;
    }

    public int evaluate(char[] matrix) {

        char currentPlayer = symbol;
        char opponentSymbol = otherPlayer();
        for (int i = 0; i < 3; i++) {
            if (matrix[i] == matrix[i + 3] && matrix[i] == matrix[i + 6]) {
                if (currentPlayer == matrix[i]) {
                    return 10;
                } else if (opponentSymbol == matrix[i]) {
                    return -10;
                }
            }
        }
        for (int i = 0; i < 8; i += 3) {
            if (matrix[i] == matrix[i + 1] && matrix[i] == matrix[i + 2]) {
                if (currentPlayer == matrix[i]) {
                    return 10;
                } else if (opponentSymbol == matrix[i]) {
                    return -10;
                }
            }
        }
        if (matrix[0] == matrix[4] && matrix[0] == matrix[8] || matrix[2] == matrix[4] && matrix[2] == matrix[6]) {
            if (currentPlayer == matrix[4]) {
                return 10;
            } else if (opponentSymbol == matrix[4]) {
                return -10;
            }
        }
        return 0;
    }

    public int minimax(int depth, Boolean isMaxValue) {

        int score = evaluate(matrix);

        if (score == 10) {
            return score;
        }
        if (score == -10) {
            return score;
        }
        if (!isAnyMoveLeft(matrix)) {
            return 0;
        }

        if (isMaxValue) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 9; i++) {

                // Check if cell is empty
                if (isCellEmpty(i)) {
                    {
                        // Make the move
                        matrix[i] = symbol;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(depth + 1, !isMaxValue));

                        // Undo the move
                        matrix[i] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 9; i++) {
                // Check if cell is empty
                if (isCellEmpty(i)) {
                    {
                        // Make the move
                        matrix[i] = otherPlayer();

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.min(best, minimax(depth + 1, !isMaxValue));

                        // Undo the move
                        matrix[i] = ' ';
                    }
                }
            }
            return best;
        }
    }
}