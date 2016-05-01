package devel.wallpapertest;

import processing.android.PWallpaper;
import processing.core.PApplet;

public class WallpaperService extends PWallpaper {
    @Override
    public PApplet createSketch() {
      PApplet sketch = new WallpaperSketch();
      return sketch;
    }
}
