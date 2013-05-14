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
	private boolean isArea;
	
	/**
	 * @param id
	 * @param playerType
	 * @param position
	 */
	public AddMissileOrder(int id, PlayerType playerType, Point position, boolean isArea) {
		super(id);
		this.playerType = playerType;
		this.position = position;
		this.setArea(isArea);
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

	/**
	 * @return the isArea
	 */
	public boolean isArea() {
		return isArea;
	}

	/**
	 * @param isArea the isArea to set
	 */
	public void setArea(boolean isArea) {
		this.isArea = isArea;
	}

}
