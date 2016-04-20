package processing.core;

import java.io.File;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.view.SurfaceView;
import android.view.View;
import processing.app.PContainer;

public interface PSurface {
  public Activity getActivity();
  public PContainer getContainer();

  public void dispose();

  public View getRootView();

  public String getName();

  public void setRootView(View view);

  public SurfaceView getSurfaceView();

  public void initView(int sketchWidth, int sketchHeight);

  public void startActivity(Intent intent);

  public void setOrientation(int which);

  public File getFilesDir();

  public File getFileStreamPath(String path);

  public InputStream openFileInput(String filename);

   /** Start the animation thread */
  public void startThread();

  /**
   * On the next trip through the animation thread, things should go sleepy-bye.
   * Does not pause the thread immediately because that needs to happen on the
   * animation thread itself, so fires on the next trip through draw().
   */
  public void pauseThread();

  public void resumeThread();

  /**
   * Stop the animation thread (set it null)
   * @return false if already stopped
   */
  public boolean stopThread();

  public boolean isStopped();

  public AssetManager getAssets();
}
