package GameEngine;
/**
 * Project - TowerDefense</br>
 * <b>Class - WarManager</b></br>
 * <p>The WarManager is the class responsible for the interactions between Towers on the map
 * and Units that traveled on.</br>
 * His job is to create and possess the ArmyManager and to the TowerManager and to transmit
 * all the informations from to the other manager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see ArmyManager
 * @see TowerManager
 */

public class WarManager {

  /**
   * Constructor of the WarManager class
   */
  public WarManager() {
  }
  /**
   * Function that tell the meeting of a unit and a tower
   */
  public void makeWar(ArmyManager armyManager, TowerManager towerManager) {
	  System.out.println("plop");
	  
	  //Browse all the units
	 for(Unit unit:armyManager.getUnits()){
		 System.out.println("plop2");
		 //Browse all towers
		 for(Tower tower:towerManager.getTowers()){
			
			 System.out.println("plop3");
			 
			 int x = unit.getPosition().x;
			 int y = unit.getPosition().y;
			 int centerX = tower.getPosition().x;
			 int centerY = tower.getPosition().y;
			 int range = tower.getRange();
			 
			 //The unit (x,y) is the area of the tower(centerX, centerY) if : (x - centerX)^2 + (y - centerY)^2 < range^2
			 if(((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY)) < range*range){
				 
				 //So active the tower
				 towerManager.activeTower(tower, unit);
			 }
		 }
	 }

  }
 
}