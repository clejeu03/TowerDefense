package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - Tower</b></br>
 * <p>The Tower class is an abstract class that implements the characteristics hold by 
 * all types of tower.</br>
 * A Tower can only be placed on a hills, on the territory of the player that wanted to create it.
 * There's two groups of Tower : 
 * <ul><li>the offensive Towers that target only enemies</li>
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
	
	protected Point position;
	protected int playerId;
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
	 * @param position - position of the center of the tower
	 * @param playerId - player id
	 * @param range 
	 */
	public Tower(Point position, int playerId, int range) {
		this.position = position;
		this.playerId= playerId;
		this.range = range;
	}
	
	/**
	 * Getter - Retrieve the playerId attribute
	 * @return playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Getter - Retrieve the range attribute
	 * @return range
	 */
	public int getRange() {
		return range;
	}
	
   /**
    * Getter - Retrieve the position attribute
    * @return position - position of the center of the tower
    */	
	public Point getPosition(){
		return position;
	}


}