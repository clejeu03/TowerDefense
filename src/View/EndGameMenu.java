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
 * <b>Class - EndGameMenu</b></br>
 * <p>The EndGameMenu is the menu that resume the main informations and stats about the 
 * finished game</br>
 * The EndGameMenu just display a table of information and the two buttons replay and quit.</br>
 * The EndGameMenu inherits from the MainViews and is controlled by the ViewManager.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see ViewManager
 * 
 */
@SuppressWarnings("serial")
public class EndGameMenu extends MainViews{
	
    private Image background;
    private JButton jButtonHome;
    
	/**
	 * Constructor of the EndGameMenu class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public EndGameMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
		
		try {
		      background = ImageIO.read(new File("img/win.png"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		jButtonHome = new javax.swing.JButton("Main Menu");
		jButtonHome.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonHomePerformed(evt);
		    }
		});
		
		
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.gray); 
		jButtonHome.setBounds(340, 500, 120,25);
		add(jButtonHome);
	}
	
	public void setBackground(boolean win){
		String filename = "img/";
		if(win) filename += "win.png";
		else filename += "gameover.png";
		
		try {
		      background = ImageIO.read(new File(filename));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}	
        //Repaint the window
    	validate();
    	repaint();
	}
	
	/**
	 * jButtonQuit Event handler - Back to the main menu
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonHomePerformed(ActionEvent evt) {
    	view.homeMenu();
    }
    
    public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }  
}