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
 * <b>Class - AddUnitOrder</b></br>
 * <p>The AddUnitOrder class represents the "add unit" tasks adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AddUnitOrder extends ArmyOrder{

	private Point dstPosition;
	private int amount;
	/**
	 * 
	 */
	public AddUnitOrder(PlayerType srcPlayerType, Point srcPosition, Point dstPosition, int amount) {
		super(srcPlayerType,srcPosition);
		
		this.dstPosition = new Point(dstPosition);
		this.amount = amount;
	}
	
	public Point getDstPosition() {
		return dstPosition;
	}
	
	public int getAmount() {
		return amount;
	}

}
