package GameEngine;
/**
 * Project - TowerDefense</br>
 * <b>Class - GameManager</b></br>
 * <p>The GameManager is the one that can start the game and determine who has won.</br>
 * It creates the Player class and manage it. It has access to the WarManager when it's necessary,
 * for example when there is a tower's construction to check the player's money.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Player
 * @see WarManager
 * 
 */

public class GameManager {
  /**
   * Boolean that indicates when the game ends :
   * <ul><li>0 : play</li>
   * <li>1 : stop</li></ul>
   */
  public Boolean gameOver;

  /**
   * Constructor
   */
  public GameManager() {
  }
  /**
   * Check the value of gameOver and stop the game by sending a signal to the View.
   */
  public void gameOver() {
  }
  /**
   * Create a new Player class, max 4 players.
   */
  public void newPlayer() {
  }

}