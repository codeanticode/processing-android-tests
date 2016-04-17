package devel.android_everything_test;


import processing.core.PApplet;
import processing.core.PFont;

public class WallpaperSketch extends PApplet {
  int delay = 5000;
  PFont fontA;

  public void settings() {
    fullScreen(P2D);
  }

  public void setup() {
    frameRate(15);
//    orientation(PORTRAIT);
//    fontA = loadFont("ArialMT-48.vlw"); //replace with any font created using create font tool
//    fill(color(0,0,0,255));
//    textSize(48);
  }

  public void draw() {
    background(150, 50, 50);
    line(0, 0, mouseX, mouseY);
    line(width, 0, mouseX, mouseY);
    line(0, height, mouseX, mouseY);
    line(width, height, mouseX, mouseY);
//    text("WALLPAPER " + frameCount,80,80,width,100);
    float x = random(0, width);
    float y = random(0, height);
    ellipse(x, y, 50, 50);
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
    frameRate(5);
  }
}