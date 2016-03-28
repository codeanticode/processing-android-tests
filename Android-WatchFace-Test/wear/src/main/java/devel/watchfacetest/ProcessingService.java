package devel.watchfacetest;


import processing.app.PWatchFace;
import processing.core.PApplet;

public class ProcessingService extends PWatchFace {
    public ProcessingService() {
        super();
        PApplet sketch = new Sketch();
        setSketch(sketch);
    }

}
