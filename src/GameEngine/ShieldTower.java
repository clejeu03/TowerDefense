/**
 * Project - TowerDefense</br>
 * <b>Class - ShieldTower</b></br>
 * <p>The ShieldTower is an evolution of the SupportTower. It can only target it's own units and give them a shield for one attack : if the unit
 * is touch during an attack, it will have no damages. But the shield can only be use once. The ShieldTower shield is launch in it's entire area.
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

public class ShieldTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public ShieldTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 80, 0, 2000, 15);
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		this.setAreaDamages(true);
	}

	/**
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		this.lastShootingTime = date;
		//TODO personalize this attack
		return new Missile(currentIdCount, this, this.getPosition(), unit,this.getSpeed(), this.getDamage(), TowerManager.AttackTypes.SHIELD);
	}

	/** 
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Shield Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
