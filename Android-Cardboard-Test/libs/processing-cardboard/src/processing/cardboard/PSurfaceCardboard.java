package processing.cardboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;

import com.google.vrtoolkit.cardboard.CardboardActivity;
import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import processing.app.PContainer;
import processing.core.PGraphics;
import processing.opengl.PSurfaceGLES;

public class PSurfaceCardboard extends PSurfaceGLES {
  protected PGraphicsCardboard pgc;
  
  protected CardboardActivity cardboard;
  protected static AndroidCardboardRenderer cardboardRenderer;
  protected static AndroidCardboardStereoRenderer cardboardStereoRenderer;

  public PSurfaceCardboard(PGraphics graphics, PContainer container,
      SurfaceView view) {
    super(graphics, container);
    
    cardboard = (CardboardActivity)container;
    pgc = (PGraphicsCardboard)graphics;
    
    if (view != null) surface = view;
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

      int displayWidth = container.getWidth();
      int displayHeight = container.getHeight();
      View rootView;
      if (sketchWidth == displayWidth && sketchHeight == displayHeight) {
        // If using the full screen, don't embed inside other layouts
//        window.setContentView(surfaceView);
        rootView = getSurfaceView();
      } else {
        // If not using full screen, setup awkward view-inside-a-view so that
        // the sketch can be centered on screen. (If anyone has a more efficient
        // way to do this, please file an issue on Google Code, otherwise you
        // can keep your "talentless hack" comments to yourself. Ahem.)
        RelativeLayout overallLayout = new RelativeLayout(activity);
        RelativeLayout.LayoutParams lp =
          new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                          LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        LinearLayout layout = new LinearLayout(activity);
        layout.addView(getSurfaceView(), sketchWidth, sketchHeight);
        overallLayout.addView(layout, lp);
//        window.setContentView(overallLayout);
        rootView = overallLayout;
      }
      setRootView(rootView);
  }

  public String getName() {
    return cardboard.getComponentName().getPackageName();
  }

  public void setOrientation(int which) {
    if (container.getKind() == PContainer.FRAGMENT) {
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

  // Android specific classes (Renderer, ConfigChooser)  

  public AndroidCardboardRenderer getCardboardRenderer() {
    cardboardRenderer = new AndroidCardboardRenderer();
    return cardboardRenderer;
  }

  public AndroidCardboardStereoRenderer getCardboardStereoRenderer() {
    cardboardStereoRenderer = new AndroidCardboardStereoRenderer();
    return cardboardStereoRenderer;
  }  
  
  protected class AndroidCardboardRenderer implements CardboardView.Renderer {
    public AndroidCardboardRenderer() {
    }

    @Override
    public void onDrawFrame(HeadTransform headTransform, Eye leftEye, Eye rightEye) {
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
    public void onDrawEye(Eye arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onFinishFrame(Viewport arg0) {
      // TODO Auto-generated method stub
      
    }

    @Override
    public void onNewFrame(HeadTransform arg0) {
      pgl.getGL(null);
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
