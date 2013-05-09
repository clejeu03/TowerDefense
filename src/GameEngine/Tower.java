package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;

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
	
	private int id;
	private Point position;
	private PlayerType playerType;
	private int range;
	/**
	 * Define the effects of the Tower. Can be positive (to the player) or negative (to his enemies). Represents a different quantity following
	 * the type of tower
	 */
	private int damage;
	  /**
	   * Define the time between each action of the Tower : case multiple missiles
	   */
	private double cadency;
	  /**
	   * Define at what speed the Tower can make an action : define the speed of the missile
	   */
	private double speed;
	
	/**
	 * Constructor of the Tower class
	 * @param position - position of the center of the tower
	 * @param playerType - player type
	 * @param range - circle of action 
	 * @param damage - positive or negative
	 * @param cadency - in milliseconds
	 * @param speed - speed of the missiles in m/s
	 */
	public Tower(int id, Point position, PlayerType playerType, int range, int damage, double cadency, double speed) {
		this.id = id;
		this.position = position;
		this.playerType= playerType;
		this.range = range;
		this.damage = damage;
		this.cadency = cadency;
		this.speed = speed;
	}
	
	/**
	 * Function called by the TowerManager when a unit go trough the tower range
	 * @see TowerManager#activeTower()
	 */
	public abstract void shoot();
	/**
	 * Function called by the TowerManager when no more units are in the tower range
	 * @see TowerManager#desactiveTower()
	 */
	public abstract void stop();
	
	/**
	 * Getter - Retrieve the playerId attribute
	 * @return playerId
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * Getter - Retrieve the range attribute
	 * @return range
	 */
	public int getRange() {
		return range;
	}
	
	public int getId() {
		return id;
	}

/**
    * Getter - Retrieve the position attribute
    * @return position - position of the center of the tower
    */	
	public Point getPosition(){
		return position;
	}
	
	/**
	 * Getter - Retrieve the damage attribute
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Getter - Retrieve the cadency attribute
	 * @return cadency
	 */
	public double getCadency() {
		return cadency;
	}

	/**
	 * Getter - Retrieve the speed attribute
	 * @return speed
	 */
	public double getSpeed() {
		return speed;
	}
}