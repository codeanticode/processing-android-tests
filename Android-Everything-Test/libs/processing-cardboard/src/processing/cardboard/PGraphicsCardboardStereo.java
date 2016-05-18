package processing.cardboard;

import android.view.SurfaceHolder;
import processing.android.AppComponent;
import processing.core.PSurface;

public class PGraphicsCardboardStereo extends PGraphicsCardboard {
  public PSurface createSurface(AppComponent component, SurfaceHolder holder) {  // ignore
    return new PSurfaceCardboard(this, component, holder, true);
  }
}
