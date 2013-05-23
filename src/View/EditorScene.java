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
	
	private EditorToolBar editorToolBar;
	
	private boolean edit;
	private boolean editHeight;
	private boolean addBaseClicked;
	
	private Point addBasePosition;
	private int playerBaseCount;
	private int neutralBaseCount;
	
	private int nbCaseInGrid;
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
		
		nbCaseInGrid = (width/16)*(height/16);
		heightGrid = new boolean[nbCaseInGrid];
		
		for(int i=0;i<nbCaseInGrid;i++){
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
		setBackground(Color.gray); 
	}
	
	/**
	 * Event "the mouse has been pressed in the zone" handler
	 * @param me - MouseEvent
	 */
	private void myMousePressed(MouseEvent me) {
		
		//Click on the map to add the base
		if (addBaseClicked) {
			//Retrieve the add base Sprite
			Iterator<Sprite> it = sprites.iterator();
			while (it.hasNext()) {
				Sprite element = it.next();
				if(element.getPosition().equals(addBasePosition)){
					
					//Checking if the position is on a plain or on a hilly area 
					Point squarePosition = new Point(addBasePosition.x/16,addBasePosition.y/16);
					
		    		if(!heightGrid[squarePosition.x+(squarePosition.y*50)]){
						if((((EditorBaseSprite)element).getPlayerType() == PlayerType.NEUTRAL) &&(neutralBaseCount<5)){
							++neutralBaseCount;
							addBaseSuccess();
						}
						else if((((EditorBaseSprite)element).getPlayerType() != PlayerType.NEUTRAL) &&(playerBaseCount<4)){
							++playerBaseCount;
							addBaseSuccess();
						}
						else{
							addBaseFailed();
							editorToolBar.displayError("ERROR : You can't add more than 4 player bases and 5 neutral bases ! ");
						}
		    		}
		    		else{
		    			addBaseFailed();
						editorToolBar.displayError("ERROR : You can't add a base on an hilly area ! ");
		    		}
					
				}
			}		
		
		
		//Click on the map when a base is selected
		/*if (baseClicked){
	    	baseClicked = false;*/
			
	    	//Repaint the Panel

		}
	}
	

	public void setEditorToolBar(EditorToolBar editorToolBar) {
		this.editorToolBar = editorToolBar;
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
						baseOnRelief(squarePosition);
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
	 * Removing a base if it's on a hilly area while painting the relief
	 */
	public void baseOnRelief(Point squarePosition){
		//Retrieve the base Sprite
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			
			//If the base position is in the newly painting square (+the 8 square around) the base need to be removed
			if(
					((element.getPosition().x >= (squarePosition.x -1)*16) && (element.getPosition().x <= (squarePosition.x +1)*16))
				&&  ((element.getPosition().y >= (squarePosition.y -1)*16) && (element.getPosition().y <= (squarePosition.y +1)*16))
			){
				if(((EditorBaseSprite)element).getPlayerType() == PlayerType.NEUTRAL){
					--neutralBaseCount;
				}
				else{
					--playerBaseCount;
				}
				it.remove();
				remove(element);
				break;
			}
		}
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
		
		editHeight = true;
		addBaseClicked = false;
		
		int nb = (width/16)*(height/16);	
		for(int i=0;i<nb;i++){
			heightGrid[i] = false;
		}
		
		//clearing the sprites list
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			remove(element);
		}
		sprites.clear();
		
		//Reseting the base count
		neutralBaseCount = 0;
		playerBaseCount = 0;

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
		
		//Reseting the base count
		neutralBaseCount = 0;
		playerBaseCount = 0;
		
		if (addBaseClicked) {
			addBaseFailed();
		}
		
		//Clear the heightGrid map
		int nb = (width/16)*(height/16);
		for(int i=0;i<nb;i++){
			heightGrid[i] = false;
		}
		
		//clearing the sprites list
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			remove(element);
		}
		sprites.clear();
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
			
			addBasePosition = new Point(position.x+1, position.y+1);
			
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
	 * Suppress the clicked base
	 * @param position
	 * @param playerType
	 */	
	public void baseClicked(Point position, PlayerType playerType){
		//Retrieve the base Sprite
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			if(element.getPosition().equals(position)){
				if(((EditorBaseSprite)element).getPlayerType() == PlayerType.NEUTRAL){
					--neutralBaseCount;
				}
				else{
					--playerBaseCount;
				}
				it.remove();
				remove(element);
				break;
			}
		}
		revalidate();
		repaint();
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
    
    

	public int getNbCaseInGrid() {
		return nbCaseInGrid;
	}

	public int getPlayerBaseCount() {
		return playerBaseCount;
	}
	

	public int getNeutralBaseCount() {
		return neutralBaseCount;
	}
	

	public boolean[] getHeightGrid() {
		return heightGrid;
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
}
