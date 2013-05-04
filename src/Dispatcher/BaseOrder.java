/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 4 mai 2013
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - BaseOrder</b></br>
 * <p>The BaseOrder class represents the "base tasks" adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 04/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public  abstract class BaseOrder extends Order{

	protected Point srcPosition;
	/**
	 * 
	 */
	public BaseOrder(PlayerType playerType, Point srcPosition) {
		super(playerType);
		this.srcPosition = new Point(srcPosition);
	}
	  /**
     * Getter srcPosition
     * @see View.ViewManager#refresh()
     * @see GameEngine.GameManager#execute()
     */	
	public Point getSrcPosition(){
		return srcPosition;
	}
}
