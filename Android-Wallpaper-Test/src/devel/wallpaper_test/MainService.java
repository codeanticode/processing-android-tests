package devel.wallpaper_test;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class MainService extends PWallpaper {
  public MainService() {
    super();
    PApplet sketch = new ProcessingSketch();
    setSketch(sketch);
  }
}
