import processing.core.PApplet;

public class ScoreView extends View {
    private int scoreBlue;
    private int scoreWhite;

    // Constructor for the ScoreView class
    public ScoreView(PApplet pg, Model model, int originX, int originY, int width, int height) {
        super(pg, model, originX, originY, width, height);
        // Additional initialization for ScoreView if needed
    }

    // Override renderView method as needed for ScoreView
    @Override
    protected void renderView() {
        // Optional: Translate to use (0,0) as the origin within this view
        pg.pushMatrix();
        pg.translate(originX, originY);

        // Clear the region with a filled rectangle (whitewashing)
        pg.fill(255);
        pg.rect(0, 0, width, height);

        // Display the count of occupied positions for each player
        pg.fill(255, 0, 0); // Red color for blue count
        pg.text("Occupied Blue: " + model.getCount(Model.Player.BLUE), 75, 40);

        pg.fill(0, 0, 255); // Blue color for white count
        pg.text("Occupied White: " + model.getCount(Model.Player.WHITE), 75, 60);

        // Display the current player
        String currentPlayerString = (model.getCurrentPlayer() == Model.Player.BLUE) ? "Blue" : "White";
        pg.fill(0, 255, 0); // Green color for current player
        pg.text("Current Player: " + currentPlayerString, 20, 80);

        // Optional: Translate back to the original coordinates
        pg.popMatrix();
    }

    // Method to update scores
    public void updateScores(int blue, int white) {
        scoreBlue = blue;
        scoreWhite = white;
    }
}
