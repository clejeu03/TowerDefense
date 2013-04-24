/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 24 avr. 2013
 */

import GameEngine.GameManager;
import View.ViewManager;
import Dispatcher.DispatcherManager;


/**
 * Project - TowerDefense</br>
 * <b>Class - TowerDefense</b></br>
 * <p>The TowerDefense class contains the main method</p>
 * <b>Creation :</b> 24/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
public class TowerDefense {

	/**
	 * Main method - Create the GameManager, ViewMananger and DispatcherManger objects.
	 * @param args - command arguments given to the program when it's executed
	 */
	public static void main(String[] args) {
		GameManager engine= new GameManager();
		ViewManager view = new ViewManager();
		
		DispatcherManager dispatcher = new DispatcherManager(engine,view); 
		view.setDispatcher(dispatcher);
		engine.setDispatcher(dispatcher);
	}

}
