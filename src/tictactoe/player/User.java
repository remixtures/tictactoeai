package tictactoe.player;

import tictactoe.MatrixBoard;

import java.util.Scanner;

public class User implements Player {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void setMatrixBySymbol(MatrixBoard matrix) {

        boolean wrongInput = true;

        do {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            if (input.length() < 3) {
                System.out.println("You should enter numbers!");
                wrongInput = true;
                continue;
            }

            int column;
            int row;

            try {
                column = Integer.parseInt(String.valueOf(input.charAt(0)));
                row = Integer.parseInt(String.valueOf(input.charAt(2)));
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                wrongInput = true;
                continue;
            }

            if (row < 1 || row > 3 || column < 1 || column > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                wrongInput = true;
                continue;
            }

            int index = MatrixBoard.coordinatesToBoardIndex(row, column);

            if (!matrix.isCellEmpty(index)) {
                System.out.println("This cell is occupied! Choose another one!");
                wrongInput = true;
            } else {
                matrix.setCell(index);
                break;
            }
        } while (wrongInput);
        matrix.opponentPlayer();
    }
}
