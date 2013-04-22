package GameEngine;
/**
 * Project - TowerDefense</br>
 * <b>Class - Unit</b></br>
 * <p>The Unit is the main force of the player. A Unit is created from a Base by the 
 * ArmyManager ans countains exactly 50% of the Base's amount of soldier it came from.</br>
 * A Unit is in constant motion on the Map and target an ennemy Base selected by the player.</br>
 * When to ennemy Units collides, they compare their amount and only the one with the
 * biggest amount will go on, but having as amount the difference between the two Units 
 * amount.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see Player
 * @see ArmyManager
 * 
 */

public class Unit {
  /**
   * Holds the number of soldier the Unit contains (soldier is a abstract notion).
   */
  public int amount;
  /**
   * Contains the speed of the Unit.
   * =========> WARNING : All the Unit have the same speed no ?
   */
  public float speed;
  /**
   * Store the current x position of the Unit
   */
  public float position_x;
  /**
   * Store the current y position of the Unit
   */
  public float position_y;
  
  /**
   * Constructor of the Unit class
   */
  public Unit() {
  }
  
  /**
   * Getter that returns the speed of the Unit
   * @return speed
   */
  public float getSpeed() {
	  return speed;
  }
  /**
   * Changes current position of the unit
   * @param position_x - x position
   * @param position_y - y position
   */
  public void setPosition(float position_x, float position_y) {
	  this.position_x = position_x;
	  this.position_y = position_y;
  }

}