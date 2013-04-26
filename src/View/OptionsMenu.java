package View;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - OptionsMenu</b></br>
 * <p>The OptionsMenu is a step menu into the process of creating a new game. It is an optional
 * Menu but that can settle personalized parameters for the user. This class inherits from
 * the MainMenusView and is managed by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see ViewManager
 * @see HomeMenu
 * 
 */

@SuppressWarnings("serial")
public class OptionsMenu extends MainViews {
	
	/**
	 * Constructor of the OptionsMenu class
	 * @param view
     * @param position
     * @param width
     * @param height
	 */
	public OptionsMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
	}
}