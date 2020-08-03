package tictactoe.player;

import tictactoe.MatrixBoard;

public class EasyAI implements Player {

    @Override
    public void setMatrixBySymbol(MatrixBoard matrix) {
        matrix.randomMove(matrix);
        System.out.println("Making move level \"easy\"");
    }
}
