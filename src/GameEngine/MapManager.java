package GameEngine;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

import GameEngine.Map;

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

public class MapManager {
  /**
   * Store the number maximum of Player 
   */
  private static final int maxnumberOfPlayer = 4; 
  /**
   * Store the number of players playing
   */
  private static final int numberOfPlayer = 4;
  /**
   * Store the path of the image we'll use to create our maps
   */
  private String imagepath;
  /**
   * Store the map that describes the plains and hills of the map
   */
  private Map heightMap;
  /**
   * Store the map that determine player's territory with their possessions
   */
  private Map territoryMap;
  /**
   * Store the map that calculate the distance between each Base
   */
  //private Map proximityMap;
  /**
   * Store the position of each base
   */
  private Point[] basePosition;
  /**
	 * MapManager's constructor, initiate and create the heightMap and the territoryMap
	 * @param i_imagepath path of the local image to analyse
	 */
	MapManager(String i_imagepath){
		super();
		imagepath = i_imagepath;
		basePosition = new Point[numberOfPlayer];
		generateHeightMap();
		generateTerritoryMap();
	}
	
	/**
	 * Algorithme de génération de la map de relief suivant une image locale
	 */
	void generateHeightMap(){
		BufferedImage img = null;//Local image containment
		
		try {
			img = ImageIO.read(new File(imagepath)); //We get the image
		} catch (IOException e) {
			System.out.println("Erreur lors de la lecture de l'image de Map");
			e.printStackTrace();
		}
		
		//Initiate the HeightMap
		heightMap = new Map(img.getWidth(),img.getHeight());
		
		for (int y = 0; y < heightMap.getHeight();y++){ 
			for (int x = 0; x < heightMap.getWidth();x++){
				
				// get the color data of each pixel
				int color = img.getRaster().getSample(x,y,0); 
				
				//If pixel data is between 0 and 5 (black) or between 250 and 255 (white) => it's height information	
				if ((color>=0 && color<=5)||(color >=250 && color <= 255)){ 
					//Divide the value in order to get more lisible information
					color = color/(255/(maxnumberOfPlayer+1)); 
					if (color==0 || color==5)
						setHeightMapValue(x,y,color); 
					else
						setHeightMapValue(x,y,5);
				}
				//Else if the pixel color is grey => it refers to bases' position
				else {
					color = (int)Math.round((float)color/(255/(maxnumberOfPlayer+1)));
					if (color <= numberOfPlayer)
					{
						//Store the position of the base according to its player
						int player = color - 1;
						setBasePosition(player,x,y);
					}
					setHeightMapValue(x,y,0);
				}
			}
		}	
		createImageFromMap(heightMap, "src/Maps/hm.png");
	}

	/**
	 * Algorithme de génération de la map de territoires
	 * @see MapManager() 
	 */
	void generateTerritoryMap(){
		//Initiate TerritoryMap
		territoryMap = new Map(heightMap.getWidth(),heightMap.getHeight());
		
		//Vectors containing pixels that have to be modified
		Vector<Point> pixelsJ4 = new Vector<Point>();
		Vector<Point> pixelsJ3 = new Vector<Point>();
		Vector<Point> pixelsJ2 = new Vector<Point>();
		Vector<Point> pixelsJ1 = new Vector<Point>();
		
		boolean loopJ1 = false,loopJ2=false, loopJ3=false,loopJ4=false;
		 
		switch (numberOfPlayer){
		case 4:
			pixelsJ4.add(basePosition[3]);// The first pixel is the player's base
			loopJ4 = true;
		case 3:
			pixelsJ3.add(basePosition[2]);
			loopJ3=true;
		case 2:
			pixelsJ2.add(basePosition[1]);
			pixelsJ1.add(basePosition[0]);
			loopJ2=true;
			loopJ1=true;
			break;
		default:
			break;
		}
		
		//Loop stopping only if not a single pixel of the TerritoryMap has been modified
		while (loopJ1||loopJ2||loopJ3||loopJ4){
			//Expand each player territory successively
			switch (numberOfPlayer){
			case 4:
				loopJ4 = circlePropagation(pixelsJ4,4);
			case 3:
				loopJ3 = circlePropagation(pixelsJ3,3);
			case 2:
				loopJ2 = circlePropagation(pixelsJ2,2);
				loopJ1 = circlePropagation(pixelsJ1,1);
				break;
			default:
				break;
			}

			
		}
		
		createImageFromMap(territoryMap,"src/Maps/tm.png");
	}
	
	/**
	 * Expand the player territory according to the pixels input parameter and store the future pixels
	 * suivants 
	 * @param pixels Store pixels that have to be modified
	 * @param player Value to set at those pixels according to the player
	 * @return true if one or more pixels have been modified, false if not
	 * @see MapManager.generateTerritoryMap() 
	 */
	private boolean circlePropagation(Vector<Point> pixels,int player){
		Vector<Point> nextPixels = new Vector<Point>();
		boolean modified=false;
		
		//Getting each pixels
		for (Point i:pixels){
			//A non-modified pixel returns a NullPointerException
			try{
				getTerritoryMapValue(i.x,i.y);
			}
			catch(NullPointerException e){
				//Add the value to the TerritoryMap according to the heightMap
				if (heightMap.getPixel(i.x,i.y).getValue()==0)
					setTerritoryMap(i.x,i.y,player);
				else
					setTerritoryMap(i.x,i.y,0);
				
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
	 * Create a color image with the information of a Map
	 * @param m Map 
	 * @param path path of the output image
	 * @see MapManager.generateHeightMap()
	 * @see MapManager.generateTerritoryMap()
	 */
	private void createImageFromMap(Map m, String path){
		Color red = new Color(255,0,0);
		Color blue = new Color(0,0,255);
		Color green = new Color(0,255,0);
		Color yellow = new Color(0,255,255);
		Color white = new Color(255,255,255);
		Color black = new Color(0,0,0);
		
		int rgb;
		BufferedImage outImage = new BufferedImage(m.getWidth(),m.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < m.getHeight();y++){ 
			for (int x = 0; x < m.getWidth();x++){
				int value;
				try
				{
					value = m.getPixel(x,y).getValue();
				}
				catch (NullPointerException e){
					value = 5;
				}
				switch(value){
				case 0:
					rgb = black.getRGB();
					break;
				case 1:
					rgb = blue.getRGB();
					break;
				case 2:
					rgb = green.getRGB();
					break;
				case 3:
					rgb = yellow.getRGB();
					break;
				case 4:
					rgb = red.getRGB();
					break;
				case 5:
					rgb = white.getRGB();
					break;
				default:
					rgb=white.getRGB();
					break;
				}
				
				outImage.setRGB(x, y, rgb);
			}
		}
		File outFile = new File(path);
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
	
	public Point[] getBasePosition(){
		return basePosition;
	}
	
	public Point getBasePosition(int player) {
		return basePosition[player];
	}

	public void setBasePosition(int player, int x, int y) {
		this.basePosition[player] = new Point(x,y);
	}

	public Map getHeightMap() {
		return heightMap;
	}

	public int getHeightMapValue(int index){
		return heightMap.getPixel(index).getValue();
	}
	
	public int getHeightMapValue(int x, int y){
		return heightMap.getPixel(x,y).getValue();
	}
	
	public void setHeightMapValue(int x, int y, int value){
		this.heightMap.setData(y*heightMap.getWidth() + x, x, y, value);
	}
	
	public Map getTerritoryMap() {
		return territoryMap;
	}

	public int getTerritoryMapValue(int index){
		return territoryMap.getPixel(index).getValue();
	}
	
	public int getTerritoryMapValue(int x, int y){
		return territoryMap.getPixel(x,y).getValue();
	}
	
	public void setTerritoryMap(int x, int y, int value){
		this.territoryMap.setData(y*territoryMap.getWidth() + x, x, y, value);
	}
	
	public static void main(String[] args){
		MapManager myMap = new MapManager("src/Maps/Map.jpg");
	}

}