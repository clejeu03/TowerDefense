/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 9 mai 2013
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;


/**
 * Project - TowerDefense</br>
 * <b>Class - MainMenusView</b></br>
 * <p>The MainMenusView abstract class represents the mains "panels" of the game :
 * HomeMenu, EndGameMenu, OptionsMenu, PlayMenu, SceneView, GameMenuBar, MapEditor
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
@SuppressWarnings("serial")
public class EditorScene extends MainViews{
	private BufferedImage mapView;
	
	private boolean edit;
	private boolean editHeight;
	
	private boolean heightGrid[];
	/**
	 * Constructor of the Editor Scene Class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public EditorScene(ViewManager view, Point position, int width, int height) {
		super(view,position,width,height);
		
		edit = false;
		//TODO : mettre un bouton => modification de la map quand on clique dessus
		editHeight = true;
		
		int nb = (width/16)*(height/16);
		heightGrid = new boolean[nb];
		
		for(int i=0;i<nb;i++){
			heightGrid[i] = false;
		}
		
		 //Add a mouse listener on the map
    	addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) { 
				if(editHeight) myMouse(me);
				else myMousePressed(me);
	        }
         });
    	
    	//Add a mouse motion listener on the map
    	addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				//myMouseMoved(e);
			}
			//TODO : 
			public void mouseDragged(MouseEvent e) {
				if(editHeight) myMouse(e);
			}
    	 });
		
		setLayout(null);
		setBackground(Color.white); 
	}
	
	/**
	 * Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {		
	    	//Repaint the Panel
	    	revalidate();
	    	repaint();	
	}
	
	/**
	 * Event "the mouse has been moved or dragged in the zone" handler
	 * @param e - MouseEvent
	 */
	private void myMouse(final MouseEvent e) {	
		SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			//Retrieve the current mouse position
			Point mousePosition = new Point(e.getPoint());
			Point squarePosition = new Point(mousePosition.x/16,mousePosition.y/16);
			
			//If the mouse is still on the map
			if(((mousePosition.x<800)&&(mousePosition.x>0))&&((mousePosition.y<400)&&(mousePosition.y>0))){
				
				int modifiers = e.getModifiers();
				
				//If the mouse left button is pressed
		        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
					if(!heightGrid[squarePosition.x+(squarePosition.y*50)]){
						heightGrid[squarePosition.x+(squarePosition.y*50)] = true;
					}
		        }
		        
		        //If the mouse right button is pressed 
		        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
					if(heightGrid[squarePosition.x+(squarePosition.y*50)]){
						heightGrid[squarePosition.x+(squarePosition.y*50)] = false;
					}
			    }
		       						
		    	//Repaint the Panel
		    	revalidate();
		    	repaint();	
			}
		}});
	}
	
	
	
	public void initiate(BufferedImage img){
		//Loading the image map
		mapView = img;
		
		//Reseting the boolean
		edit = true;

		revalidate();
		repaint();
	}

	public void quitEditor() {
		//Reseting
		edit = false;
		//TODO editHeight = false;
		
		//Clear the heightGrid map
		int nb = (width/16)*(height/16);
		for(int i=0;i<nb;i++){
			heightGrid[i] = false;
		}
	}
	
    /**
     * Draw the SceneView Panel
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(edit){
		    g.drawImage(mapView, 0, 0, this.getWidth(), this.getHeight(), this);
		    g.setColor(Color.gray);
		    
		    //Draw the grid
		    for(int i=0; i<=this.getWidth();i+=16){
		    	g.drawLine(i, 0, i, 400); 	
		    }
		    for(int j=0; j<=this.getHeight();j+=16){
		    	g.drawLine(0, j, 800, j); 	
		    }
		    
		    g.setColor(Color.black);
		    //Draw the heightGrid
		    for(int y =0; y<25; y++){
		    	for(int x = 0; x<50; x++){
		    		if(heightGrid[x+(y*50)]){
		    			g.fillRect(x*16, y*16, 16, 16); 
		    		}
		    	}
		    }
		    
		}
    }
}
