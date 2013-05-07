package GameEngine;

import Dispatcher.*;
import GameEngine.Player.PlayerType;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import View.Sprite;

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
	public void initiateGame(PlayerType humanType, int nbEnemies, ArrayList<PlayerType> enemiesType){
		
		//Creating the player (human and IA)
		//Clear the player list
		players.clear();
		
		//Adding the human player
		players.add(new Player(humanType));
		
		ArrayList<PlayerType> playerTypes = new ArrayList<PlayerType>();
		playerTypes.add(humanType);
		
		//Adding the enemies
		Iterator<PlayerType> iter = enemiesType.iterator();
		while (iter.hasNext()) {
			PlayerType enemyType = iter.next();
			players.add(new Player(enemyType));
			playerTypes.add(enemyType);
		}	
		
		//Adding a mapManager
		mapManager = new MapManager("Map", playerTypes);
		
		//Clear the towers list
		towers.clear();
				
		//Retrieve the bases positions
		Point[] basesPositions = mapManager.getPlayerBasePosition();
		//Clear the bases list
		bases.clear();
		for(int i=0; i<enemiesType.size();i++){
			bases.add(new Base(basesPositions[i+1],enemiesType.get(i),false,mapManager.getPlayerProximityMap(i+1)));
		}
		//human player base
		bases.add(new Base(basesPositions[0],humanType,false,mapManager.getPlayerProximityMap(0)));
		
		//TODO add neutral bases ! cd MapManager neutralBasePosition !
		ArrayList<Point> neutralBasePosition = mapManager.getNeutralBasePosition();
		for(int i=0; i<neutralBasePosition.size();i++){
			bases.add(new Base(neutralBasePosition.get(i),PlayerType.NEUTRAL,true,mapManager.getNeutralProximityMap(i)));
		}	
				
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
					
					//Remove the tower from the engine list
					Iterator<Tower> it = towers.iterator();
					while (it.hasNext()) {
						Tower element = it.next();
						if(element.getPosition().equals(((TowerOrder) order).getPosition())){
							it.remove();
							//Tell the dispatcher that the tower need to be remove from the view
							dispatcher.addOrderToView(new SuppressTowerOrder(order.getPlayerType(), ((TowerOrder) order).getPosition()));
						}
					}
				}
				
				//If the order is a AddTowerOrder one
				if(order instanceof AddTowerOrder) {
					
					//TODO : Check if the tower can be add here !
					
					//TODO : Add the good type of tower !
					//Add the tower to the list
					towers.add(new MedicalTower(((TowerOrder) order).getPosition(),order.getPlayerType(), 90));
					
					//Tell the dispatcher that the tower can to be add on the view
					dispatcher.addOrderToView(new AddTowerOrder(order.getPlayerType(), ((TowerOrder) order).getPosition(), 2));
				}
				
				
				//If the order is an AttackBaseOrder one
				if(order instanceof AttackBaseOrder) {
					
					//PlayerType srcPlayerType, Point srcPosition, Point dstPosition, int amount
					//Retrieve the source base from the engine list
					Iterator<Base> it = bases.iterator();
					while (it.hasNext()) {
						Base element = it.next();
						if(element.getPosition().equals(((AttackBaseOrder) order).getPosition())){
							//Calculate the amount of unit that will be send according to the percent chose by the player
							int baseAmount = element.getAmount();
							int attackPercent = ((AttackBaseOrder) order).getAmount();
							
							int attackAmount = (attackPercent * baseAmount)/100;
							
							//TODO send amount of unit !
							System.out.println("Engine - TODO : base : "+((BaseOrder) order).getPosition()+" want to send "+attackAmount+" Units to "+((AttackBaseOrder) order).getDstPosition());
							
							//Set the new amount
							element.setAmount(baseAmount - attackAmount);
							
							//Tell the dispatcher that the sourceBase amount need to be decreased in the view
							dispatcher.addOrderToView(new AttackBaseOrder(order.getPlayerType(), ((BaseOrder) order).getPosition(), ((AttackBaseOrder) order).getDstPosition(), baseAmount - attackAmount));
						}
					
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