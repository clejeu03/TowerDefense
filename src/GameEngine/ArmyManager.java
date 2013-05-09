/**
 * Project - TowerDefense</br>
 * <b>Class - ArmyManager</b></br>
 * <p>The ArmyManager is the class responsible for the Unit management knowing the Bases. Directly</br>
 * under the control of the WarManager, and allows it to pass both damage attacks on units and</br>
 * inform the Towers of the Units presence.</br>
 * It has access to the Player's bases and all the Units.</br>
 * It is also responsible for the creation of a new base, different of the four start bases.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see Player
 * @see WarManager
 * 
 */

package GameEngine;
import java.awt.Point;
import java.util.ArrayList;

import GameEngine.Player.PlayerType;



public class ArmyManager {
  /**
   * Save all the bases
   */
  private ArrayList<Base> bases;

  /**
   * Save all the units
   */
  private ArrayList<Unit> units;
  
  /**
   * List all the sizes the Bases can take. The size of a base affects 
   * the visual size of the Base and the speed of producing units into itself. 
   * We define three sizes for the beginning :
   * <ul><li>small,</li>
   * <li>medium</li>
   * <li>large</li></ul>
   * @see Base
   */
  public enum BaseType{
	  SMALL,
	  MEDIUM,
	  LARGE
  }

  /**
   * Constructor of the ArmyManager class
   * @param bases - list of bases
   * @see GameManager#GameManager()
   */
  public ArmyManager(ArrayList<Base> bases) {
	  super();
	  
	  //Initializations
	  units = new ArrayList<Unit>();
	  this.bases = bases;
	  
	  System.out.println("Initialization with bases : "+bases.toString());
	  
	  }
  
  /**
   * Constructor of the ArmyManager class
   */
  public ArmyManager() {
	  super();
	  
	  //Initializations
	  units = new ArrayList<Unit>();
	  bases = new ArrayList<Base>();
	  }
  
  
  /**
   * Just create the unit and make the agents numbers of both base and unit right
   * @param origin - Base from where the unit is sent
   * @param destination
   * @see WarManager
   */
  public void launchUnit(Base origin, Base destination) {
	  
	  //Calculates the agents number
	  int baseAmount = origin.getAmount();
	  int unitAmount = baseAmount%2;
	  
	  //Creates the new Unit
	  Unit unit = new Unit(origin, destination, unitAmount);
	  
	  //Changing the base amount
	  origin.setAmount(baseAmount-unitAmount);
	  
	  //Adding the unit to the hashMap by finding the owner of the base
	  units.add(unit);
	  
  }
  
  /**
   * Create a new Base, with the BaseType parameter, and add it to the global list of bases
   * @param pos
   * @param playerType - the owner
   * @param neutral - true or false
   * @param type
   * @param proxMap
   * @see ArmyManager#BaseType
   	*@return base
   	*/
  public Base createBase(Point pos, PlayerType playerType, Boolean neutral, BaseType type, Map proxMap) {
	Base base = new Base(pos, playerType, neutral, type, proxMap);
	bases.add(base);
	return base;
  }
  
  /**
   * Create a simple Base and add it to the global list of bases
   * @param pos
   * @param playerType - the owner
   * @param neutral - true or false
   * @param proxMap
   * @return base
   */
  public Base createBase(Point pos, PlayerType playerType, Boolean neutral, Map proxMap) {
	Base base = new Base(pos, playerType, neutral, proxMap);
	bases.add(base);
	return base;
  }
  /**
   * Change the owner of the base, or add one if the base was neutral
   * @param base
   * @param player - the new player that own the base
   */
  public void reattributeBase(Base base, PlayerType playerType){
	  //Change the owner
	  base.setPlayerType(playerType);
  }
  
  /**
   * Move a Unit, from its position to a new position, in the path to the Unit's destination.
   */
  public void moveUnits() {
	  	//TODO take the array units and move all of it's components
  }
  
 /**
  * Getter - return the list of bases 
  * @return bases
  */
  public ArrayList<Base> getBases(){
	  return this.bases;
  }
  
  /**
   * Getter - return the list of units
   * @return units
   */
   public ArrayList<Unit> getUnits(){
 	  return this.units;
   }

}