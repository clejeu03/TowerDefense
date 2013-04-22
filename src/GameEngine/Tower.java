package GameEngine;
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
  /**
   * Holds the x position of the center of the Tower
   */
  public float position_x;
  /**
   * Holds the y position of the center of the Tower
   */
  public float position_y;
  /**
   * Contains the circle size of action of the Tower, if a Unit pass trough, the Tower
   * become active.
   */
  public int range;
  /**
   * Define the strength of damages, that can be negative or positive
   */
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
  public Tower() {
  }

  /**
   * Getter that returns the range of action of the selected Tower
   * @return range
   */
  public int getRange() {
	  return this.range;
  }
  /**
   * Setter that changes the current circle of activation of the selected Tower
   * @param range - new radius of the circle of activation of the Tower
   */
  public void setRange(int range) {
	  this.range = range;
  }
  /**
   * Action to send a missile
   * @see Missile
   */
  public void shoot() {
  }

}