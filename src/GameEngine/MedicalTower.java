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
	public MedicalTower(int id, int cost, Point position, PlayerType playerType) {
		super(id, cost, position, playerType, 80, 10, 2500, 12); 
		this.setEvolutions(TowerTypes.NOTOWER, TowerTypes.NOTOWER);
		this.setAreaDamages(false);
	}

	/** 
	 * @see GameEngine.Tower#shoot(GameEngine.Unit, long)
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		this.lastShootingTime = date;
		//TODO personalize this attack
		return new Missile(currentIdCount, this, this.getPosition(), unit,this.getSpeed(), this.getDamage(), TowerManager.AttackTypes.GENERATION);
	}

	/** 
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Medical Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");

	}

}
