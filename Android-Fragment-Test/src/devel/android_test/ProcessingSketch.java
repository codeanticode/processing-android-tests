package devel.android_test;

import processing.core.PApplet;
import processing.core.PFont;

public class ProcessingSketch extends PApplet {
  int delay = 5000;
  PFont fontA;
  
  public void settings() {    
    fullScreen(P2D);    
  }
  
  public void setup() {
    orientation(PORTRAIT);
    fontA = loadFont("ArialMT-48.vlw"); //replace with any font created using create font tool    
    textSize(48);    
  }
  
  public void draw() {
    background(255);
//    if (millis()>delay) {
//      textFont(fontA, 48);
//    }
    fill(color(0));
    text("FPS " + frameRate,80,80);
//    pushStyle();
//    fill(color(255,0,0,50));
//    ellipse(displayWidth*0.48f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
//    fill(color(0,0,255,50));
//    ellipse(displayWidth*0.53f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
//    popStyle();
    fill(color(0,0,255,50));
    ellipse(mouseX, mouseY, 100, 100);
  }  
}
