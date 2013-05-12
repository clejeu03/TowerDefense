/**
 * Project - TowerDefense</br>
 * <b>Class - EvolveTowerOrder</b></br>
 * <p>The EvolveTowerOrder class represents the "evolve tower" tasks adding to the engine and view queues by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : The view tell the Engine to evolve a tower into one of it's evolutions</li>
 * <li>Engine ->View : </li>
 * </ul>
 * <b>Creation :</b> 10/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;
import GameEngine.TowerManager.TowerTypes;

public class EvolveTowerOrder extends ArmyOrder {
	
private TowerTypes type;
	/**
	 * @param id
	 * @param playerType
	 * @param position
	 */
	public EvolveTowerOrder(int id, PlayerType playerType, Point position, TowerTypes towerTypes) {
		super(id, playerType, position);
	}

	/**
	 * Getter
	 * @return
	 */
	public TowerTypes getType(){
		return type;
	}
}
