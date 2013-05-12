/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package Dispatcher;


/**
 * Project - TowerDefense</br>
 * <b>Class - Order</b></br>
 * <p>The Abstract Order class represents the tasks adding to the engine and view queues by the dispatcher</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public abstract class Order {
	
	protected int id;
	
	/**
	 * Constructor of the Order class
	 * @param id - id of the object affected by the order
	 */
	public Order(int id) {
		super();
		this.id = id;
	}
	
    /**
     * Getter - id of the object affected by the order
     * @see View.ViewManager#refresh()
     * @see GameEngine.GameManager#execute()
     */	
	public int getId() {
		return id;
	}

}
