package GameEngine;

import Dispatcher.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
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
 * 
 */

public class GameManager implements Runnable{
	/*Thread managers*/
	private boolean running;
	private DispatcherManager d;
	private ConcurrentLinkedQueue<Order> q;
	
	/*Position de l'objet (correspondant au Sprite dans l'interface)*/
    private ArrayList<Tower> towers;
	
	public GameManager() {
		super();
		running = false;
		q = new ConcurrentLinkedQueue<Order>();
		towers = new ArrayList<Tower>();
	}
	
	/**
	 * Initialise l'attribut d (myDispatcher)
	 * @see TowerDefense.main(String[] args) (appelant)
	 */
	public void setDispatcher(DispatcherManager d){
		this.d = d;
	}

	/**
	 * Initialise l'attribut running
	 * @param r boolean
	 * @see MyDispatcher.start()(appelant)
	 * @see MyDispatcher.stop()(appelant)
	 */
    public void setRunning(boolean r){
    	running = r;
    }
			
	/**
	 * Initialise le jeu (On aura besoin des paramètres choisi par l'utilisateur...)
	 * @see MyDispatcher.initiateGame() (appelant)
	 */
	public void initiateGame(){
		System.out.println("Engine say : Initating the game...");
		
		/*Ajout de tour*/
		towers.add(new MedicalTower(new Point(50,50), 1, 70));
		towers.add(new MedicalTower(new Point(125,50), 0, 70));
		
		/*Envois des informations à l'interface*/
		d.initiateGameInterface(towers);
	}
	
	/**
	 * Exécution des actions souhaitées par le joueur
	 * @seeE Engine.run() (appelant)
	 */	
	public void execute(){
		/*Récupère la taille actuelle de la queue q*/
		int nb = q.size();
		/*Effectue et supprime les nb premières tâches de la queue q*/
		if(nb>0){
			for(int i = 0;i<nb; i++){
				/*Récupère et supprime la tête de la queue le premier ordre*/
				Order o = q.poll();
				if(o instanceof SuppressTowerOrder) {
					System.out.println("Engine say : I have to suppress the tower : OwnerID "+o.getIdOwner()+" Position "+((TowerOrder) o).getPosition().x + " "+((TowerOrder) o).getPosition().y);
					/*Suppresion de la tour dans le model*/
					Iterator<Tower> it = towers.iterator();
					while (it.hasNext()) {
						Tower element = it.next();
						if(element.getPosition().equals(((TowerOrder) o).getPosition())){
							it.remove();
						}
					/*Suppression de la tour dans l'interface*/
					d.addOrderToView(new SuppressTowerOrder(o.getIdOwner(), ((TowerOrder) o).getPosition()));
				}
				
			}
		}
		}
	}
	
	/**
	 * Ajoute une tâche à la ConcurrentLinkedQueue q
	 * @see MyDispatcher.moveSprite(Point p) (appelant)
	 */	
	public void addOrder(Order o){
		/*Ajoute l'ordre o à la queue q*/
		q.add(o);
	}
	
	/**
	 * Méthode run() du thread du moteur
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