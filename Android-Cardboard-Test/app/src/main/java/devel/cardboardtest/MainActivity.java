package devel.cardboardtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import processing.cardboard.PCardboard;

public class MainActivity extends PCardboard {
    //private static String TAG = "CARDBOARD_ACTIVITY";
//CardboardView cardboardView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Sketch sketch = new Sketch();
        setSketch(sketch);
        init(sketch);
        setConvertTapIntoTrigger(true);

//    System.err.println(">>>>>>>>>>>>>LAUNCHING");
//    StereoSketch sketch = new StereoSketch();
//    setSketch(sketch);
//    SurfaceView surfaceView = sketch.surface.getSurfaceView();
//    cardboardView = (CardboardView) surfaceView;
//    setCardboardView(cardboardView);
//    cardboardView.setDistortionCorrectionEnabled(false);
//    cardboardView.setChromaticAberrationCorrectionEnabled(false);
//    setConvertTapIntoTrigger(true);
//    System.err.println(">>>>>>>>>>>>>LAUNCHED");
    }
}