package devel.fragmenttest;

import android.util.Log;
import processing.core.PApplet;
import processing.core.PFont;

public class FragmentSketch extends PApplet {
  String TAG = "FragmentSketch";
  boolean rotate = true;
  float angle;

  public void settings() {
//    fullScreen(P3D);
    size(1280, 800, P3D);
  }

  public void setup() {
    orientation(LANDSCAPE);
  }

  public void draw() {
    background(157);
    lights();
    translate(mouseX, mouseY);
    rotateX(angle);
    rotateY(angle);
    box(100);
    if (rotate) {
      angle += 0.01f;
    }
  }

  public void mousePressed() {
      rotate = !rotate;
  }
}
