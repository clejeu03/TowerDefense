package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * Project - TowerDefense</br>
 * <b>Class - HomeMenu</b></br>
 * <p>The HomeMenu is the that lead the player through the different steps of the creation 
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
    
    private Image background;
    
    /**
     * Constructor of the HomeMenu class
     * @param view
     * @param position
     * @param width
     * @param height
     */
	public HomeMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
		
		//Creating the components
		jButtonPlay = new javax.swing.JButton();
		jButtonQuit = new javax.swing.JButton();

		//Setting the components parameters and theirs listeners            
		jButtonPlay.setText("New game");
		jButtonQuit.setText("Quit");	
		
		//Loading the home background
		try {
		      background = ImageIO.read(new File("img/home.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
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
		jButtonPlay.setBounds(340, 100, 120,25);
		jButtonQuit.setBounds(340, 150, 120,25);
		add(jButtonPlay);
		add(jButtonQuit);
	}
	
	/**
	 * jButtonPlay Event handler - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonPlayPerformed(ActionEvent evt) {
    	view.createGame();
    }
    
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonQuitPerformed(ActionEvent evt) {
    	System.exit(0);
    }
    
    public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }              
}