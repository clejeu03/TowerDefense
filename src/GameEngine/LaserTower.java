package GameEngine;

import java.awt.Point;

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
	public LaserTower(Point p, int id,int r) {
		  super(p,id,r);
	} 
}