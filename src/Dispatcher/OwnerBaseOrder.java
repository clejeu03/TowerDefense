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
 * <b>Class - OwnerBaseOrder</b></br>
 * <p>The OwnerBaseOrder class represents the "change the owner of a base" tasks adding to the view queue by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the owner of a base need to be change.
 * This kind of order can be send when a base is the destination of an attack and has lose all of its soldiers.</li>
 * </ul>
 * <b>Creation :</b> 07/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class OwnerBaseOrder extends ArmyOrder{
	private PlayerType newPlayerType;

	/**
	 * Constructor of the OwnerBaseOrder class
	 * @param playerType
	 * @param position
	 * @param newPlayerType
	 */
	public OwnerBaseOrder(int id, PlayerType playerType, Point position, PlayerType newPlayerType) {
		super(id, playerType,position);
		this.newPlayerType = newPlayerType;
	}

    /**
     * Getter newPlayerType
     * @see View.ViewManager#refresh()
     * @see GameEngine.GameManager#execute()
     */	
	public PlayerType getNewPlayerType(){
		return newPlayerType;
	}	
	
}
