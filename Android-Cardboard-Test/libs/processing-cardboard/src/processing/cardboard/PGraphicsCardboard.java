package processing.cardboard;

import com.google.vrtoolkit.cardboard.HeadTransform;

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
  private PMatrix3D headMatrix;
  private PMatrix3D correctionMatrix;
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
  
  public void headTransform(HeadTransform headTransform) {
    float[] quat = new float[4];
    headTransform.getQuaternion(quat, 0);
    
    // get pos?
    // 
    // headTransform.getHeadView(headView, offset);
    // look at other cardboard examples, documentation
    PMatrix3D m = calcMatrix(0, 0, 0, quat[0], quat[1], quat[2], quat[3]);
    if (correctionMatrix == null) {
      correctionMatrix = new PMatrix3D(m);
      correctionMatrix.invert();
    }    
    m.apply(correctionMatrix);
    headMatrix = m;
    applyMatrix(headMatrix);
    
    /*
      float[] quat = new float[4];
      headTransform.getQuaternion(quat, 0);
      // normalize quaternion
      float length = (float) Math.sqrt(quat[0] * quat[0] + quat[1] * quat[1] + quat[2] * quat[2] + quat[3] * quat[3]);
      int DIV = 10;
      float lowSpeed = .01f;  //.005f;
      float mediumSpeed = .02f;  //.01f;
      float highSpeed = .04f;  //.02f;
      float pitchSpeed = 0;
      float yawSpeed = 0;
      float rollSpeed = 0;
      if (length != 0) {
          int pitch = (int) ((quat[0] / length) * DIV);  // pitch up/down
          int yaw = (int) ((quat[1] / length) * DIV);  // yaw left/ right
          int roll = (int) ((quat[2] / length) * DIV);  // roll left/right
          //int w = (int) ((quat[3] / length) * DIV);  //
          //Log.d(TAG, "normalized quaternion " + pitch + " " + yaw + " " + roll );

          if (pitch >= 3)
              pitchSpeed = -highSpeed;
          else if (pitch <= -3)
              pitchSpeed = highSpeed;
          else if (pitch == 2)
              pitchSpeed = -mediumSpeed;
          else if (pitch == -2)
              pitchSpeed = mediumSpeed;
          else if (pitch == 1)
              pitchSpeed = -lowSpeed;
          else if (pitch == -1)
              pitchSpeed = lowSpeed;
          else
              pitchSpeed = 0;

          if (yaw >= 3)
              yawSpeed = -highSpeed;
          else if (yaw <= -3)
              yawSpeed = highSpeed;
          else if (yaw == 2)
              yawSpeed = -mediumSpeed;
          else if (yaw == -2)
              yawSpeed = mediumSpeed;
          else if (yaw == 1)
              yawSpeed = -lowSpeed;
          else if (yaw == -1)
              yawSpeed = lowSpeed;
          else
              yawSpeed = 0;

          if (roll >= 3)
              rollSpeed = -highSpeed;
          else if (roll <= -3)
              rollSpeed = highSpeed;
          else if (roll == 2)
              rollSpeed = -mediumSpeed;
          else if (roll == -2)
              rollSpeed = mediumSpeed;
          else if (roll == 1)
              rollSpeed = -lowSpeed;
          else if (roll == -1)
              rollSpeed = lowSpeed;
          else
              rollSpeed = 0;

          if ((cameraPositionX > XBOUND && yawSpeed < 0) ||
                  (cameraPositionX < -XBOUND && yawSpeed > 0) ||
                  (cameraPositionX <= XBOUND && cameraPositionX >= -XBOUND))
              cameraPositionX += yawSpeed;


          if ((cameraPositionY > YBOUND && pitchSpeed < 0) ||
                  (cameraPositionY < -YBOUND && pitchSpeed > 0) ||
                  (cameraPositionY <= YBOUND && cameraPositionY >= -YBOUND))
              cameraPositionY += pitchSpeed;

          if ((cameraPositionZ > ZBOUND_IN && rollSpeed < 0) ||
                  (cameraPositionZ < ZBOUND_OUT && rollSpeed > 0) ||
                  (cameraPositionZ <= ZBOUND_OUT && cameraPositionZ >= ZBOUND_IN))
              cameraPositionZ += rollSpeed;

//          Log.d(TAG, "Normalized quaternion " + pitch + " " + yaw + " " + roll + " Camera position "+ cameraPositionX + " " + cameraPositionY + " " + cameraPositionZ);
      } else {
          Log.d(TAG, "Quaternion 0");
      }
*/


  }
  
  public void beginDraw() {
    super.beginDraw();
    if (!initialized) initCardboard();
  }
  
  
  private void initCardboard() {
    correctionMatrix = null;
    headMatrix = new PMatrix3D();    
    initialized = true;
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
