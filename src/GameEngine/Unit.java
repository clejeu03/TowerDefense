package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - Unit</b></br>
 * <p>The Unit is the main force of the player. A Unit is created from a Base by the 
 * ArmyManager ans countains exactly 50% of the Base's amount of soldier it came from.</br>
 * A Unit is in constant motion on the Map and target an ennemy Base selected by the player.</br>
 * When to ennemy Units collides, they compare their amount and only the one with the
 * biggest amount will go on, but having as amount the difference between the two Units 
 * amount.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see Player
 * @see ArmyManager
 * 
 */

public class Unit {
	
  private int id;
  /**
   * Holds the number of soldier the Unit contains (soldier/agent is a abstract notion).
   */
  private int amount;
  private double speed;
  private Point position;
  private Base origin;
  private Base destination;
  private PlayerType playerType;
  
  
  /**
   * Constructor of the Unit class
   * @param origin
   * @param amount
   */
  public Unit(int id, Base origin, Base destination, int amount) {
	  super();
	  this.id = id;
	  this.destination = destination;
	  this.origin = origin;
	  this.amount = amount;
	  this.position = origin.getPosition();
	  this.playerType = origin.getPlayerType();
	  //Temporary !
	  this.speed = 0.5;
  }
  
  /**
   * Getter - Return the base from where started the unit
   * @return base
   */
public Base getOrigin(){
	  return this.origin;
  }
  /**
   * Getter that returns the speed of the Unit
   * @return speed
   */
public double getSpeed() {
	  return speed;
  }
  /**
   * Set the speed of the unit
   * @param speed
   */
public void setSpeed(double speed){
	  this.speed = speed;
  }
  /**
   * Return the amount of the unit 
   * @return amount
   */
public int getAmount(){
	  return this.amount;
  }

/**
 * Return the playerType of the unit
 * @return playerType
 */
  public PlayerType getPlayerType() {
	return playerType;
}

/**
   * Change the amount of the unit
   * @param amount
   */
public void setAmount(int amount){
	  this.amount = amount;
  }
  /**
   * Changes current position of the unit
   * @param position
   */
public void setPosition(Point pos) {
	  this.position = pos;
  }
  /**
   * Return the position of the unit 
   * @return position
   */
public Point getPosition(){
	  return this.position;
  }

/**
 * @return the destination
 */
public Base getDestination() {
	return destination;
}

/**
 * @param destination the destination to set
 */
public void setDestination(Base destination) {
	this.destination = destination;
}

  
  public int getId() {
	return id;
  }

}