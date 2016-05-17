package devel.cardboardtest;

import processing.core.PApplet;
import processing.cardboard.*;

public class CardboardSketch extends PApplet {
    public void settings() {
        fullScreen(PCardboard.STEREO);
    }

    public void setup() {

    }

    public void draw() {
      background(157);
      lights();
//        translate(width / 2, height / 2);
//        rotateX(frameCount * 0.01f);
//        rotateY(frameCount * 0.01f);
        box(250);
//        println(frameCount);
    }
}
