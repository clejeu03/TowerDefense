package GameEngine;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import GameEngine.Map;
import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - MapManager</b></br>
 * <p>The MapManager is the responsible for the three maps the game need and that are calculated
 * after the original image :
 * <ul><li> the HeightMap : which separates plains and hills</li>
 * <li>the TerritoryMap : which generated the allowed areas of each players following the Bases
 * they possess</li>
 * <li> the ProximityMap : which calculate the distance of a Base and all the other Bases in
 * game</li></ul>
 * The MapManager create these maps as Map before the game starts and has the 
 * responsibility to update them when it's necessary.</br>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Player
 */

public class MapManager implements Serializable{
  /**
  * Default serial version id
  * */
  private static final long serialVersionUID = 1L;

/**
   * Store the number maximum of Player 
   */
  private final int maxnumberOfPlayer = 4;
  
  /**
   * Store the number maximum of Neutral Bases
   */
  private final int maxnumberOfNeutralBases = 5;
  
  /**
   * Store the number of players playing
   */
  private final int numberOfPlayer;
  
  /**
   * Store the number of neutral bases on the map
   */
  private int numberOfNeutralBases;
  
  /**
   * Store the path of the image we'll use to create our maps
   */
  private String imagePath;
  
  /**
   * Store the name of the image we'll use to create our maps
   */
  private String imageName;
  
  /**
   * Store the map that describes the plains and hills of the map
   */
  private HeightMap heightMap;
  
  /**
   * Store the map that determine player's territory
   */
  private TerritoryMap[] playerTerritoryMap;
  
  /**
   * Store the map that determine neutral bases' territory
   */
  private ArrayList<TerritoryMap> neutralTerritoryMap;
  
  /**
   * Store the position of each player's base
   */
  private Point[] playerBasePosition;

/**
   * Store the map that calculate the distance to each player's Base
   */
  private ProximityMap playerProximityMap[];

/**
   * Store the position of each neutral bases
   */
  private ArrayList<Point> neutralBasePosition;
  
  /**
   * Store the map that calculate the distance to each neutral Base
   */
  private ArrayList<ProximityMap> neutralProximityMap;
  
  private ArrayList<PlayerType> playersType;
  
  /**
	 * MapManager's constructor, initiate and create the heightMap and the territoryMap
	 * @param i_imagepath path of the local image to analyse
	 */
	MapManager(String i_imageName, ArrayList<PlayerType> playersType){
		super();
		imageName = i_imageName;
		imagePath = "img/map/"+i_imageName+".jpg";
		numberOfPlayer = playersType.size();
		numberOfNeutralBases = 0;
		this.playersType = playersType;
		if (!existingMapManager()){
			System.out.println("Creating Maps !");
			
			playerBasePosition = new Point[numberOfPlayer];
			playerProximityMap = new ProximityMap[numberOfPlayer];
			neutralBasePosition = new ArrayList<Point>();
			neutralProximityMap = new ArrayList<ProximityMap>();
			playerTerritoryMap = new TerritoryMap[numberOfPlayer];
			neutralTerritoryMap = new ArrayList<TerritoryMap>();
			
			generateHeightMap();
			generateRelief();
			generateTerritoryMap();
			generateAllProximityMap();
			
			saveAllTerritoryMap(playersType);
			saveAsTMP();
		}
		else{
			System.out.println("Reading Maps!");
		}

		saveTerritoryMap(playersType);
	}
	
	/**
	 * Test if there's an existing MapManager in a tmp file which meet the requirements
	 * @return true if the tmp exists, false otherwise
	 * @see #MapManager()
	 */
	private boolean existingMapManager(){
		File tmpFile = new File("tmp/"+imageName+numberOfPlayer+".tmp");
		if (tmpFile.exists()){
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			
			try {
				fis = new FileInputStream(tmpFile);
			} catch (FileNotFoundException e) {
				System.out.println("Error : tmpFile not found");
				e.printStackTrace();
			}
			
			try {
				ois = new ObjectInputStream(fis);
				try {
					this.setMapManager((MapManager)ois.readObject());
					ois.close();
					fis.close();
					return true;
				} catch (ClassNotFoundException e) {
					System.out.println("Error : ois.readObject didn't return an MapManager");
					e.printStackTrace();
				}
			} catch (IOException e) {
				System.out.println("Error : ObjectInputStream fis throw an IOException");
				e.printStackTrace();
			}
			return false;
		}
		else
			return false;
	}
	
	/**
	 * Set the MapManager according to an other pre-existing MapManager
	 * @param mm : pre-existing MapManager
	 * @see #existingMapManager()
	 */
	private void setMapManager(MapManager mm){
		this.heightMap=mm.heightMap;
		this.playerTerritoryMap=mm.playerTerritoryMap;
		this.neutralTerritoryMap=mm.neutralTerritoryMap;
		this.playerBasePosition=mm.playerBasePosition;
		this.playerProximityMap=mm.playerProximityMap;
		this.neutralBasePosition=mm.neutralBasePosition;
		this.neutralProximityMap=mm.neutralProximityMap;
	}
	
	/**
	 * Generate the HeightMap using a local picture and save it as a png at /map
	 * @see #MapManager()
	 */
	private void generateHeightMap(){
		BufferedImage img = null;//Local image containment
		
		try {
			img = ImageIO.read(new File(imagePath)); //We get the image
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture de l'image de Map");
			e.printStackTrace();
		}
		
		//Initiate the HeightMap
		heightMap = new HeightMap(img.getWidth(),img.getHeight());
		
		for (int y = 0; y < heightMap.getHeight();y++){ 
			for (int x = 0; x < heightMap.getWidth();x++){
				
				// get the color data of each pixel
				int color = img.getRaster().getSample(x,y,0); 
				
				//If pixel data is between 0 and 5 (black) or between 250 and 255 (white) => it's height information	
				if ((color>=0 && color<=5)||(color >=250 && color <= 255)){ 
					//Divide the value in order to get more lisible information
					color = color/(255/(maxnumberOfPlayer+1)); 
					if (color==0 || color==5)
						heightMap.setPixel(x,y, color);
					else
						heightMap.setPixel(x,y, 5);
				}
				//Else if the pixel color is grey => it refers to bases' position
				else {
					color = (int)Math.round((float)color/(255/(maxnumberOfPlayer+2)));
					if (color <= numberOfPlayer)
					{
						//Store the position of the base according to its player
						int player = color - 1;
						playerBasePosition[player]=new Point(x,y);
					}
					else{
						if (numberOfNeutralBases<maxnumberOfNeutralBases)
						{
							neutralBasePosition.add(new Point(x,y));
							numberOfNeutralBases++;
						}
							
					}
					heightMap.setPixel(x,y, 5);
				}
			}
		}	
		heightMap.saveAsPNG("hm.png");
	}
	
	/**
	 * Getter - get the bases position
	 * @return Point[]
	 */
	public Point[] getPlayerBasePosition() {
		return playerBasePosition;
	}
	
	/**
	 * Getter - get the proximityMap of the player base 
	 * @return Map
	 */
	public Map getPlayerProximityMap(int index) {
		return playerProximityMap[index];
	}
	
	/**
	 * Getter - get the proximityMap of the neutral base 
	 * @return Map
	 */
	public Map getNeutralProximityMap(int index) {
		return neutralTerritoryMap.get(index);
	}
	
	/**
	 * Generate relief for the HeightMap and save the image at tmp/hrm.png
	 * @see #MapManager()
	 */
	private void generateRelief(){
		//Intensity of the relief
		int reliefDown = 17;
		int reliefLeft = 16;
		int reliefRight = 16;
		
		for (int y = 0; y < heightMap.getHeight();y++){ 
			for (int x = 0; x < heightMap.getWidth();x++){
				if (heightMap.getPixel(x,y)==0){
					
					//Relief bottom
					if(y<heightMap.getHeight()-1){
						if (heightMap.getPixel(x,y+1)==5)
						{
							for (int i=1;i<reliefDown;i++){
								if((y+i)<heightMap.getHeight())
									heightMap.setPixel(x,y+i,6);
							}
						}
					}
					
					//Relief left
					if (x>0){
						if (heightMap.getPixel(x-1,y)==5)
						{
							for (int i=1;i<reliefLeft;i++)
							{
								if((x-i)>=0)
									heightMap.setPixel(x-i,y,6);
							}
						}
					}
					
					//Relief right
					if (x<heightMap.getWidth()-1){
						if (heightMap.getPixel(x+1,y)==5)
						{
							for (int i=1;i<reliefRight;i++)
							{
								if((x+i)<=heightMap.getWidth())
									heightMap.setPixel(x+i,y,6);
							}
						}
					}
					
				}
			}
		}
		heightMap.saveAsPNG("hrm.png");
	}
	
	/**
	 * Generate the TerritoryMap
	 * @see #MapManager() 
	 */
	private void generateTerritoryMap(){
		//Initiate TerritoryMaps
		for (int i=0;i<numberOfPlayer;i++){
			playerTerritoryMap[i] = new TerritoryMap(heightMap.getWidth(),heightMap.getHeight());
		}
		for (int i=0;i<numberOfNeutralBases;i++){
			neutralTerritoryMap.add(new TerritoryMap(heightMap.getWidth(),heightMap.getHeight()));
		}
		
		//Vectors containing pixels that have to be modified
		ArrayList<Point> pixelsN4 = new ArrayList<Point>();
		ArrayList<Point> pixelsN3 = new ArrayList<Point>();
		ArrayList<Point> pixelsN2 = new ArrayList<Point>();
		ArrayList<Point> pixelsN1 = new ArrayList<Point>();
		ArrayList<Point> pixelsN0 = new ArrayList<Point>();
		ArrayList<Point> pixelsJ4 = new ArrayList<Point>();
		ArrayList<Point> pixelsJ3 = new ArrayList<Point>();
		ArrayList<Point> pixelsJ2 = new ArrayList<Point>();
		ArrayList<Point> pixelsJ1 = new ArrayList<Point>();
		
		boolean[] loopBases = new boolean[numberOfPlayer+numberOfNeutralBases];
		Arrays.fill(loopBases, false);
		boolean loop = true;
		
		switch (numberOfPlayer){
		case 4:
			pixelsJ4.add(playerBasePosition[3]);// The first pixel is the player's base
			loopBases[3] = true;
		case 3:
			pixelsJ3.add(playerBasePosition[2]);
			loopBases[2]=true;
		case 2:
			pixelsJ2.add(playerBasePosition[1]);
			pixelsJ1.add(playerBasePosition[0]);
			loopBases[1]=true;
			loopBases[0]=true;
			break;
		default:
			break;
		}
		
		switch (numberOfNeutralBases){
		case 5:
			pixelsN4.add(neutralBasePosition.get(4));
			loopBases[numberOfPlayer+4]=true;
		case 4:
			pixelsN3.add(neutralBasePosition.get(3));
			loopBases[numberOfPlayer+3]=true;
		case 3:
			pixelsN2.add(neutralBasePosition.get(2));
			loopBases[numberOfPlayer+2]=true;
		case 2:
			pixelsN1.add(neutralBasePosition.get(1));
			loopBases[numberOfPlayer+1]=true;
		case 1:
			pixelsN0.add(neutralBasePosition.get(0));
			loopBases[numberOfPlayer]=true;
			break;
		default:
			break;
		}
		
		//Loop stopping only if not a single pixel of the TerritoryMap has been modified
		while (loop){
			//Expand each player territory successively
			switch (numberOfPlayer){
			case 4:
				loopBases[3] = circlePropagation(playerTerritoryMap[3],pixelsJ4,4);
			case 3:
				loopBases[2] = circlePropagation(playerTerritoryMap[2],pixelsJ3,3);
			case 2:
				loopBases[1] = circlePropagation(playerTerritoryMap[1],pixelsJ2,2);
				loopBases[0] = circlePropagation(playerTerritoryMap[0],pixelsJ1,1);
				break;
			default:
				break;
			}
			
			switch (numberOfNeutralBases){
			case 5:
				loopBases[numberOfPlayer+4] = circlePropagation(neutralTerritoryMap.get(4),pixelsN4,6);
			case 4:
				loopBases[numberOfPlayer+3] = circlePropagation(neutralTerritoryMap.get(3),pixelsN3,6);
			case 3:
				loopBases[numberOfPlayer+2] = circlePropagation(neutralTerritoryMap.get(2),pixelsN2,6);
			case 2:
				loopBases[numberOfPlayer+1] = circlePropagation(neutralTerritoryMap.get(1),pixelsN1,6);
			case 1:
				loopBases[numberOfPlayer] = circlePropagation(neutralTerritoryMap.get(0),pixelsN0,6);
				break;
			default:
				break;
			}
			
			loop = false;
			for(int i =0;i<numberOfPlayer+numberOfNeutralBases;i++)
			{
				if (loopBases[i]){
					loop = true;
				}
			}
		}
	}
	
	/**
	 * Expand the player territory according to the pixels input parameter and store the future pixels
	 * suivants 
	 * @param pixels Store pixels that have to be modified
	 * @param player Value to set at those pixels according to the player
	 * @return true if one or more pixels have been modified, false if not
	 * @see #generateTerritoryMap() 
	 */
	private boolean circlePropagation(TerritoryMap territoryMap, ArrayList<Point> pixels,int player){
		ArrayList<Point> nextPixels = new ArrayList<Point>();
		boolean modified=false;
		
		//Getting each pixels
		for (Point i:pixels){
			//A non-modified pixel value is -1
			if(getTerritoryMapValue(i.x, i.y)==-1){
				//Add the value to the TerritoryMap according to the heightMap
				if (heightMap.getPixel(i.x,i.y)==0)
					territoryMap.setPixel(i.y*heightMap.getWidth() + i.x, player);
				else
					territoryMap.setPixel(i.y*heightMap.getWidth() + i.x, 0);
				
				//Then we store the 8 pixels around the modified pixel
				
				//Right pixel
				if (i.x<territoryMap.getWidth()-1)
					nextPixels.add(new Point(i.x+1,i.y));
				
				//Bottom pixel
				if (i.y<territoryMap.getHeight()-1)
					nextPixels.add(new Point(i.x,i.y+1));
				
				//Left pixel
				if (i.y>0)
					nextPixels.add(new Point(i.x,i.y-1));
				
				//Top pixel
				if (i.x>0)
					nextPixels.add(new Point(i.x-1,i.y));
				
				//Bottom-right pixel
				if ((i.x<territoryMap.getWidth()-1)&&(i.y<territoryMap.getHeight()-1)){
					nextPixels.add(new Point(i.x+1,i.y+1));
				}
				
				//Bottom-left pixel
				if (i.x>0&&(i.y<territoryMap.getHeight()-1)){
					nextPixels.add(new Point(i.x-1,i.y+1));
				}
				
				//Top-left pixel
				if (i.x>0&&i.y>0){
					nextPixels.add(new Point(i.x-1,i.y-1));
				}
				
				//Top-right pixel
				if ((i.x<territoryMap.getWidth()-1)&&i.y>0){
					nextPixels.add(new Point(i.x+1,i.y-1));
				}
				
				
				//Warn that a pixel has been modified : another loop has to be done
				modified=true;
			}
		}
		pixels.clear();
		
		if (modified)
		{
			//Get the future pixels
			pixels.addAll(nextPixels);
			nextPixels.clear();
		}
		return modified;
	}
	
	/**
	 * Return the value of the pixel at (x,y) if the value has already been modified on one TerritoryMap
	 * @param x 
	 * @param y
	 * @return the value of the pixel if it is not -1 on at least one map, -1 otherwise
	 * @see #circlePropagation()
	 */
	private int getTerritoryMapValue(int x, int y){
		for (TerritoryMap tm:playerTerritoryMap){
			if (tm.getPixel(x,y)!=-1)
				return tm.getPixel(x,y);
		}
		
		for (TerritoryMap tm:neutralTerritoryMap){
			if (tm.getPixel(x,y)!=-1){
				return tm.getPixel(x,y);
			}
			
		}
		return -1;
	}
	
	/**
	 * Generate All the ProximityMap (one for each base)
	 * @see #MapManager()
	 */
	private void generateAllProximityMap(){
		//Generate the proximityMaps of the players' bases
		for (int i=0;i<numberOfPlayer;i++){
			ProximityMap proximityMap = new ProximityMap(heightMap.getWidth(),heightMap.getHeight());
			generateProximityMap(proximityMap,playerBasePosition[i]);
			playerProximityMap[i] = proximityMap;
			playerProximityMap[i].saveAsPNG("pm"+i+".png");
		}
		
		//Generate the neutral bases' proximityMaps
		for(int i=0;i<numberOfNeutralBases;i++){
			ProximityMap proximityMap = new ProximityMap(heightMap.getWidth(),heightMap.getHeight());
			generateProximityMap(proximityMap,neutralBasePosition.get(i));
			neutralProximityMap.add(proximityMap);
			neutralProximityMap.get(i).saveAsPNG("npm"+i+".png");
		}
	}
		
	/**
	 * Generate a ProximityMap for a base and save it as png at tmp/
	 * @param player id of the base
	 * @see #generateAllProximityMap()
	 */
	private void generateProximityMap(Map proximityMap, Point base){
		
		LinkedList<Point> nextPixels = new LinkedList<Point>();
		nextPixels.add(base);
		int value=0;
		Point p;
		while(!nextPixels.isEmpty()){
			p = nextPixels.poll();
			if (proximityMap.getPixel(p.x,p.y)==-1){
				if (heightMap.getPixel(p.x,p.y)!=0){
					proximityMap.setPixel(p.x,p.y,value);
				}
				else
					proximityMap.setPixel(p.x,p.y,9999);
				
				//Right pixel
				if (p.x<proximityMap.getWidth()-1)
					nextPixels.add(new Point(p.x+1,p.y));
				
				
				//Bottom pixel
				if (p.y<proximityMap.getHeight()-1)
					nextPixels.add(new Point(p.x,p.y+1));
					
				//Left pixel
				if (p.y>0)
					nextPixels.add(new Point(p.x,p.y-1));

				
				//Top pixel
				if (p.x>0)
					nextPixels.add(new Point(p.x-1,p.y));
				
				//Bottom-right pixel
				if ((p.x<proximityMap.getWidth()-1)&&(p.y<proximityMap.getHeight()-1)){
					nextPixels.add(new Point(p.x+1,p.y+1));
				}
				
				//Bottom-left pixel
				if (p.x>0&&(p.y<proximityMap.getHeight()-1)){
					nextPixels.add(new Point(p.x-1,p.y+1));
				}
				
				//Top-left pixel
				if (p.x>0&&p.y>0){
					nextPixels.add(new Point(p.x-1,p.y-1));
				}
				
				//Top-right pixel
				if ((p.x<proximityMap.getWidth()-1)&&p.y>0){
					nextPixels.add(new Point(p.x+1,p.y-1));
				}
				
				value++;
			}
		}
	}

	/**
	 * Change the territory of the owner of a base and refresh the global TerritoryMap
	 * @param tm
	 * @param player
	 */
	public void changeOwnerOfTerritoryMap(TerritoryMap tm, int player){
		
		for (int y = 0; y < tm.getHeight();y++){ 
			for (int x = 0; x < tm.getWidth();x++){
				if (tm.getPixel(x,y)!=-1 && heightMap.getPixel(x,y)==0){
					tm.setPixel(x,y,player);
				}
			}
		}
		
		saveTerritoryMap(playersType);
	}
	
	/**
	 * Save the MapManager in a tmp file to have faster results if we use it again
	 * @see #MapManager()
	 */
	private void saveAsTMP(){
		File tmpFile = new File("tmp/"+imageName+numberOfPlayer+".tmp");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(tmpFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error : tmpFile not found");
			e.printStackTrace();
		}
		
		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} catch (IOException e) {
			System.out.println("Error : ObjectOutputStream fos throw an IOException");
			e.printStackTrace();
		}
	}
	
	/**
	 * Save all Territory Maps as PNG images at tmp/
	 * @param playerTypes
	 * @see #MapManager()
	 */
	private void saveAllTerritoryMap(ArrayList<PlayerType> playerTypes){
		for (int i=0;i<numberOfPlayer;i++)
			playerTerritoryMap[i].saveAsPNG("ptm"+i+".png",playerTypes);
		
		for (int i=0;i<numberOfNeutralBases;i++)
			neutralTerritoryMap.get(i).saveAsPNG("ntm"+i+".png", playerTypes);
	}
	
	/**
	 * Save the global Territory Map according to each Territory Maps as a PNG image at tmp/tm.png
	 * @param playerTypes
	 * @see #MapManager
	 */
	private void saveTerritoryMap(ArrayList<PlayerType> playerTypes){
		Color playerColor[] = new Color[4];
		Color c;
		
		int i = 0;
		for (PlayerType pt:playerTypes){
			switch(pt){
			case ELECTRIC:
				playerColor[i]=Color.yellow;
				break;
			case FIRE:
				playerColor[i]=Color.red;
				break;
			case WATER:
				playerColor[i]=Color.cyan;
				break;
			case GRASS:
				playerColor[i]=Color.green;
				break;
			default:
				playerColor[i]=Color.pink;
				break;
			}
			i++;
		}
		
		BufferedImage outImage = new BufferedImage(heightMap.getWidth(),heightMap.getHeight(),BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < heightMap.getHeight();y++){ 
			for (int x = 0; x < heightMap.getWidth();x++){
				int value = getTerritoryMapValue(x,y);
				
				switch(value){
				case 0:
					c = Color.black;
					break;
				case 1:
					c = playerColor[0];
					break;
				case 2:
					c = playerColor[1];
					break;
				case 3:
					c = playerColor[2];
					break;
				case 4:
					c = playerColor[3];
					break;
				case 5:
					c = Color.white;
					break;
				case 6:
					c = Color.darkGray;
					break;
				default:
					c=Color.pink;
					break;
				}
				Color alphaColor = new Color(c.getRed(),c.getGreen(),c.getBlue(),150);
				
				outImage.setRGB(x, y, alphaColor.getRGB());
			}
		}
		
		File outFile = new File("tmp/tm.png");
		try{
			if (!ImageIO.write(outImage, "png", outFile)){
				System.out.println("Format d'écriture non pris en charge");
			}
		}
		catch(Exception e){
			System.out.println("Erreur lors de l'enregistrement de l'image :");
			e.printStackTrace();
		}
	}

	public ArrayList<Point> getNeutralBasePosition() {
		return neutralBasePosition;
	}
	
	
}