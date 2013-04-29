/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package GameEngine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * Project - TowerDefense</br>
 * <b>Class - Map</b></br>
 * <p>A Map contains an array of pixel data, and will be used to represent heightMap, territoryMap and proximityMap</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MapManager
 */
public class Map {
	
	private int[] data;
	private final int height;
	private final int width;
	
	Map(int width, int height){
		super();
		this.height = height;
		this.width = width;
		data = new int[height*width];
		Arrays.fill(data,-1);
	}
	
	public int getPixel(int index){
		return data[index];
	}
	
	public int getPixel(int x, int y)
	{
		return getPixel(y*getWidth() + x);
	}
	
	public void setPixel(int index, int value) {
		this.data[index] = value;
	}
	
	public void setPixel(int x, int y, int value){
		this.data[y*getWidth() + x]= value;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	/**
	 * Create a color image with the information of the Map
	 * @param path of the output image
	 * @see MapManager.generateHeightMap()
	 * @see MapManager.generateTerritoryMap()
	 */
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
		File outFile = new File("img/map/"+path);
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

	/**
	 * Create a color image with the information of a ProximityMap
	 * @param path of the output image
	 * @see MapManager.generateAllProximityMap()
	 */
	public void saveAsPNGProximity(String path){
		BufferedImage outImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
		int value;
		for (int y = 0; y < this.getHeight();y++){ 
			for (int x = 0; x < this.getWidth();x++){
				value = this.getPixel(x,y);
				if (value==9999){
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
		
		File outFile = new File("img/map/"+path);
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