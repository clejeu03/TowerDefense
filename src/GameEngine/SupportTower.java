/**
 * Project - TowerDefense</br>
 * <b>Class - SupportTower</b></br>
 * <p>The SupportTower is one of the two basic towers proposed to the player. The SupportTower
 * is the first component of the defensive towers family.</br>
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

public class SupportTower extends Tower {

	/**
	 * Constructor of the SupportTower class. The SupportTower doesn't have any effects until it's
	 * upgraded.
	 * @param position
	 * @param playerType
	 */
	public SupportTower(int id, int cost, Point position, PlayerType playerType) {
		super(id, cost, position, playerType, 35, 0, 0, 9);
		this.setEvolutions(TowerTypes.MEDICALTOWER, TowerTypes.SHIELDTOWER);
		this.setAreaDamages(false);
	}

	/**
	 * @see GameEngine.Tower#shoot()
	 */
	@Override
	public Missile shoot(Unit unit, long date, int currentIdCount) {
		return null;
	}

	/**
	 * @see GameEngine.Tower#stop()
	 */
	@Override
	public void stop() {
		//System.out.println("Support Tower n°"+this.getId()+" position "+this.getPosition().toString()+" says STOP !!!");
	}

}
