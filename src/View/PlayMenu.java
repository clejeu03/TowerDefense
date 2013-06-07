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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import GameEngine.Player.PlayerType;

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
    private PlayerType starterType;

	private JLabel jEnemies;
	private JCheckBox jCheck1;
	private JCheckBox jCheck2;
	private JCheckBox jCheck3;
	private int nbEnemies;
	private PlayerType firstEnemyType;
	private PlayerType secondEnemyType;
	private PlayerType thirdEnemyType;
	private EnemySprite firstEnemySprite;
	private EnemySprite secondEnemySprite;
	private EnemySprite thirdEnemySprite;
	
	private JLabel jMap;
	private JComboBox<String> mapList;
	
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
		
		starterType = PlayerType.ELECTRIC;
		starters = new ArrayList<Sprite>();
		
		nbEnemies = 0;
		firstEnemyType = PlayerType.WATER;
		secondEnemyType = PlayerType.GRASS;
		thirdEnemyType = PlayerType.FIRE;
		
		//Loading the home background
		try {
		      background = ImageIO.read(new File("img/parameters.jpg"));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
		
		
		//Creating the components
		jStarter = new javax.swing.JLabel("Choose your starter wisely : ");		
		starters.add(new StarterSprite(this, new Point(350,60), true, PlayerType.ELECTRIC, 50,50));
		starters.add(new StarterSprite(this, new Point(400,60), true, PlayerType.WATER, 50,50));
		starters.add(new StarterSprite(this, new Point(450,60), true, PlayerType.GRASS, 50,50));
		starters.add(new StarterSprite(this, new Point(500,60), true, PlayerType.FIRE, 50,50));
		
		jEnemies = new javax.swing.JLabel("Choose your enemies : ");
		jCheck1 = new JCheckBox();
		jCheck2 = new JCheckBox();
		jCheck3 = new JCheckBox();
		firstEnemySprite = new EnemySprite(this, new Point(375,110), false, firstEnemyType, 50,50);
		secondEnemySprite = new EnemySprite(this, new Point(425,110), false, secondEnemyType, 50,50);		
		thirdEnemySprite = new EnemySprite(this, new Point(475,110), false, thirdEnemyType, 50,50);
		
		jMap = new javax.swing.JLabel("Choose your battlefield : ");
		mapList = new JComboBox<String>();
		
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
		
		mapList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		      
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
		jMap.setBounds(180,180, 250, 15);
		
		jCheck1.setMargin(new Insets(0, 0, 0, 0));
		jCheck1.setBounds(365, 135, 15, 15);
		jCheck2.setMargin(new Insets(0, 0, 0, 0));
		jCheck2.setBounds(415, 135, 15, 15);
		jCheck3.setMargin(new Insets(0, 0, 0, 0));
		jCheck3.setBounds(465, 135, 15, 15);
		firstEnemySprite.setBounds(firstEnemySprite.getPosition().x -(firstEnemySprite.getWidth()/2), firstEnemySprite.getPosition().y -(firstEnemySprite.getHeight()/2),firstEnemySprite.getWidth(),firstEnemySprite.getHeight());
		secondEnemySprite.setBounds(secondEnemySprite.getPosition().x -(secondEnemySprite.getWidth()/2), secondEnemySprite.getPosition().y -(secondEnemySprite.getHeight()/2),secondEnemySprite.getWidth(),secondEnemySprite.getHeight());
		thirdEnemySprite.setBounds(thirdEnemySprite.getPosition().x -(thirdEnemySprite.getWidth()/2), thirdEnemySprite.getPosition().y -(thirdEnemySprite.getHeight()/2),thirdEnemySprite.getWidth(),thirdEnemySprite.getHeight());

		//mapList.setPreferredSize(new Dimension(100, 20));
		mapList.setBounds(370, 170, 110, 25);
		
		jButtonStart.setBounds(340, 220, 120,25);
		jButtonHome.setBounds(340, 260, 120,25);
		
		add(jStarter);
		add(jButtonHome);	
	}
	/**
	 * To call after all the choices are made - Time to launch the game!
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonStartPerformed(ActionEvent evt) {
    	//Get the enemies id
    	ArrayList<PlayerType> enemiesType = new ArrayList<PlayerType>();
    	if (jCheck1.isSelected()) enemiesType.add(firstEnemyType);
    	if (jCheck2.isSelected()) enemiesType.add(secondEnemyType);
    	if (jCheck3.isSelected()) enemiesType.add(thirdEnemyType); 	
    	
    	view.play(starterType, nbEnemies, enemiesType, (String)mapList.getSelectedItem());
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
    	//If the player has chosen at least one enemy, the button start and the map choser can appear
    	if(nbEnemies>0){
    		add(jButtonStart);
    		add(jMap);
    		add(mapList);
    	}
    	else {
    		remove(jButtonStart);
    		remove(jMap);
    		remove(mapList);
    	}
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
		
		//Resetting the map list
		mapList.removeAllItems();
		
		File tmpFolder = new File("img/map");
		String[] files = tmpFolder.list();
		for(int i=0; i<files.length;i++){
			if (files[i].endsWith("_hm.png")){
				for(int k=0;k<files.length;k++){
					if(files[k].endsWith("_view.png")){
						String nameOfHmMap = files[i].split("_")[0];
						String nameOfViewMap = files[k].split("_")[0];
						if(nameOfHmMap.equals(nameOfViewMap)) mapList.addItem(nameOfHmMap);
					}
				}
			}
		}
		//TODO Checking if there's a least one item in the list of map 
		//If not : back to Home and errorDialog
		
		if(mapList.getItemCount() != 0){
			mapList.setSelectedIndex(0);
			for(int i=0; i<mapList.getItemCount(); i++){
				if(mapList.getItemAt(i).equals("DefaultMap")){
					mapList.setSelectedIndex(i);
					break;
				}
			}
		}

		
		nbEnemies = 0;
		remove(jEnemies);
		remove(jCheck1);
		remove(jCheck2);
		remove(jCheck3);
		remove(firstEnemySprite);
		remove(secondEnemySprite);
		remove(thirdEnemySprite);
		remove(jMap);
		remove(mapList);
		remove(jButtonStart);

		
        //Repaint the panel
    	revalidate();
    	repaint();	
	}
    
	
	public void checkMapNumber(){
		if(mapList.getItemCount() == 0){
			JOptionPane.showMessageDialog(this,
				    "There's no existing battlefield ! Create a map with the MapEditor first. ",
				    "NoExistingMap warning",
				    JOptionPane.WARNING_MESSAGE);
			view.homeMenu();
		}
	}
    /**
	 * Set starterType
	 * @param playerType
	 * @see StarterSprite#yMousePressed(MouseEvent)
	 */
	public void starterClicked(PlayerType playerType){
		this.starterType = playerType;
		
		//Reset the boolean chosen of the other starters sprites
		Iterator<Sprite> it = starters.iterator();
		while (it.hasNext()) {
			Sprite element = it.next();
			if(element.getPlayerType()!= playerType){
				((StarterSprite) element).setChosen(false);
			}
		}
		
		//Set the Enemies selection boxes identifier according to the chosen starter
		if(playerType == PlayerType.ELECTRIC){
			firstEnemyType = PlayerType.WATER;
			secondEnemyType = PlayerType.GRASS;
			thirdEnemyType = PlayerType.FIRE;
		}
		if(playerType == PlayerType.WATER){
			firstEnemyType = PlayerType.ELECTRIC;
			secondEnemyType = PlayerType.GRASS;
			thirdEnemyType = PlayerType.FIRE;
		}
		if(playerType == PlayerType.GRASS){
			firstEnemyType = PlayerType.ELECTRIC;
			secondEnemyType = PlayerType.WATER;
			thirdEnemyType = PlayerType.FIRE;
		}
		if(playerType == PlayerType.FIRE){
			firstEnemyType = PlayerType.ELECTRIC;
			secondEnemyType = PlayerType.WATER;
			thirdEnemyType = PlayerType.GRASS;
		}
		
		firstEnemySprite.resetImage(firstEnemyType);
		secondEnemySprite.resetImage(secondEnemyType);
		thirdEnemySprite.resetImage(thirdEnemyType);
		
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