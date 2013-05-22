/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 10 mai 2013
 */
package AI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;

import Dispatcher.AddTowerOrder;
import Dispatcher.AddTowerOrder.ErrorType;
import Dispatcher.AddUnitOrder;
import Dispatcher.ChangeOwnerOrder;
import Dispatcher.DispatcherManager;
import Dispatcher.EvolveTowerOrder;
import Dispatcher.MoneyOrder;
import Dispatcher.Order;
import GameEngine.AttackTower;
import GameEngine.Base;
import GameEngine.BombTower;
import GameEngine.FrostTower;
import GameEngine.GunTower;
import GameEngine.LazerTower;
import GameEngine.MedicalTower;
import GameEngine.ShieldTower;
import GameEngine.TerritoryMap;
import GameEngine.Player.PlayerType;
import GameEngine.SupportTower;
import GameEngine.Tower;
import GameEngine.TowerManager;
import GameEngine.TowerManager.TowerTypes;


public class AIManager implements Runnable {

	private DispatcherManager dispatcher;
	
	private ArrayList<Base> bases;
	private ArrayList<Base> enemyBases;
	private ArrayList<Tower> towers;
	private ArrayList<Tower> enemyTowers;
	
	private LinkedList<Order> orderQueue;
	
	private PlayerType aiType;
	private int timeToSleep;
	private int money;
	private boolean running;
	private int mapHeight;
	private int mapWidth;

	public AIManager(DispatcherManager dispatcher, PlayerType aiType) {
		this.dispatcher = dispatcher;
		this.timeToSleep = 2000;
		
		bases = new ArrayList<Base>();
		enemyBases = new ArrayList<Base>();
		towers = new ArrayList<Tower>();
		enemyTowers = new ArrayList<Tower>();
		orderQueue = new LinkedList<Order>();
		this.aiType = aiType;
	}
	
	/**
	 * Get the initial bases on screen
	 * @param bases
	 */
	public void initiateGameView(ArrayList<Base> bases, int money){
		for (Base b:bases){
			if (b.getPlayerType().equals(aiType)){
				this.bases.add(b);
			}
			else{
				enemyBases.add(b);
			}
		}
		
		if (!bases.isEmpty()){
			mapHeight = bases.get(0).getTerritoryMap().getHeight();
			mapWidth = bases.get(0).getTerritoryMap().getWidth();
		}
		
		this.money=money;
	}

	/**
	 * Main loop of the thread
	 */
	@Override
	public void run() {
		running = true;
		while(running){
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				running = false;
			}
			refreshInfo();
			//printInfo();
			attackBehavior();
			towerBehavior();
			evolveBehavior();
		}
	}

	/**
	 * Refresh the new infos emited by the GameManager
	 */
	private void refreshInfo(){
		//Retrieve the current size of the queue
		int size = orderQueue.size();
		
		//Execute and remove the "size" first orders of the queue
		if(size>0){
			for(int i = 0;i<size; i++){
				//Retrieve and remove the head of the queue
				Order o = orderQueue.poll();
				
				if(o instanceof AddTowerOrder){
					//If a ai tower is correctly added in the GameEngine
					if (((AddTowerOrder) o).getPlayerType()==aiType){
						//We add it in the ai list
						if(((AddTowerOrder) o).getTowerType()==TowerTypes.ATTACKTOWER){
							towers.add(new AttackTower(((AddTowerOrder) o).getId(), TowerManager.TowerTypes.ATTACKTOWER.cost(), ((AddTowerOrder) o).getPosition(), aiType));
						}
						
						else if (((AddTowerOrder) o).getTowerType()==TowerTypes.SUPPORTTOWER){
							towers.add(new SupportTower(((AddTowerOrder) o).getId(), TowerManager.TowerTypes.SUPPORTTOWER.cost(), ((AddTowerOrder) o).getPosition(), aiType));
						}
					}
					//If a tower is created by a player
					else
					{
						if(((AddTowerOrder) o).getTowerType()==TowerTypes.ATTACKTOWER){
							enemyTowers.add(new AttackTower(((AddTowerOrder) o).getId(), TowerManager.TowerTypes.ATTACKTOWER.cost(), ((AddTowerOrder) o).getPosition(), aiType));
						}
						
						else if (((AddTowerOrder) o).getTowerType()==TowerTypes.SUPPORTTOWER){
							enemyTowers.add(new SupportTower(((AddTowerOrder) o).getId(), TowerManager.TowerTypes.SUPPORTTOWER.cost(), ((AddTowerOrder) o).getPosition(), aiType));
						}
					}
				}
				
				if (o instanceof ChangeOwnerOrder){
					//if the new owner is the AI
					if (((ChangeOwnerOrder)o).getNewPlayerType()==aiType){
						//if the object is a base
						int index=0;
						boolean notABase = true;
						for (Base b:enemyBases){
							if (b.getId()==o.getId()){
								bases.add(enemyBases.remove(index));
								notABase = false;
								break;
							}
							index++;
						}
						//if the object is a tower
						if (notABase){
							index = 0;
							for (Tower t:enemyTowers){
								if (t.getId()==t.getId()){
									towers.add(enemyTowers.remove(index));
									break;
								}
								index++;
							}
						}
					}
					//if the new owner is not the ai
					else{
						//if the object is a base
						int index=0;
						boolean notABase = true;
						for (Base b:bases){
							if (b.getId()==o.getId()){
								enemyBases.add(bases.remove(index));
								notABase = false;
								break;
							}
							index++;
						}
						index=0;
						//if the object is a tower
						if(notABase){
							for (Tower t:towers){
								if (t.getId()==o.getId()){
									enemyTowers.add(towers.remove(index));
									notABase = false;
									break;
								}
								index++;
							}
						}
						//if the Ai doesn't have bases anymore
						if (bases.isEmpty()) {
							stop();
							interrupt();
						}
					}
				}
				
				if (o instanceof EvolveTowerOrder){
					Tower oldTower = null;
					boolean notAAiTower = true;
					
					//Check if the tower is owned by the AI and remove the old one if so
					int index = 0;
					for(Tower t:towers){
						if (((EvolveTowerOrder)o).getId()==t.getId()){
							notAAiTower = false;
							oldTower = towers.remove(index);
							break;
						}
						index++;
					}
					
					//Check if the tower is owned by a player and remove the old one if so
					if (notAAiTower){
						index=0;
						for(Tower t:enemyTowers){
							if (((EvolveTowerOrder)o).getId()==t.getId()){
								oldTower = enemyTowers.remove(index);
								break;
							}
							index++;
						}
					}
					
					//Check into what the tower is evolving
					Tower newTower = null;
					try{
						switch(((EvolveTowerOrder)o).getType()){
						case BOMBTOWER:
							newTower = new BombTower(oldTower.getId(), TowerTypes.BOMBTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						case FROSTTOWER:
							newTower = new FrostTower(oldTower.getId(), TowerTypes.FROSTTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						case GUNTOWER:
							newTower = new GunTower(oldTower.getId(), TowerTypes.GUNTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						case LAZERTOWER:
							newTower = new LazerTower(oldTower.getId(), TowerTypes.LAZERTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						case MEDICALTOWER:
							newTower = new MedicalTower(oldTower.getId(), TowerTypes.MEDICALTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						case SHIELDTOWER:
							newTower = new ShieldTower(oldTower.getId(), TowerTypes.SHIELDTOWER.cost(), oldTower.getPosition(),oldTower.getPlayerType());
							break;
						default:
							System.out.println("AI - Unknown type of evolving tower");
						}
					}
					catch(NullPointerException e){
						System.out.println("Program interrupted");
					}
					
				
					//Create a new tower and add it in the right container
					if(notAAiTower) enemyTowers.add(newTower);
					else towers.add(newTower);					
				
				}
				
				if (o instanceof MoneyOrder){
					if(((MoneyOrder)o).getPlayerType()==aiType) money = ((MoneyOrder)o).getAmount();
				}
			}
		}
	}
	
	/**
	 * Print the infos of the GameEngine the AI can access
	 */
	private void printInfo(){
		if (!bases.isEmpty() || !enemyBases.isEmpty() || !towers.isEmpty()){
			System.out.println("-----------------AI "+aiType+"---MONEY +"+money+"-----------------");
			if(!bases.isEmpty()){
				for(Base b:bases){
					System.out.println("AI : AIBase ID="+b.getId()+" Amount="+b.getAmount());
				}
			}
			
			if(!enemyBases.isEmpty()){
				for(Base b:enemyBases){
					System.out.println("AI : EnemyBase ID="+b.getId()+" Type="+b.getPlayerType()+" Amount="+b.getAmount());
				}
			}
			
			if(!towers.isEmpty()){
				for(Tower t:towers){
					TowerTypes type = null;
					if (t instanceof AttackTower)
						type = TowerTypes.ATTACKTOWER;
					else if (t instanceof SupportTower)
						type = TowerTypes.SUPPORTTOWER;
					else if (t instanceof GunTower)
						type = TowerTypes.GUNTOWER;
					else if (t instanceof FrostTower)
						type = TowerTypes.FROSTTOWER;
					else if (t instanceof LazerTower)
						type = TowerTypes.LAZERTOWER;
					else if (t instanceof BombTower)
						type = TowerTypes.BOMBTOWER;
					else if (t instanceof MedicalTower)
						type = TowerTypes.MEDICALTOWER;
					else if (t instanceof ShieldTower)
						type = TowerTypes.SHIELDTOWER;
					System.out.println("AI : Tower ID="+t.getId()+" Position="+t.getPosition() + " Type="+type);
				}
			}
			if(!enemyTowers.isEmpty()){
				for(Tower t:enemyTowers){
					TowerTypes type = null;
					if (t instanceof AttackTower)
						type = TowerTypes.ATTACKTOWER;
					else if (t instanceof SupportTower)
						type = TowerTypes.SUPPORTTOWER;
					else if (t instanceof GunTower)
						type = TowerTypes.GUNTOWER;
					else if (t instanceof FrostTower)
						type = TowerTypes.FROSTTOWER;
					else if (t instanceof LazerTower)
						type = TowerTypes.LAZERTOWER;
					else if (t instanceof BombTower)
						type = TowerTypes.BOMBTOWER;
					else if (t instanceof MedicalTower)
						type = TowerTypes.MEDICALTOWER;
					else if (t instanceof ShieldTower)
						type = TowerTypes.SHIELDTOWER;
					System.out.println("AI : EnemyTower ID="+t.getId()+" Position="+t.getPosition() + " Type="+type);
				}
			}
			System.out.println("---------------------------------------------");
		}
	}
	
	/**
	 * How the AI choose who to attack 
	 */
	private void attackBehavior(){
		//Search which ai base have the most amount of units
		if (!bases.isEmpty() && !enemyBases.isEmpty()){
			Base baseAttacking = null;
			int amountOfAiUnit=0;
			for (Base b:bases){
				if (b.getAmount()>amountOfAiUnit){
					baseAttacking = b;
					amountOfAiUnit = b.getAmount();
				}
			}
			
			//Search which enemy base is the closest to his
			Base baseAttacked = null;
			double distance=Double.MAX_VALUE;
			for (Base b:enemyBases){
				if (distanceBetween(baseAttacking,b)<distance){
					baseAttacked = b;
					distance = distanceBetween(baseAttacking,b);
				}
			}
			
			//Test if there are enough units to get it
			if ((baseAttacking.getAmount()*0.7)>baseAttacked.getAmount()){
				sendUnit(baseAttacking.getId(),baseAttacked.getId(),80);
			}
		}
	}
	
	/**
	 * Returns the distance between two bases
	 * @param b1 - First base
	 * @param b2 - Second base
	 * @return distance between them in int
	 */
	private int distanceBetween(Base b1, Base b2){
		return b1.getProximityMap().getPixel(b2.getPosition().x, b2.getPosition().y);
	}
	
	/**
	 * Stop the loop in the Thread
	 * @see #run()
	 */
	public void stop(){
		System.out.println("AI "+aiType+" IS NOW DEAD");
		bases.clear();
		towers.clear();
		enemyBases.clear();
		this.running = false;
	}
	
	private void interrupt(){
		dispatcher.interruptAiThread(aiType);
	}
	
	/**
	 * Add a new order in the LinkedList of the AIManager
	 * @param order
	 * @see Dispatcher#DispatcherManager
	 */
	public void addOrder(Order order){
		orderQueue.add(order);
	}
	
	/**
	 * Send unit toward an enemy base
	 * @param idBaseSrc - unit's starting point
	 * @param idBaseDst - unit's destination
	 * @param amount - troops sent
	 */
	private void sendUnit(int idBaseSrc, int idBaseDst, int amount){
		dispatcher.addOrderToEngine(new AddUnitOrder(-1, idBaseSrc, idBaseDst, amount));	
		System.out.println("Order send : attack from="+idBaseSrc+" to="+idBaseDst+" with "+amount+"% of his power");
	}
	
	/**
	 * How the AI choose to create towers
	 */
	private void towerBehavior(){
		
		if(money>100&&towers.size()<=2){	
			LinkedList<Point> availablePositions = new LinkedList<Point>();
			
			//Get all the available positions for placing tower
			for (int y = 32; y < mapHeight-32;y++){ 
				for (int x = 32; x < mapWidth-32;x++){
					Point p = new Point (x,y);
					if (canPlaceTowerAt(p)) availablePositions.add(p);
				}
			}
			
			if (!availablePositions.isEmpty()){
				
				//Place the tower the nearest possible position to his main base
				Point nearestPosition = null;
				double prevDistance = Double.MAX_VALUE;
				for (Point p:availablePositions){
					 double distance = Math.sqrt(Math.pow((bases.get(0).getPosition().x - p.x), 2)+Math.pow((bases.get(0).getPosition().y - p.y), 2));
					 if (prevDistance>distance){
						 nearestPosition = p;
						 prevDistance = distance;
					 }
				}
				if (nearestPosition != null)
				{	
					if (Math.random()<0.5) placeTower(nearestPosition, TowerTypes.ATTACKTOWER);
					else placeTower(nearestPosition, TowerTypes.SUPPORTTOWER);
				}
				else{
					System.out.println("AI TOWER BEHAVIOR ERROR : NEAREST POSITION = NULL");
				}
			}
		}
	}
	
	/**
	 * Check if the AI can place a tower at a given position
	 * @param position - position checked
	 * @return true if it can, false otherwise
	 */
	private boolean canPlaceTowerAt(Point position){
		
		//Let half of the sprite height's and half of the sprite's width exceed the limits of territory
		int spriteQuart = 15; //Size of sprite : 64x64
		
		int zoneId = getTerritoryMapValue(position.x,position.y);
		
		int supRightZoneId = getTerritoryMapValue(position.x+spriteQuart,position.y-spriteQuart);
		int supLeftZoneId = getTerritoryMapValue(position.x-spriteQuart, position.y-spriteQuart);
		int infRightZoneId = getTerritoryMapValue(position.x+spriteQuart, position.y+spriteQuart);
		int infLeftZoneId = getTerritoryMapValue(position.x-spriteQuart, position.y+spriteQuart);
		
		if(supRightZoneId == zoneId && supLeftZoneId == zoneId && 
			infLeftZoneId == zoneId && infRightZoneId == zoneId){
			
			//Compare the zone with the order of types in the playerTypes array 
			//-1 : null, 0: plain, 1 to 5 : territories
			if(zoneId >0 && zoneId<6){
				if (towers.isEmpty())
					return true;
				else{
					//Check if there is already a tower at this position
					for (Tower t:towers){
						if (towersCollide(position,t.getPosition())){
							return false;
						}
					}
					return true;
				}
			}
			else return false;
		}
		else return false;
	}

	/**
	 * Get the pixel value of the AI territory maps at a given position
	 * @param x - position x of the pixel
	 * @param y - position y of the pixel
	 * @return value of the pixel at position (x,y)
	 */
	private int getTerritoryMapValue(int x, int y){
		for (Base b:bases){
			if (b.getTerritoryMap().getPixel(x,y)!=-1){
				return b.getTerritoryMap().getPixel(x,y);
			}	
		}
		return -1;
	}
	
	/**
	 * Check if two towers t1 and t2 are colliding
	 * @param t1 - position of tower t1
	 * @param t2 - position of tower t2
	 * @return true if they collide, false otherwise
	 */
	private boolean towersCollide(Point t1, Point t2){
		int spriteHalf = 32;
		
		int maxgauche = Math.max(t1.x-spriteHalf, t2.x-spriteHalf);
		int mindroit = Math.min(t1.x+spriteHalf,t2.x+spriteHalf);
		int maxbas = Math.max(t1.y-spriteHalf, t2.y-spriteHalf);
		int minhaut = Math.min(t1.y+spriteHalf, t2.y+spriteHalf);
		
		if (maxgauche<mindroit && maxbas<minhaut) return true;
		else return false;
	}
	
	/**
	 * Create an AI Tower at a specific position
	 * @param position - Tower's position
	 * @param type - Tower's type
	 */
	private void placeTower(Point position, TowerTypes type){
		dispatcher.addOrderToEngine(new AddTowerOrder(-1, aiType, position, type, -1,ErrorType.NONE));
	}
	
	/**
	 * How the AI chooses to evolve its towers
	 */
	private void evolveBehavior(){
		if (!towers.isEmpty()){
			for(Tower t:towers){
				double random = Math.random();
				int evolution;
				if (t.getFirstEvolution()!=TowerTypes.NOTOWER && t.getSecondEvolution()!=TowerTypes.NOTOWER){
					if (random<0.5) evolution = 0;
					else evolution = 1;
					
					if(t.getEvolutions().get(evolution).cost()<=money){
						evolveTower(t.getId(),t.getEvolutions().get(evolution));
					}
				}
				else if (t.getFirstEvolution()!=TowerTypes.NOTOWER && t.getSecondEvolution()==TowerTypes.NOTOWER){
					evolution=0;
					
					if(t.getEvolutions().get(evolution).cost()<=money){
						evolveTower(t.getId(),t.getEvolutions().get(evolution));
					}
				}
				else if (t.getFirstEvolution()==TowerTypes.NOTOWER && t.getSecondEvolution()!=TowerTypes.NOTOWER){
					evolution=1;
					
					if(t.getEvolutions().get(evolution).cost()<=money){
						evolveTower(t.getId(),t.getEvolutions().get(evolution));
					}
				}
			}
		}
	}
	
	/**
	 * Evolve an AI Tower 
	 * @param idTower - ID's tower
	 */
	private void evolveTower(int idTower, TowerTypes newType){
		System.out.println("AI - ID "+idTower+" newType "+newType);
		dispatcher.addOrderToEngine(new EvolveTowerOrder(idTower, newType, -1));
	}
	
	/**
	 * Check if the Ai is still active
	 * @return boolean
	 */
	public boolean isRunning(){
		return running;
	}
	
	/**
	 * Getter - Return the PlayerType of the AI
	 * @return
	 */
	public PlayerType getPlayerType(){
		return aiType;
	}
}
