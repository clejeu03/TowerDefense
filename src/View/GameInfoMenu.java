/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 26 avr. 2013
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

import GameEngine.Missile;
import GameEngine.TowerManager;
import GameEngine.Player.PlayerType;
import GameEngine.TowerManager.TowerTypes;

/**
 * Project - TowerDefense</br>
 * <b>Class - GameInfoMenu</b></br>
 * <p>The GameInfoMenu class is used to display a user friendly interface while playing the game.
 * The player would be allowed to make actions thanks to buttons and informations hold in the
 * GameInfoPanel.
 * The GameInfoMenu is created and managed by the ViewManager.</br>
 * </p> 
 * <b>Creation :</b> 26/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see ViewManager
 */
@SuppressWarnings("serial")
public class GameInfoMenu extends MainViews{

    private ArrayList<Sprite> sprites;
    private PlayerType humanType;
    
    private Image img;
    private boolean towerClicked;

    private ArrayList<Sprite> attackTree;
    private ArrayList<Sprite> supportTree;
	/**
	 * Constructor of the GameInfoMenu class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public GameInfoMenu(ViewManager view, Point position, int width, int height) {
		super(view, position, width,height);
		
		sprites = new ArrayList<Sprite>();
		towerClicked = false;
	
		attackTree = new ArrayList<Sprite>();
		supportTree = new ArrayList<Sprite>();
		
		//Loading the background
		try {
			 img = ImageIO.read(new File("img/gameInfo.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.gray); 		
	}
	
	/**
	 * Add a Sprite in the GameInfoMenu ArrayList
	 * @param sprite
	 * @see ViewManager#initiateGameView(ArrayList)
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
	 * Reset the SceneView
	 * @see ViewManager#initiateGameView(ArrayList)
	 */
	public void initiate(SceneView scene){
			
		//Removing all the Sprites		
		Iterator<Sprite> it = sprites.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			it.remove();
			remove(element);
		}
		
		//Removing all the attackTree Sprites	
		attackTree.clear();
		
		//Add the AddTower Attack Sprite on the panel
		addSprite(new AddTowerSprite(scene, this, new Point(32,40), true, humanType, 64, 64, TowerTypes.ATTACKTOWER));
		addSprite(new AddTowerSprite(scene, this, new Point(32,100), true, humanType, 64, 64, TowerTypes.SUPPORTTOWER));
		
		//Initiating the attackTree
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(88,90), false, humanType, 48, 48, TowerTypes.ATTACKTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(160,54), false, humanType, 48, 48, TowerTypes.GUNTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(160,126), false, humanType, 48, 48, TowerTypes.FROSTTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(232,18), false, humanType, 48, 48, TowerTypes.BOMBTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(232,90), false, humanType, 48, 48, TowerTypes.LAZERTOWER));
			
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
	
	/**
	 * Setter - humanType
	 * @param humanType - id of the human player
	 * @see ViewManager#play(int)
	 */
	public void setHumanType(PlayerType humanType) {
		this.humanType = humanType;
	}
	
	/**
	 * Display the information of the tower
	 * @param id
	 * @param playerType
	 * @param towerType
	 */
	public void towerClicked(int id, PlayerType playerType, TowerTypes towerType, ArrayList<TowerManager.TowerTypes> evolutions){
		towerClicked= true;
		
		for(TowerTypes type:evolutions){
			System.out.println(type);
		}
  		//TODO Displaying some information and a supressbouton..
		
		//Displaying the evolution tree of the tower
		if((towerType == TowerTypes.SUPPORTTOWER)||(towerType == TowerTypes.SHIELDTOWER)||(towerType == TowerTypes.MEDICALTOWER)){
			System.out.println("Support");
		}
		else{
			for(Sprite evolve:attackTree){
				if((((EvolveTowerSprite) evolve).getTowerType() == evolutions.get(0))||(((EvolveTowerSprite) evolve).getTowerType() == evolutions.get(1))){
					((EvolveTowerSprite) evolve).setId(id);
					((EvolveTowerSprite) evolve).setClickable(true);
				}
				else{
					((EvolveTowerSprite) evolve).setId(-1);
					((EvolveTowerSprite) evolve).setClickable(false);
				}
				addSprite(evolve);
			}
			
		}
		/*addSprite(new EvolveTowerSprite(this, id, new Point(100,80), false, humanType, 48, 48, towerType));
		int i=0;
		for(TowerTypes type:evolutions){
			System.out.println(type);
			if(type != TowerTypes.NOTOWER){
				addSprite(new EvolveTowerSprite(this,id, new Point(150,40+i*80), true, humanType, 48, 48, type));
				++i;
			}
		}*/
	}
	
	/**
	 * Hide the information of the tower
	 */
	public void hideTowerInfo(){
		towerClicked = false;
		SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			//Removing the towerInfoSprite			
			Iterator<Sprite> it = sprites.iterator();
			while (it.hasNext()) {
				Sprite element = it.next();
				if(element instanceof EvolveTowerSprite){
					it.remove();
					remove(element);
				}
			}
	    	//Repaint the Panel
	    	revalidate();
	    	repaint();
		}});
	}
	
	public void evolveTower(int id, TowerTypes towerType){
		//Tell the engine that the player want to evolve his tower
		view.evolveTower(id, towerType);
	}
	
    /**
     * Draw the GameInfoMenu Panel
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(! towerClicked) g.drawImage(img, 64, 0, 551, this.getHeight(), this);
	 }   
}
