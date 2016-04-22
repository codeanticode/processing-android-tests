package devel.wallpapertest;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class WallpaperService extends PWallpaper {
    @Override
    public PApplet createSketch() {
      PApplet sketch = new WallpaperSketch();
      return sketch;
    }
}
