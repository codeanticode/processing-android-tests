package processing.cardboard;

import processing.opengl.PGLES;
import processing.opengl.PGraphicsOpenGL;

import javax.microedition.khronos.egl.EGLConfig;

import com.google.vrtoolkit.cardboard.CardboardView;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;


public class PGLCardboard extends PGLES {

  public PGLCardboard(PGraphicsOpenGL pg) {
    super(pg);
  }
  
  
}
