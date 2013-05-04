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
 * <b>Class - AttackBaseOrder</b></br>
 * <p>The AttackBaseOrder class represents the "attack base" tasks adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AttackBaseOrder extends BaseOrder{

	private Point dstPosition;
	private int amount;
	/**
	 * 
	 */
	public AttackBaseOrder(PlayerType srcPlayerType, Point srcPosition, Point dstPosition, int amount) {
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
