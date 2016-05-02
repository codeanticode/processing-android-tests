package devel.watchfacetest;

import processing.core.PApplet;
import processing.core.PConstants;

public class WatchFaceSketch extends PApplet {
    float angle = 0;

    public void settings() {
        fullScreen();
    }

    public void setup() {
//        background(60, 60, 160);
        frameRate(10);
    }

    public void draw() {
//        background(random(255), random(255), random(255));
//        println("Processing draw:" + frameCount);
        background(60, 60, 160);
        fill(180, 150);
//        rect(random(0, width), random(0, height), 40, 40);
//        line(0, 0, mouseX, mouseY);

//        fill(200);

        translate(mouseX, mouseY);
        rotate(angle);
        rect(-25, 25, 50, 50);
        angle += 0.01;

        println("FRAMECOUNT: " + frameCount);
    }
}