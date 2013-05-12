/**
 * Project - TowerDefense</br>
 * <b>Class - AttackTower</b></br>
 * <p>The AttackTower is one of the two basic towers proposed to the player. The AttackTower
 * is the first component of the offensive towers family.</br>
 * It is inherited from the abstract class Tower and it is controlled by the TowerManager.
 * </p> 
 * <b>Creation :</b> 08/05/2013</br>
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

public class AttackTower extends Tower {

	/**
	 * Constructor of the AttackTower class
	 * @param position
	 * @param playerType
	 */
	public AttackTower(int id, Point position, PlayerType playerType) {
		super(id, position, playerType, 60, 2, 2000, 5);
		this.setEvolutions(TowerTypes.GUNTOWER, TowerTypes.FROSTTOWER);
	}

	/**
	 * @see GameEngine.Tower#shoot()
	 */
	@Override
	public Missile shoot(Unit unit, long date) {
		//System.out.println("Attack Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says SHOOT !!!");
		this.lastShootingTime = date;
		return new Missile(date, this, this.getPosition(), unit,this.getSpeed(), this.getDamage());
	}

	/**
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		System.out.println("Attack Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
