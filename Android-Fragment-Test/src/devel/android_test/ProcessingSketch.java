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
    fill(color(0,0,0,255));
    textSize(48);    
  }
  
  public void draw() {
    background(255,255,255);
    if (millis()>delay) {
      textFont(fontA, 48);
    }
    text("Watch the circles",80,80,width,100);
    pushStyle();
    fill(color(255,0,0,50));
    ellipse(displayWidth*0.48f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
    fill(color(0,0,255,50));
    ellipse(displayWidth*0.53f,displayHeight*0.5f,displayWidth*0.2f,displayWidth*0.2f);
    popStyle();
  }  
}
