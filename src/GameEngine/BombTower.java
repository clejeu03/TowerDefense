/**
 * Project - TowerDefense</br>
 * <b>Class - BombTower</b></br>
 * <p>The BombTower is an evolution of the GunTower. It has only one attack each 10 seconds but it's attack hit every units in it's range and make
 * huge damages.
 * </p> 
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see WarManager
 * @see TowerManager
 * @see Tower
 * 
 */
package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;
import GameEngine.TowerManager.TowerTypes;

public class BombTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public BombTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 120, 20, 10000, 5);
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		this.setAreaDamages(true);
	}

	/**
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		this.lastShootingTime = date;
		//TODO personalize the damages made by this tower
		return new Missile(currentIdCount, this, this.getPosition(), unit,this.getSpeed(), this.getDamage());
	}

	/**
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Bomb Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
