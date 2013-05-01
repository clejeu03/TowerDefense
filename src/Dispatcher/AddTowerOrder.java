/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 1 mai 2013
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - AddTowerOrder</b></br>
 * <p>The AddTowerOrder class represents the "add tower" tasks adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AddTowerOrder extends TowerOrder{
	private int towerType;

	/**
	 * Constructor of the AddTowerOrder class
	 * @param idPlayer - player id
	 * @param position - position of the tower to suppress
	 */
	public AddTowerOrder(PlayerType playerType, Point position, int towerType) {
		super(playerType,position);
		this.towerType = towerType;
	}

	public int getTowerType() {
		return towerType;
	}

}
