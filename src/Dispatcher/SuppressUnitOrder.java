/**
 * Project - TowerDefense</br>
 * <b>Class - SuppressUnitOrder</b></br>
 * <p>The SuppressUnitOrder class manage the suppression of a unit if it has reached it's target or if it has taken too much damages :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the unit need to be suppress.</li>
 * </ul>
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

public class SuppressUnitOrder extends ArmyOrder {

	/**
	 * @param id
	 * @param playerType
	 * @param position
	 */
	public SuppressUnitOrder(int id, PlayerType playerType, Point position) {
		super(id, playerType, position);
	}

}
