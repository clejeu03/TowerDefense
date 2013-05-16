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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingUtilities;

import GameEngine.Player.PlayerType;


/**
 * Project - TowerDefense</br>
 * <b>Class - EditorScene</b></br>
 * <p>The ditorScene class represents the editor scene, displaying the user MapView and its edit
 * </p> 
 * <b>Creation :</b> 10/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see EditorToolBar
 * @see ViewManager
 */
@SuppressWarnings("serial")
public class EditorScene extends MainViews{
	private BufferedImage mapView;
	
	private boolean edit;
	private boolean editHeight;
	private boolean addBaseClicked;
	
	private Point addBasePosition;
	private int playerBaseCount;
	private int neutralBaseCount;
	
	private boolean heightGrid[];
	
    private ArrayList<Sprite> sprites;
	
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
		editHeight = false;
		addBaseClicked = false;
		
		neutralBaseCount = 0;
		playerBaseCount = 0;
		
		sprites = new ArrayList<Sprite>();
		
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
				myMouseMoved(e);
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
		
		//Click on the map to add the tower
		if (addBaseClicked) {
			//Retrieve the add tower Sprite
			Iterator<Sprite> it = sprites.iterator();
			while (it.hasNext()) {
				Sprite element = it.next();
				if(element.getPosition().equals(addBasePosition)){
					addBaseSuccess();
				}
			}		
		
		
		//Click on the map when a base is selected
		/*if (baseClicked){
	    	baseClicked = false;*/
			
	    	//Repaint the Panel

		}
	}

	/**
	 * Event "the mouse has moved in the zone" handler
	 * @param e - MouseEvent
	 */
	private void myMouseMoved(final MouseEvent e) {	
		SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			if(addBaseClicked){
				//Retrieve the the add tower Sprite
				Iterator<Sprite> it = sprites.iterator();
				while (it.hasNext()) {
					Sprite element = it.next();
					if(element.getPosition().equals(addBasePosition)){
						//Reset the tower Sprite Position according to the mouse one
						if(e.getPoint().y<(height-10)){
							addBasePosition = new Point(e.getPoint());
							element.setPosition(addBasePosition);
							element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
							add(element);
						}
						else {
							remove(element);
						}
					}
				}		
				//Repaint the Panel
		    	revalidate();
		    	repaint();
			}
		}});
	}
	
	
	/**
	 * Add a Sprite in the EditorToolBar ArrayList
	 * @param sprite
	 * @see
	 */
	public void addSprite(Sprite sprite){
		sprites.add(sprite);
		
		//TO DO : retrieve the last element added...
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
	
	
	/**
	 * Initiate the EditorScene
	 * @param img
	 * @see EditorToolBar#openImage(String, String)
	 */
	public void initiate(BufferedImage img){
		//Loading the image map
		mapView = img;
		
		//Reseting the boolean
		edit = true;

		revalidate();
		repaint();
	}

	/**
	 * Prepare the Editor to be quitted
	 * @see EditorToolBar#jButtonBackPerformed(ActionEvent)
	 */
	public void quitEditor() {
		//Reseting
		edit = false;
		if (addBaseClicked) {
			addBaseFailed();
		}
		
		//Clear the heightGrid map
		int nb = (width/16)*(height/16);
		for(int i=0;i<nb;i++){
			heightGrid[i] = false;
		}
	}
	
	/**
	 * Setter EditHeight
	 * @param editHeight
	 * @see EditorToolBar#paintRelief()
	 */
    public void setEditHeight(boolean editHeight) {
		this.editHeight = editHeight;
		if (addBaseClicked) {
			addBaseFailed();
		}
	}
    
	/**
	 * Display the territory map when the player want to add a tower
	 * @param position - Position of the center of the AddTower button clicked
	 * @param playerType
	 * @param towerType
	 */
	public void addBaseClicked(Point position, PlayerType playerType){
		if((edit)&&(!addBaseClicked)){
			editHeight = false;
			addBaseClicked = true;
			
			System.out.println("Add base clicked");
			
			addBasePosition = new Point(position.x+1, position.y+1);
			
			//TODO : change the id of the tower if it's add by the engine...
			EditorBaseSprite bs = new EditorBaseSprite(this, -1, false, addBasePosition, playerType, 36, 36);
			
			//Add the baseSprite in the EditorScene list of Sprites
			addSprite(bs);
		}
		else{
			addBaseFailed();
		}
		
		//if (baseClicked) baseClicked = false;	
	}
	
	/**
	 * Add the tower the player wanted to add
	 * @see #addTower(Point, PlayerType, int)
	 */
	public void addBaseSuccess(){
		addBaseClicked = false;
		
		//Set the base Sprite clickable attribute to true
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			if(element.getPosition().equals(addBasePosition)){
				((EditorBaseSprite) element).setClickable(true);
			}
		}
    	//Repaint the Panel
    	revalidate();
    	repaint();	
	}
	
	/**
	 * Remove the tower-to-add Sprite
	 * @see #baseClicked(Point, PlayerType)
	 * @see #initiate()
	 * @see #towerClicked(Point, PlayerType)
	 * @see #addTowerClicked(Point, PlayerType, int)
	 */
	public void addBaseFailed(){
		addBaseClicked = false;
		
		SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			//Suppress the base-to-add Sprite
			Iterator<Sprite> it = sprites.iterator();
			while (it.hasNext()) {
				Sprite element = it.next();
				if(element.getPosition().equals(addBasePosition)){
					it.remove();
					remove(element);
				}
			}
			//Repaint the panel
	    	revalidate();
	    	repaint();	
		}});
	}
	
	

	/**
     * Draw the EditorScene Panel
     * @param g Graphics
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
