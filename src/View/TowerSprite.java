/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */
package View;



import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author aurelie
 *
 */
public class TowerSprite extends Sprite{
	private int towerType;
	private SceneView map;
	private int range;
	
	public TowerSprite(Point p, boolean c, int id,int w, int h, int type, int r, SceneView m) {
		// TODO Auto-generated constructor stub
		super(p,c,id,w, h);
		
		towerType = type;
		map = m;
		range = r;
				
		/*Chargement de l'image : différent suivant le propriétaire de l'élement et le type*/
		try {
		      img = ImageIO.read(new File("img/bear.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		/*Si la tour est clickable, on ajoute les listeners*/
		if(clickable){
			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent me) { 
		             myMousePressed(me);
		            } 
		       public void mouseEntered(MouseEvent me) { 
		    	   myMouseEntered(me);
	           }
		       public void mouseExited(MouseEvent me) { 
		    	   myMouseExited(me);
	           } 
	         });
		}
	}
	
	
    /**
     * Getter range
     * @return Point : position du Sprite
     * @see SceneView (appelant)
     */	
	public int getRange(){
		return range;
	}
	
	/**
	 * Gestionnaire de l'évènement "souris pressée dans la zone"
	 * @param me : MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		/*Récupère la source de l'évènement etvérifie qu'il s'agit bien d'un Sprite "cliquable"*/
		//if(me.getComponent() instanceof Sprite){
			//Sprite source = (Sprite) me.getComponent();
			System.out.println("TowerOwner number : "+ idOwner+" !");
			System.out.println("Position on the Sprite ("+ me.getPoint().x+","+ me.getPoint().y+")");
			map.towerClicked(position, idOwner);
			
		//}
	}
	
	/**
	 * Gestionnaire de l'évènement "souris entrée dans la zone"
	 * @param me : MouseEvent
	 */
	private void myMouseEntered(MouseEvent me) {
		/*Change l'aspect du pointeur de la souris*/
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	/**
	 * Gestionnaire de l'évènement "souris sortie dans la zone"
	 * @param me : MouseEvent
	 */
	private void myMouseExited(MouseEvent me) {
		/*Change l'aspect du pointeur de la souris*/
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}


}
