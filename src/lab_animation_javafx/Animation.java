package lab_animation_javafx;

//T Harvey
// Loosely based on https://github.com/tutsplus/Introduction-to-JavaFX-for-Game-Development/blob/master/Example3.java 

import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

// Animation of Orc walking
public class Animation extends Application {
    final int canvasCount = 10;
    int picInd = 0;
    double xloc = 0;
    double yloc = 0;
    final double xIncr = 8;
    final double yIncr = 2;
    final static int canvasWidth = 500;
    final static int canvasHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

    // TODO: Change the code so that at least eight orc animation pngs are loaded

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Orc");
        
        boolean flag1 = true;
        boolean flag2 = true;

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ArrayList<Image> orc_img = createImage();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1e9; 
                
                if(xloc == canvasWidth) {
                	flag1 = false;
                }
                else if(xloc == 0) {
                	flag1 = true;
                }
                if(yloc == canvasHeight) {
                	yloc += yIncr;
                } else if(yloc == 0) {
                	yloc += yIncr;
                }

                if(flag1 == true) {
                	xloc += xIncr;
                } else {
                	xloc -= xIncr;
                }
                
                if(flag2 == true ) {
                	yloc += yIncr;
                } else {
                	yloc -= yIncr;
                }
                //xloc += xIncr;
                //yloc += yIncr;

                // Clear the canvas
                gc.clearRect(0, 0, canvasWidth, canvasHeight);

                // draw the subimage from the original png to animate the orc's motion
                gc.drawImage(orc_img.get(0), imgWidth*picInd, 0, imgWidth, imgHeight,
                                    xloc, yloc, imgWidth, imgHeight);
                picInd = (picInd + 1) % canvasCount;
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
                //Be sure that animation picture direction matches what is happening on screen.
            }
        }.start();
        theStage.show();
    }

    //Read image from file and return
    private ArrayList<Image> createImage() {
    	ArrayList<Image> imgs = new ArrayList();
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_die_east.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_die_south.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_die_west.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_die_north.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_fire_east.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_fire_west.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_fire_northeast.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_fire_northwest.png"));
    	imgs.add(new Image("lab_animation_javafx\\images\\orc_idle_ewns.png"));
       
    	
    	//Image  img = new Image("lab_animation_javafx\\images\\orc_die_east.png");
        return imgs;   	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}


