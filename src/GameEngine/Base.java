package GameEngine;

import GameEngine.ArmyManager.BaseType;

/**

 * Project - TowerDefense</br>
 * <b>Class - Base</b></br>
 * <p>The Base is a possession of a player or can be neutral. It gives the player the
 * Units to fight. A Base can change of owner indefinitely. Visually, it has a fixed 
 * position on the map.</br>
 * Base is created and managed by the ArmyManager.</br>
 * It has several sizes that determine the speed of Unit production and the effective place
 * it requires on the map.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see Player
 * @see WarManager
 * 
 */

public class Base {
  /**
   * Represents the number of soldier currently in the Base. A soldier is only a notion
   * and has no existence on itself. The amount increments with time.
   */
  public int amount;
  /**
   * Store the proximityMap deduced from the all Bases positions.
   */
  public int[] proximityTab;
  /**
   * Store the x position of the center of the Base.
   */
  public float position_x;
  /**
   * Store the y position of the center of the Base.
   */
  public float position_y;
  /**
   * Store the amount maximum the Base can produce. When the amount reach sizeMax, the Base 
   * stop the production.
   */
  public int sizeMax;
  /**
   * Defined in a enum in the ArmyManager. Determine the speed of unit production and the visual
   * size of the Base.
   * @see ArmyManager
   */
  public BaseType type;

  
  /**
   * Constructor of the Base class.
   */
  public Base() {
  }
  /**
   * Return the current amount of soldier produced by Base.
   * @return amount - number of soldier into the Base
   */
  public int getAmount() {
	  return amount;
  }
  /**
   * Return the proximity map into an int tab.
   * @return proximityTab - int table that store the proximityMap
   */
  public int[] getProximitytab() {
	  return proximityTab;
  }
  /**
   * Setter that changes the current amount of soldier into the Base
   * @param amount - new number of soldier into the Base
   */
  public void setAmount(int amount) {
	  this.amount = amount;
  }


}