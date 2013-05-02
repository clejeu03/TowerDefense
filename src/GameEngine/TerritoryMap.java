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

import javax.imageio.ImageIO;

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
	 * Create a color image in the tmp folder with the information of a TerritoryMap
	 * @param path of the output image
	 * @see MapManager#generateTerritoryMap()
	 */
	@Override
	public void saveAsPNG(String path){
		int rgb;
		BufferedImage outImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < this.getHeight();y++){ 
			for (int x = 0; x < this.getWidth();x++){
				int value = this.getPixel(x,y);
				
				switch(value){
				case 0:
					rgb = Color.black.getRGB();
					break;
				case 1:
					rgb = Color.cyan.getRGB();
					break;
				case 2:
					rgb = Color.green.getRGB();
					break;
				case 3:
					rgb = Color.yellow.getRGB();
					break;
				case 4:
					rgb = Color.red.getRGB();
					break;
				case 5:
					rgb = Color.white.getRGB();
					break;
				case 6:
					rgb = Color.darkGray.getRGB();
					break;
				default:
					rgb=Color.pink.getRGB();
					break;
				}
				
				outImage.setRGB(x, y, rgb);
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
