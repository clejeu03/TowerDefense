/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package View;

import java.awt.Point;

import javax.swing.JPanel;

/**
 * Project - TowerDefense</br>
 * <b>Class - MainMenusView</b></br>
 * <p>The MainMenusView abstract class represents the mains "panels" of the game :
 * HomeMenu, EndGameMenu, OptionsMenu, PlayMenu, SceneView, GameMenuBar
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public abstract class MainViews extends JPanel {
    protected ViewManager view;
    protected Point position;
    protected int width;
    protected int height;

	/**
	 * 
	 */
	public MainViews(ViewManager view, Point position, int width, int height) {
		super();
		this.view = view;
		this.position = new Point(position);
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Point getPosition() {
		return position;
	}
}
