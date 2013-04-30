package GameEngine;

import Dispatcher.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import View.Sprite;
import View.StarterSprite;

/**
 * Project - TowerDefense</br>
 * <b>Class - GameManager</b></br>
 * <p>The GameManager is the one that can start the game and determine who has won.</br>
 * It creates the Player class and manage it. It has access to the WarManager when it's necessary,
 * for example when there is a tower's construction to check the player's money.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Player
 * @see WarManager
 */

public class GameManager implements Runnable{
	//Thread managers
	private boolean running;
	private DispatcherManager dispatcher;
	private ConcurrentLinkedQueue<Order> queue;
	
    private ArrayList<Player> players;
	
	//Temporary !!
    private ArrayList<Tower> towers;
    private ArrayList<Base> bases;
    
	private MapManager mapManager;
    
    /**
     * Constructor of the GameManger class
     */
	public GameManager() {
		super();
		running = false;
		queue = new ConcurrentLinkedQueue<Order>();
		players = new ArrayList<Player>();
		
		towers = new ArrayList<Tower>();
		bases = new ArrayList<Base>();
	}
	
	/**
	 * Setter. Initiate the dispatcher attribute.
	 * @see TowerDefense.TowerDefense#main(String[]) 
	 */
	public void setDispatcher(DispatcherManager dispatcher){
		this.dispatcher = dispatcher;
	}

	/**
	 * Setter. Initiate the running attribute.
	 * @param running - tells if the engine threads is running
	 * @see Dispatcher.DispatcherManager#start()
	 * @see Dispatcher.DispatcherManager#stop()
	 */
    public void setRunning(boolean running){
    	this.running = running;
    }
			
	/**
	 * Initiate the game according to the player choices
	 * @see Dispatcher.DispatcherManager#initiateGame()
	 */
	public void initiateGame(int humanId, int nbEnemies, ArrayList<Integer> enemiesId){
		System.out.println("Engine say : Initating the game...");
		System.out.println("Engine say : "+nbEnemies+" enemies");
		
		//Adding a mapManager
		mapManager = new MapManager("Map", nbEnemies+1);
		
		//Creating towers TEMPORARY !
		//Clear the towers list
		towers.clear();
		//Adding Enemies towers (temporary !)
		Iterator<Integer> it = enemiesId.iterator();
		while (it.hasNext()) {
			int enemyId = it.next();
			towers.add(new MedicalTower(new Point(50+(100*enemyId),50+(100*enemyId)), enemyId, 90));
		}	
		//human player tower (temporary !)
		towers.add(new MedicalTower(new Point(125,50), humanId, 90));
		
		//Creating the player (human and IA)
		//Clear the player list
		players.clear();
		//Adding the enemies
		Iterator<Integer> iter = enemiesId.iterator();
		while (iter.hasNext()) {
			int enemyId = iter.next();
			players.add(new Player(enemyId));
		}	
		//Adding the human player
		players.add(new Player(humanId));
		
		
		//Retrieve the bases positions
		Point[] basesPositions = mapManager.getPlayerBasePosition();
		//Clear the bases list
		bases.clear();
		for(int i=0; i<enemiesId.size();i++){
			bases.add(new Base(basesPositions[i+1],enemiesId.get(i),false,mapManager.getPlayerProximityMap(i+1)));
		}
		//human player tower (temporary !)
		bases.add(new Base(basesPositions[0],humanId,false,mapManager.getPlayerProximityMap(0)));
		
				
		//Tells the dispatcher that the View need to be initialized
		dispatcher.initiateGameView(towers, bases);
	}
	
	/**
	 * Execute the player actions
	 * @see GameManager#run()
	 */	
	public void execute(){
		//Retrieve the current size of the queue
		int size = queue.size();
		
		//Execute and remove the "size" first orders of the queue
		if(size>0){
			for(int i = 0;i<size; i++){
				//Retrieve and remove the head of the queue
				Order order = queue.poll();
				
				//If the order is a SuppressTowerOrder one
				if(order instanceof SuppressTowerOrder) {
					System.out.println("Engine say : I have to suppress the tower : OwnerID "+order.getPlayerId()+" Position "+((TowerOrder) order).getPosition().x + " "+((TowerOrder) order).getPosition().y);
					
					//Remove the tower from the engine list
					Iterator<Tower> it = towers.iterator();
					while (it.hasNext()) {
						Tower element = it.next();
						if(element.getPosition().equals(((TowerOrder) order).getPosition())){
							it.remove();
						}
						//Tell the dispatcher that the tower need to be remove from the view
						dispatcher.addOrderToView(new SuppressTowerOrder(order.getPlayerId(), ((TowerOrder) order).getPosition()));
					}
				
				}
			}
		}
	}
	
	
	/**
	 * Add an order to the engine ConcurrentLinkedQueue queue
	 * @see Dispatcher.DispatcherManager#addOrderToEngine(Order)
	 */	
	public void addOrder(Order order){
		//Add the order to the queue
		queue.add(order);
	}
	
	/**
	 * run() method of the engine thread
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
			execute();
		}
		
	}


}