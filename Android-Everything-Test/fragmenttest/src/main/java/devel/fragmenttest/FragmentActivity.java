package devel.fragmenttest;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import processing.core.PApplet;
import processing.android.PFragment;



    public class FragmentActivity extends Activity {
        private static final String MAIN_FRAGMENT_TAG = "main_fragment";
        PFragment fragment;
        int viewId = View.generateViewId();
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

            // http://stackoverflow.com/questions/29696879/android-expected-resource-of-type-id
            frame.setId(viewId);
            setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                             ViewGroup.LayoutParams.MATCH_PARENT));
            PApplet sketch = new FragmentSketch();


            if (savedInstanceState == null) {
                fragment = new PFragment();
                fragment.setSketch(sketch);
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

