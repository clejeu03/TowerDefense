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
 * Project - TowerDefense</br>
 * <b>Class - AmountBaseOrder</b></br>
 * <p>The AmountBaseOrder class represents the "change amount of soldier in a base" tasks adding to the view queue by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the amount of soldiers in a base need to be change.
 * This kind of order can be send when a base is the source of an attack (after it has send a Unit)
 * or when a base is the destination of an attack (it has lose some of its soldiers) </li>
 * </ul>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AmountBaseOrder extends ArmyOrder{
	private int amount;

	/**
	 * 
	 */
	public AmountBaseOrder(int id, PlayerType playerType, Point position, int amount) {
		super(id, playerType,position);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

}
