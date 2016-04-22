package processing.cardboard;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.HeadMountedDisplayManager;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import processing.app.PContainer;
import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

// http://pastebin.com/6wPgFYhq
public class PCardboard extends CardboardActivity implements PContainer {
  static public final int CARDBOARD = 3;
  
  private DisplayMetrics metrics;
  private PApplet sketch;
//  private CardboardView cardboardView;

  public PCardboard() {

  }

  public PCardboard(PApplet sketch) {
    System.err.println("-----> PCardboard CONSTRUCTOR: " + sketch);
    this.sketch = sketch;
  }

  public void initDimensions() {
//    metrics = new DisplayMetrics();
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
      sketch.start();
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
}
