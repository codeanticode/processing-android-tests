package devel.fragmenttest;

import android.util.Log;
import processing.core.PApplet;
import processing.core.PFont;

public class FragmentSketch extends PApplet {
  String TAG = "FragmentSketch";
  boolean rotate = true;
  float angle;

  public void settings() {
    fullScreen(P3D);
//    size(800, 1280, P3D);
  }

  public void setup() {
    frameRate(15);
  }

  public void draw() {
    background(157);
    lights();
    translate(width / 2, height / 2);
    rotateX(angle);
    rotateY(angle);
    box(500);
    if (rotate) {
      angle += 0.01f;
    }
  }

  public void mousePressed() {
      rotate = !rotate;
  }
}
