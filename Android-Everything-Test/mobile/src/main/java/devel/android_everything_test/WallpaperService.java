package devel.android_everything_test;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class WallpaperService extends PWallpaper {
    @Override
    public PApplet createSketch() {
      PApplet sketch = new WallpaperSketch();
      return sketch;
    }
}
