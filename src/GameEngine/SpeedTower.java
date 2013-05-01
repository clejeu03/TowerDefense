package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - SpeedTower</b></br>
 * <p>BombTower is a kind of offensive tower that can make increase the speed of the unit from
 * the same team.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see TowerManager
 * @see Tower
 * 
 */
public class SpeedTower extends MedicalTower {
	public SpeedTower(Point position, PlayerType playerType,int range) {
		  super(position,playerType,range);
	} 
}