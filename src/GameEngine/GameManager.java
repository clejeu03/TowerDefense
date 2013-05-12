package GameEngine;

import Dispatcher.*;
import GameEngine.Player.PlayerType;
import GameEngine.TowerManager.TowerTypes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	private int idCount;
	
	//Thread managers
	private boolean running;
	private DispatcherManager dispatcher;
	private ConcurrentLinkedQueue<Order> queue;
	
    private ArrayList<Player> players;
    private ArrayList<PlayerType> playerTypes;
	
	//Temporary !!
    private ArrayList<Tower> towers;
    private ArrayList<Base> bases;
    private ArrayList<Unit> units;
    
	private MapManager mapManager;
	private ArmyManager armyManager;
	private TowerManager towerManager;
	
	private Timer timer;
	private TimerTask timerTask;
	private TimerTask baseTimerTask;
    private long timeStart;
	
    /**
     * Constructor of the GameManager class
     */
	public GameManager() {
		super();
		running = false;
		queue = new ConcurrentLinkedQueue<Order>();
		players = new ArrayList<Player>();
		
		towers = new ArrayList<Tower>();
		bases = new ArrayList<Base>();
		units = new ArrayList<Unit>();
	
		idCount = 0;
		
		timeStart = 0;
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
		idCount = 0;
		
		//Creating the player (human and IA)
		//Clear the player list
		players.clear();
		
		//Adding the human player
		players.add(new Player(humanType));
		
		playerTypes = new ArrayList<PlayerType>();
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
		
		//Adding the Tower and Army managers
		armyManager = new ArmyManager();
		towerManager = new TowerManager();
		
		//Clear the towers list
		towers.clear();
		units.clear();
				
		//Retrieve the bases positions
		Point[] basesPositions = mapManager.getPlayerBasePosition();
		//Clear the bases list
		bases.clear();
		for(int i=0; i<enemiesType.size();i++){
			bases.add(armyManager.createBase(idCount, basesPositions[i+1],enemiesType.get(i),false,mapManager.getPlayerProximityMap(i+1)));
			++idCount;
		}
		//human player base
		bases.add(armyManager.createBase(idCount, basesPositions[0],humanType,false,mapManager.getPlayerProximityMap(0)));
		++idCount;
		
		//TODO add different sizes of neutral base
		ArrayList<Point> neutralBasePosition = mapManager.getNeutralBasePosition();
		for(int i=0; i<neutralBasePosition.size();i++){
			bases.add(armyManager.createBase(idCount, neutralBasePosition.get(i),PlayerType.NEUTRAL,true,mapManager.getNeutralProximityMap(i)));
			++idCount;
		}
		//Tells the dispatcher that the View need to be initialized
		dispatcher.initiateGameView(bases);
		
		//Start the timer
		timer = new Timer();
		timeStart = System.currentTimeMillis();
		timer();
		
	}
	
	public void timer(){
		
		//Actions each seconds
		timerTask=new TimerTask(){
            public void run(){
            	//get the current Date 
            	long playingTime = System.currentTimeMillis()-timeStart;
            	//System.out.println("Temps écoulé : " + playingTime);
            	
            	//TODO MoveUnit (following lines are temporary !!)
        		Iterator<Unit> iter = armyManager.getUnits().iterator();
        		while (iter.hasNext()) {
        			Unit unit = iter.next();
        			//int random = 2 + (int)(Math.random() * ((10 - 2) + 1));
        			Point newPosition = new Point(unit.getPosition().x +2, unit.getPosition().y+2);
        			
        			//Tell the dispatcher that the unit need to be move
					dispatcher.addOrderToView(new MoveUnitOrder(unit.getId(), newPosition));
        			unit.setPosition(newPosition);
        		}
        	          
            }
        };
		
		//Actions each 2 seconds
		baseTimerTask=new TimerTask(){
            public void run(){
            	
            	//Increasing the amount of each base
            	for(Base base:armyManager.getBases()){
            		if(base.getPlayerType()!=PlayerType.NEUTRAL){
            			//System.out.println(base.getNeutral());
            			base.setAmount(base.getAmount()+1);
            			dispatcher.addOrderToView(new AmountBaseOrder(base.getId(),base.getAmount()));
            		}
            	}
            }
        };
        
        timer.schedule(timerTask ,0, 100);
		timer.schedule(baseTimerTask ,0, 2000);
	}
	
	public void endGame(){
		//Stop the timer
		timer.cancel();
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
					System.out.println("Engine Order : "+ order.getId());
					//Remove the tower from the engine list
					towerManager.suppressTower(order.getId());
					//Tell the dispatcher that the tower need to be remove from the view
					dispatcher.addOrderToView(new SuppressTowerOrder(order.getId()));
					
				}
				
				//If the order is a AddTowerOrder one
				if(order instanceof AddTowerOrder) {

					//Get the owner of the selected pixel in the territoryMap
					int zoneId = mapManager.getTerritoryMapValue(((AddTowerOrder) order).getPosition().x, ((AddTowerOrder) order).getPosition().y);
					
					//Let half of the sprite height's and half of the sprite's width exceed the limits of territory
					int spriteQuart = 15; //Size of sprite : 64x64
					
					int supRightZoneId = mapManager.getTerritoryMapValue(((AddTowerOrder) order).getPosition().x+spriteQuart, ((AddTowerOrder) order).getPosition().y+spriteQuart);
					int supLeftZoneId = mapManager.getTerritoryMapValue(((AddTowerOrder) order).getPosition().x-spriteQuart, ((AddTowerOrder) order).getPosition().y+spriteQuart);
					int infRightZoneId = mapManager.getTerritoryMapValue(((AddTowerOrder) order).getPosition().x+spriteQuart, ((AddTowerOrder) order).getPosition().y-spriteQuart);
					int infLeftZoneId = mapManager.getTerritoryMapValue(((AddTowerOrder) order).getPosition().x-spriteQuart, ((AddTowerOrder) order).getPosition().y-spriteQuart);
					
					if(	supRightZoneId == zoneId && supLeftZoneId == zoneId &&
							infLeftZoneId == zoneId && infRightZoneId == zoneId){
						
						//Compare the zone with the order of types in the playerTypes array 
						
						//0 : plain
						if(zoneId !=0){
							
							//1 to 5 : territories
							if(zoneId <6 && ((AddTowerOrder) order).getPlayerType()== playerTypes.get(zoneId-1)){
								
								//Add the Tower and draw it
								towerManager.createTower(idCount, ((AddTowerOrder) order).getPlayerType(), ((AddTowerOrder) order).getTowerType(), ((AddTowerOrder) order).getPosition());
								dispatcher.addOrderToView(new AddTowerOrder(idCount, ((AddTowerOrder) order).getPlayerType(), ((AddTowerOrder) order).getPosition(), TowerTypes.SUPPORTTOWER));
								dispatcher.addOrderToAI(new AddTowerOrder(idCount, ((AddTowerOrder) order).getPlayerType(), ((AddTowerOrder) order).getPosition(), TowerTypes.SUPPORTTOWER));
								++idCount;
								
							}else{
								
								//Tell the dispatcher that the tower CAN'T be add on the view
								System.out.println("GameEngine says : You try to add a tower but this is not your territory");
								dispatcher.addOrderToView(new AddTowerOrder(-1,((AddTowerOrder) order).getPlayerType(), new Point(-1, -1), TowerTypes.SUPPORTTOWER));
							}
							
						}else{
							//Tell the dispatcher that the tower CAN'T be add on the view
							System.out.println("GameEngine says : maybe you should try on a hill...");
							dispatcher.addOrderToView(new AddTowerOrder(-1, ((AddTowerOrder) order).getPlayerType(), new Point(-1, -1), TowerTypes.SUPPORTTOWER));
						}
						
					//The required part of the sprite is not on the same territory	
					}else{
						//Tell the dispatcher that the tower CAN'T be add on the view
						dispatcher.addOrderToView(new AddTowerOrder(-1, ((AddTowerOrder) order).getPlayerType(), new Point(-1, -1), TowerTypes.SUPPORTTOWER));
					}
					
				}
				
				
				//If the order is an AddUnitOrder one
				if(order instanceof AddUnitOrder) {
	
					//Create the unit
					Unit unit = armyManager.launchUnit(idCount, ((AddUnitOrder) order).getSrcId(), ((AddUnitOrder) order).getDstId(), ((AddUnitOrder) order).getAmount());
					
					System.out.println("Engine - TODO : base : "+((AddUnitOrder) order).getSrcId()+" want to send "+unit.getAmount()+" Units to "+((AddUnitOrder) order).getDstId());
					dispatcher.addOrderToView(new AddUnitOrder(idCount, ((AddUnitOrder) order).getSrcId(), ((AddUnitOrder) order).getDstId(), unit.getAmount()));
					++idCount;
					
					//Retrieve the new source base amount
					for(Base base:armyManager.getBases()){
						if(base.getId() == order.getId()){
							dispatcher.addOrderToView(new AmountBaseOrder(order.getId(), base.getAmount()));
							break;
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