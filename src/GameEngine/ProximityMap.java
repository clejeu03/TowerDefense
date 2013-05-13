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

/**
 * @author maho
 *
 */
public class ProximityMap extends Map implements Serializable {

	/**
	 * Store the default serial version ID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the ProximityMap
	 * @param width of the ProximityMap
	 * @param height of the ProximityMap
	 */
	public ProximityMap(int width, int height) {
		super(width, height);
	}
	
	/**
	 * Create a color image in the tmp folder with the information of the ProximityMap
	 * @param path of the output image
	 * @see MapManager#generateAllProximityMap()
	 */
	public void saveAsPNG(String path){
		BufferedImage outImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		int value;
		for (int y = 0; y < this.getHeight();y++){ 
			for (int x = 0; x < this.getWidth();x++){
				value = this.getPixel(x,y);
				if (value==Integer.MAX_VALUE || value==-1){
					outImage.setRGB(x, y, Color.black.getRGB());
				}
				else
				{
					int R=(255*value)/500000;
					int G=(255*(500000-value))/500000; 
					int B=0;
					outImage.setRGB(x, y, new Color(R,G,B).getRGB());
				}
				
				
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
