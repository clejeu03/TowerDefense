/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package Dispatcher;

import java.awt.Point;

/**
 * @author aurelie
 *
 */
public abstract class TowerOrder extends Order{

	protected Point position;
	
	/**
	 * 
	 */
	public TowerOrder(int id, Point p) {
		super(id);
		position = new Point(p);
	}
	
    /**
     * Getter position
     * @see PlayerInterface.refresh()(appelant)
     * @see Engine.execute() (appelant)
     */	
	public Point getPosition(){
		return position;
	}
}

