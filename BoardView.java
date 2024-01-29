import processing.core.PApplet;
import java.awt.Color;

public class BoardView extends View {
    // Private final fields in the BoardView class
    private final Controller control;
    private final int size;        // model.getSize()
    private final int rowHeight;   // height/size
    private final int colWidth;    // width/size

    // Constructor for the BoardView class
    public BoardView(PApplet pg, Model model, Controller control, int originX, int originY, int width, int height) {
        super(pg, model, originX, originY, width, height);
        this.control = control;
        this.size = model.getSize();
        this.rowHeight = height / size;
        this.colWidth = width / size;
    }

    // Override renderView method as needed for BoardView
    @Override
    protected void renderView() {
        // Loop through each position on the board and render it
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                renderPosition(row, col);
            }
        }
    }

    // Method to render a position on the board
    private void renderPosition(int row, int col) {
        switch (model.getOccupant(row, col)) {
            case NONE:
                pg.fill(175);
                break;
            case WHITE:
                pg.fill(255);
                break;
            case BLUE:
                pg.fill(100, 100, 255);
                break;
        }

        // Draw a rectangle at the specified position
        pg.rect(col * colWidth, row * rowHeight, colWidth, rowHeight);

        // Draw text over the rectangle
        pg.fill(0);
        pg.text(String.format("%d,%d", row, col), (col + 0.5F) * colWidth, (row + 0.5F) * rowHeight);
    }

    // Override mousePressed method as needed for BoardView
    @Override
    public boolean mousePressed() {
        // Determine the position of the mouse relative to the BoardView region
        float relativeMouseX = pg.mouseX - originX;
        float relativeMouseY = pg.mouseY - originY;

        // Compute the row and column based on the mouse position and rowHeight, colWidth
        int row = (int) (relativeMouseY / rowHeight);
        int col = (int) (relativeMouseX / colWidth);

        // Check if the click is out of bounds
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false; // Click is out of bounds, return false
        }

        // Pass the coordinates to the Controller and return true
        control.evalMove(row, col);
        return true;
    }

    
}
