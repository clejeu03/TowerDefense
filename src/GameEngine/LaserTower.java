package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - LazerTower</b></br>
 * <p>BombTower is a kind of offensive tower that can damage enemy's units with a lazer shoot.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see TowerManager
 * @see Tower
 * 
 */
public class LaserTower extends GunTower {
	public LaserTower(Point position, PlayerType playerType,int range) {
		  super(position,playerType,range);
	} 
}