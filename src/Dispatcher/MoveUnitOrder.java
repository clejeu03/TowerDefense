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
 * <b>Class - MoveUnitOrder</b></br>
 * <p>The MoveUnitOrder class represents the "move or suppress Unit" tasks adding to the view queue by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the position of a unit need to be change.
 * 	If the newPosition is set to (-1,-1), it means that the unit need to be remove from the view.</li>
 * </ul>
 * <b>Creation :</b> 07/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class MoveUnitOrder extends Order{
	private Point newPosition;

	/**
	 * Constructor of the MoveUnitOrder class
	 * @param playerType
	 * @param position
	 * @param newPosition
	 */
	public MoveUnitOrder(int id, Point newPosition) {
		super(id);
		this.newPosition = newPosition; 
	}
	
    /**
     * Getter newPosition
     * @see View.ViewManager#refresh()
     * @see GameEngine.GameManager#execute()
     */	
	public Point getNewPosition(){
		return newPosition;
	}

}
