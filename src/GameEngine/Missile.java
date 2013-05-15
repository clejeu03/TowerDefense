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
 private int id; //The missile id is it's creation's date
 private Point position;
 private Unit target;
 private int damages;
 private double speed;
 private Vector2D direction;
 private Tower origin;
 private boolean area;

  /**
   * Constructor of the Missile class
   */
  public Missile(int id, Tower origin, Point position, Unit target, double speed, int damages) {
	  System.out.println("Creation of a new missile !");
	  this.id = id;
	  this.position = position;
	  this.target = target;
	  this.speed = speed;
	  this.damages = damages;
	  this.origin = origin;
	  
	  if(origin.isAreaDamages())this.setArea(true);
	  else this.setArea(false);
	  
	  this.direction = new Vector2D(this.target.getPosition().x - this.position.x, this.target.getPosition().y - this.position.y);
}

  /**
   * This function manage the move of a missile, using the Vector2D class from this package. The aim is to make the missile reach it's target
   * while this one is moving. So the missile must actualize it's direction very often. The missile move of 1 pixel each millisecond, and we
   * consider that it has reached it's target with a precision of 2 pixel because of movement.
   * @return
   * @see Vector2D
   * @see WarManager#beginEncounters(ArmyManager, TowerManager, Dispatcher.DispatcherManager, Long)
   */
  public boolean searchForTarget(){
	  
	  if(Math.abs(this.getPosition().x -this.getDestination().x) <= 2 && Math.abs(this.getPosition().y -this.getDestination().y) <= 2){
			return false;
			
	  }else{
	  
		  //Define a vector to be followed by the missile
			Vector2D currentDirection = new Vector2D(this.getDestination().x - this.getPosition().x, this.getDestination().y - this.getPosition().y);
			Vector2D axeX = new Vector2D(1,0);
			Vector2D axeY = new Vector2D(0,1);
			double factorX = Math.abs(currentDirection.dotProduct(axeX));
			double factorY = Math.abs(currentDirection.dotProduct(axeY));
			
			//Projection on axeX and axeY and updating the this position according to the tallest component's axe
			if(factorX > factorY){
				if(this.getDestination().x > this.getPosition().x)
					this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y));
				else
					this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y));
			}
			if(factorY > factorX){
				if(this.getDestination().y > this.getPosition().y)
					this.setPosition(new Point(this.getPosition().x, this.getPosition().y+1));
				else
					this.setPosition(new Point(this.getPosition().x, this.getPosition().y-1));
			}
			if(factorX - factorY < 0.0001){
				if(this.getDestination().x > this.getPosition().x && this.getDestination().y > this.getPosition().y)
					this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y+1));
				else
					this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y-1));
			}
			
			//Determine what should have been the ideal position
			Point realPosition = this.getDirection().normalize().pointPlusVector(this.getPosition());
			
			//Correction of errors between realPosition and actualPosition
			if(realPosition.x - this.getPosition().x > 1)this.setPosition(new Point(this.getPosition().x+1, this.getPosition().y));
			else if(realPosition.x - this.getPosition().x < -1)this.setPosition(new Point(this.getPosition().x-1, this.getPosition().y));
			if(realPosition.y - this.getPosition().y > 1)this.setPosition(new Point(this.getPosition().x, this.getPosition().y+1));
			else if(realPosition.y - this.getPosition().y < -1)this.setPosition(new Point(this.getPosition().x, this.getPosition().y-1));
			
			return true;
		}
		
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
   * Getter
   * @return
   */
  public int getId(){
	  return this.id;
  }
  /**
   * Getter
   * @return
   */
  public Tower getOrigin(){
	  return this.origin;
  }
  /**
   * Getter - return the vector direction from the beginning of the missile movement
   * @return
   */
  public Vector2D getDirection(){
	  return this.direction;
  }
  
  public Unit getTarget(){
	  return this.target;
  }

/**
 * @return the area
 */
public boolean isArea() {
	return area;
}

/**
 * @param area the area to set
 */
public void setArea(boolean area) {
	this.area = area;
}

}