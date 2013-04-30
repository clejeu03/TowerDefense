package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

	private JLabel jEnemies;
	private JCheckBox jCheck1;
	private JCheckBox jCheck2;
	private JCheckBox jCheck3;
	private int nbEnemies;
	private int firstEnemyId;
	private int secondEnemyId;
	private int thirdEnemyId;
	private EnemySprite firstEnemySprite;
	private EnemySprite secondEnemySprite;
	private EnemySprite thirdEnemySprite;
	
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
		
		nbEnemies = 0;
		firstEnemyId = 1;
		secondEnemyId = 2;
		thirdEnemyId = 3;
		
		//Loading the home background
		try {
		      background = ImageIO.read(new File("img/parameters.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		//Creating the components
		jStarter = new javax.swing.JLabel("Choose your starter wisely : ");		
		starters.add(new StarterSprite(this, new Point(350,60), true, 0, 50,50));
		starters.add(new StarterSprite(this, new Point(400,60), true, 1, 50,50));
		starters.add(new StarterSprite(this, new Point(450,60), true, 2, 50,50));
		starters.add(new StarterSprite(this, new Point(500,60), true, 3, 50,50));
		
		jEnemies = new javax.swing.JLabel("Choose your enemies : ");
		jCheck1 = new JCheckBox();
		jCheck2 = new JCheckBox();
		jCheck3 = new JCheckBox();
		firstEnemySprite = new EnemySprite(this, new Point(375,110), false, firstEnemyId, 50,50);
		secondEnemySprite = new EnemySprite(this, new Point(425,110), false, secondEnemyId, 50,50);		
		thirdEnemySprite = new EnemySprite(this, new Point(475,110), false, thirdEnemyId, 50,50);
		

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
	
		
		jCheck1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jCheck1Performed(evt);
		    }
		});
		jCheck2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jCheck2Performed(evt);
		    }
		});
		jCheck3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jCheck3Performed(evt);
		    }
		});
		
		//Laying the components on the Panel
		setLayout(null);
		setBackground(Color.gray); 
		
		Iterator<Sprite> it = starters.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			element.setBounds(element.getPosition().x -(element.getWidth()/2), element.getPosition().y -(element.getHeight()/2), element.getWidth(),element.getHeight());
			add(element);
		}
		
		jStarter.setBounds(120, 60, 250,15);
		jEnemies.setBounds(180, 135, 250,15);
		
		jCheck1.setMargin(new Insets(0, 0, 0, 0));
		jCheck1.setBounds(365, 135, 15, 15);
		jCheck2.setMargin(new Insets(0, 0, 0, 0));
		jCheck2.setBounds(415, 135, 15, 15);
		jCheck3.setMargin(new Insets(0, 0, 0, 0));
		jCheck3.setBounds(465, 135, 15, 15);
		firstEnemySprite.setBounds(firstEnemySprite.getPosition().x -(firstEnemySprite.getWidth()/2), firstEnemySprite.getPosition().y -(firstEnemySprite.getHeight()/2),firstEnemySprite.getWidth(),firstEnemySprite.getHeight());
		secondEnemySprite.setBounds(secondEnemySprite.getPosition().x -(secondEnemySprite.getWidth()/2), secondEnemySprite.getPosition().y -(secondEnemySprite.getHeight()/2),secondEnemySprite.getWidth(),secondEnemySprite.getHeight());
		thirdEnemySprite.setBounds(thirdEnemySprite.getPosition().x -(thirdEnemySprite.getWidth()/2), thirdEnemySprite.getPosition().y -(thirdEnemySprite.getHeight()/2),thirdEnemySprite.getWidth(),thirdEnemySprite.getHeight());

		jButtonStart.setBounds(340, 180, 120,25);
		jButtonHome.setBounds(340, 220, 120,25);
		
		add(jStarter);
		add(jButtonHome);		
	}
	/**
	 * To call after all the choices are made - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonStartPerformed(ActionEvent evt) {
    	//Get the enemies id
    	ArrayList<Integer> enemiesId = new ArrayList<Integer>();
    	if (jCheck1.isSelected()) enemiesId.add(firstEnemyId);
    	if (jCheck2.isSelected()) enemiesId.add(secondEnemyId);
    	if (jCheck3.isSelected()) enemiesId.add(thirdEnemyId); 	
    	
    	view.play(starterId, nbEnemies, enemiesId);
    }
    
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonHomePerformed(ActionEvent evt) {
    	view.homeMenu();
    }
 
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jCheck1Performed(ActionEvent evt) {
    	//If jCheck1 is selected 
    	if (((JCheckBox)evt.getSource()).isSelected()){
    		++nbEnemies;
    	}
    	else {
    		--nbEnemies;
    	}
    	showHideStart();
    }
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jCheck2Performed(ActionEvent evt) {
    	//If jCheck2 is selected 
    	if (((JCheckBox)evt.getSource()).isSelected()){
    		++nbEnemies;
    	}
    	else {
    		--nbEnemies;
    	}
    	showHideStart();
    }
	/**
	 * jButtonQuit Event handler - Quit the program
	 * @param evt - ActionEvent performed by the player
	 */
    private void jCheck3Performed(ActionEvent evt) {
    	//If jCheck3 is selected 
    	if (((JCheckBox)evt.getSource()).isSelected()){
    		++nbEnemies;
    	}
    	else {
    		--nbEnemies;
    	}
    	
    	showHideStart();
    }
    
    private void showHideStart(){
    	//If the player has chosen at least one enemy, the button start can appear
    	if(nbEnemies>0){
    		add(jButtonStart);
    	}
    	else remove(jButtonStart);
        //Repaint the panel
    	revalidate();
    	repaint();
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
		
		//Remove all components
		jCheck1.setSelected(false);
		jCheck2.setSelected(false);
		jCheck3.setSelected(false);
		
		nbEnemies = 0;
		remove(jEnemies);
		remove(jCheck1);
		remove(jCheck2);
		remove(jCheck3);
		remove(firstEnemySprite);
		remove(secondEnemySprite);
		remove(thirdEnemySprite);
		remove(jButtonStart);
		
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
    
    /**
	 * Set StarterId
	 * @param starterId
	 * @see StarterSprite#yMousePressed(MouseEvent)
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
		
		//Set the Enemies selection boxes identifier according to the chosen starter
		if(starterId == 0){
			firstEnemyId = 1;
			secondEnemyId = 2;
			thirdEnemyId = 3;
		}
		if(starterId == 1){
			firstEnemyId = 0;
			secondEnemyId = 2;
			thirdEnemyId = 3;
		}
		if(starterId == 2){
			firstEnemyId = 0;
			secondEnemyId = 1;
			thirdEnemyId = 3;
		}
		if(starterId == 3){
			firstEnemyId = 0;
			secondEnemyId = 1;
			thirdEnemyId = 2;
		}
		
		firstEnemySprite.resetImage(firstEnemyId);
		secondEnemySprite.resetImage(secondEnemyId);
		thirdEnemySprite.resetImage(thirdEnemyId);
		
		System.out.println("Check 1 : "+firstEnemyId+" Check 2 : "+secondEnemyId+" Check 3 : "+thirdEnemyId);
		
		//The player has chosen a starter : the enemies selection boxes can appear on the panel
		add(jEnemies);
		add(jCheck1);
		add(jCheck2);
		add(jCheck3);
		add(firstEnemySprite);
		add(secondEnemySprite);
		add(thirdEnemySprite);
		//add(jButtonStart);
        //Repaint the panel
    	revalidate();
    	repaint();
	}

    public void paintComponent(Graphics g){
		super.paintComponent(g);
	    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
    }     
}