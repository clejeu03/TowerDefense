package View;
/**
 * Project - TowerDefense</br>
 * <b>Class - Sprite</b></br> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Sprite
 * @see ViewManager
 * 
 */

public class Sprite {
  /**
   * Contains the path to file .png or .jpg
   */
  public String path;

  /**
   * Define the x position of the sprite
   */
  public float position_x;
  /**
   * Define the y position of the sprite
   */
  public float position_y;

  /**
   * Constructor of the Sprite class
   */
  public Sprite() {
  }

  /**
   * Getter that returns the path of the source image
   * @return path
   */
  public String getPath() {
	  return this.path;
  }

  /**
   * Setter that changes the path to the displayed picture
   */
  public void setPath(String path) {
	  this.path = path;
  }

}