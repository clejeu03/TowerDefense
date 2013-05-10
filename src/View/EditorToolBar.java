/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 9 mai 2013
 */
package View;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Project - TowerDefense</br>
 * <b>Class - MainMenusView</b></br>
 * <p>The MainMenusView abstract class represents the mains "panels" of the game :
 * HomeMenu, EndGameMenu, OptionsMenu, PlayMenu, SceneView, GameMenuBar, MapEditor
 * </p> 
 * <b>Creation :</b> 22/04/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 */
@SuppressWarnings("serial")
public class EditorToolBar extends MainViews{
	static private final String newline = "\n";
    private JButton jButtonOpen;
    private JButton jButtonSave;
    private JButton jButtonBack;
  
    private  JTextArea log;
    private  JScrollPane logScrollPane;
    
    private JFileChooser fileChooser;

	/**
	 * Constructor of the Editor Toolbar class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public EditorToolBar(ViewManager view, Point position, int width, int height) {
		super(view,position,width,height);
		
		
		//Creating the components
		jButtonOpen = new javax.swing.JButton();
		jButtonSave = new javax.swing.JButton();
		jButtonBack = new javax.swing.JButton();
	 
		//Log : 5 lines, 20 column
        log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        logScrollPane = new JScrollPane(log);
		
		//Setting the components parameters and theirs listeners        		
		jButtonOpen.setText("Open");
		jButtonOpen.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonOpenPerformed(evt);
		    }
		});
		
		jButtonSave.setText("Save");
		jButtonSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonSavePerformed(evt);
		    }
		});
		
		jButtonBack.setText("Home");
		jButtonBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        jButtonBackPerformed(evt);
		    }
		});
		
		setLayout(null);
		setBackground(Color.DARK_GRAY); 
		jButtonOpen.setBounds(10, 10, 100,25);
		jButtonSave.setBounds(10, 50, 100,25);
		jButtonBack.setBounds(10, 90, 100,25);
		logScrollPane.setBounds(140,100, 650,65);
		add(jButtonOpen);
		add(jButtonSave);
		add(jButtonBack);
		add(logScrollPane);
	}
	
	/**
	 * jButtonBack Event handler - Back to the home menu !
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonOpenPerformed(ActionEvent evt) {
    	showChooseFile();
    }
    
	/**
	 * jButtonBack Event handler - Back to the home menu !
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonSavePerformed(ActionEvent evt) {
    	System.out.println("Editor - I need to save the user mapView and map");
    	
    }
	
	/**
	 * jButtonBack Event handler - Back to the home menu !
	 * @param evt - ActionEvent performed by the player
	 */
    private void jButtonBackPerformed(ActionEvent evt) {
    	view.homeMenu();
    }
    

	/**
	 * @param editorScene
	 */
	public void showChooseFile() {
		log.append("Open a 800x400 jpg or png map"+newline);
		fileChooser = new JFileChooser();
		int retour=fileChooser.showOpenDialog(view);
		//If a file have been choose 
		if(retour==JFileChooser.APPROVE_OPTION){
			openImage(fileChooser.getSelectedFile().getName(),fileChooser.getSelectedFile().getAbsolutePath() );
		}
		else log.append("Open a damn map ! "+newline);
	}
    
	public void openImage(String fileName, String filePath){
		
		
		log.append("Checking the "+filePath+" map format..."+newline);
		
		//Checking the image format (file and extension)
		
	}
    

}
