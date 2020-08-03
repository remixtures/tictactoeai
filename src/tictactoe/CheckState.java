package tictactoe;

public enum CheckState {

    NOT_FINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins");
    final String message;

    CheckState(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
