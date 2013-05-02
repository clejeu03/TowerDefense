/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package GameEngine;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Project - TowerDefense</br>
 * <b>Class - Map</b></br>
 * <p>A Map contains an array of pixel data, and will be used to represent heightMap, territoryMap and proximityMap</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MapManager
 */
public abstract class Map implements Serializable {
	
	/**
	 * Default serial version ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Store the information of each pixel of the Map
	 */
	private int[] data;
	
	/**
	 * Store the height of the Map (in px)
	 */
	private final int height;
	
	/**
	 * Store the width of the Map (in px)
	 */
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
	
	abstract void saveAsPNG(String path);

}