package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Project - TowerDefense</br>
 * <b>Class - GameToolbar</b></br>
 * <p>The GameToolbar class is used to display a user friendly interface while playing the game.
 * The player would be allowed to make actions thanks to buttons ans informations hold in the
 * GameToolbar.
 * The GameToolbar is created and managed by the ViewManager.</br>
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see ViewManager
 * 
 */

public class GameToolbar extends JPanel{
    private ViewManager player;
    
    private JButton jButtonBack;
    /*private TowerInfoInterface jPanelTowerInfo;
    private boolean infoVisible;*/
	
	public GameToolbar(ViewManager p) {
		super();
		player = p; 
		//infoVisible = false;
		
		/*Création des composants*/
		jButtonBack = new javax.swing.JButton();
		 
		/*Paramètrage des composants et de leur listeners*/	        
		jButtonBack.setText("Back");
		jButtonBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonBackPerformed(evt);
		    }
		});
		
		/*Disposition des composants sur le panneau*/
		setLayout(null);
		setBackground(Color.gray); 
		jButtonBack.setBounds(350, 50, 100,25);
		add(jButtonBack);
	}
	
	/**
	 * Gestionnaire des évènements concernant le bouton Back
	 * @param evt : ActionEvent
	 */
    private void jButtonBackPerformed(ActionEvent evt) {
    	System.out.println("Back");
    	
        /*Rafraichit la fenêtre*/
    	/*if(infoVisible){
	    	remove(jPanelTowerInfo);
	    	revalidate();
	    	repaint();	  	
    	}*/
    	player.mainMenu();

    }
    
    /*public void towerClicked(Point position, int idOwner){
    	jPanelTowerInfo = new TowerInfoInterface(position, idOwner, player);
    	jPanelTowerInfo.setBounds(350, 100, 100,100);
    	add(jPanelTowerInfo);
    	
    	infoVisible = true;
    	revalidate();
    	repaint();	  	
    }*/
}
