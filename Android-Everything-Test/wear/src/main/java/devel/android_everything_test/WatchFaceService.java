package devel.android_everything_test;

import processing.core.PApplet;

public class WatchFaceService extends processing.app.PWatchFaceGLES {
    public WatchFaceService() {
        super();
        PApplet sketch = new WatchFaceSketch();
        setSketch(sketch);
    }

}
