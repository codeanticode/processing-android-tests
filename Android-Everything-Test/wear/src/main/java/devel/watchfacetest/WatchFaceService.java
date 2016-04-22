package devel.watchfacetest;

import processing.app.PWatchFaceGLES;
import processing.core.PApplet;

public class WatchFaceService extends PWatchFaceGLES {
    public WatchFaceService() {
        super();
        PApplet sketch = new WatchFaceSketch();
        setSketch(sketch);
    }

}
