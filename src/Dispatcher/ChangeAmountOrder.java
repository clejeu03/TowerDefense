/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 7 mai 2013
 */
package Dispatcher;


/**
 * Project - TowerDefense</br>
 * <b>Class - AmountBaseOrder</b></br>
 * <p>The AmountBaseOrder class represents the "change amount of soldier in a base or an unit" tasks adding to the view queue by the dispatcher :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tells the view that the amount of soldiers in a base or unit need to be change.
 * This kind of order can be send when a base is the source of an attack (after it has send a Unit)
 * or when a base or an unit is the destination of an attack (it has lose some of its soldiers) </li>
 * </ul>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class ChangeAmountOrder extends Order{
	
	private int amount;

	/**
	 * 
	 */
	public ChangeAmountOrder(int id, int amount) {
		super(id);
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}

}
