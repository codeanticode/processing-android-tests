package devel.cardboardtest;

import processing.core.PApplet;
import processing.cardboard.*;

public class Sketch extends PApplet {
    public void settings() {
        fullScreen(PGraphicsCardboard.RENDERER);
    }

    public void setup() {

    }

    public void draw() {
      background(150, 0, 0);
        translate(width / 2, height / 2);
        rotateX(frameCount * 0.01f);
        rotateY(frameCount * 0.01f);
        box(200);
        println(frameCount);
    }
}