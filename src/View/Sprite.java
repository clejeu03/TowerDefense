package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JLabel;

import GameEngine.Player.PlayerType;

/**
 * Project - TowerDefense</br>
 * <b>Class - Sprite</b></br> 
 * <p>The Sprite class represents the images displayed on the SceneView</p
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Sprite
 * @see ViewManager
 * 
 */

@SuppressWarnings("serial")
public abstract class Sprite extends JLabel{
	//Position of the center of the Sprite
	protected Point position;
	protected int width;
	protected int height;
	protected Image image;
	protected boolean clickable;
	protected PlayerType playerType;
	protected MainViews view;
	protected int id;
	
	/**
	 * Constructor of the Sprite class
	 * @param position - position of the center of the Sprite
	 * @param clickable - is the Sprite clickable ?
	 * @param idPlayer - the id of the owner of the game object represented by the Sprite
	 * @param width - Sprite width
	 * @param height - Sprite height
	 */
	public Sprite(MainViews view, int id, Point position, boolean clickable, PlayerType playerType, int width, int height){ 
		super();
		
		this.view = view;
		this.id = id;
		this.position = new Point(position);
		this.clickable = clickable;
		this.playerType = playerType;
		this.width = width;
		this.height = height;
	}

    /**
     * Getter - retrieve the Sprite playerId
     * @return int - the id of the owner of the game object represented by the Sprite
     */
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	/**
	 * Getter - retrieve the Sprite width
	 * @return int 
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter - retrieve the Sprite height
	 * @return int
	 */
	public int getHeight() {
		return height;
	}
	
    /**
     * Getter - retrieve the position of the center of the Sprite
     * @return Point - position of the Sprite
     */	
	public Point getPosition(){
		return position;
	}
	
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
     * Setter - set the position of the center of the Sprite
     * @param newPosition - Point
     */	
	public void setPosition(Point newPosition){
		position.setLocation(newPosition);
	}
	
    /**
     * Draw the Sprite
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(image, 0, 0,this);
	  }  
}
