/* -*- mode: java; c-basic-offset: 2; indent-tabs-mode: nil -*- */

/*
  Part of the Processing project - http://processing.org

  Copyright (c) 2016 The Processing Foundation

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License version 2.1 as published by the Free Software Foundation.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
*/

package processing.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import processing.android.AppComponent;
import processing.android.PFragment;

public class PSurfaceAndroid2D implements PSurface, PConstants {
  protected PApplet sketch;
  protected PGraphics graphics;
  protected AppComponent component;
  protected Activity activity;
  protected WallpaperService wallpaper;
  protected CanvasWatchFaceService watchface;

  protected View view;

  protected SketchSurfaceView surface;

  public PSurfaceAndroid2D() {

  }

  public PSurfaceAndroid2D(PGraphics graphics, AppComponent component, SurfaceHolder holder) {
    this.sketch = graphics.parent;
    this.graphics = graphics;
    this.component = component;
    if (component.getKind() == AppComponent.FRAGMENT) {
      PFragment frag = (PFragment)component;
      activity = frag.getActivity();
      surface = new SketchSurfaceView(activity, null);
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      wallpaper = (WallpaperService)component;
      surface = new SketchSurfaceView(wallpaper, holder);
    } else if (component.getKind() == AppComponent.WATCHFACE_CANVAS) {
      watchface = (CanvasWatchFaceService)component;
      surface = null;
    }
  }

  @Override
  public AppComponent getComponent() {
    return component;
  }


  @Override
  public Activity getActivity() {
    return activity;
  }


  @Override
  public View getRootView() {
    return view;
  }


  @Override
  public void setRootView(View view) {
    this.view = view;
  }


  @Override
  public SurfaceView getSurfaceView() {
    return surface;
  }


  public AssetManager getAssets() {
    if (component.getKind() == AppComponent.FRAGMENT) {
      return activity.getAssets();
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      return wallpaper.getBaseContext().getAssets();
    } else if (component.getKind() == AppComponent.WATCHFACE_CANVAS) {
      return watchface.getBaseContext().getAssets();
    }
    return null;
  }


  public void startActivity(Intent intent) {
    if (component.getKind() == AppComponent.FRAGMENT) {
      component.startActivity(intent);
    }
  }


  public void setSystemUiVisibility(int visibility) {
    int kind = component.getKind();
    if (kind == AppComponent.FRAGMENT || kind == AppComponent.WALLPAPER) {
      surface.setSystemUiVisibility(visibility);
    }
  }


  public void initView(int sketchWidth, int sketchHeight) {
    if (component.getKind() == AppComponent.FRAGMENT) {
      int displayWidth = component.getWidth();
      int displayHeight = component.getHeight();
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
        overallLayout.setBackgroundColor(sketch.sketchWindowColor());
//        window.setContentView(overallLayout);
        rootView = overallLayout;
      }
      setRootView(rootView);
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      /*
      int displayWidth = component.getWidth();
      int displayHeight = component.getHeight();
      View rootView;
      // Looks like a wallpaper can be larger than the screen res, and have an offset, need to
      // look more into that.
      if (sketchWidth == displayWidth && sketchHeight == displayHeight) {
        // If using the full screen, don't embed inside other layouts
//        window.setContentView(surfaceView);
        rootView = getSurfaceView();
      } else {
        // If not using full screen, setup awkward view-inside-a-view so that
        // the sketch can be centered on screen. (If anyone has a more efficient
        // way to do this, please file an issue on Google Code, otherwise you
        // can keep your "talentless hack" comments to yourself. Ahem.)
        RelativeLayout overallLayout = new RelativeLayout(wallpaper);
        RelativeLayout.LayoutParams lp =
          new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                                          LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        LinearLayout layout = new LinearLayout(wallpaper);
        layout.addView(getSurfaceView(), sketchWidth, sketchHeight);
        overallLayout.addView(layout, lp);
        overallLayout.setBackgroundColor(sketch.sketchWindowColor());
//        window.setContentView(overallLayout);
        rootView = overallLayout;
      }
      */
      setRootView(getSurfaceView());
    }
  }


  public String getName() {
    if (component.getKind() == AppComponent.FRAGMENT) {
      return activity.getComponentName().getPackageName();
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      return wallpaper.getPackageName();
    } else if (component.getKind() == AppComponent.WATCHFACE_CANVAS) {
      return watchface.getPackageName();
    }
    return "";
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
    if (component.getKind() == AppComponent.FRAGMENT) {
      return activity.getFilesDir();
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      return wallpaper.getFilesDir();
    } else if (component.getKind() == AppComponent.WATCHFACE_CANVAS) {
      return watchface.getFilesDir();
    }
    return null;
  }


  public InputStream openFileInput(String filename) {
    if (component.getKind() == AppComponent.FRAGMENT) {
      try {
        return activity.openFileInput(filename);
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    return null;
  }


  public File getFileStreamPath(String path) {
    if (component.getKind() == AppComponent.FRAGMENT) {
      return activity.getFileStreamPath(path);
    } else if (component.getKind() == AppComponent.WALLPAPER) {
      return wallpaper.getFileStreamPath(path);
    } else if (component.getKind() == AppComponent.WATCHFACE_CANVAS) {
      return watchface.getFileStreamPath(path);
    }
    return null;
  }



//  public void dispose() {
//    // TODO Auto-generated method stub
//
//  }

  ///////////////////////////////////////////////////////////

  // Thread handling

  private final Handler handler = new Handler();
  private final Runnable drawRunnable = new Runnable() {
    public void run() {
      if (sketch != null) {
        sketch.handleDraw();
      }
      scheduleNextDraw();
    }
  };

  private void scheduleNextDraw() {
    handler.removeCallbacks(drawRunnable);
    component.requestDraw();
    int targetMillis = 1000 / 15;
//    if (sketch != null) {
//      targetMillis = (int)(1000 / sketch.frameRateTarget);
//    }
    if (component.canDraw()) {
      handler.postDelayed(drawRunnable, targetMillis);
    }
  }


  private void pauseNextDraw() {
    handler.removeCallbacks(drawRunnable);
  }


  private void requestNextDraw() {
    handler.post(drawRunnable);
  }


  public void startThread() {
    requestNextDraw();
  }


  public void pauseThread() {
    pauseNextDraw();
  }


  public void resumeThread() {
    scheduleNextDraw();
  }


  public boolean stopThread() {
    pauseNextDraw();
    return true;
  }

  public void setFrameRate(float fps) {}

  public boolean isStopped() {
    return !handler.hasMessages(0);
  }

  ///////////////////////////////////////////////////////////

  // GL SurfaceView

  public class SketchSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder holder;

    public SketchSurfaceView(Context context, SurfaceHolder holder) {
      super(context);
      this.holder = holder;

//    println("surface holder");
    // Install a SurfaceHolder.Callback so we get notified when the
    // underlying surface is created and destroyed
      SurfaceHolder h = getHolder();
      h.addCallback(this);
//    surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_GPU); // no longer needed.

//    println("setting focusable, requesting focus");
      setFocusable(true);
      setFocusableInTouchMode(true);
      requestFocus();
//    println("done making surface view");
    }

    @Override
    public SurfaceHolder getHolder() {
      if (holder == null) {
        return super.getHolder();
      } else {
        return holder;
      }
    }
//  public PGraphics getGraphics() {
//    return g2;
//  }

    // part of SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder holder) {
    }


    // part of SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder holder) {
      //g2.dispose();
    }


    // part of SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
      if (PApplet.DEBUG) {
        System.out.println("SketchSurfaceView2D.surfaceChanged() " + w + " " + h);
      }

      sketch.displayWidth = w;
      sketch.displayHeight = h;
      graphics.setSize(sketch.sketchWidth(), sketch.sketchHeight());
      sketch.surfaceChanged();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
      super.onWindowFocusChanged(hasFocus);
      sketch.surfaceWindowFocusChanged(hasFocus);
    }

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
}
