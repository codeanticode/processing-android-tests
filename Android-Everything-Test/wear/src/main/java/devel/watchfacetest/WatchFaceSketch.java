package devel.watchfacetest;

import processing.core.PApplet;
import processing.core.PConstants;

public class WatchFaceSketch extends PApplet {
    public void settings() {
        fullScreen(PConstants.P2D);
    }

    public void setup() {
        background(160, 60, 60);
        frameRate(10);
    }

    public void draw() {
//        background(random(255), random(255), random(255));
//        println("Processing draw:" + frameCount);
        fill(180, 150);
//        rect(random(0, width), random(0, height), 40, 40);
//        line(0, 0, mouseX, mouseY);

//        fill(200);
        rect(mouseX, mouseY, 40, 40);
//        println("MOUSE POSITION: " + mouseX + " " + mouseY);
    }
}