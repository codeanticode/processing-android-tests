package processing.cardboard;

import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

import android.view.SurfaceView;
import processing.app.PContainer;
import processing.core.PMatrix3D;
import processing.core.PSurface;
import processing.opengl.PGL;
import processing.opengl.PGLES;
import processing.opengl.PGraphics3D;
import processing.opengl.PGraphicsOpenGL;

public class PGraphicsCardboard extends PGraphics3D {
  public static final String RENDERER = "processing.cardboard.PGraphicsCardboard";
  protected static final int LEFT_EYE  = 0;
  protected static final int RIGHT_EYE = 1;
  
  private boolean initialized = false;
  private Viewport viewPort;
  private PMatrix3D headMatrix;
  private PMatrix3D viewMatrix;
  private PMatrix3D perspectiveMatrix;
  private PMatrix3D correctionMatrix;
  private float[] headRotation;
  private final float sensingScale  = 1.0f;  // for millimeter
  private int currentEye;
  
  protected PGL createPGL(PGraphicsOpenGL pg) {
    return new PGLES(pg);
//    return new PGLcdb(pg);
  }
  
  public PSurface createSurface(PContainer container, SurfaceView view) {  // ignore
    return new PSurfaceCardboard(this, container, view);
  }
  
  public void setLeftEye() {
    currentEye = LEFT_EYE;
  }
  
  public void setRightEye() {
    currentEye = RIGHT_EYE;
  }

  public void eyeTransform(Eye eye) {
    float[] v = eye.getEyeView();
    float[] p = eye.getPerspective(cameraNear, cameraFar);
    if (!initialized) initCardboard();
    viewPort = eye.getViewport();
    // Matrices in Processing are row-major, and Cardboard API is column-major
    perspectiveMatrix.set(p[0], p[4],  p[8], p[12],
                          p[1], p[5],  p[9], p[13],
                          p[2], p[6], p[10], p[14],
                          p[3], p[7], p[11], p[15]);
    viewMatrix.set(v[0], v[4],  v[8], v[12],
                   v[1], v[5],  v[9], v[13],
                   v[2], v[6], v[10], v[14],
                   v[3], v[7], v[11], v[15]);
  }


    public void beginDraw() {
        super.beginDraw();
        pgl.viewport(viewPort.x, viewPort.y, viewPort.width, viewPort.height);
        camera(0.0f, 0.0f, cameraZ, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        setProjection(perspectiveMatrix);
        applyMatrix(viewMatrix);
    }


    private void initCardboard() {
        correctionMatrix = null;
        headMatrix = new PMatrix3D();
        viewMatrix = new PMatrix3D();
        perspectiveMatrix = new PMatrix3D();
        headRotation = new float[16];
        initialized = true;
    }

  public void headTransform(HeadTransform headTransform) {
    float[] v = new float[16];

    headTransform.getHeadView(v, 0);
    headMatrix.set(v[0], v[4], v[8], v[12],
                   v[1], v[5], v[9], v[13],
                   v[2], v[6], v[10], v[14],
                   v[3], v[7], v[11], v[15]);

    headTransform.getQuaternion(headRotation, 0);

    // get pos?
    // 
    // headTransform.getHeadView(headView, offset);
    // look at other cardboard examples, documentation
//    PMatrix3D m = calcMatrix(0, 0, 0, quat[0], quat[1], quat[2], quat[3]);
//    if (correctionMatrix == null) {
//      correctionMatrix = new PMatrix3D(m);
//      correctionMatrix.invert();
//    }
//    m.apply(correctionMatrix);
//    headMatrix = m;
//    applyMatrix(headMatrix);
    


  }

  
  
  private PMatrix3D calcMatrix(float px, float py, float pz, float qx, float qy, float qz, float qw) {
    PMatrix3D mat = new PMatrix3D();

    // calculate matrix terms
    float two_xSquared = 2 * qx * qx;
    float two_ySquared = 2 * qy * qy;
    float two_zSquared = 2 * qz * qz;
    float two_xy = 2 * qx * qy;
    float two_wz = 2 * qw * qz;
    float two_xz = 2 * qx * qz;
    float two_wy = 2 * qw * qy;
    float two_yz = 2 * qy * qz;
    float two_wx = 2 * qw * qx;

    // update view matrix orientation
    mat.m00 = 1 - two_ySquared - two_zSquared;
    mat.m01 = two_xy + two_wz;
    mat.m02 = two_xz - two_wy;
    mat.m10 = two_xy - two_wz;
    mat.m11 = 1 - two_xSquared - two_zSquared;
    mat.m12 = two_yz + two_wx;
    mat.m20 = two_xz + two_wy;
    mat.m21 = two_yz - two_wx;
    mat.m22 = 1 - two_xSquared - two_ySquared;

    // change right-hand to left-hand
    mat.preApply(
    1, 0, 0, 0, 
    0, -1, 0, 0, 
    0, 0, 1, 0, 
    0, 0, 0, 1
      );
    mat.scale(1, -1, 1);

    // Position    
    mat.m03 = sensingScale * pz;
    mat.m13 = sensingScale * py;
    mat.m23 = sensingScale * (-px);

    return mat;
  }  
  
}
