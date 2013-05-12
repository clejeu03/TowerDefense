/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 4 mai 2013
 */
package Dispatcher;


/**
 * Project - TowerDefense</br>
 * <b>Class - AddUnitOrder</b></br>
 * <p>The AddUnitOrder class represents the "add unit" tasks adding to the engine and view queues by the dispatcher</p>
 * <ul>
 * <li>View → Engine : A player want to launch an attack. Amount represents the percent of soldiers in the source base to send to the destination base.</li>
 * <li>Engine → View : The Engine tells the view that a unit with the "amount" of soldiers need to be display</li>
 * </ul>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class AddUnitOrder extends Order{

	private int srcId;
	private int dstId;
	private int amount;
	/**
	 * 
	 */
	public AddUnitOrder(int id, int srcId, int dstId, int amount) {
		super(id);
		
		this.srcId = srcId;
		this.dstId = dstId;
		this.amount = amount;
	}
	
	
	public int getSrcId() {
		return srcId;
	}


	public int getDstId() {
		return dstId;
	}

	public int getAmount() {
		return amount;
	}

}
