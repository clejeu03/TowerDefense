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
 * <b>Class - TowerOrder</b></br>
 * <p>The TowerOrder class represents the "tower tasks" adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public abstract class ArmyOrder extends Order{

	protected Point position;
	
	/**
	 * Constructor of the TowerOrder class
	 * @param playerId - player id
	 * @param position - position of the Tower
	 */
	public ArmyOrder(PlayerType playerType, Point position) {
		super(playerType);
		this.position = new Point(position);
	}
	
    /**
     * Getter position
     * @see View.ViewManager#refresh()
     * @see GameEngine.GameManager#execute()
     */	
	public Point getPosition(){
		return position;
	}
}

