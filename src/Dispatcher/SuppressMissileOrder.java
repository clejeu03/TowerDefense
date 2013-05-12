/**
 * Project - TowerDefense</br>
 * <b>Class - SuppressMissileOrder</b></br>
 * <p>The SuppressMissileOrder class commands the suppression of a missile from the View :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the missile need to be erased.</li>
 * </ul>
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

public class SuppressMissileOrder extends ArmyOrder {

	/**
	 * @param id - long because the missile's id are their creation date
	 * @param playerType
	 * @param position
	 */
	public SuppressMissileOrder(long id, PlayerType playerType, Point position) {
		super((int)id, playerType, position);
	}

}
