package View;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * Project - TowerDefense</br>
 * <b>Class - Sprite</b></br> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Sprite
 * @see ViewManager
 * 
 */

public abstract class Sprite extends JComponent{    
	protected Point position;//Position du centre du Sprite !!
	protected int width;
	protected int height;
	protected Image img;
	protected boolean clickable;
	protected int idOwner;
	
	public Sprite(Point p, boolean c, int id, int w, int h ){ 
		super();
		
		position = new Point(p);
		clickable = c;
		idOwner = id;
		width = w;
		height = h;
	}

    /**
     * Getter idOwner
     * @return int : idOwner  du Sprite
     * @see MapInterface.myMousePressed(MouseEvent me) (appelant)
     */	
	public int getIdOwner() {
		return idOwner;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
    /**
     * Getter position
     * @return Point : position du Sprite
     * @see MapInterface (appelant)
     */	
	public Point getPosition(){
		return position;
	}
	
    /**
     * Setter position
     * @param newP : Point
     * @see MapInterface.moveSprite(Point newPosition) (appelant)
     */	
	public void setPosition(Point newP){
		position.setLocation(newP);
	}
	
    /**
     * Dessin du Sprite
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(img, 0, 0,this);

	  }  
}
