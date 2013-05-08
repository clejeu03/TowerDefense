package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

import Dispatcher.ArmyOrder;

/**
 * Project - TowerDefense</br>
 * <b>Class - TowerManager</b></br>
 * <p>The TowerManager is the class responsible for the Towers management in positionning
 * and in leading attacks.</br>
 * It has access to the Player's Towers and know the Unit's positions.</br>
 * It is also responsible for the creation of a new Tower according to the territoryMap,
 * and for the evolution of the Towers.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see WarManager
 * @see Tower
 * 
 */

public class TowerManager {
  /**
   * Associate all the Towers in game to the Players that owns them in thread safe Map. 
   */
  private ArrayList<Tower> towers;

  /**
   * List all the the types of towers. There are two families of towers :
   * <ul><li>the AttackTower family, that attack your enemies</li>
   * <li>the SupportTower family, that supports your units</li></ul>
   *@see Tower
   */
  public enum TowerTypes{
	  NOTOWER, //replace the ancient zero in the view
	  ATTACKTOWER,
	  SUPPORTTOWER,
	  GUNTOWER,
	  FROSTTOWER,
	  BOMBTOWER,
	  LAZERTOWER,
	  MEDICALTOWER,
	  SHIELDTOWER
  }
  
  /**
   * Constructor of the TowerManager class
   */
  public TowerManager() {
	  super();
	  towers = new ArrayList<Tower>();
  }
  /**
   * Set the selected tower active because of the proximity of a enemy Unit.
   */
  public void activeTower() {
  }
  /**
   * Set the selected Tower inactive because no Unit's in the neighborhood.
   */
  public void desactiveTower() {
  }
 /**
  * Create a Tower of the given type
  * @param playerType
  * @param towerType
  * @param position
  */
  public void createTower(Player.PlayerType playerType, TowerTypes towerType, Point position) {
	  Tower tower = null;
	  
	  switch(towerType){
	  	case ATTACKTOWER:
	  		tower = new AttackTower(position, playerType);
	  		break;
	  	case SUPPORTTOWER :
	  		tower = new SupportTower(position, playerType);
	  		break;
	  	default :
	  		break;
	  }  
	  
	  towers.add(tower);
  }

  /**
   * Remove a tower from the game
   * @param position
   * @see GameManager#execute()
   */
  public void suppressTower(Point position){
	  
	  //Search the tower by it's position
	  for(Tower tower: towers){
		  if(tower.getPosition().equals(position)){
			  towers.remove(tower);
			  break;
		  }
	  }
  }

}