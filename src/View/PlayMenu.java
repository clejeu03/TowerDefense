package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Project - TowerDefense</br>
 * <b>Class - PlayMenu</b></br>
 * <p>The PlayMenu is a step menu into the process for creating a new game. It is called after the 
 * homeMenu and inherit from the MainMenusView.</br>
 * Allow the player to choose the number of players and to choose a map.
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see MainViews
 * @see ViewManager
 * @see HomeMenu
 * 
 */
@SuppressWarnings("serial")
public class PlayMenu extends MainViews{
   
	private JLabel jStarter;
    private ArrayList<Sprite> starters;
    private int starterId;

	private JButton jButtonStart;
    private JButton jButtonHome;
    
    private Image background;
	
	/**
	 * Constructor of the PlayMenu class
	 * @param view
     * @param position
     * @param width
     * @param height
	 */
	public PlayMenu(ViewManager view, Point position, int width, int height){
		super(view, position, width,height);
		
		starterId = 0;
		starters = new ArrayList<Sprite>();
		
		//Creating the components
		jStarter = new javax.swing.JLabel("Choose your starter wisely : ");
		
		starters.add(new StarterSprite(this, new Point(350,60), true, 0, 50,50));
		starters.add(new StarterSprite(this, new Point(400,60), true, 1, 50,50));
		starters.add(new StarterSprite(this, new Point(450,60), true, 2, 50,50));
		starters.add(new StarterSprite(this, new Point(500,60), true, 3, 50,50));

		jButtonStart = new javax.swing.JButton();
		jButtonHome = new javax.swing.JButton();

		//Setting the components parameters and theirs listeners            
		jButtonStart.setText("Start");
		jButtonHome.setText("Main Menu");		
		
		jButtonStart.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonStartPerformed(evt);
		    }
		});
		jButtonHome.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonHomePerformed(evt);
		    }
		});
		
		//Loading the home background
		try {
		      background = ImageIO.read(new File("img/parameters.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.gray); 
		
		Iterator<Sprite> it = starters.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
			add(element);
		}
		
		jStarter.setBounds(100, 50, 250,25);
		jButtonStart.setBounds(340, 150, 120,25);
		jButtonHome.setBounds(340, 200, 120,25);
		
		add(jStarter);
		add(jButtonStart);
		add(jButtonHome);		
	}
	/**
	 * To call after all the choices are made - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonStartPerformed(ActionEvent evt) {
    	view.play(starterId);
    }
    
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonHomePerformed(ActionEvent evt) {
    	view.homeMenu();
    }
    
	/**
	 * Reset to false the chosen attribute of each Starter
	 * @see ViewManager#createGame()
	 */
	public void initiate(){
		//Reset the boolean chosen of the other starters sprites
		Iterator<Sprite> it = starters.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			((StarterSprite) element).setChosen(false);
		}
		
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
    
    /**
	 * Set StarterId
	 * @param starterId
	 * @see BaseSprite#myMousePressed(MouseEvent)
	 */
	public void starterClicked(int starterId){
		this.starterId = starterId;
		
		//Reset the boolean chosen of the other starters sprites
		Iterator<Sprite> it = starters.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			if(element.getPlayerId()!= starterId){
				((StarterSprite) element).setChosen(false);
			}
		}
		
	}

    public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }     
}