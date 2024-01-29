import java.util.List;
import java.util.ArrayList;

public class Model {
    public enum Player {
        NONE, BLUE, WHITE
    }

    private Position[][] theBoard;
    private Player currentPlayer;
    private List<View> views;

    public Model(int size) {
        theBoard = new Position[size][size];
        views = new ArrayList<>();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                theBoard[row][col] = new Position();
            }
        }

        resetModel();  // Initialize the model
    }

    // Method to reset the game board and set initial player.
    public void resetBoard() {
        int numRows = theBoard.length;
        int numCols = theBoard[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                theBoard[row][col].setPlayer(Player.NONE);
            }
        }

        // Set initial player positions
        setOccupant(numRows / 2 - 1, numCols / 2 - 1, Player.BLUE);
        setOccupant(numRows / 2 - 1, numCols / 2, Player.WHITE);
        setOccupant(numRows / 2, numCols / 2 - 1, Player.WHITE);
        setOccupant(numRows / 2, numCols / 2, Player.BLUE);

        currentPlayer = Player.BLUE;
    }

    // Method to represent the model as a string.
    public String toString() {
        int size = theBoard.length;
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                result.append(theBoard[row][col].toString());
            }
            result.append("\n");
        }

        return result.toString();
    }

    // Method to count the number of pieces on the board for a given player.
    public int getCount(Player player) {
        int count = 0;

        for (Position[] row : theBoard) {
            for (Position position : row) {
                if (position.getPlayer() == player) {
                    count++;
                }
            }
        }

        return count;
    }

    // Getter method for the board size.
    public int getSize() {
        return theBoard.length;
    }

    // Getter method for the current player.
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static class Position {
        private Player occupiedBy;

        public Position() {
            occupiedBy = Player.NONE;
        }

        public Player getPlayer() {
            return occupiedBy;
        }

        public void setPlayer(Player player) {
            occupiedBy = player;
        }

        public String toString() {
            switch (occupiedBy) {
                case BLUE:
                    return "B";
                case WHITE:
                    return "W";
                default:
                    return ".";
            }
        }
    }

    // Getter method for the occupant of a specific position on the board.
    public Player getOccupant(int row, int col) {
        return theBoard[row][col].getPlayer();
    }

    // Setter method to set the occupant of a specific position on the board.
    public void setOccupant(int row, int col, Player player) {
        theBoard[row][col].setPlayer(player);
    }

    // Method to check if a specific position on the board is occupied.
    public boolean isOccupied(int row, int col) {
        return theBoard[row][col].getPlayer() != Player.NONE;
    }

    // Method to check if a specific position on the board is occupied by a given player.
    public boolean isOccupiedBy(int row, int col, Player player) {
        return theBoard[row][col].getPlayer() == player;
    }

    // Method to reset the model to its initial state.
    public void resetModel() {
        resetBoard();
        currentPlayer = Player.BLUE;
    }

    public void takeTurn() {
        exchangeTurnPlayer();
        updateViews();
    }

    public void exchangeTurnPlayer() {
        currentPlayer = (currentPlayer == Player.BLUE) ? Player.WHITE : Player.BLUE;
    }

    public void addView(View view) {
        views.add(view);
        view.updateView();
    }

    // This method is primarily invoked by the Model to direct the View(s) to update their
    public void updateViews() {
        for (View view : views) {
            view.updateView();
        }
    }
}
