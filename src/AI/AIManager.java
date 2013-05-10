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
import Dispatcher.AmountBaseOrder;
import Dispatcher.DispatcherManager;
import Dispatcher.Order;
import GameEngine.AttackTower;
import GameEngine.Base;
import GameEngine.Player.PlayerType;
import GameEngine.SupportTower;
import GameEngine.Tower;
import GameEngine.TowerManager.TowerTypes;


public class AIManager implements Runnable {

	private DispatcherManager dispatcher;
	
	private ArrayList<Base> bases;
	private ArrayList<Base> enemyBases;
	private ArrayList<Tower> towers;
	
	private LinkedList<Order> orderQueue;
	
	private PlayerType aiType;
	private int timeToSleep;
	private int money;
	private boolean running;

	public AIManager(DispatcherManager dispatcher) {
		this.dispatcher = dispatcher;
		this.timeToSleep = 2000;
		
		bases = new ArrayList<Base>();
		enemyBases = new ArrayList<Base>();
		towers = new ArrayList<Tower>();
		
		orderQueue = new LinkedList<Order>();
	}
	
	/**
	 * Set the type of the AI
	 * @param aiType - AI's type
	 */
	public void setType(PlayerType aiType){
		this.aiType = aiType;
	}
	
	/**
	 * Get the initial bases on screen
	 * @param bases
	 */
	public void initiateGameView(ArrayList<Base> bases){
		for (Base b:bases){
			if (b.getPlayerType().equals(aiType)){
				this.bases.add(b);
			}
			else{
				enemyBases.add(b);
			}
		}
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
			behavior();
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
							towers.add(new AttackTower(((AddTowerOrder) o).getId(), new Point(0,0), aiType));
						}
						
						else if (((AddTowerOrder) o).getTowerType()==TowerTypes.SUPPORTTOWER){
							towers.add(new SupportTower(((AddTowerOrder) o).getId(), new Point(0,0), aiType));
						}
					}
				}
				
				if (o instanceof AmountBaseOrder){
					//TODO Réactualiser les infos sur les bases
					System.out.println("AI : Je vois que qu'il y a des changements dans les bases");
				}
				
			}
		}
	}
	
	/**
	 * Print the infos of the GameEngine the AI can access
	 */
	private void printInfo(){
		if (!bases.isEmpty() || !enemyBases.isEmpty() || !towers.isEmpty()){
			System.out.println("-----------------AI "+aiType+"--------------------");
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
					System.out.println("AI : Tower ID="+t.getId());
				}
			}
			System.out.println("---------------------------------------------");
		}
	}
	
	/**
	 * Principal behavior of the AI 
	 */
	private void behavior(){
		
	}
	
	/**
	 * Stop the loop in the Thread
	 * @see #run()
	 */
	public void stop(){
		bases.clear();
		towers.clear();
		enemyBases.clear();
		this.running = false;
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
		//TODO Changer constructeur AddUnitOrder
		//dispatcher.addOrderToEngine(new AddUnitOrder(idBaseSrc, idBaseDst, amount));	
	}
	
	/**
	 * Create an AI Tower at a specific position
	 * @param position - Tower's position
	 * @param type - Tower's type
	 */
	private void placeTower(Point position, TowerTypes type){
		dispatcher.addOrderToEngine(new AddTowerOrder(-1, aiType, position, type));
	}
	
	/**
	 * Upgrate an AI Tower 
	 * @param idTower - ID's tower
	 */
	private void upgradeTower(int idTower){
		//dispatcher.addOrderToEngine(new ......)
	}
}
