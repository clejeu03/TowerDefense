/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 21 mai 2013
 */
package View;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
public class Lazer {
	protected int id;
	protected Point position;
	protected PlayerType playerType;
	protected Point towerPosition;
	/**
	 * 
	 */
	public Lazer(int id, Point position, PlayerType playerType, Point towerPosition) {
		super();
		this.id = id;
		this.position = new Point(position);
		this.playerType = playerType;
		this.towerPosition = new Point(towerPosition);
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public PlayerType getPlayerType() {
		return playerType;
	}
	public Point getTowerPosition() {
		return towerPosition;
	}	

}
