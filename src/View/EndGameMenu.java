package View;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - EndGameMenu</b></br>
 * <p>The EndGameMenu is the menu that resume the main informations and stats about the 
 * finished game</br>
 * The EndGameMenu just display a table of information and the two buttons replay and quit.</br>
 * The EndGameMenu inherits from the MainViews and is controlled by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see ViewManager
 * 
 */
public class EndGameMenu extends MainViews{
  /**
  * Define each items to use into the AccueilMenu
  */

  /**
   * Constructor of the EndGameMenu class
   */
  public EndGameMenu(ViewManager view, Point position, int width, int height){
	  super(view, position, width,height);
  }
  /**
   * Allow the player to quit the game
   */
  public void quit() {
  }
  /**
   * Lead the player back to the AccueilMenu
   * @see HomeMenu
   */
  public void goMenu() {
  }
}