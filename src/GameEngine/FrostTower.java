/**
 * Project - TowerDefense</br>
 * <b>Class - FrostTower</b></br>
 * <p>The Frost Tower is the evolution of the AttackTower. It has the same characteristics plus it can frost enemies, meaning change their
 * speed.
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

public class FrostTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public FrostTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 60, 2, 2000, 5);
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
	}

	/** 
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date) {
		this.lastShootingTime = date;
		return new Missile(this.getPosition(), unit,this.getSpeed(), this.getDamage());
	}

	/** 
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Frost Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
