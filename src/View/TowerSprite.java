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

import GameEngine.GameManager;

/**
 * Project - TowerDefense</br>
 * <b>Class - TowerSprite</b></br>
 * <p>The TowerSprite class represents the tower images displayed on the ScenView</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */
public class TowerSprite extends Sprite{
	private int towerType;
	private int range;
	
	/**
	 * Constructor of the TowerSprite class
	 * @param scene
	 * @param position
	 * @param clickable
	 * @param playerId
	 * @param width
	 * @param height
	 * @param type
	 * @param range
	 */
	public TowerSprite(SceneView scene, Point position, boolean clickable, int playerId, int width, int height, int type, int range) {
		super(scene, position,clickable,playerId,width,height);
		
		towerType = type;
		this.range = range;
				
		/*Chargement de l'image : différent suivant le propriétaire de l'élement et le type*/
		try {
		      image = ImageIO.read(new File("img/bear.png"));
		  
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
			System.out.println("TowerOwner number : "+ playerId+" !");
			System.out.println("Position on the Sprite ("+ me.getPoint().x+","+ me.getPoint().y+")");
			scene.towerClicked(position, playerId);
			
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
