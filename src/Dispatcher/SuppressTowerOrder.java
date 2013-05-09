/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - SuppressTowerOrder</b></br>
 * <p>The SuppressTowerOrder class represents the "suppress tower" tasks adding to the engine and view queues by the dispatcher</p>
 * <ul>
 * <li>View → Engine : A player want to suppress on of his tower</li>
 * <li>Engine → View : A tower need to be remove from the view</li>
 * </ul>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class SuppressTowerOrder extends ArmyOrder{

	/**
	 * Constructor of the SuppressTowerOrder class
	 * @param idPlayer - player id
	 * @param position - position of the tower to suppress
	 */
	public SuppressTowerOrder(int id, PlayerType playerType, Point position) {
		super(id, playerType,position);
	}

}
