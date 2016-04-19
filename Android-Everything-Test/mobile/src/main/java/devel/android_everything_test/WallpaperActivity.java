package devel.android_everything_test;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Build;
import android.view.View;
import android.content.ComponentName;
import android.widget.Button;

public class WallpaperActivity extends Activity {

    protected Button setWallpaper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        setWallpaper = (Button) findViewById(R.id.setWallpaper);
        setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
//                startActivity(intent);

              Intent intent = new Intent();
              if (Build.VERSION.SDK_INT > 15) {
//                  WallpaperActivity.this
                intent.setAction(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                String pkg = WallpaperService.class.getPackage().getName();
                String cls = WallpaperService.class.getCanonicalName();
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(pkg, cls));
              } else {
                intent.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
              }

//              startActivity(intent);
//              startActivityForResult(intent, 0);
                startService(intent);

//              Intent intent;
//              if (Build.VERSION.SDK_INT > 15) {
//                intent.setAction(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//                String pkg = WallpaperService.class.getPackage().getName();
//                String cls = WallpaperService.class.getCanonicalName();
//                i.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(pkg, cls));
//              } else {
//                i.setAction(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
//              }
//


            }
        });
    }
}
