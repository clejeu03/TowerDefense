/**
 * Project - TowerDefense</br>
 * <b>Class - LazerTower</b></br>
 * <p>The LazerTower in an evolution of the GunTower. It can shoot without stopping. It's a very powerful tower.
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

public class LazerTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public LazerTower(int id, int cost, Point position, PlayerType playerType) {
		super(id, cost, position, playerType, 80, 1, 300, 12);
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		this.setAreaDamages(false);
	}

	/** 
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		this.lastShootingTime = date;
		//TODO personalize the damages made by this tower
		return new Missile(currentIdCount, this, this.getPosition(), unit,this.getSpeed(), this.getDamage(), TowerManager.AttackTypes.NORMAL);
	}

	/**
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("BLazer Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
