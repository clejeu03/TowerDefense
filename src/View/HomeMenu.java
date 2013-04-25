package View;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Project - TowerDefense</br>
 * <b>Class - HomeMenu</b></br>
 * <p>The HomeMenu is the that lead the player trought the differents steps of the creation 
 * of a new game. The informations that came out after the player selection are directly sent
 * to the GameEngine for him to start the game.</br>
 * The HomeMenu inherit from the MainMenusView and is controlled by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Base
 * @see Unit
 * @see MainViews
 * @see ViewManager
 * 
 */
@SuppressWarnings("serial")
public class HomeMenu extends MainViews{
	
    private JButton jButtonPlay;
    private JButton jButtonQuit;
    
	public HomeMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
		
		//Creating the components
		jButtonPlay = new javax.swing.JButton();
		jButtonQuit = new javax.swing.JButton();

		//Setting the components parameters and theirs listeners            
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
		
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.gray); 
		jButtonPlay.setBounds(350, 100, 100,25);
		jButtonQuit.setBounds(350, 150, 100,25);
		add(jButtonPlay);
		add(jButtonQuit);
	}
	
	/**
	 * jButtonPlay Event handler - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonPlayPerformed(ActionEvent evt) {
    	view.play();
    }
    
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonQuitPerformed(ActionEvent evt) {
    	System.exit(0);
    }
}