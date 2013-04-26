package GameEngine;
import java.awt.Point;
import java.util.HashMap;

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

public class ArmyManager {
  /**
   * Associates in a HashMap thread safe the Bases 
   * to the Players who own them.
   * @see Base
   * @see Player
   */
  public HashMap<Base, Player> whomBaseList;

  /**
   * Associates in a HashMap thread safe the Units
   * currently on the map to the Players who own them. 
   * @see Units
   * @see Player
   */
  public HashMap<Unit, Player> unitList;
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
   */
  public ArmyManager() {
	  super();
	  }
  /**
   * Initialize the attributes whomBaseList and unitList at the creation of the game.
   */
  public void init() {
  }
  /**
   * Create a new group, called Unit, from the selected Base with an amount equal 50% from the 
   * Base amount
   * @param origin - Base from where the unit is sent
   * @see Base
   */
  public void launchUnit(Base origin) {
	  //Calculates the agents number
	  int baseAmount = origin.getAmount();
	  int unitAmount = baseAmount%2;
	  //Creates the new Unit
	  Unit unit = createUnit(origin, unitAmount);
	  //Changing the base amount
	  origin.setAmount(baseAmount-unitAmount);
	  //Adding the unit to the hashMap by finding the owner of the base
	  unitList.put(unit, whomBaseList.get(origin));
	  
  }
  /**
   * Create a new unit from the base
   * @param origin - base from where the unit is launch
   * @param amount
   * @return unit
   */
  public Unit createUnit(Base origin, int amount){
	  Unit unit = new Unit(origin, amount);
	  return unit;
  }
  
  /**
   * Create a new base, neutral or active.
   * =========> WARNING : And attributes it to a player ?
   * @param pos - position
   * @param neutral - is neutral or not ?
   * @param type - BaseType (small, medium or large)
   * @param proxMap - proximityMap
   * @return base
   */
  public Base createBase(Point pos, Boolean neutral, BaseType type, Map proxMap) {
	Base base = new Base(pos, neutral, type, proxMap);
	return base;
  }
  /**
   * Create especially an active base.
   * =========> WARNING : And attributes it to a player ?
   * @param pos position
   * @param neutral is it neutral or not ?
   * @param proxMap the proximityMap
   * @return base
   */
  public Base createBase(Point pos, Boolean neutral, Map proxMap) {
	Base base = new Base(pos, neutral, proxMap);
	return base;
  }
  /**
   * Change the owner of the base, or add one if the base was neutral
   * @param base
   * @param player - the new player that own the base
   */
  public void attributeBase(Base base, Player player){
	  if(base.getNeutral()){
		  //Si la base est neutre alors on lui attribue un possesseur pour la première fois
		  whomBaseList.put(base, player);
	  }else{
		  //Sinon on enlève le dernier possesseur et lui attribue le nouveau
		  whomBaseList.remove(base);
		  whomBaseList.put(base, player);
	  }
  }
  
  /**
   * Move a Unit, from its position to a new position, in the path to the Unit's destination.
   * @param destination - Base to where the unit must go
   * @see Base
   */
  public void moveUnit(Base destination) {
	  	
  }
 

}