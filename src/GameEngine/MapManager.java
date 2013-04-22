package GameEngine;
/**
 * Project - TowerDefense</br>
 * <b>Class - MapManager</b></br>
 * <p>The MapManager is the responsible for the three maps the game need and that are calculated
 * after the original image :
 * <ul><li> the HeightMap : that separates plains and hills</li>
 * <li>the TerritoryMap : that generated the allowed areas of each players following the Bases
 * they possess</li>
 * <li> the ProximityMap : that calculate the distance of a Base and all the other Bases in
 * game</li></ul>
 * The MapManager create these maps into int tables before the game starts and has the 
 * responsibility to update them when it's necessary.</br>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Player
 */

public class MapManager {
  /**
   * Store the map that describes the plains and hills of the map
   */
  public int[] heightMap;
  /**
   * Store the map that determine player's territory with their possessions
   */
  public int[] territoryMap;
  /**
   * Store the map that calculate the distance between each Base
   */
  public int[] proximityMap;
  /**
   * Constructor of the MapManager class
   */
  public MapManager(){
	  
  }
  /**
   * Function called each new game at the very beginning of the game, and that calculate
   * all the maps we need.
   */
  public void generateMaps() {
  }

}