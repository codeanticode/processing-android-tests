package devel.watchfacetest;

import processing.android.PWatchFaceGLES;
import processing.android.PWatchFaceCanvas;
import processing.core.PApplet;

public class WatchFaceService extends PWatchFaceCanvas {
//public class WatchFaceService extends PWatchFaceGLES {
    public WatchFaceService() {
        super();
        PApplet sketch = new WatchFaceSketch();
        setSketch(sketch);
    }

}
