/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 10 mai 2013
 */
package AI;

import java.util.ArrayList;
import java.util.LinkedList;

import Dispatcher.AddTowerOrder;
import Dispatcher.AddUnitOrder;
import Dispatcher.AmountBaseOrder;
import Dispatcher.DispatcherManager;
import Dispatcher.Order;
import Dispatcher.SuppressTowerOrder;


public class AIManager implements Runnable {

	private DispatcherManager dispatcher;
	private boolean running;
	private int timeToSleep;
	//private ArrayList<Integer> aiBasesID;
	//private ArrayList<Integer> enemyBasesID;
	private LinkedList<Order> orderQueue;

	public AIManager(DispatcherManager dispatcher) {
		this.dispatcher = dispatcher;
		timeToSleep = 2000;
		//aiBasesID = new ArrayList<Integer>();
		//enemyBasesID = new ArrayList<Integer>();
		orderQueue = new LinkedList<Order>();
	}

	
	@Override
	public void run() {
		running = true;
		while(running){
			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
				running = false;
			}
			refreshInfo();
			System.out.println("Je suis une IA et je t'emmerde");
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
				
				if(o instanceof SuppressTowerOrder){
					//TODO Retirer la tour dans la liste de l'AI
					System.out.println("AI: Je vois que tu as supprimé une tour");
				}
				
				if(o instanceof AddTowerOrder){
					//TODO Ajouter la tour dans la liste de l'AI
					System.out.println("AI : Je vois que tu ajoutes une tour");
				}
				
				if (o instanceof AddUnitOrder){
					//TODO Que faire ?
					System.out.println("AI : Je vois que tu envoies des unités");
				}
				
				if (o instanceof AmountBaseOrder){
					//TODO Réactualiser les infos sur les bases
					System.out.println("AI : Je vois que qu'il y a des changements dans les bases");
				}
				
			}
		}
	}
	
	/**
	 * Stop the loop in the Thread
	 * @see #run()
	 */
	public void stop(){
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
	
	private void sendUnit(int idBaseSrc, int idBaseDst, int amount){
		//TODO Changer constructeur AddUnitOrder ou créer nouvel order AddAIUnitOrder
		//dispatcher.addOrderToEngine(new AddUnitOrder(idBaseSrc, idBaseDst, amount));	
	}
}
