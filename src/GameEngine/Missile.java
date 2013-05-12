package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - Missile</b></br>
 * <p>The Missile class stand for all the projectils that are launch by the Tower, 
 * whatever their types and owners. An offensive projectil will maybe be a bullet and a
 * defensive one maybe a special capacity that is symbolized the same way that a bullet.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see TowerManager
 * 
 */

public class Missile {
  /**
   * Define the current x position of the missile
   */
 private Point position;
// private int id;
 private Unit target;
 private int damages;
 private double speed;
 private Vector2D direction;

  /**
   * Constructor of the Missile class
   */
  public Missile(Point position, Unit target, double speed, int damages) {
	  System.out.println("Creation of a new missile !");
	  this.position = position;
	  this.target = target;
	  this.speed = speed;
	  this.damages = damages;
	  this.direction = new Vector2D(this.target.getPosition().x - this.position.x, this.target.getPosition().y - this.position.y);
}

  /**
   * Getter that returns the current position of the selected missile
   * @return position
   */
  public Point getPosition() {
	  return this.position;
  }
  /**
   * Getter returns the current position of the target
   * @return
   */
  public Point getDestination(){
	  return this.target.getPosition();
  }
  
  /**
   * Getter return the damages
   * @return
   */
  public int getDamages(){
	  return this.damages;
  }
  /**
   * Getter - return the speed
   * @return
   */
  public double getSpeed(){
	  return this.speed;
  }
  /**
   * Setter that changes the current position of the selected missile
   * @param position - the new position to apply
   */
  public void setPosition(Point point) {
	  this.position = point;
  }
  
  /**
   * Getter - return the vector direction from the beginning of the missile movement
   * @return
   */
  public Vector2D getDirection(){
	  return this.direction;
  }

}