package tictactoe.player;

import tictactoe.MatrixBoard;

public class MediumAI implements Player {

    @Override
    public void setMatrixBySymbol(MatrixBoard matrix) {
        System.out.println("Making move level \"medium\"");
        if (matrix.likelyToWin()) {
            matrix.opponentPlayer();
        } else if (matrix.likelyToLoose()) {
            matrix.opponentPlayer();
        } else {
            matrix.randomMove(matrix);
        }
    }
}