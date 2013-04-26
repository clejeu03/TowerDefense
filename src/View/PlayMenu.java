package View;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - PlayMenu</b></br>
 * <p>The PlayMenu is a step menu into the process for creating a new game. It is called after the 
 * homeMenu and inherit from the MainMenusView.</br>
 * Allow the player to choose the number of players and to choose a map.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see ViewManager
 * @see HomeMenu
 * 
 */
@SuppressWarnings("serial")
public class PlayMenu extends MainViews{
	/**
	 * Constructor of the PlayMenu class
	 * @param view
     * @param position
     * @param width
     * @param height
	 */
	public PlayMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
	}
	/**
	 * Function to be called after all the choices are made
	 */
	public void startGame() {
	}

}