package devel.watchfacetest;

import processing.core.PApplet;

public class Sketch extends PApplet {
    public void settings() {
        fullScreen(P2D);
    }

    public void setup() {
        background(160, 160, 160);
    }

    public void draw() {
//        background(random(255), random(255), random(255));
//        println("Processing draw:" + frameCount);
        fill(180, 200);
//        rect(random(0, width), random(0, height), 40, 40);
//        line(0, 0, mouseX, mouseY);

        fill(200);
        ellipse(mouseX, mouseY, 40, 40);
//        println("MOUSE POSITION: " + mouseX + " " + mouseY);
    }
}