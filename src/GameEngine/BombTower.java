package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - BombTower</b></br>
 * <p>BombTower is a kind of offensive tower that can make damages into entire areas.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see TowerManager
 * @see Tower
 * 
 */
public class BombTower extends GunTower{
  /**
   * Define the time of inactivity a shoot involve
   */
	public int latency;
  /**
   * Constructor of the BombTower class
   */
	public BombTower(Point position, int playerId,int range) {
		  super(position,playerId,range);
	} 

}