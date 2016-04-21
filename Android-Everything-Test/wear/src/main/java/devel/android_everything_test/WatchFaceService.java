package devel.android_everything_test;

import processing.app.PWatchFace;
import processing.core.PApplet;

public class WatchFaceService extends PWatchFace {
    public WatchFaceService() {
        super();
        PApplet sketch = new WatchFaceSketch();
        setSketch(sketch);
    }

}
