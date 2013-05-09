package GameEngine;
import java.awt.Point;
import GameEngine.ArmyManager.BaseType;
import GameEngine.Player.PlayerType;

/**

 * Project - TowerDefense</br>
 * <b>Class - Base</b></br>
 * <p>The Base is a possession of a player or can be neutral. It gives the player the
 * Units to fight. A Base can change of owner indefinitely. Visually, it has a fixed 
 * position on the map.</br>
 * Base is created and managed by the ArmyManager.</br>
 * It has several sizes that determine the speed of Unit production and the effective place
 * it requires on the map.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see Player
 * @see WarManager
 * 
 */

public class Base {

  private int id;
  /**
   * Represents the number of soldier currently in the Base. A soldier is only a notion
   * and has no existence on itself. The amount increments with time.
   */
  private int amount;
  /**
   * Store the proximityMap deduced from the all Bases positions.
   */
  private Map proximityTab;
  /**
   * Store the owner id of the base
   */
  private PlayerType playerType;
  /**
   * Store the position of the center of the Base.
   */
  public Point position;
  /**
   * Store the amount maximum the Base can produce. When the amount reach sizeMax, the Base 
   * stop the production.
   */
  private int sizeMax;
  /**
   * Defined in a enum in the ArmyManager. Determine the speed of unit production and the visual
   * size of the Base.
   * @see ArmyManager
   */
  public BaseType type;
  /**
   * Define if the Base is a neutral one or not. It affects his amount all the time the neutral
   * base remain neutral.
   */
  protected Boolean neutral;

  
  /**
   * Constructor of the Base class case the type is given. If the base a neutral you must specify 
   * his type (small, medium or large). If the base is active, it will be a medium one.
   * @see ArmyManager.createBase(Point pos, Boolean neutral, BaseType type, Map proxMap);
   */
  public Base(int id, Point position, PlayerType playerType, Boolean neutral, BaseType type, Map proximityTab) {
	  super();
	  this.id = id;
	  this.playerType = playerType;
	  if(position != null){
	 	setPosition(position);
	  }else{
		  //TODO 
	  }
	  if(proximityTab != null){
		  setProximityTab(proximityTab);
	  }else{
		  //TODO
	  }
	  if(neutral){
		  //Specifications if the base is an neutral one
		  createNeutralBase(type);
	  }else{
		  //Create by default a medium sized base if it is not neutral
		   createActiveBase();
	  }
  }
  /**Constructor of the active base only called from the ArmyManager
   * @see ArmyManager.createBase(Point pos, Boolean neutral, Map proxMap)*/
  public Base(int id,Point position, PlayerType playerType, Boolean neutral, Map proximityTab) {
	  super();
	  this.id = id;
	  this.playerType = playerType;
	  setPosition(position);
	  setProximityTab(proximityTab);
	  createActiveBase();
  }
 /**
  * Create a neutral base following the given type
  * @param type
  */
  protected void createNeutralBase(BaseType type){
	  setBaseType(type);
	  setNeutral(true);
	  switch(type){
	  case SMALL :
		  createSmallBase();
		  break;
	  case MEDIUM :
		  createMediumBase();
		  break;
	  case LARGE :
		  createLargeBase();
		  break;
	  default :
		  break;
	  }
  }
  /**
   * Create an active base by default a medium sized one
   */
  protected void createActiveBase(){
	  setBaseType(BaseType.MEDIUM);
	  setNeutral(false);
	  createMediumBase();
  }
  /**
   * Creation of a small base
   */
  protected void createSmallBase(){
	  setAmount(40);
	  setSizeMax(120);
  }
  /**
   * Creation of a medium sized base
   */
  protected void createMediumBase(){
	  setAmount(60);
	  setSizeMax(160);
  }
  /**
   * Creation of a large base
   */
  protected void createLargeBase(){
	  setAmount(80);
	  setSizeMax(200);
  }
  
  
  
  public int getId() {
	return id;
  }
/**
   * Return the current amount of soldier produced by Base.
   * @return amount - number of soldier into the Base
   */
  public int getAmount() {
	  return amount;
  }
  /**
   * Return the proximity map into an int tab.
   * @return proximityTab (that store the proximityMap)
   */
  public Map getProximitytab() {
	  return this.proximityTab;
  }
  /**
   * Set the proximity map of the concerned base
   * @param proximityTab
   */
  public void setProximityTab(Map proximityTab){
	  this.proximityTab = proximityTab;
  }
  /**
   * Setter that changes the current amount of soldier into the Base
   * @param amount - new number of soldier into the Base
   */
  public void setAmount(int amount) {
	  this.amount = amount;
  }
  /**
   * Return the current position of the Base's center
   * @return position
   */
  public Point getPosition(){
	  return this.position;
  }
  /**
   * Set the Base's center position
   * @param pos
   */
  public void setPosition(Point pos){
	  this.position = pos;
  }
  /**
   * Return the max size of the base
   * @return sizeMax
   */
  public int getSizeMax(){
	  return this.sizeMax;
  }
  /**
   * Set the max size of the base
   * @param sizeMax - the new sizeMax
   */
  public void setSizeMax(int sizeMax){
	  this.sizeMax = sizeMax;
  }
  /**
   * Set the type of the Base following the enum declared in GameManager
   * @param type
   * @see GameManager
   */
  public void setBaseType(BaseType type){
	  this.type = type;
  }
  /**
   * Get the type of the Base following the enum declaration in GameManager
   * @return type
   */
  public BaseType getBaseType(){
	  return this.type;
  }
  /**
   * If false the Base is active, if true, the Base is neutral
   * @return neutral
   */
  public Boolean getNeutral(){
	  return this.neutral;
  }
  /**
   * Set the neutral caracteristic of the Base : true or false.
   * @param neutral
   */
  public void setNeutral(Boolean neutral){
	  this.neutral = neutral;
  }
	/**
	 * @return
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}
	/**
	 * @param playerType
	 */
	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
		
	}
}