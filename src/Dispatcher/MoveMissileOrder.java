/**
 * Project - TowerDefense</br>
 * <b>Class - MoveMissileOrder</b></br>
 * <p>The MoveMissileOrder class actualize the position of the missile :</p>
 * <ul>
 * <li>View -> Engine : none</li>
 * <li>Engine -> View : The engine tell the view that the missile must be moved.</li>
 * </ul>
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;

import java.awt.Point;

public class MoveMissileOrder extends Order {
private Point newPosition;
	/**
	 * @param id - type long because missile's id are the date they were created
	 * @param playerType
	 * @param position
	 */
	public MoveMissileOrder(long id, Point newPosition) {
		super((int)id);
		this.newPosition = newPosition;
	}
	
	public Point getNewPosition(){
		return newPosition;
	}

}
