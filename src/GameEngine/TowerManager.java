package GameEngine;

import java.awt.Point;
import java.util.ArrayList;

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
  
  private ArrayList<Tower> activatedTowers;
  
  private ArrayList<Missile> missiles;

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
	  activatedTowers = new ArrayList<Tower>();
	  missiles = new ArrayList<Missile>();
  }
  /**
   * Set the selected tower active because of the proximity of a enemy Unit.
   * @param toActiveTower
   * @param unit - target
   * @see WarManager
   */
  public void activeTower(Tower toActiveTower, Unit unit, Long date) {
	  for(Tower tower:towers){
		  if(tower.getId() == toActiveTower.getId()){

			  //TODO multiple targets
			  long currentTime = GameManager.getTime();
			  
			  //Temporary !
			  System.out.println("currentTime : "+currentTime+" - date : "+date+" cadency : "+toActiveTower.getCadency()*10);
			  
			  if(currentTime - date == toActiveTower.getCadency()*10){
				  missiles.add(toActiveTower.shoot(unit, date));
			  }
		  }
	  }
  }
  /**
   * Set the selected Tower inactive because no Unit's in the neighborhood.
   * @param toDesactiveTower
   * @see WarManager
   */
  public void desactiveTower(Tower toDesactiveTower) {
	  for(Tower tower:activatedTowers){
		  if(tower.getId() == toDesactiveTower.getId()){
			  toDesactiveTower.stop();
		  }
	  }
  }
 /**
  * Create a Tower of the given type
  * @param playerType
  * @param towerType
  * @param position
  */
  public void createTower(int id, Player.PlayerType playerType, TowerTypes towerType, Point position) {
	  Tower tower = null;
	  
	  switch(towerType){
	  	case ATTACKTOWER:
	  		tower = new AttackTower(id, position, playerType);
	  		break;
	  	case SUPPORTTOWER :
	  		tower = new SupportTower(id, position, playerType);
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
  public void suppressTower(int id, Point position){
	  
	  //Search the tower by it's position
	  for(Tower tower: towers){
		  if((tower.getPosition().equals(position))&&(tower.getId()==id)){
			  towers.remove(tower);
			  break;
		  }
	  }
  }

  /**
   * Getter - return the current tower list
   * @return
   */
  public ArrayList<Tower> getTowers(){
	  return this.towers;
  }
  
  /**
   * Getter - return the current activated tower list
   * @return
   */
  public ArrayList<Tower> getActivatedTowers(){
	  return this.activatedTowers;
  }
  /**
   * Getter - return the missiles list
   * @return
   */
  public ArrayList<Missile> getMissiles(){
	  return this.missiles;
  }
}