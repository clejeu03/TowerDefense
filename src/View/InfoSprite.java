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
public class InfoSprite extends Sprite{
	private  int infoType;
	private Point positionElement;
	private SceneView map;
		
	
	public InfoSprite(Point p, boolean c, int id, int w, int h, int type, Point pos, SceneView m) {
		super(p,c,id,w,h);
		infoType = type;
		positionElement = pos;
		map = m;
		/*Chargement de l'image : différent suivantle type*/
		try {
		      img = ImageIO.read(new File("img/delete.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		/*Si l'info est clickable, on ajoute les listeners*/
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
	 * Gestionnaire de l'évènement "souris pressée dans la zone"
	 * @param me : MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		/*Récupère la source de l'évènement etvérifie qu'il s'agit bien d'un Sprite "cliquable"*/
		//if(me.getComponent() instanceof Sprite){
			//Sprite source = (Sprite) me.getComponent();
			/*System.out.println("TowerOwner number : "+ idOwner+" !");
			System.out.println("Position on the Sprite ("+ me.getPoint().x+","+ me.getPoint().y+")");*/
			//map.towerClicked(position, idOwner);
			map.towerSuppressed(positionElement, idOwner);
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
