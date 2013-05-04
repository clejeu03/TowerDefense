package GameEngine;
/**
 * Project - TowerDefense</br>
 * <b>Class - Player</b></br>
 * <p>The Player class stand for the actual players of the game, from 1 to 4. The Player class
 * doesn't make a difference between reel player and IAs. It stores the main informations about
 * the player : name, money and color. </br>
 * This class does not represents the player action interface, but only stores few informations
 * that the GameEngine can uses.</br>
 * The GameManager is responsible for the creation of a new Player and for it's management.
 * </p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see GameManager
 * 
 */

public class Player {
  /**
   * Represents the color of the player 
   * 0 electric 
   * 1 Water 
   * 2 Grass
   * 3 Fire
   */
	
  /**
   * List all the sizes the Bases can take. The size of a base affects 
   * the visual size of the Base and the speed of producing units into itself. 
   * We define three sizes for the beginning :
   * <ul><li>small,</li>
   * <li>medium</li>
   * <li>large</li></ul>
   * @see Base
   */
  public enum PlayerType{
	  ELECTRIC,
	  FIRE,
	  GRASS,
	  WATER,
	  NEUTRAL
  }
  
  private PlayerType playerType;

  /**
   * Represents the amount of money the player owns.
   */
  public int money;
  /**
   * Contains the name of the player.
   */
  public String name;
  /**
   * Store the color (or team ?) that represents the player on the map.
   */
  public String color;

  /**
   * Constructor
   */
  public Player(PlayerType playerType) {
	  this.playerType = playerType;
  }
  /**
   * Setter that changes the current amount of player's money.
   * @param money - new amount of money
   */
  public void setMoney(int money) {
	  this.money = money;
  }
  /**
   * Getter that returns the amount of money owned by the player.
   * @return money
   */
  public int getMoney() {
	  return this.money;
  }

}