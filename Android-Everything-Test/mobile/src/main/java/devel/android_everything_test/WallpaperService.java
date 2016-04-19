package devel.android_everything_test;

import android.util.Log;

import processing.app.PWallpaper;
import processing.core.PApplet;

public class WallpaperService extends PWallpaper {
    String TAG = "WallpaperService";

//    public WallpaperService() {
//        super();
//    }

//    public void onCreate() {
//      Log.d(TAG, "onCreate()");
//      createSketch();
//    }

    @Override
    public void createSketch() {
      PApplet sketch = new WallpaperSketch();
      System.out.println("created sketch " + sketch);
      setSketch(sketch);
    }
}
