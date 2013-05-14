/**
 * Project - TowerDefense</br>
 * <b>Class - GunTower</b></br>
 * <p>The GunTower is an evolution of the AttackTower. It has four more attacks per seconds but less range and less damages.
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

public class GunTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public GunTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 40, 1, 500, 5);
		this.setEvolutions(TowerTypes.BOMBTOWER, TowerTypes.LAZERTOWER);
		this.setAreaDamages(false);
	}

	/**
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		this.lastShootingTime = date;
		return new Missile(currentIdCount, this, this.getPosition(), unit,this.getSpeed(), this.getDamage());
	}

	/**
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Gun Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
