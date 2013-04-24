/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package Dispatcher;

/**
 * @author aurelie
 *
 */
public abstract class Order {
	
	protected int idOwner;
	
	public Order(int id) {
		super();
		idOwner = id;
	}
	
    /**
     * Getter idOwner
     * @see PlayerInterface.refresh()(appelant)
     * @see Engine.execute() (appelant)
     */	
	public int getIdOwner(){
		return idOwner;
	}

}
