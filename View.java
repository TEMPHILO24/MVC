import java.awt.Color;
import processing.core.PApplet;

public abstract class View {
    // Protected final fields in the View class
    protected final PApplet pg;
    protected final Model model;
    protected final int originX;
    protected final int originY;
    protected final int width;
    protected final int height;

    // Constructor for the View class
    public View(PApplet pg, Model model, int originX, int originY, int width, int height) {
        this.pg = pg;
        this.model = model;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
    }

    // Abstract method to be implemented by extending classes
    protected abstract void renderView();

    // Final method providing a container for renderView
    protected final void updateView() {
        pg.translate(originX, originY); // move the effective origin
        pg.pushMatrix(); // never trust!
        renderView();
        pg.popMatrix(); // restore trust
        pg.translate(-originX, -originY); // restore the original origin
    }

    // Default implementation for mousePressed
    protected boolean mousePressed() {
        // Default implementation returns false
        return false;
    }

    // Default implementation for keyPressed
    protected boolean keyPressed() {
        // Default implementation returns false
        return false;
    }

   
}
