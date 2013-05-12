package GameEngine;

import java.awt.Point;
import java.util.Vector;

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
   * Fonction that handle the meetings between towers and units
   * @param armyManager
   * @param towerManager
   * @see WarManager#beginEncounters(ArmyManager, TowerManager)
   * @see WarManager#terminateEncounters(ArmyManager, TowerManager)
   */
  public void war(ArmyManager armyManager, TowerManager towerManager, Long playingTime){
	  beginEncounters(armyManager, towerManager, playingTime);
	  terminateEncounters(armyManager, towerManager);
  }
  
  /**
   * Function that tell the meeting of a unit and a tower
   * @param TowerManager
   * @param ArmyManager
   * @see GameManager#timer()
   */
  public void beginEncounters(ArmyManager armyManager, TowerManager towerManager, Long playingTime) {

	  if(armyManager.getUnits().isEmpty() == false && towerManager.getTowers().isEmpty() == false){
      	for(Unit unit:armyManager.getUnits()){

     		 //Browse all towers
     		 for(Tower tower:towerManager.getTowers()){
     			 
     			 int x = unit.getPosition().x;
     			 int y = unit.getPosition().y;
     			 int centerX = tower.getPosition().x;
     			 int centerY = tower.getPosition().y;
     			 int range = tower.getRange();
     			 
     			 //The unit (x,y) is the area of the tower(centerX, centerY) if : (x - centerX)^2 + (y - centerY)^2 < range^2
     			 if(((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY)) < range*range){

     				 
     				 
     				 //So active the tower
     				 towerManager.activeTower(tower, unit, playingTime);
     			 }
     		 }
     	 }
	  }
  	}

	  /**
	   * Desactive Towers if no units is in there range
	   * @param armyManager
	   * @param towerManager
	   * @see GameManager#timer()
	   */
public void terminateEncounters(ArmyManager armyManager, TowerManager towerManager){
	if(armyManager.getUnits().isEmpty() == false && towerManager.getActivatedTowers().isEmpty() == false){
		//Browse activated towers
      	for(Tower tower:towerManager.getActivatedTowers()){
      		//Browse all units
      		for(Unit unit:armyManager.getUnits()){

        			 int x = unit.getPosition().x;
        			 int y = unit.getPosition().y;
        			 int centerX = tower.getPosition().x;
        			 int centerY = tower.getPosition().y;
        			 int range = tower.getRange();
        			 
        			 //The unit (x,y) is in the area of the tower(centerX, centerY) if : (x - centerX)^2 + (y - centerY)^2 < range^2
        			 if(((x - centerX)*(x - centerX) + (y - centerY)*(y - centerY)) > range*range){
        				 
        				 //So desactive the tower
        				 towerManager.desactiveTower(tower);
        			 }
        		 }
        	 }
     		 
     	 }
  	}
	/**
	 * Move a missile 
	 * @param armyManager
	 * @param towerManager
	 * @return true if the missile need to be move / false if the missile have reached it's target
	 */
	public Boolean moveMissile(Missile missile){
		
		//Temporary !
		
		System.out.println("Missile target : "+missile.getDestination());
		
		if(missile.getPosition() != missile.getDestination()){
			//System.out.println("Missile = move !");
			
			//For each pixel until the speed value, find the following pixel
			for(int i=0; i< ( missile.getSpeed()*10 ); ++i){
			
				System.out.println("Turn "+i+" -- Missile position : "+missile.getPosition());
				
				//Define a vector to be followed by the missile
				Vector2D currentDirection = new Vector2D(missile.getDestination().x - missile.getPosition().x, missile.getDestination().y - missile.getPosition().y);
				Vector2D axeX = new Vector2D(1,0);
				Vector2D axeY = new Vector2D(0,1);
				double factorX = currentDirection.dotProduct(axeX);
				double factorY = currentDirection.dotProduct(axeY);
				
				//Projection on the tallest component's axe and updating the missile position
				if(factorX > factorY){
					if(missile.getDestination().x > missile.getPosition().x)
						missile.setPosition(new Point(missile.getPosition().x+1, missile.getPosition().y));
					else
						missile.setPosition(new Point(missile.getPosition().x-1, missile.getPosition().y));
				}else if(factorY > factorX){
					if(missile.getDestination().y > missile.getPosition().y)
						missile.setPosition(new Point(missile.getPosition().x, missile.getPosition().y+1));
					else
						missile.setPosition(new Point(missile.getPosition().x, missile.getPosition().y-1));
				}else if(factorX - factorY < 0.0001){
					if(missile.getDestination().x > missile.getPosition().x && missile.getDestination().y > missile.getPosition().y)
						missile.setPosition(new Point(missile.getPosition().x+1, missile.getPosition().y+1));
					else
						missile.setPosition(new Point(missile.getPosition().x-1, missile.getPosition().y-1));
				}
				
				//First vector direction : le path to follow
				Vector2D realDirection = missile.getDirection();
				
				//Correction of errors
				
				
				
			}
			return true;
		}else{
			//System.out.println("Missile = reach target!");
			return false;
		}
	}

}