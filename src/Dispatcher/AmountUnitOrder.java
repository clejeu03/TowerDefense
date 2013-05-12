/**
 * Project - TowerDefense</br>
 * <b>Class - AmountUnitOrder</b></br>
 * <p>The AmountUnitOrder class handle the changements of amount for the unit :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tell the view that the unit have taken damages.</li>
 * </ul>
 * <b>Creation :</b> 07/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;


public class AmountUnitOrder extends Order {

	private int amount;
	
	
	/**
	 * @param id
	 * @param playerType
	 * @param position
	 */
	public AmountUnitOrder(int id, int newAmount) {
		super(id);
		this.amount = newAmount;
	}
	/**
	 * Getter
	 * @return
	 */
	public int getAmount(){
		return amount;
	}

}
