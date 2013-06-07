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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

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
    private Image img2;

    private ArrayList<Sprite> attackTree;
    private ArrayList<Sprite> supportTree;
    
    private boolean showAttackTree;
    private boolean showSupportTree;
    
    private TowerTypes towerClickedType;
    private TowerTypes newTowerType;
    private int idTower;
    
    private JButton jButton;
    private boolean sell;
    private JLabel jTowerName;
    private JLabel jTowerCost;
    
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
		
		attackTree = new ArrayList<Sprite>();
		supportTree = new ArrayList<Sprite>();
		
		showAttackTree = false;
		showSupportTree = false;
		
		jButton = new javax.swing.JButton();
		sell = false;
		jTowerName = new JLabel();
		jTowerCost = new JLabel();
		jTowerName.setForeground(Color.BLACK);
		jTowerCost.setForeground(Color.BLACK);
		
		jButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonPerformed(evt);
		    }
		});
		
		//Loading the background
		try {
			 img = ImageIO.read(new File("img/towerMenuInactive.png"));
			 img2 = ImageIO.read(new File("img/towerMenuActive.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Laying the components on the Panel
		setLayout(null);
		jButton.setBounds(430, 110, 100,25);
		jTowerName.setBounds(372, 35, 200,25);
		jTowerCost.setBounds(372, 50, 200,25);
		setBackground(Color.white); 		
	}
	
	/**
	 * jButtonPlay Event handler - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonPerformed(ActionEvent evt) {
    	////Tell the engine that the player want to evolve or sell his tower
    	if(sell)view.suppressTower(idTower);
    	else view.evolveTower(idTower, newTowerType);
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
		//Removing all the supportTree Sprites
		supportTree.clear();
		
		//Add the AddTower Attack Sprite on the panel
		addSprite(new AddTowerSprite(scene, this, new Point(32,40), true, humanType, 64, 64, TowerTypes.ATTACKTOWER));
		addSprite(new AddTowerSprite(scene, this, new Point(32,100), true, humanType, 64, 64, TowerTypes.SUPPORTTOWER));
		
		//Initiating the attackTree
		setAttackTree();
		setSupportTree();
			
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
	
	public void setAttackTree(){
		attackTree.clear();
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(90,95), false, humanType, 44, 44, TowerTypes.ATTACKTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(156,62), false, humanType, 44, 44, TowerTypes.GUNTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(156,124), false, humanType, 44, 44, TowerTypes.FROSTTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(222,29), false, humanType, 44, 44, TowerTypes.BOMBTOWER));
		attackTree.add(new EvolveTowerSprite(this,-1, new Point(222,95), false, humanType, 44, 44, TowerTypes.LAZERTOWER));
	}
	
	public void setSupportTree(){
		supportTree.clear();
		supportTree.add(new EvolveTowerSprite(this,-1, new Point(90,95), false, humanType, 44, 44, TowerTypes.SUPPORTTOWER));
		supportTree.add(new EvolveTowerSprite(this,-1, new Point(156,62), false, humanType, 44, 44, TowerTypes.MEDICALTOWER));
		supportTree.add(new EvolveTowerSprite(this,-1, new Point(156,124), false, humanType, 44, 44, TowerTypes.SHIELDTOWER));
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
		
		towerClickedType = towerType;
		
		//Displaying the evolution tree of the tower
		if((towerType == TowerTypes.SUPPORTTOWER)||(towerType == TowerTypes.SHIELDTOWER)||(towerType == TowerTypes.MEDICALTOWER)){
			for(Sprite evolve:supportTree){
				if((evolutions.indexOf(((EvolveTowerSprite) evolve).getTowerType())!= -1)||(((EvolveTowerSprite) evolve).getTowerType() == towerType)){
					((EvolveTowerSprite) evolve).setId(id);
					((EvolveTowerSprite) evolve).setClickable(true);
				}
				addSprite(evolve);
			}
			
			//Resetting the attackTree
			setSupportTree();
			
			showSupportTree = true;
		}
		else{
			for(Sprite evolve:attackTree){
				if((evolutions.indexOf(((EvolveTowerSprite) evolve).getTowerType())!= -1)||(((EvolveTowerSprite) evolve).getTowerType() == towerType)){
					((EvolveTowerSprite) evolve).setId(id);
					((EvolveTowerSprite) evolve).setClickable(true);
				}
				addSprite(evolve);
			}
			
			//Resetting the attackTree
			setAttackTree();
			
			showAttackTree = true;
		}
		
		//Displaying the tower informations 
		jTowerName.setText(towerType+"");
		jTowerCost.setText("Purchase price : "+towerType.cost()+" P$");
		add(jTowerName);
		add(jTowerCost);
		
		//Adding the selling button
		jButton.setText("Sell");
		sell = true;
		add(jButton);
		
        //Repaint the panel
    	revalidate();
    	repaint();
	}
	
	/**
	 * Hide the information of the tower
	 */
	public void hideTowerInfo(){
		if(showAttackTree) showAttackTree = false;
		if(showSupportTree) showSupportTree = false;
		
		remove(jButton);
		remove(jTowerName);
		remove(jTowerCost);
		
		SwingUtilities.invokeLater(new Runnable(){
		public void run() {
			//Removing the EvolveTowerSprite			
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
	
	public void evolveTowerClicked(int id, TowerTypes towerType){
		newTowerType = towerType;
		idTower = id;
		
		if(towerType == towerClickedType){
			jButton.setText("Sell");
			sell = true;
		}
		else{
			jButton.setText("Evolve");
			if(sell) sell = false;
		}
		
		jTowerName.setText(towerType+"");
		jTowerCost.setText("Purchase price : "+towerType.cost()+" P$");
		add(jTowerName);
		add(jTowerCost);
	}
	
    /**
     * Draw the GameInfoMenu Panel
     */
    @Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.darkGray);
		if((! showAttackTree)&&(!showSupportTree)) g.drawImage(img, 64, 0, 545, this.getHeight(), this);
		else g.drawImage(img2, 64, 0, 545, this.getHeight(), this);
		if((showAttackTree)||(showSupportTree)){
			g.drawLine(86,95, 152,62);
			g.drawLine(86,95, 152,128);

			if(showAttackTree){
				g.drawLine(152,62, 218,29);
				g.drawLine(152,62, 218, 95);
			}
		}
	 }   
}
