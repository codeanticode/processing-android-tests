package devel.carboard;

import android.os.Bundle;
import android.view.SurfaceView;
import com.google.vrtoolkit.cardboard.CardboardView;
import processing.app.PCardboard;

public class MainActivity extends PCardboard {
  private static String TAG = "CARDBOARD_ACTIVITY";    
  CardboardView cardboardView;
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);  
      System.err.println(">>>>>>>>>>>>>LAUNCHING");
      StereoSketch sketch = new StereoSketch();
      setSketch(sketch);          
      SurfaceView surfaceView = sketch.surface.getSurfaceView();
      cardboardView = (CardboardView) surfaceView;
      setCardboardView(cardboardView);
      cardboardView.setDistortionCorrectionEnabled(false);
      cardboardView.setChromaticAberrationCorrectionEnabled(false);
      setConvertTapIntoTrigger(true);
      System.err.println(">>>>>>>>>>>>>LAUNCHED");    
  }
}





