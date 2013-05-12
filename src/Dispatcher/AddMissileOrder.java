/**
 * Project - TowerDefense</br>
 * <b>Class - AddMissileOrder</b></br>
 * <p>The AddMissileOrder class represents the "create missile" tasks adding to the engine and view queues by the dispatcher</p>
 * <ul>
 * <li>View → Engine : nothing</li>
 * <li>Engine → View : The Engine tells the view that a new missile must be created</li>
 * </ul>
 * <b>Creation :</b> 11/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
package Dispatcher;

import java.awt.Point;

import GameEngine.Player.PlayerType;

public class AddMissileOrder extends Order {

	private PlayerType playerType;
	private Point position;
	
	/**
	 * @param id
	 * @param playerType
	 * @param position
	 */
	public AddMissileOrder(long id, PlayerType playerType, Point position) {
		super((int)id);
		this.setPlayerType(playerType);
		this.setPosition(position);
	}

	/**
	 * @return the playerType
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * @param playerType the playerType to set
	 */
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

}
