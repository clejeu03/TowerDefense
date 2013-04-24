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
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Sprite
 * @see ViewManager
 * 
 */

public class SceneView extends JPanel{
    private ViewManager player;
    
    private Image map;
    private ArrayList<Sprite> sprites;
    
    private boolean towerClicked;
    private int indexTowerClicked;
    
   /* private TowerSprite bear;
    private TowerSprite bear2;*/

	public SceneView(ViewManager p) {
		super();
	
		sprites = new ArrayList<Sprite>();
		player = p; 
		towerClicked = false;
		indexTowerClicked = 0;
		
		/*Chargement de l'image de la map*/
		try {
		      map = ImageIO.read(new File("img/map.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		/*Dispose les composants sur le panneau*/
		setLayout(null);
	
        /*Ajout d'un listener sur la map*/
    	addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
	             myMousePressed(me);
	            } 
         });
		
        /*Paramètres principaux du panneau*/
		setPreferredSize(new Dimension(player.WIDTH,player.HEIGHT-200));
	    setBackground(Color.WHITE);
	}
	
	public void addSprite(Tower t){
		//Si la tour appartient au joueur 0 => Le sprite correspondant doit être clickable
		boolean c = false;
		int type =0;
		
		if(t.getIdOwner()==0){
			c = true;
		}
		//Choisir le type en fonction dutype de la tour...
		if(t instanceof MedicalTower){
			
		}
		TowerSprite ts = new TowerSprite(t.getPosition(),c, t.getIdOwner(), 32, 32, type, t.getRange(), this);
		sprites.add(ts);
		
		//ajoute l'élement sur la map
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
			add(element);
		}
		
        /*Rafraichit la fenêtre*/
    	revalidate();
    	repaint();	
	}
	
	/**
	 * Gestionnaire de l'évènement "souris pressée dans la zone"
	 * @param me : MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		/*Récupère la source de l'évènement etvérifie qu'il s'agit bien d'un Sprite "cliquable"*/
		//if(me.getComponent() instanceof Sprite){
			//Sprite source = (Sprite) me.getComponent();
			System.out.println("Position on the Map ("+ me.getPoint().x+","+ me.getPoint().y+")");
			//Click sur la map si une tour était déjà cliquée
			if (towerClicked){
				//Suppression de ses sprites d'info
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
			}
			
			/*Rafraichit la fenêtre*/
	    	revalidate();
	    	repaint();	
		//}
	}
	
	
	public void towerClicked(Point position, int idOwner){
		/*Affiche les infos de la Tour surla map*/ 
		/*Ajout un Spirtede suppression de tour à côté de la Tour*/
		Point positionSprite = new Point(position);
		positionSprite.translate((32/2) + (16/2),(32/2) + (16/2));
		System.out.println("Position Tower "+position.x);
		System.out.println("Position Delete "+positionSprite.x);
		/*Ajout à la fin de la queue*/
		sprites.add(new InfoSprite(positionSprite, true, idOwner, 16,16, 0, position, this));	
		
		/*TO DO A Améliorer  => récupérer la fin de la queue...!*/	
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
		   player.towerSuppressed(position, idOwner);
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
     * Dessin de la map
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
