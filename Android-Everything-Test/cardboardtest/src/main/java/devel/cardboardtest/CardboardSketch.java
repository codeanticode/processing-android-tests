package devel.cardboardtest;

import processing.core.PApplet;
import processing.cardboard.*;

public class CardboardSketch extends PApplet {
    public void settings() {
//      fullScreen(PCardboard.STEREO);
      fullScreen(PCardboard.MONO);
    }

    public void setup() {

    }

    public void draw() {
//      PGraphicsCardboard pgc = (PGraphicsCardboard)g;
//      if (pgc.eyeType == PCardboard.LEFT) {
//        background(200, 50, 50);
//      } else if (pgc.eyeType == PCardboard.RIGHT) {
//        background(50, 50, 200);
//      } else if (pgc.eyeType == PCardboard.MONOCULAR) {
//        background(50, 200, 50);
//      }

      background(157);
      lights();
      translate(width / 2, height / 2);

      rotateX(frameCount * 0.01f);
      rotateY(frameCount * 0.01f);
      box(500);
    }
}
