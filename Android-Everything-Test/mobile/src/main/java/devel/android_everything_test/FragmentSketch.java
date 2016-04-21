package devel.android_everything_test;

import android.util.Log;
import processing.core.PApplet;
import processing.core.PFont;

public class FragmentSketch extends PApplet {
  String TAG = "FragmentSketch";
  boolean rotate = true;
  float angle;

  public void settings() {
    fullScreen(P3D);
  }

  public void setup() {
    frameRate(15);
  }

  public void draw() {
    background(157);
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