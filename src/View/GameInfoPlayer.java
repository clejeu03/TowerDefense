/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 26 avr. 2013
 */
package View;

import java.awt.Color;
import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - GameInfoPanel</b></br>
 * <p>The GameInfoPanel class is used to display a user friendly interface while playing the game.
 * The player would be allowed to make actions thanks to buttons and informations hold in the
 * GameInfoPanel.
 * The GameMenuBar is created and managed by the ViewManager.</br>
 * </p> 
 * <b>Creation :</b> 26/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see ViewManager
 */
@SuppressWarnings("serial")
public class GameInfoPlayer extends MainViews{
	
	/**
	 * Constructor of the GameInfoPlayer class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public GameInfoPlayer(ViewManager view, Point position, int width, int height) {
		super(view, position, width,height);
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.blue); 
	}

}
