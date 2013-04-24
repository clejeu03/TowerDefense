package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - Tower</b></br>
 * <p>The Tower class is an abstract class that implements the caracteristics hold by 
 * all types of tower.</br>
 * A Tower can only be placed on a hills, on the territory of the player that wanted to create it.
 * There's two groups of Tower : 
 * <ul><li>the offensive Towers that target only enemys</li>
 * <li>the defensive Towers that supports the Units of the same Player</li></ul>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Unit
 * @see Player
 * @see TowerManager
 * 
 */
public abstract class Tower {
	protected Point position;//Position du centre du Sprite !!
	protected int idOwner;
	private int range;
  public int damage;
  /**
   * Define the time between each action of the Tower
   */
  public int cadency;
  /**
   * Define at what speed the Tower can make an action
   */
  public int speed;

  /**
   * Constructor of the Tower class
   */
	public Tower(Point p, int id,int r) {
		// TODO Auto-generated constructor stub
		position = p;
		idOwner = id;
		range = r;
	}
	
	public int getIdOwner() {
		return idOwner;
	}

	public int getRange() {
		return range;
	}
   /**
    * Getter position
    * @return Point : position du Sprite
    * @see SceneView (appelant)
    */	
	public Point getPosition(){
		return position;
	}


}