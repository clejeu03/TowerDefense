/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 2 mai 2013
 */
package GameEngine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import GameEngine.Player.PlayerType;

public class TerritoryMap extends Map implements Serializable {

	/**
	 * Store the default serial number ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the Territorymap class
	 * @param width of the Map
	 * @param height of the Map
	 */
	public TerritoryMap(int width, int height) {
		super(width, height);
	}
	
	/**
	 * Create a color image (with transparency) in the tmp folder with the information of a TerritoryMap
	 * @param path of the output image
	 * @see MapManager#generateTerritoryMap()
	 */
	@Override
	void saveAsPNG(String path) {
		//Create a default playerTypes
		ArrayList<PlayerType> playerTypes = new ArrayList<PlayerType>();
		playerTypes.add(PlayerType.ELECTRIC);
		playerTypes.add(PlayerType.FIRE);
		playerTypes.add(PlayerType.GRASS);
		playerTypes.add(PlayerType.WATER);
		saveAsPNG(path,playerTypes);
	}
	
	/**
	 * Create a color image (with transparency) in the tmp folder with the information of a TerritoryMap
	 * @param path of the output image
	 * @param playersTypes : contains the color of each player
	 * @see MapManager#generateTerritoryMap()
	 */
	public void saveAsPNG(String path, ArrayList<PlayerType> playerTypes){
		Color playerColor[] = new Color[4];
		Color c;
		int i = 0;
		Iterator<PlayerType> it = playerTypes.iterator();
		
		while(it.hasNext()){
			PlayerType pt = it.next();
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
		
		BufferedImage outImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		for (int y = 0; y < this.getHeight();y++){ 
			for (int x = 0; x < this.getWidth();x++){
				int value = this.getPixel(x,y);
				
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
		File outFile = new File("tmp/"+path);
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

}
