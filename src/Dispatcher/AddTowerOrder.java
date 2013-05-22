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
import GameEngine.TowerManager.TowerTypes;

/**
 * Project - TowerDefense</br>
 * <b>Class - AddTowerOrder</b></br>
 * <p>The AddTowerOrder class represents the "add tower" tasks adding to the engine and view queues by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : The view ask the engine if a tower can be add at the position wanted by a player</li>
 * <li>Engine ->View : The Engine tells the view that a tower need to be display at the given position. 
 * (If the position is (-1,-1), the engine tells the view that the tower can't be add at the position wanted by the player</li>
 * </ul>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AddTowerOrder extends Order{
	
	public enum ErrorType{
		MONEY,
		TERRITORY,
		RELIEF,
		PLACE,
		NONE
	}
	
	private PlayerType playerType;
	private TowerTypes towerType;
	private Point position;
	private int range;
	private ErrorType errorType;

	/**
	 * Constructor of the AddTowerOrder class
	 * @param idPlayer - player id
	 * @param position - position of the tower to suppress
	 */
	public AddTowerOrder(int id, PlayerType playerType, Point position, TowerTypes towerTypes, int range, ErrorType errorType) {
		super(id);
		this.playerType = playerType;
		this.position = position;
		this.towerType = towerTypes;
		this.range = range;
		this.errorType = errorType;
	}
	

	public ErrorType getErrorType() {
		return errorType;
	}


	public PlayerType getPlayerType() {
		return playerType;
	}


	public Point getPosition() {
		return position;
	}


	public TowerTypes getTowerType() {
		return towerType;
	}


	public int getRange() {
		return range;
	}

	
}
