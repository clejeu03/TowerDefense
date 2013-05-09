/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 4 mai 2013
 */
package View;

import java.awt.Color;
import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
@SuppressWarnings("serial")
public class TextInfoSprite extends Sprite{

	/**
	 * 
	 */
	public TextInfoSprite(SceneView scene, int id, Point position, boolean clickable, PlayerType playerType, int width, int height) {
		super(scene, id, position,clickable,playerType,width,height);
		setForeground(Color.BLACK);
		
	}

}
