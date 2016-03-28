package devel.android_wallpaper_test2;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class MainService extends PWallpaper {
    public MainService() {
        super();
        PApplet sketch = new Sketch();
        setSketch(sketch);
    }
}
