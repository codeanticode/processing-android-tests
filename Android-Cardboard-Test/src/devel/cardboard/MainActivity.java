package devel.cardboard;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.google.vrtoolkit.cardboard.CardboardView;
import processing.app.PFragment;
import processing.cardboard.PCardboard;
import processing.core.PApplet;

/*
public class MainActivity extends Activity {
  private static final String MAIN_FRAGMENT_TAG = "main_fragment";
  PFragment fragment;    
  int viewId = 0x1000;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Window window = getWindow();
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, 
                      WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
      window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
      FrameLayout frame = new FrameLayout(this);
      frame.setId(viewId);
      setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT, 
                                             LayoutParams.MATCH_PARENT));
      PApplet sketch = new StereoSketch();
      
      
      if (savedInstanceState == null) {
          fragment = new PFragment(sketch);
          FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.add(frame.getId(), fragment, MAIN_FRAGMENT_TAG).commit();
      } else {
          fragment = (PFragment) getFragmentManager().findFragmentByTag(MAIN_FRAGMENT_TAG);
          fragment.setSketch(sketch);
      }
      
      
  }
  @Override
  public void onBackPressed() {
      fragment.onBackPressed();
      super.onBackPressed();
  }
}
*/

public class MainActivity extends PCardboard {
//  private static String TAG = "CARDBOARD_ACTIVITY";    
  CardboardView cardboardView;
  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);  
      StereoSketch sketch = new StereoSketch();
      setSketch(sketch);
      init(sketch);
      setConvertTapIntoTrigger(true);
      
//      System.err.println(">>>>>>>>>>>>>LAUNCHING");
//      StereoSketch sketch = new StereoSketch();
//      setSketch(sketch);          
//      SurfaceView surfaceView = sketch.surface.getSurfaceView();
//      cardboardView = (CardboardView) surfaceView;
//      setCardboardView(cardboardView);
//      cardboardView.setDistortionCorrectionEnabled(false);
//      cardboardView.setChromaticAberrationCorrectionEnabled(false);
//      setConvertTapIntoTrigger(true);
//      System.err.println(">>>>>>>>>>>>>LAUNCHED");    
  }
}





