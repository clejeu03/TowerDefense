package View;

import GameEngine.*;
import Dispatcher.*; 

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
 * The ViewManager communicates with the GameEngine trought the Dispatcher.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see GameMenuBar
 * @see SceneView
 * @see GameManager
 */

public class ViewManager extends JFrame implements Runnable{
	/*Thread managers*/
	private boolean running;
	private DispatcherManager d;
	private ConcurrentLinkedQueue<Order> q;
	
	/*Paramètes de la fenêtre*/
    public static final int WIDTH = 800 ;
    public static final int HEIGHT = 600 ;
    private Image icon;
    
    /*Panneaux*/	
    private HomeMenu jPanelMainMenu;
    private SceneView jPanelMap;
    private GameMenuBar jPanelTools;
	
    public ViewManager() {
		super("TowerDefense");	
		
		q = new ConcurrentLinkedQueue<Order>();
		running = false;
		
		jPanelMainMenu = new HomeMenu(this, new Point(0,0), WIDTH, HEIGHT);	
		
		jPanelMap = new SceneView(this,new Point(0,25), 800,400);
		jPanelTools = new GameMenuBar(this,new Point(0,0),800, 25);

		/*Chargement de l'icone du jeu*/
		try {
		      icon = ImageIO.read(new File("img/bear.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
        /*Création et disposition des composants sur la fenêtre*/
		initComponents();
		layComponents();

        /*Paramètres principaux de la  fenêtre*/
		centralization();
		setIconImage(icon);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setSize(WIDTH,HEIGHT);
		setResizable(false);
        setVisible(true);
	}
    
    /**
     * Initialise les composants de la fenêtre
     * @see PlayerInterface.PlayerInterface() (appelant)
     */	
    public void  initComponents(){
		jPanelMainMenu.setPreferredSize(new Dimension(jPanelMainMenu.getWidth(), jPanelMainMenu.getHeight()));	
        jPanelMap.setPreferredSize(new Dimension(jPanelMap.getWidth(), jPanelMap.getHeight()));
        jPanelTools.setPreferredSize(new Dimension(jPanelTools.getWidth(), jPanelTools.getHeight()));
    }

    /**
     * Dispose les composants sur la fenêtre
     * @see PlayerInterface.PlayerInterface() (appelant)
     */	
    public void layComponents(){
    	/*Supprime le layout manager de la fenêtre nstauré par défaut
    	 * Cela nous permet ensuite de disposer les composants à la main (en coordonnées absolues)*/
    	setLayout(null);
    	
        /*Dispose et dimensionne les composants*/
		jPanelMainMenu.setBounds(jPanelMainMenu.getPosition().x, jPanelMainMenu.getPosition().y,jPanelMainMenu.getWidth(), jPanelMainMenu.getHeight());	   	
    	jPanelMap.setBounds(jPanelMap.getPosition().x, jPanelMap.getPosition().y,jPanelMap.getWidth(), jPanelMap.getHeight());	
        jPanelTools.setBounds(jPanelTools.getPosition().x, jPanelTools.getPosition().y,jPanelTools.getWidth(), jPanelTools.getHeight());	
        
        /*ajoute sur la fenêtre le Main Menu Panel*/
        add(jPanelMainMenu);
    }
    
	/**
	 * Dispose la fenêtre au centre de l'écran
	 * @see PlayerInterface.PlayerInterface() (appelant)
	 */
	public void centralization(){
		/*Récupère la taille de l'écran*/
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
	    /*Dispose la fenêtre au centre de l'écran*/
		setLocation((screenSize.width-WIDTH)/2,(screenSize.height-HEIGHT)/2);	
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
	 * Initialise les paramètres de l'interface au lancement du jeu
	 * @param p: coordonnées initiales du Sprite test 
	 * @see MyDispatcher.initiateGameInterface(Point p) (appelant)
	 */	
    public void initiateGameView(ArrayList<Tower> t){
		System.out.println("Engine say : Initating the game. interface..");
		//jPanelMap.moveSprite(p);
		Iterator<Tower> it = t.iterator();
		while (it.hasNext()) {
			//Récupération de la tour
			Tower element = it.next();
			//Ajout dans la liste de la Map
			jPanelMap.addSprite(element);
		}	
		/*Les initialisation de l'interface par le moteur sont finies. Le jeu peut commencer !*/
		d.start();	
    }
	
	/**
	 * Lance une partie depuis le Main menu
	 * @see HomeMenu.jButtonPlayPerformed(ActionEvent evt) (appelant)
	 */	
    public void play(){
    	/*Demande au moteur via le dispatcher d'initaliser le jeu*/
    	d.initiateGame();
    	
    	/*Supprime le Main Menu Panel de la fenêtre*/
    	remove(jPanelMainMenu);
    	
    	/*Ajoute les game Panels sur la fenêtre*/
    	add(jPanelMap);
        add(jPanelTools);
        
        /*Rafraichit la fenêtre*/
    	revalidate();
    	repaint();	  	
    }
  
	/**
	 * Retourne au Main menu une fois dans le jeu
	 * @see GameToolsInterface.jButtonBackPerformed(ActionEvent evt) (appelant)
	 */	
    public void homeMenu(){
    	/*Prévient le dispatcher qu'il faut stopper les threads de jeu*/
    	d.stop();
    	
    	/*Supprime les game Panels de la fenêtre*/
    	remove(jPanelMap);
    	remove(jPanelTools);
    	
    	/*Ajoute le Main Menu Panel sur la fenêtre*/
    	add(jPanelMainMenu);
        
        /*Rafraichit la fenêtre*/
    	revalidate();
    	repaint();	  	
    }
    
	/**
	 * Prévient le moteur via le Dispatcher que le joueur veut bouger le Sprite
	 * @see GameToolsInterface.jButtonMovePerformed(ActionEvent evt) (appelant)
	 */	
    /*public void moveSprite(){
    	d.moveSprite(new Point(10,10));
    }*/
    
    /*public void towerClicked(Point position, int idOwner){
		/*Affiche les infos de la Tour dans le panel GameToolsInterface*/
		/*jPanelTools.towerClicked(position,idOwner);
	}*/
    
   public void towerSuppressed(Point position, int idOwner){
	   d.addOrderToEngine(new SuppressTowerOrder(idOwner, position));
   }
    
	/**
	 * Rafraîchissement des paramètres des composants de l'interface du jeu
	 * @see PlayerInterface.run() (appelant)
	 */	
	public void refresh(){
		/*Récupère la taille actuelle de la queue q*/
		int nb = q.size();
		/*Effectue et supprime les nb premières tâches de la queue q*/
		if(nb>0){
			for(int i = 0;i<nb; i++){
				/*Récupère et supprime la tête de la queue le premier ordre*/
				Order o = q.poll();
				if(o instanceof SuppressTowerOrder) {
					System.out.println("Interface say : I have to suppress the tower : OwnerID "+o.getPlayerId()+" Position "+((TowerOrder) o).getPosition().x + " "+((TowerOrder) o).getPosition().y);
					jPanelMap.suppressTower(((TowerOrder) o).getPosition(), o.getPlayerId());
				}
			}
		}
	}
	
	/**
	 * Ajoute une tâche à la ConcurrentLinkedQueue q
	 * @see MyDispatcher.refresh(Point p) (appelant)
	 */	
	public void addOrder(Order o){
		/*Ajoute l'ordre o à la queue q*/
		q.add(o);
	}

	/**
	 * Méthode run() du thread de l'interface
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