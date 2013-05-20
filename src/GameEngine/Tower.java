package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

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
	private ArrayList<TowerManager.TowerTypes> evolutions;
	private boolean areaDamages; //if false, just send missiles
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
	
	protected long lastShootingTime;
	
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
		this.lastShootingTime = 0;
		this.evolutions = new ArrayList<TowerManager.TowerTypes>();
	}
	
	/**
	 * Function called by the TowerManager when a unit go trough the tower range
	 * @see TowerManager#activeTower()
	 * @param unit - target
	 */
	public abstract Missile shoot(Unit unit, long date, int currentIdCount);
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
	 * Setter - Change the playerType attribute
	 * @param newPlayerType
	 * @see TowerManager#changeOwner(int, PlayerType)
	 */
	public void setPlayerType(PlayerType newPlayerType){
		this.playerType = newPlayerType;
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
	 * Setter that determine the evolutions of a tower. If there's no evolutions, the two types must be NOTOWER.
	 * @param type1
	 * @param type2
	 */
	public void setEvolutions(TowerManager.TowerTypes type1, TowerManager.TowerTypes type2){
		this.evolutions.add(type1);
		this.evolutions.add(type2);
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
	/**
	 * Getter - return the last date at which the tower created a missile
	 * @return
	 */
	public long getLastShootingTime(){
		return this.lastShootingTime;
	}

	/**
	 * Getter - Array with one or two elements the two types of evolution
	 * @return the evolutions
	 */
	public ArrayList<TowerManager.TowerTypes> getEvolutions() {
		return evolutions;
	}
	
	/**
	 * Getter that return the first type instead of the complete array
	 * @return
	 */
	public TowerManager.TowerTypes getFirstEvolution(){
		if(!evolutions.isEmpty())
			return evolutions.get(0);
		else
			return TowerManager.TowerTypes.NOTOWER;
	}
	/**
	 * Getter that return the second type of evolution instead of the complete array
	 * @return
	 */
	public TowerManager.TowerTypes getSecondEvolution(){
		if(!evolutions.isEmpty() && evolutions.size()>1)
			return evolutions.get(1);
		else
			return TowerManager.TowerTypes.NOTOWER;
	}

	/**
	 * @return the areaDamages
	 */
	public boolean isAreaDamages() {
		return areaDamages;
	}

	/**
	 * @param areaDamages the areaDamages to set
	 */
	public void setAreaDamages(boolean areaDamages) {
		this.areaDamages = areaDamages;
	}

}