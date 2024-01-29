// import processing.core.PApplet;
// import java.util.ArrayList;
//
// public class Othello extends PApplet {

  // An implementation of MVC requires three components: the Model,
  // one or more View(s), and the Controller.
  //
    private Model theModel;
    ArrayList<View> views;
    private Controller theController;
// @override. The method settings() establishes the sketch window.
  //
    public void settings() {
      size(800,800);
      frameRate=2;
    }
 // @override. The method setup() can be thought of as a constructor
  // for the Othello MVC class. 
  //
    public void setup() {
      // these are configuration settings for various graphical items we
    // will use to render View presentations.
    //
        textAlign(CENTER, CENTER);  // text aligned right/left, up/down
        textSize(20);               // font-size (in points)
        ellipseMode(CENTER);        // coordinate specifies center of ellipse
        rectMode(CORNER);
        
        // the Model is constructed first...
        theModel = new Model(8); 
        // because it is needed by the controller...
        theController = new Controller(theModel);
    // and all views need both Model and Controler. We will build a 
    // list to contain the views. This is mostly a demonstation of a
    // [should be] familiar design pattern: a container of Views.
    //
    // a short integration test
        theController.evalMove(1,1);
        theController.evalMove(1,2);
        System.out.println(theModel);
        
        views = new ArrayList<>();
        views.add(new BoardView(this, theModel, theController, 0, 0, width, height-100));
        views.add(new ScoreView(this, theModel, 0, height-100, width, 100));                        
        for(View v : views)
          theModel.addView(v);
    }
    
    public void draw() {
        for (View view : views) {
          view.renderView();
    }
       println(theModel.toString());
  }

    public void mousePressed() {
        for(View v : views)
          if (v.mousePressed())
            return;
   }
   // the end of the "class" Othello
// }
