/**
 * Project - TowerDefense</br>
 * <b>Class - MedicalTower</b></br>
 * <p>The MedicalTower is an evolution of the SupportTower. Give 10 agents to it's own units. The attack of this tower happens on a area.
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

public class MedicalTower extends Tower {

	/**
	 * @param id
	 * @param position
	 * @param playerType
	 */
	public MedicalTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 70, 0, 2000, 5);
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		this.setAreaDamages(false);
	}

	/** 
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date) {
		this.lastShootingTime = date;
		//TODO personalize this attack
		return new Missile(date, this, this.getPosition(), unit,this.getSpeed(), this.getDamage());
	}

	/** 
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Medical Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");

	}

}
