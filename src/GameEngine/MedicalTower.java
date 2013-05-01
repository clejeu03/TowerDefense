package GameEngine;

import java.awt.Point;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - MedicalTower</b></br>
 * <p>The MedicalTower is a child class inherited from Tower and that define the defensive type
 * of tower. This type of Tower can only support the units of the same team by giving them more
 * soldiers, that increasing their resistance, by giving them a shield for one special attack...
 * </br>
 * The MedicalTower, managed by the TowerManager, is the first level of defensive tower and can evolve into all the inherited 
 * classes from this one.</br>
 * Compare to the GunTower that represents the offensive tower type,the MedicalTower has two 
 * attributes more : strenght and duration. </br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see GunTower
 * @see Tower
 * @see TowerManager
 * 
 */
public class MedicalTower extends Tower {
  /**
   * Define what percentage of the effect is applied currently.
   */
  public int strenght;
  /**
   * Define the time the temporary effect last.
   */
  public int duration;
  /**
   * Constructor of the MedicalTower class
   */
  public MedicalTower(Point position, PlayerType playerType,int range) {
	  super(position,playerType,range);
  } 
  /**
   * Setter that changes the percentage of effect applied
   * @param strenght - new strenght to apply between 0 and 1;
   */
  public void setStrenght(int strenght) {
	  this.strenght = strenght;
  }
  /**
   * Getter that return the strenght
   * @return strenght
   */
  public int getStrenght() {
	  return this.strenght;
  }


}