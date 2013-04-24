package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Project - TowerDefense</br>
 * <b>Class - AccueilMenu</b></br>
 * <p>The AccueilMenu is the that lead the player trought the differents steps of the creation 
 * of a new game. The informations that came out after the player selection are directly sent
 * to the GameEngine for him to start the game.</br>
 * The AccueilMenu inherit from the MainMenusView ans is controlled by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see MainMenusView
 * @see ViewManager
 * 
 */
public class HomeMenu extends MainMenusView{
    private ViewManager player;
    
    /*TO DO : Mettre des Sprites avec nos propres images à la place des bouttons*/
    private JButton jButtonPlay;
    private JButton jButtonQuit;
    
	public HomeMenu(ViewManager p) {
		super();
		player = p;
		
		/*Création des composants*/
		 jButtonPlay = new javax.swing.JButton();
		 jButtonQuit = new javax.swing.JButton();

		/*Paramètrage des composants et de leur listeners*/	        
		jButtonPlay.setText("Play");
		jButtonQuit.setText("Quit");		
		
		jButtonPlay.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonPlayPerformed(evt);
		    }
		});
		jButtonQuit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonQuitPerformed(evt);
		    }
		});
		
		/*Disposition des composants sur le panneau*/
		setLayout(null);
		setBackground(Color.gray); 
		jButtonPlay.setBounds(350, 100, 100,25);
		jButtonQuit.setBounds(350, 150, 100,25);
		add(jButtonPlay);
		add(jButtonQuit);
	}
	
	/**
	 * Gestionnaire des évènements concernant le bouton Play
	 * @param evt : ActionEvent
	 */
    private void jButtonPlayPerformed(ActionEvent evt) {
    	player.play();
    }
    
	/**
	 * Gestionnaire de l'évènement concernant le bouton Quit
	 * @param evt : ActionEvent
	 */
    private void jButtonQuitPerformed(ActionEvent evt) {
    	System.exit(0);
    }
}