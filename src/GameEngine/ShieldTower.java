package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - ShieldTower</b></br>
 * <p>BombTower is a kind of defensive tower that can make increase protection upon the unit from
 * the same team.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see TowerManager
 * @see Tower
 * @see MedicalTower
 * 
 */
public class ShieldTower extends MedicalTower {
	  
	public ShieldTower(Point position, int playerId,int range) {
		  super(position,playerId,range);
	} 
}