package devel.android_everything_test;


import android.util.Log;

import processing.core.PApplet;
import processing.core.PFont;

public class WallpaperSketch extends PApplet {
  String TAG = "WallpaperSketch";
  int delay = 5000;
  PFont fontA;
  int r, g, b;


  public void settings() {
    fullScreen(P2D);
  }

  public void setup() {
    Log.d(TAG, "calling setup() for " + this);
    frameRate(15);
//    orientation(PORTRAIT);
//    fontA = loadFont("ArialMT-48.vlw"); //replace with any font created using create font tool
//    fill(color(0,0,0,255));
//    textSize(48);
    r = (int)random(255);
    g = (int)random(255);
    b = (int)random(255);
  }

  public void draw() {
    background(r, g, b);
    strokeWeight(5);
    line(0, 0, mouseX, mouseY);
    line(width, 0, mouseX, mouseY);
    line(0, height, mouseX, mouseY);
    line(width, height, mouseX, mouseY);
//    text("WALLPAPER " + frameCount,80,80,width,100);
    float x = random(0, width);
    float y = random(0, height);
    ellipse(x, y, 50, 50);

//    if (frameCount % 15 == 0) System.out.println("draw " + this);

//    System.err.println("wallpaper: " + frameCount + " " + mouseX + " " + mouseY);


//    if (millis()>delay) {
//      textFont(fontA, 48);
//    }
//    text("WALLPAPER",80,80,width,100);
//    pushStyle();
//    fill(color(255,0,0,50));
//    ellipse(displayWidth*0.48f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
//    fill(color(0,0,255,50));
//    ellipse(displayWidth*0.53f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
//    popStyle();
  }

  public void mousePressed() {
//    frameRate(5);
  }
}