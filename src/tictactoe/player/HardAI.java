package tictactoe.player;

import tictactoe.MatrixBoard;

public class HardAI implements Player {

    @Override
    public void setMatrixBySymbol(MatrixBoard matrix) {
        System.out.println("Making move level \"hard\"");
        matrix.setCell(matrix.bestMove());
        matrix.opponentPlayer();
    }
}
