package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - FrozenTower</b></br>
 * <p>BombTower is a kind of offensive tower that can make limite the enemy's units speed or
 * even freeze them for a while.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see TowerManager
 * @see Tower
 * 
 */

public class FrozenTower extends GunTower {
	public FrozenTower(Point p, int id,int r) {
		  super(p,id,r);
	} 
}