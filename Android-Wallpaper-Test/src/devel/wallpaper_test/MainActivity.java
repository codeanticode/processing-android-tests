package devel.wallpaper_test;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

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
      PApplet sketch = new ProcessingSketch();
      
      
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


public class MainActivity extends Activity {
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

    Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
    intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
        new ComponentName(this, MainService.class));
    startActivity(intent);

  }
} 
