package View;

/**
 * Project - TowerDefense</br>
 * <b>Class - AccueilMenu</b></br>
 * <p>The AccueilMenu is the that lead the player trought the differents steps of the creation 
 * of a new game. The informations that came out after the player selection are directly sent
 * to the GameEngine for him to start the game.</br>
 * The AccueilMenu inherit from the MainMenusView ans is controlled by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see MainMenusView
 * @see ViewManager
 * 
 */
public class AccueilMenu extends MainMenusView {
  /**
   * Define each items to use into the AccueilMenu
   */

  /**
   * Constructor of the AccueilMenu class
   */
  public AccueilMenu() {
  }

  /**
   * One exemple of function handle by a button : play that start a new game by 
   * sending informations to the GameEngine
   */
  public void play() {
  }

  /**
   * The function quit must be accessible often
   */
  public void quit() {
  }

}