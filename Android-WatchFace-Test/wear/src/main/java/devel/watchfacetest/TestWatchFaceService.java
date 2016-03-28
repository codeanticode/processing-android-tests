package devel.watchfacetest;

import android.opengl.GLES20;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint;
import android.support.wearable.watchface.Gles2WatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.SurfaceHolder;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.TimeUnit;


//public class TestWatchFaceService extends CanvasWatchFaceService {
public class TestWatchFaceService extends Gles2WatchFaceService {

    private static final String TAG = "TestWatchFaceService";

//    private static final long TICK_PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final long TICK_PERIOD_MILLIS = 100;
    private Handler timeTick;

    @Override
    public Engine onCreateEngine() {
        return new WatchFaceEngine();
    }

//    private class WatchFaceEngine extends CanvasWatchFaceService.Engine {
    private class WatchFaceEngine extends Gles2WatchFaceService.Engine {
        private static final int BACKGROUND_DEFAULT_COLOUR = Color.RED;
//        private static final int BACKGROUND_DEFAULT_COLOUR = Color.BLUE;
//        private static final int BACKGROUND_DEFAULT_COLOUR = Color.GREEN;
        Paint backgroundPaint;
        int frame;
        @Override
        public void onCreate(SurfaceHolder holder) {
            super.onCreate(holder);

            setWatchFaceStyle(new WatchFaceStyle.Builder(TestWatchFaceService.this)
                    .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                    .setAmbientPeekMode(WatchFaceStyle.AMBIENT_PEEK_MODE_HIDDEN)
                    .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                    .setShowSystemUiTime(false)
                    .build());

            backgroundPaint = new Paint();
            backgroundPaint.setColor(BACKGROUND_DEFAULT_COLOUR);

            timeTick = new Handler(Looper.myLooper());
            startTimerIfNecessary();
        }

        private void startTimerIfNecessary() {
            timeTick.removeCallbacks(timeRunnable);
            if (isVisible() && !isInAmbientMode()) {
                timeTick.post(timeRunnable);
            }
        }

        private final Runnable timeRunnable = new Runnable() {
            @Override
            public void run() {
                onSecondTick();

                if (isVisible() && !isInAmbientMode()) {
                    timeTick.postDelayed(this, TICK_PERIOD_MILLIS);
                }
            }
        };

        private void onSecondTick() {
            invalidateIfNecessary();
        }

        private void invalidateIfNecessary() {
            if (isVisible() && !isInAmbientMode()) {
                invalidate();
            }
        }

//        @Override
//        public void onDraw(Canvas canvas, Rect bounds) {
//            super.onDraw(canvas, bounds);
//            Log.i(TAG, "FRAME " + frame);
//            frame++;
//            canvas.drawRect(0, 0, bounds.width(), bounds.height(), backgroundPaint);
//        }

        @Override
        public void onTimeTick() {
            invalidate();
        }

        @Override
        public void onDraw() {
            super.onDraw();
            Log.i(TAG, "FRAME " + frame);
            frame++;

            GLES20.glClearColor(1, 0, 1, 1);
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        }
    }
}
