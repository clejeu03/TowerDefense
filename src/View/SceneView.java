package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import GameEngine.*;

/**
 * Project - TowerDefense</br>
 * <b>Class - SceneView</b></br> 
 * <p>The SceneView class displays the map and its Sprites</p>
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Sprite
 * @see ViewManager
 * 
 */

public class SceneView extends MainViews{   
    private Image map;
    private ArrayList<Sprite> sprites;
    
    private boolean towerClicked;
    private int indexTowerClicked;
    
    /**
     * Constructor of the SceneView class
     * @param view
     * @param position
     * @param width
     * @param height
     */
	public SceneView(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
	
		sprites = new ArrayList<Sprite>();
		towerClicked = false;
		indexTowerClicked = 0;
		
		//Loading the image map
		try {
		      map = ImageIO.read(new File("img/map.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
        //Add a listener on the map
    	addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	             myMousePressed(me);
	            } 
         });
		
		//Suppress the layout manager of the SceneView
		setLayout(null);
	    setBackground(Color.WHITE);
	}
	
	/**
	 * Add a Sprite on the map
	 * @param tower 
	 */
	public void addSprite(Tower tower){
		boolean clickable = false;
		int type = 0;
		
		//If the tower's owner is the human player (id = 0), the Sprite needs to be clickable
		if(tower.getPlayerId()==0){
			clickable = true;
		}
		//TODO Choose the the type of the tower
		if(tower instanceof MedicalTower){
			
		}
		
		//Add the Sprite on the map
		TowerSprite ts = new TowerSprite(this, tower.getPosition(),clickable, tower.getPlayerId(), 32, 32, type, tower.getRange());
		sprites.add(ts);
		
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
			add(element);
		}
		
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
	
	/**
	 * Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		
		System.out.println("Position on the Map ("+ me.getPoint().x+","+ me.getPoint().y+")");
		
		//Click on the map when a tower is selected
		if (towerClicked){
			//Removing from the view the towerInfoSprite
			//TODO  Plus simple => chercher dans la liste de sprite les instance de TowerInfoSprite
	    	Sprite s = sprites.get(indexTowerClicked);
			Point positionInfo= new Point(s.getPosition());
			positionInfo.translate((32/2) + (16/2),(32/2) + (16/2));
			
			Iterator<Sprite> it = sprites.iterator();
			while (it.hasNext()) {
				Sprite element = it.next();
				if(element.getPosition().equals(positionInfo)){
					it.remove();
					remove(element);
				}
			}
	    	towerClicked = false;
			
	    	//Repaint the Panel
	    	revalidate();
	    	repaint();	
		}
	}
	
	
	public void towerClicked(Point position, int idOwner){
		//Display the  TowerInfoSprites of the clicked tower on the map
		Point positionSprite = new Point(position);
		positionSprite.translate((32/2) + (16/2),(32/2) + (16/2));
		
		System.out.println("Position Tower "+position.x);
		System.out.println("Position Delete "+positionSprite.x);
		
		//Add the TowerInfoSprite
		sprites.add(new TowerInfoSprite(this, positionSprite, true, idOwner, 16,16, 0, position));	
		
		
		//TODO Retrieve the t améliorer	
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			/*Récupération del'index de la tour cliquée*/
			if(element.getPosition().equals(position)){
				indexTowerClicked = sprites.indexOf(element);
				System.out.println(indexTowerClicked);
			}
			element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
			add(element);
		}		
		towerClicked = true;
		/*Rafraichit la fenêtre*/
    	revalidate();
    	repaint();	
		
	}
	
	 public void towerSuppressed(Point position, int idOwner){
		   view.towerSuppressed(position, idOwner);
	   }
	 
	public void suppressTower(Point position, int idOwner){
		/*On supprime la Tour et ses sprites d'info*/
		Iterator<Sprite> it = sprites.iterator();
		Point positionSuppress = new Point(position);
		positionSuppress.translate((32/2) + (16/2),(32/2) + (16/2));
		//boolean find = false;
		while (it.hasNext()) {
			Sprite element = it.next();
			if(element.getPosition().equals(position)){
				it.remove();
				remove(element);
				//find = true;
			}
			//Sprite suppress
			if(element.getPosition().equals(positionSuppress)){
				it.remove();
				remove(element);
				//find = true;
			}
		}	
		towerClicked =  false;
        /*Rafraichit la fenêtre*/
    	revalidate();
    	repaint();	
	}
	
    /**
     * Draw the SceneView Panel
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(map, 0, 0, this.getWidth(), this.getHeight(), this);
	    if(towerClicked){
	    	//Récupère la tour cliquée
	    	Sprite s = sprites.get(indexTowerClicked);
	    	if(s instanceof TowerSprite){
	    		g.setColor(Color.blue);
				System.out.println("Circle ! "+s.getPosition().x);
	    		g.drawOval(s.getPosition().x-(((TowerSprite) s).getRange()/2), s.getPosition().y -(((TowerSprite) s).getRange()/2), ((TowerSprite) s).getRange(), ((TowerSprite) s).getRange());
	    	}
	    }
	  }              
}
