package devel.android_everything_test;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class WallpaperService extends PWallpaper {
    public WallpaperService() {
        super();
    }

    public void onCreate() {
        PApplet sketch = new WallpaperSketch();
        setSketch(sketch);
    }
}
