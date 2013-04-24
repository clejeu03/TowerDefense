/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package GameEngine;

import java.awt.Point;

/**
 * Project - TowerDefense</br>
 * <b>Class - Map</b></br>
 * <p>A Map contains an array of pixel data, and will be used to represent heightMap, territoryMap and proximityMap</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MapManager
 */
public class Map {
	
	private Pixel[] data;
	private final int height;
	private final int width;
	
	Map(int width, int height){
		super();
		this.height = height;
		this.width = width;
		data = new Pixel[height*width];
	}
	
	public Pixel getPixel(int index){
		return data[index];
	}
	
	public Pixel getPixel(int x, int y)
	{
		return getPixel(y*getWidth() + x);
	}
	
	public void setData(int index, int x, int y, int value) {
		Pixel p = new Pixel(x,y,value);
		this.data[index] = p;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	class Pixel{
		private final Point position;
		private final int value;
		
		Pixel(int x, int y, int value){
			super();
			this.position = new Point(x,y);
			this.value = value;
		}
		
		public int getX() {
			return position.x;
		}

		public int getY() {
			return position.y;
		}

		public int getValue() {
			return value;
		}
	}
}