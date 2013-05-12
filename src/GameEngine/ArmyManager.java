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
  public Unit launchUnit(int id, Point src, Point dst, int attackPercent) {
	  Base origin = null;
	  Base destination = null;

	  //Retrieve source and destinations bases
	  for(Base base: bases){
		  if(base.getPosition().equals(src)){
			  origin = base;
		  }
		  if(base.getPosition().equals(dst)){
			  destination = base;
		  }
	  }

	  //Calculates the agents number
	  int baseAmount = origin.getAmount();
	  int attackAmount = (attackPercent * baseAmount)/100;

	  //Creates the new Unit
	  Unit unit = new Unit(id, origin, destination, attackAmount);

	  //Changing the base amount
	  origin.setAmount(baseAmount-attackAmount);

	  //Adding the unit to the hashMap by finding the owner of the base
	  units.add(unit);

	  return unit;

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
  public Base createBase(int id, Point pos, PlayerType playerType, Boolean neutral, BaseType type, Map proxMap) {
	Base base = new Base(id, pos, playerType, neutral, type, proxMap);
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
  public Base createBase(int id, Point pos, PlayerType playerType, Boolean neutral, Map proxMap) {
	Base base = new Base(id, pos, playerType, neutral, proxMap);
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
   * @see GameManager#timer()
   * @return true if the unit need to be move / false if the unit have reached it's destination
   */
  public boolean moveUnit(Unit unit, MapManager mapManager) {
	 
	  //The unit need to be move
	  if(!unit.getPosition().equals(unit.getDestination().getPosition())){

		  //Find the way ! For each pixel until the unit's speed, find the smallest value and go on....
  		  for(int i=0; i<(unit.getSpeed()*10);++i){	
  			  unit.setPosition(mapManager.proximityMapFindMin(unit.getDestination().getProximitytab(), unit.getPosition()));
  		  }
  		  unit.setPosition(unit.getPosition());
 
  		  return true;
			
  	  //The unit have reached it's destination
	  }else{
		  
		  //Updating the base amount
		  unit.getDestination().setAmount(unit.getDestination().getAmount() - unit.getAmount());
	
		  return false;
	  }
	  
  }
  /**
   * Suppress a unit from the current game
   * @param id
   * @param position
   */
  public void suppressUnit(int id, Point position){
	  for(Unit unit:this.getUnits()){
		  if(unit.getPosition().equals(position) && unit.getId() == id){
			  units.remove(unit);
			  break;
		  }
	  }
  }
  /**
   * Suppress a unit from the current game
   * @param toSuppressUnit
   */
  public void suppressUnit(Unit toSuppressUnit){
	  for(Unit unit:this.getUnits()){
		  if(unit.getPosition().equals(toSuppressUnit.getPosition()) && unit.getId() == toSuppressUnit.getId()){
			  units.remove(unit);
			  break;
		  }
	  }
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