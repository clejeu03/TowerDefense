package View;

import javax.swing.JPanel;

/**
 * Project - TowerDefense</br>
 * <b>Class - GameToolbar</b></br>
 * <p>The GameToolbar class is used to display a user friendly interface while playing the game.
 * The player would be allowed to make actions thanks to buttons ans informations hold in the
 * GameToolbar.
 * The GameToolbar is created and managed by the ViewManager.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see ViewManager
 * 
 */

public class GameToolbar extends JPanel {

  /**
  * Contains the x size of the Menu
  */
  public int size_x;
  /**
  * Contains the y size of the Menu
  */
  public int size_y;

  /**
   * Define on wich state the player is so we can display correct buttons and informations.
   * ====> WARNING : how to use in reality ?
   */
  public int state;

  /**
   * Constructor of the GameToolbar class
   */
  public GameToolbar() {
  }
  /**
   * Setter that changes the size of the selected GameToolbar
   * @param size - the new size to apply
   */
  public void setToolbarSize() {
  }
  /**
   * Getter that returns the current size of the GameToolbar
   * @return size
   */
  public void getToolbarSize() {
  }

}