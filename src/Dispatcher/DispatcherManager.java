/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */

package Dispatcher;

import java.util.ArrayList;

import GameEngine.*;
import View.*;

/**
 * Project - TowerDefense</br>
 * <b>Class - DispatcherManager</b></br>
 * <p>The DispatcherManager make the link beetween the ViewManager (interface) and the GameManager (Engine)</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see TowerDefense.TowerDefense#main(String[]) 
 */
public class DispatcherManager {

	private GameManager engine;
	private ViewManager view;
	private Thread threadEngine;
	private Thread threadView;
	
	/**
	 * Constructor of the DisptacherManger class
	 */
	public DispatcherManager(GameManager e,ViewManager v) {
		// TODO Auto-generated constructor stub
		super();
		engine =e;
		view = v;
	}
	
	/**
	 * Prévient le moteur qu'il faut initialiser le jeu (selon les paramètres choisi par le joueur)
	 * @see PlayerInterface.play()(appelant)
	 */	
	public void initiateGame(){
		engine.initiateGame();	
	}
	
	/**
	 * Prévient l'interface qu'il faut initialiser ses composants (selon les paramètres calculés par le moteur)
	 * @see Engine.initiateGame()(appelant)
	 */	
	public void initiateGameInterface(ArrayList<Tower> t){
		view.initiateGameInterface(t);	
	}

	/**
	 * Lance les threads du jeu
	 * @see PlayerInterface.initiateGameInterface(Point p)(appelant)
	 */	
	public void start(){
		view.setRunning(true);
		engine.setRunning(true);
		threadView = new Thread(this.view);
		threadEngine = new Thread(this.engine);
		threadView.start();
		threadEngine.start();
		System.out.println("Dispatcher say: " + Thread.activeCount());
	}
	
	/**
	 * Stop les threads du jeu
	 * @see PlayerInterface.mainMenu() (appelant)
	 */	
	public void stop(){
		view.setRunning(false);
		engine.setRunning(false);
		threadView.interrupt();
		threadEngine.interrupt();
		System.out.println("Dispatcher say: " + Thread.activeCount());
	}
	
	public void addOrderToEngine(Order o){
		engine.addOrder(o);
	}
	public void addOrderToInterface(Order o){
		view.addOrder(o);
	}
}
