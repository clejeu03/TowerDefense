package View;

import GameEngine.*;
import GameEngine.Player.PlayerType;
import GameEngine.TowerManager.TowerTypes;
import Dispatcher.*; 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Project - TowerDefense</br>
 * <b>Class - ViewManager</b></br>
 * <p>The ViewManager class is responsible for the display of all the items the game need. It 
 * concerns the game and the user interface but also the display of menus at the beginning and
 * the end of a game.</br>
 * The ViewManager is also responsible for the updating of the scene.</br>
 * The ViewManager communicates with the GameEngine trough the Dispatcher.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */

@SuppressWarnings("serial")
public class ViewManager extends JFrame implements Runnable{
	//Thread managers
	private boolean running;
	private DispatcherManager dispatcher;
	private ConcurrentLinkedQueue<Order> queue;
	
	//Windows setting
    public static final int WIDTH = 800 ;
    public static final int HEIGHT = 600 ;
    private Image icon;
    
    //Main Panels	
    private HomeMenu homeMenu;
    private PlayMenu playMenu;
   // private OptionsMenu optionsMenu;
    
    //Game Panels
    private SceneView sceneView;
    private GameMenuBar gameMenuBar;
    private GameInfoPlayer gameInfoPlayer;
    private GameInfoMenu gameInfoMenu;
    private EditorScene editorScene;
    private EditorToolBar editorToolBar;
	
    /**
     * Constructor of the ViewManager class
     */
    public ViewManager() {
		super("PokéWars");
		
		queue = new ConcurrentLinkedQueue<Order>();
		running = false;
		
		homeMenu = new HomeMenu(this, new Point(0,0), WIDTH, HEIGHT);	
		playMenu = new PlayMenu(this, new Point(0,0), WIDTH, HEIGHT);	

		
		sceneView = new SceneView(this,new Point(0,25), 800,400);
		gameMenuBar = new GameMenuBar(this,new Point(0,0),800, 25);
		gameInfoPlayer = new GameInfoPlayer(this, new Point(0,425), 185,175);
		gameInfoMenu = new GameInfoMenu(this, new Point(195,425), 605 ,175);
		editorScene = new EditorScene(this, new Point(0,0), 800, 400);
		editorToolBar = new EditorToolBar(this, new Point(0,400), 800, 200);
		
		
		//Loading the map icon
		try {
		      icon = ImageIO.read(new File("img/icon.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
        //Create and lay the component on the windows
		initComponents();
		layComponents();

        //Main settings of the window
		centralization();
		setIconImage(icon);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setBackground(Color.gray);
		setResizable(false);
        setVisible(true);
	}
    
    /**
     * Initiate the window components
     * @see #ViewManager()
     */	
    public void  initComponents(){
		homeMenu.setPreferredSize(new Dimension(homeMenu.getWidth(), homeMenu.getHeight()));
		playMenu.setPreferredSize(new Dimension(playMenu.getWidth(), playMenu.getHeight()));
		
		
        sceneView.setPreferredSize(new Dimension(sceneView.getWidth(), sceneView.getHeight()));
        gameMenuBar.setPreferredSize(new Dimension(gameMenuBar.getWidth(), gameMenuBar.getHeight()));
        gameInfoPlayer.setPreferredSize(new Dimension(gameInfoPlayer.getWidth(), gameInfoPlayer.getHeight()));
        gameInfoMenu.setPreferredSize(new Dimension(gameInfoMenu.getWidth(), gameInfoMenu.getHeight()));
        editorScene.setPreferredSize(new Dimension(editorScene.getWidth(), editorScene.getHeight()));
        editorToolBar.setPreferredSize(new Dimension(editorToolBar.getWidth(), editorToolBar.getHeight()));
    }

    /**
     * Lay the component on the window
     * @see #ViewManager()
     */	
    public void layComponents(){
    	//Remove the default layout manager of the windows
    	//Allow us to lay the components according to absolute coordinates on the windows
    	setLayout(null);
    	
        //Move and Resize the components
		homeMenu.setBounds(homeMenu.getPosition().x, homeMenu.getPosition().y,homeMenu.getWidth(), homeMenu.getHeight());	   	
		playMenu.setBounds(playMenu.getPosition().x, playMenu.getPosition().y,playMenu.getWidth(), playMenu.getHeight());	   	
		
		sceneView.setBounds(sceneView.getPosition().x, sceneView.getPosition().y,sceneView.getWidth(), sceneView.getHeight());	
        gameMenuBar.setBounds(gameMenuBar.getPosition().x, gameMenuBar.getPosition().y,gameMenuBar.getWidth(), gameMenuBar.getHeight());	
        gameInfoPlayer.setBounds(gameInfoPlayer.getPosition().x, gameInfoPlayer.getPosition().y,gameInfoPlayer.getWidth(), gameInfoPlayer.getHeight());	
        gameInfoMenu.setBounds(gameInfoMenu.getPosition().x, gameInfoMenu.getPosition().y,gameInfoMenu.getWidth(), gameInfoMenu.getHeight());	
        editorScene.setBounds(editorScene.getPosition().x, editorScene.getPosition().y,editorScene.getWidth(), editorScene.getHeight());	   	
        editorToolBar.setBounds(editorToolBar.getPosition().x, editorToolBar.getPosition().y,editorToolBar.getWidth(), editorToolBar.getHeight());	   	
		
        //add the homeMenu panel on the window
        add(homeMenu);
    }
    
	/**
	 * Lay the window at the center of the screen
	 * @see #ViewManager()
	 */
	public void centralization(){
		//Retrieve the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setLocation((screenSize.width-WIDTH)/2,(screenSize.height-HEIGHT)/2);	
	}

	/**
	 * Initiate the dispatcher attribute
	 * @see TowerDefense.TowerDefense#main(String[])
	 */
	public void setDispatcher(DispatcherManager dispatcher){
		this.dispatcher = dispatcher;
	}
	
	/**
	 * Initialize the running attribute
	 * @param r boolean
	 * @see Dispatcher.DispatcherManager#start()
	 * @see Dispatcher.DispatcherManager#stop()
	 */
    public void setRunning(boolean running){
    	this.running = running;
    }
	
    /**
     * Launch the Map Editor (you don't say !)
     */
    public void launchMapEditor(){
    	//Remove the homeMenu panel from the window
    	remove(homeMenu);
    	
    	//Add the editor panels on the window
    	add(editorScene);
    	add(editorToolBar);
        
        //Repaint the window
    	validate();
    	repaint();	  
    }
    
	/**
	 * Initialize the view when the game is launched
	 * @param towers - ArrayList of towers created by the engine during the game initialization
	 * @param bases 
	 * @see Dispatcher.DispatcherManager#initiateGameView(ArrayList)
	 */	
    public void initiateGameView(ArrayList<Base> bases){

		//Clear the Sprites list of the scene
		sceneView.initiate();
		gameInfoMenu.initiate(sceneView);
		
		Iterator<Base> iter = bases.iterator();
		while (iter.hasNext()) {
			//Retrieve the tower
			Base base = iter.next();
			//Create the corresponding TowerSprit
			BaseSprite bs = new BaseSprite(sceneView, base.getId(), base.getPosition(),true, base.getPlayerType(), 36, 36,  base.getAmount());

			//Add the baseSprite in the sceneView list of Sprites
			sceneView.addSprite(bs);
			//Add the matching TextInfoSprite in the sceneView list of Sprites
			sceneView.addSprite(bs.getTextAmount());
		}
				
		//The view and engine initializations are done ! The game can start !
		dispatcher.start();	
    }
	
	/**
	 * Show the game settings panel
	 * @see HomeMenu#jButtonPlayPerformed(ActionEvent)
	 */	
    public void createGame(){
    	//Clear the Sprites list of the playMenu
    	playMenu.initiate();
    	
    	//Remove the homeMenu panel from the window
    	remove(homeMenu);
    	
    	//Add the play panels on the window
    	add(playMenu);
        
        //Repaint the window
    	validate();
    	repaint();	  	
    }
    
	/**
	 * Launch the game
	 * @see HomeMenu#jButtonPlayPesrformed(ActionEvent)
	 */	
    public void play(PlayerType humanType, int nbEnemies, ArrayList<PlayerType> enemiesType){
    	
		//tell the scene and the gameInfoMenu the id of the human player
		sceneView.setHumanType(humanType);
		gameInfoMenu.setHumanType(humanType);
    	
    	//Tell the engine (via the dispatcher) to initiate the game
    	dispatcher.initiateGame(humanType,nbEnemies, enemiesType);

    	//Remove the homeMenu panel from the window
    	remove(playMenu);
    	
    	//Add the game panels on the window
    	add(sceneView);
        add(gameMenuBar);
        add(gameInfoPlayer);
        add(gameInfoMenu);
        
        //Repaint the window
    	validate();
    	repaint();	  	
    }
	/**
	 * Stop the game and display the homeMenu
	 * @see GameMenuBar#jButtonBackPerformed(ActionEvent) 
	 */	
    public void homeMenu(){
    	//Tell the dispatcher to stop the game threads
    	if(running){
    		dispatcher.stop();
    	
	    	//Remove the game panels from the window
	    	remove(sceneView);
	    	remove(gameMenuBar);
	    	remove(gameInfoPlayer);
	    	remove(gameInfoMenu);
    	}
    	
    	else {
    		remove(playMenu);
    	}
	    	//Add the homeMenu panel on the window
	    	add(homeMenu);
        
    	//Repaint the window
    	validate();
    	repaint();	  	
    }
 
   /**
    * Tell the dispatcher a tower need to be suppress
    * @param position
    * @param playerType
    * @see SceneView#towerToSupress(Point, int)
    */
   public void towerToSupress(int id, Point position, PlayerType playerType){
	   dispatcher.addOrderToEngine(new SuppressTowerOrder(id, playerType, position));
   }
   
	/**
	 * Tell the dispatcher a tower need to be add
	 * @param position
	 * @param humanType
	 * @param towerTypes
	 * @see SceneView#myMousePressed()
	 */
	public void towerToAdd(Point position, PlayerType playerType, TowerTypes towerTypes) {
		   dispatcher.addOrderToEngine(new AddTowerOrder(-1, playerType, position, towerTypes));
	}
	/**
	 * Tell the dispatcher a player want to attack an other base
	 * @param position
	 * @param humanType
	 * @param towerType
	 * @see SceneView#myMousePressed()
	 */
	public void baseToAttack(int idBaseSrc, Point srcPosition, PlayerType srcPlayerType,Point dstPosition, int amount) {
		   dispatcher.addOrderToEngine(new AddUnitOrder(idBaseSrc, srcPlayerType, srcPosition, dstPosition, amount));
	}
    
	/**
	 * Refresh the graphic component of the view
	 * @see #run()
	 */	
	public void refresh(){
		//Retrieve the current size of the queue
		int nb = queue.size();
		
		//Execute and remove the "size" first orders of the queue
		if(nb>0){
			for(int i = 0;i<nb; i++){
				//Retrieve and remove the head of the queue
				Order o = queue.poll();
				
				//If the order is a SuppressTowerOrder one
				if(o instanceof SuppressTowerOrder) {
					sceneView.suppressTower(((ArmyOrder) o).getId(), ((ArmyOrder) o).getPosition(), o.getPlayerType());
				}
				
				//If the order is an AddTowerOrder one
				if(o instanceof AddTowerOrder) {
					sceneView.addTower(((ArmyOrder) o).getId(),((ArmyOrder) o).getPosition(), o.getPlayerType(), ((AddTowerOrder) o).getTowerType());
				}
				//If the order is an AddUnitOrder one
				if(o instanceof AddUnitOrder) {
					UnitSprite unit = new UnitSprite(sceneView,((AddUnitOrder) o).getId(), ((AddUnitOrder) o).getPosition(),o.getPlayerType(),((AddUnitOrder) o).getAmount());
					sceneView.addSprite(unit);
					sceneView.addSprite(unit.getTextAmount());
					System.out.println("View - TODO : Add  "+((AddUnitOrder) o).getAmount()+" "+o.getPlayerType()+" units at "+((AddUnitOrder) o).getPosition());
				}
				//If the order is an MoveUnitOrder one
				if(o instanceof MoveUnitOrder) {
					sceneView.moveUnit(((MoveUnitOrder) o).getId(),o.getPlayerType(), ((ArmyOrder) o).getPosition(), ((MoveUnitOrder) o).getNewPosition());
				}
				//If the order is an AmountBaseOrder one
				if(o instanceof AmountBaseOrder){
					sceneView.setAmountBase(((AmountBaseOrder) o).getId(),((ArmyOrder) o).getPosition(),o.getPlayerType(), ((AmountBaseOrder) o).getAmount());
				}
			}
		}
	}
	
	/**
	 * Add an order to the engine ConcurrentLinkedQueue queue
	 * @see Dispatcher.DispatcherManager#addOrderToView(Order)
	 */	
	public void addOrder(Order order){
		//Add the order to the queue
		queue.add(order);
	}

	/**
	 * run() method of the view thread
	 */	
	@Override
	public void run() {
		while(running){
			/*try{
				Thread.sleep(500);
			}
			catch(InterruptedException e){
				System.out.println(e.getMessage());
			}*/
			
			refresh();
		}
	}

}