package devel.carboard;

import processing.core.PApplet;

public class StereoSketch extends PApplet {
  public void settings() {    
    fullScreen(P2D);    
  }
  
  public void setup() {
  }
  
  public void draw() {
    background(150);
    translate(width/2, height/2);
    rotateX(frameCount * 0.01f);
    rotateY(frameCount * 0.01f);
    box(200);    
  }   
}
