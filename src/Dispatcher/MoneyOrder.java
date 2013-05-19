/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 19 mai 2013
 */
package Dispatcher;

import GameEngine.Player.PlayerType;


/**
 * Project - TowerDefense</br>
 * <b>Class - MoneyOrder</b></br>
 * <p>The MoneyOrder class represents the "change amount of soldier in a base or an unit" tasks adding to the view queue by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View or AI : The engine tells the view (or AI) that the player money need to be change.
 * </li>
 * </ul>
 * <b>Creation :</b> 19/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class MoneyOrder extends Order{
	
	private int amount;
	private PlayerType playerType;
	/**
	 * 
	 */
	public MoneyOrder(int id, int amount, PlayerType playerType) {
		super(id);
		this.amount = amount;
		this.playerType = playerType;
	}
	
	public int getAmount() {
		return amount;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}
}
