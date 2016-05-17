package devel.cardboardtest;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import java.io.File;
import android.os.Environment;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;
import processing.core.PApplet;
import processing.cardboard.PCardboard;

public class CardboardActivity extends PCardboard {
//    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        // All of this is to handle the new permission mechanism in Android 23, it is not enough
        // to have the permission in the manifest file
        int hasReadExternalPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasReadExternalPermission != PackageManager.PERMISSION_GRANTED) {
          // Permissions trouble in 23 (Marshmallow, 6.0):
          // http://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en
          requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
        }
        */

        /*
        // Check that external storage can be accessed.
        File configFolder = new File(Environment.getExternalStorageDirectory(), "Cardboard");
        File paramFile = new File(configFolder, "current_device_params");
        if (paramFile.exists() && !paramFile.isDirectory()) {
            FileInputStream is = null;
            try {
                is = new FileInputStream(paramFile);
            } catch (Exception ex) {
                System.out.println("FileInputStream exception");
            }

            if (is != null) {
                try {
                    BufferedInputStream e = new BufferedInputStream(is);
                } catch (Exception ex) {
                    System.out.println("BufferedInputStream exception");
                }
            }
        }
        */

//        setContentView(R.layout.activity_main);
        PApplet sketch = new CardboardSketch();
        setSketch(sketch);
        init(sketch);
        setConvertTapIntoTrigger(true);
    }
}
