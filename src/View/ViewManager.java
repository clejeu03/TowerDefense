package View;

import GameEngine.*;
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
    
	
    /**
     * Constructor of the ViewManager class
     */
    public ViewManager() {
		super("TowerDefense");
		
		queue = new ConcurrentLinkedQueue<Order>();
		running = false;
		
		homeMenu = new HomeMenu(this, new Point(0,0), WIDTH, HEIGHT);	
		playMenu = new PlayMenu(this, new Point(0,0), WIDTH, HEIGHT);	

		
		sceneView = new SceneView(this,new Point(0,25), 800,400);
		gameMenuBar = new GameMenuBar(this,new Point(0,0),800, 25);
		gameInfoPlayer = new GameInfoPlayer(this, new Point(0,425), 185,175);
		gameInfoMenu = new GameInfoMenu(this, new Point(195,425), 605 ,175);

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
	 * Initialize the view when the game is launched
	 * @param towers - ArrayList of towers created by the engine during the game initialization
	 * @see Dispatcher.DispatcherManager#initiateGameView(ArrayList)
	 */	
    public void initiateGameView(ArrayList<Tower> towers){
		System.out.println("Engine say : Initating the game. interface..");

		//Clear the Sprites list of the scene
		sceneView.initiate();
			
		Iterator<Tower> it = towers.iterator();
		while (it.hasNext()) {
			//Retrieve the tower
			Tower tower = it.next();
			
			//Create the corresponding TowerSprite
			boolean clickable = false;
			int towerType = 0;
			
			//If the tower's owner is the human player, the Sprite needs to be clickable
			if(tower.getPlayerId() == sceneView.getHumanId()){
				clickable = true;
			}
			//TODO Choose the the type of the tower
			if(tower instanceof MedicalTower){
				
			}
			TowerSprite ts = new TowerSprite(sceneView, tower.getPosition(),clickable, tower.getPlayerId(), 50, 50, towerType, tower.getRange());

			//Add the towerSprite in the sceneView list of Sprites
			sceneView.addSprite(ts);
		}
		
		//Adding two Bases (temporary !)
		BaseSprite bs1 = new BaseSprite(sceneView, new Point(200,200), true,  sceneView.getHumanId(), 36, 36);
		sceneView.addSprite(bs1);
		BaseSprite bs2 = new BaseSprite(sceneView, new Point(400,300), true, 1, 36, 36);
		sceneView.addSprite(bs2);
		
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
    	revalidate();
    	repaint();	  	
    }
    
	/**
	 * Launch the game
	 * @see HomeMenu#jButtonPlayPerformed(ActionEvent)
	 */	
    public void play(int humanId){
		//tell the scene the id of the human player
		sceneView.setHumanId(humanId);
    	
    	//Tell the engine (via the dispatcher) to initiate the game
    	dispatcher.initiateGame(humanId);
    	
    	//Remove the homeMenu panel from the window
    	remove(playMenu);
    	
    	//Add the game panels on the window
    	add(sceneView);
        add(gameMenuBar);
        add(gameInfoPlayer);
        add(gameInfoMenu);
        
        //Repaint the window
    	revalidate();
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
    	revalidate();
    	repaint();	  	
    }
 
   /**
    * Tell the dispatcher a tower need to be suppress
    * @param position
    * @param playerId
    * @see SceneView#towerToSupress(Point, int)
    */
   public void towerToSupress(Point position, int playerId){
	   dispatcher.addOrderToEngine(new SuppressTowerOrder(playerId, position));
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
					System.out.println("Interface say : I have to suppress the tower : OwnerID "+o.getPlayerId()+" Position "+((TowerOrder) o).getPosition().x + " "+((TowerOrder) o).getPosition().y);
					sceneView.suppressTower(((TowerOrder) o).getPosition(), o.getPlayerId());
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