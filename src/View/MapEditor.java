/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 9 mai 2013
 */
package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JFileChooser;

/**
 * Project - TowerDefense</br>
 * <b>Class - MainMenusView</b></br>
 * <p>The MapEditor class give the player the possibility to create his own Maps
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class MapEditor extends MainViews{

	private JFileChooser fc;
	private Image userViewMap;

	
	
	/**
	 * Constructor of the MapEditor
	 */
	public MapEditor(ViewManager view, Point position, int width, int height) {
		super(view, position, width, height);
		fc = new JFileChooser();
		setLayout(null);
		setBackground(Color.gray);
	}
	
	public void initiate(){
		
	}
	
}
