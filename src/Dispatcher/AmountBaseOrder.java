/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 7 mai 2013
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
public class AmountBaseOrder extends ArmyOrder{
	private int amount;

	/**
	 * 
	 */
	public AmountBaseOrder(PlayerType playerType, Point position, int amount) {
		super(playerType,position);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

}
