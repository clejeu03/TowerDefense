package GameEngine;
import java.awt.Point;
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
  protected int[] proximityTab;
  /**
   * Store the position of the center of the Base.
   */
  protected Point position;
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
	  super();
	  
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
   * @return proximityTab (that store the proximityMap)
   */
  public int[] getProximitytab() {
	  return proximityTab;
  }
  /**
   * Set the proximity map of the concerned base
   * @param proximityTab
   */
  public void setProximityTab(int[] proximityTab){
	  this.proximityTab = proximityTab;
  }
  /**
   * Setter that changes the current amount of soldier into the Base
   * @param amount - new number of soldier into the Base
   */
  public void setAmount(int amount) {
	  this.amount = amount;
  }
  /**
   * Return the current position of the Base's center
   * @return position
   */
  public Point getPosition(){
	  return this.position;
  }
  /**
   * Set the Base's center position
   * @param pos
   */
  public void setPosition(Point pos){
	  this.position = pos;
  }
  /**
   * Return the max size of the base
   * @return sizeMax
   */
  public int getSizeMax(){
	  return this.sizeMax;
  }
  /**
   * Set the max size of the base
   * @param sizeMax - the new sizeMax
   */
  public void setSizeMax(int sizeMax){
	  this.sizeMax = sizeMax;
  }
  /**
   * Set the type of the Base following the enum declared in GameManager
   * @param type
   * @see GameManager
   */
  public void setBaseType(BaseType type){
	  this.type = type;
  }
  /**
   * Get the type of the Base following the enum declaration in GameManager
   * @return type
   */
  public BaseType getBaseType(){
	  return this.type;
  }
}