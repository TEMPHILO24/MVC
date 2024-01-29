import java.util.Scanner;

public class Controller {
    private Model model;

    // Constructor
    public Controller(Model model) {
        this.model = model;
    }

    // Private method for evaluating a direction
    private boolean evalDownstream(int row, int col, int dRow, int dCol) {
        row += dRow;
        col += dCol;
        if (!isValidPosition(row, col)) {
            return false;
        }

        Model.Player currentPlayer = model.getCurrentPlayer();
        Model.Player opponent = getOpponent(currentPlayer);
        if (model.getOccupant(row, col) != opponent) {
            return false;
        }

        boolean foundPlayerDisc = false;
        while (isValidPosition(row, col) && model.getOccupant(row, col) == opponent) {
            row += dRow;
            col += dCol;
            foundPlayerDisc = true;
        }

        return isValidPosition(row, col) && foundPlayerDisc && model.getOccupant(row, col) == currentPlayer;
    }

    private void flipOpponentDiscs(int row, int col, int dRow, int dCol) {
        row += dRow;
        col += dCol;
        while (isValidPosition(row, col) && model.getOccupant(row, col) != model.getCurrentPlayer()) {
            model.setOccupant(row, col, model.getCurrentPlayer());
            row += dRow;
            col += dCol;
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < model.getSize() && col >= 0 && col < model.getSize();
    }

    private Model.Player getOpponent(Model.Player player) {
        return (player == Model.Player.BLUE) ? Model.Player.WHITE : Model.Player.BLUE;
    }

    // Public method for evaluating a move
    public void evalMove(int row, int col) {
        if (model.getOccupant(row, col) != Model.Player.NONE) {
            return;
        }

        boolean validMove = false;

        for (int dRow = -1; dRow < 2; dRow++) {
            for (int dCol = -1; dCol < 2; dCol++) {
                if (dRow != 0 || dCol != 0) {
                    if (evalDownstream(row, col, dRow, dCol)) {
                        flipOpponentDiscs(row, col, dRow, dCol);
                        validMove = true;
                    }
                }
            }
        }

        if (validMove) {
            model.setOccupant(row, col, model.getCurrentPlayer());
            model.takeTurn();
        }
    }
}
