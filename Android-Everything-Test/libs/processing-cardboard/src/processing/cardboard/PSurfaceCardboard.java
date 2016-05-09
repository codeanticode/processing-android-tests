package processing.cardboard;

import java.io.File;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import processing.android.AppComponent;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.opengl.PGLES;
import processing.opengl.PGraphicsOpenGL;
import processing.opengl.PSurfaceGLES;
import android.view.Window;
import android.view.WindowManager;
import android.util.Log;
import android.view.SurfaceView;

public class PSurfaceCardboard extends PSurfaceGLES {
  private static final String TAG = "PSurfaceCardboard";

  protected GLCardboardSurfaceView glview;
  protected PGraphicsCardboard pgc;
  
  protected CardboardActivity cardboard;
//  protected AndroidCardboardRenderer cardboardRenderer;
  protected AndroidCardboardStereoRenderer cardboardStereoRenderer;

  public PSurfaceCardboard(PGraphics graphics, AppComponent component, SurfaceHolder holder) {
    this.sketch = graphics.parent;
    this.graphics = graphics;
    this.component = component;
    this.pgl = (PGLES)((PGraphicsOpenGL)graphics).pgl;
    
    cardboard = (CardboardActivity)component;
    pgc = (PGraphicsCardboard)graphics;

    glview = new GLCardboardSurfaceView(cardboard);
    glview.setRestoreGLStateEnabled(false);
    glview.setDistortionCorrectionEnabled(false);
//    glview.setDistortionCorrectionEnabled(true);
//    glview.setChromaticAberrationCorrectionEnabled(false);
    cardboard.setCardboardView(glview);

    surface = null;
  }
  
  @Override
  public Activity getActivity() {
    return cardboard;
  }
  
  public AssetManager getAssets() {
    return cardboard.getAssets();
  }

  public void startActivity(Intent intent) {
    cardboard.startActivity(intent);
  }

  public void initView(int sketchWidth, int sketchHeight) {
    Window window = cardboard.getWindow();

    // Take up as much area as possible
    //requestWindowFeature(Window.FEATURE_NO_TITLE);  // may need to set in theme properties
    // the above line does not seem to be needed when using cardboard
    //  android:theme="@android:style/Theme.Holo.NoActionBar.Fullscreen" >
    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

    // This does the actual full screen work
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

    window.setContentView(glview);
  }

  public String getName() {
    return cardboard.getComponentName().getPackageName();
  }

  public void setOrientation(int which) {
    if (component.getKind() == AppComponent.FRAGMENT) {
      if (which == PORTRAIT) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      } else if (which == LANDSCAPE) {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      }
    }
  }

  public File getFilesDir() {
    return cardboard.getFilesDir();
  }

  public InputStream openFileInput(String filename) {
    return null;
  }

  public File getFileStreamPath(String path) {
    return cardboard.getFileStreamPath(path);
  }

  public void dispose() {
    // TODO Auto-generated method stub
//    surface.onDestroy();
  }

  ///////////////////////////////////////////////////////////

  // Thread handling


  @Override
  public void startThread() { }

  @Override
  public void pauseThread() {
    glview.onPause();
  }

  @Override
  public void resumeThread() {
    glview.onResume();
  }

  @Override
  public boolean stopThread() {
    glview.onPause();
    return true;
  }

  @Override
  public boolean isStopped() {
    return false; // no
  }

  ///////////////////////////////////////////////////////////

  public class GLCardboardSurfaceView extends CardboardView {
//    PGraphicsOpenGL g3;
//    SurfaceHolder surfaceHolder;

    public GLCardboardSurfaceView(Context context) {
      super(context);

      // Check if the system supports OpenGL ES 2.0.
      final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
      final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
      final boolean supportsGLES2 = configurationInfo.reqGlEsVersion >= 0x20000;

      if (!supportsGLES2) {
        throw new RuntimeException("OpenGL ES 2.0 is not supported by this device.");
      }

//      surfaceHolder = getHolder();
//      // are these two needed?
//      surfaceHolder.addCallback(this);
//      surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU);

      // Tells the default EGLContextFactory and EGLConfigChooser to create an GLES2 context.
//      setEGLContextClientVersion(2);
//      setPreserveEGLContextOnPause(true);

      setFocusable(true);
      setFocusableInTouchMode(true);
      requestFocus();

      int quality = sketch.sketchQuality();
      if (1 < quality) {
        setEGLConfigChooser(8, 8, 8, 8, 16, 1);
      }
      // The renderer can be set only once.
//      setRenderer(surf.getCardboardRenderer());
      setRenderer(getCardboardStereoRenderer());
//
      // Cardboard needs to run with its own loop.
//      setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
//      setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    /*
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
      super.surfaceChanged(holder, format, w, h);

      if (PApplet.DEBUG) {
        System.out.println("SketchSurfaceView3D.surfaceChanged() " + w + " " + h);
      }
      sketch.surfaceChanged();
//      width = w;
//      height = h;
//      g.setSize(w, h);

      // No need to call g.setSize(width, height) b/c super.surfaceChanged()
      // will trigger onSurfaceChanged in the renderer, which calls setSize().
      // -- apparently not true? (100110)
    }
*/

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      return sketch.surfaceTouchEvent(event);
    }


    @Override
    public boolean onKeyDown(int code, android.view.KeyEvent event) {
      sketch.surfaceKeyDown(code, event);
      return super.onKeyDown(code, event);
    }


    @Override
    public boolean onKeyUp(int code, android.view.KeyEvent event) {
      sketch.surfaceKeyUp(code, event);
      return super.onKeyUp(code, event);
    }
  }
  
  ///////////////////////////////////////////////////////////

  // Android specific classes (Renderer, ConfigChooser)  

//  public AndroidCardboardRenderer getCardboardRenderer() {
//    cardboardRenderer = new AndroidCardboardRenderer();
//    return cardboardRenderer;
//  }

  public AndroidCardboardStereoRenderer getCardboardStereoRenderer() {
    cardboardStereoRenderer = new AndroidCardboardStereoRenderer();
    return cardboardStereoRenderer;
  }  
  
  protected class AndroidCardboardRenderer implements CardboardView.Renderer {
    public AndroidCardboardRenderer() {
    }

    @Override
    public void onDrawFrame(HeadTransform headTransform, Eye leftEye, Eye rightEye) {
      Log.i(TAG, "onDrawFrame");
      pgc.headTransform(headTransform);
      if (leftEye != null) pgc.setLeftEye();
      sketch.handleDraw();
      if (rightEye != null) pgc.setRightEye();
      sketch.handleDraw();      
    }

    @Override
    public void onFinishFrame(Viewport arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onRendererShutdown() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onSurfaceChanged(int iwidth, int iheight) {
      pgl.getGL(null);
      graphics.setSize(iwidth, iheight);     
    }

    @Override
    public void onSurfaceCreated(EGLConfig arg0) {
      pgl.init(null);      
    }
  }
 
  protected class AndroidCardboardStereoRenderer implements CardboardView.StereoRenderer {
    public AndroidCardboardStereoRenderer() {

    }


    @Override
    public void onNewFrame(HeadTransform headTransform) {
      pgl.getGL(null);
      // TODO Auto-generated method stub
//      pgc.headTransform(arg0);
    }

    @Override
    public void onDrawEye(Eye arg0) {
//      Log.i(TAG, "onDrawEye");
      // TODO Auto-generated method stub
      pgc.eyeTransform(arg0);
      sketch.handleDraw();
    }

    @Override
    public void onFinishFrame(Viewport arg0) {
      // TODO Auto-generated method stub
    }

    @Override
    public void onRendererShutdown() {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onSurfaceChanged(int arg0, int arg1) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onSurfaceCreated(EGLConfig arg0) {
      // TODO Auto-generated method stub
      
    }
  }
}
