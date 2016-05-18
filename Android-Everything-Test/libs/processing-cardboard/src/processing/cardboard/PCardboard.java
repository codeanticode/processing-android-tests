package processing.cardboard;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.Eye;

import android.content.Intent;
import android.util.DisplayMetrics;
import processing.android.AppComponent;
import processing.core.PApplet;

// http://pastebin.com/6wPgFYhq
public class PCardboard extends CardboardActivity implements AppComponent {
  public static final String STEREO = "processing.cardboard.PGraphicsCardboardStereo";
  public static final String MONO = "processing.cardboard.PGraphicsCardboardMono";

  public static final int LEFT      = Eye.Type.LEFT;
  public static final int RIGHT     = Eye.Type.RIGHT;
  public static final int MONOCULAR = Eye.Type.MONOCULAR;

  static public final int CARDBOARD = 3;
  
  private DisplayMetrics metrics;
  private PApplet sketch;

  public PCardboard() {

  }

  public PCardboard(PApplet sketch) {
    this.sketch = sketch;
  }

  public void initDimensions() {
    metrics = getResources().getDisplayMetrics();
  }

  public int getWidth() {
    return metrics.widthPixels;
  }

  public int getHeight() {
    return metrics.heightPixels;
  }

  public int getKind() {
      return CARDBOARD;
  }

  public void setSketch(PApplet sketch) {
    this.sketch = sketch;
  }

  public void init(PApplet sketch) {
    setSketch(sketch);
    if (sketch != null) {
      //cardboardView.setChromaticAberrationCorrectionEnabled(true);
      //cardboardView.setVRModeEnabled(false); // sets Monocular mode
      sketch.initSurface(PCardboard.this, null);


      // Don't start Papplet's animation thread bc cardboard will drive rendering
      // continuously
      sketch.startSurface();
    }
  }

  /*
   * Called with the activity is first created.
   */
//  @SuppressWarnings("unchecked")
//  @Override
//  public void onCreate(Bundle savedInstanceState) {
//     super.onCreate(savedInstanceState);
//
//  }

  @Override
  public void startActivity(Intent intent) {
    // TODO Auto-generated method stub
  }

  @Override
  public void onResume() {
    super.onResume();
    sketch.onResume();
  }


  @Override
  public void onPause() {
    super.onPause();
    sketch.onPause();
  }


  @Override
  public void onDestroy() {
    sketch.onDestroy();
    super.onDestroy();
  }


  @Override
  public void onStart() {
    super.onStart();
    sketch.onStart();
  }


  @Override
  public void onStop() {
    sketch.onStop();
    super.onStop();
  }

  public void requestDraw() {
  }

  public boolean canDraw() {
    return true;
  }
}
