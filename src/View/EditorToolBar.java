/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 9 mai 2013
 */
package View;

import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import GameEngine.HeightMap;


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
	private EditorScene editorScene;
	
	static private final String newline = "\n";
    private JButton jButtonOpen;
    private JButton jButtonSave;
    private JButton jButtonBack;
  
    private  JTextArea log;
    private  JScrollPane logScrollPane;
    
    private JFileChooser fileChooser;
    private FileNameExtensionFilter fileFilter;

	/**
	 * Constructor of the Editor Toolbar class
	 * @param view
	 * @param position
	 * @param width
	 * @param height
	 */
	public EditorToolBar(ViewManager view, Point position, int width, int height, EditorScene editorScene) {
		super(view,position,width,height);
		
		this.editorScene = editorScene;
		
		//Creating the components
		jButtonOpen = new javax.swing.JButton();
		jButtonSave = new javax.swing.JButton();
		jButtonBack = new javax.swing.JButton();
	 
		//Log : 5 lines, 20 column
        log = new JTextArea(5,200);
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
		logScrollPane.setBounds(140,80, 650,85);
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
    	editorScene.quitEditor();
    	view.homeMenu();
    }
    

	/**
	 * @param editorScene
	 */
	public void showChooseFile() {
		log.append("Open a 800x400 jpg or png map"+newline);
		fileChooser = new JFileChooser();
		
		//Create a filter to show png or jpg file only
		fileFilter = new FileNameExtensionFilter("JPEG file", "PNG file","jpg", "jpeg", "png");
		
		fileChooser.setFileFilter(fileFilter);
		
		int choice=fileChooser.showOpenDialog(view);
		//If a file have been choose 
		if(choice==JFileChooser.APPROVE_OPTION){
			openImage(fileChooser.getSelectedFile().getName(),fileChooser.getSelectedFile().getAbsolutePath() );
		}
		else log.append("Open a damn map ! "+newline);
	}
    
	public void openImage(String fileName, String filePath){
		
		log.append("Checking the "+filePath+" map format..."+newline);
		
		//Trying to load the image and 		
		BufferedImage img = null;//Local image containment
		
		try {
			img = ImageIO.read(new File(filePath)); 
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		//Checking the format
		if((img.getWidth()!= 800)||(img.getHeight()!=400)){
			log.append("The image size need to be 800x400 instead of "+img.getWidth()+"x"+img.getHeight()+".Try again ! "+newline);
		}
		else{
			log.append ("Loading the "+filePath+" map..."+newline);
			editorScene.initiate(img);
			log.append("_________________________________________________"+newline);
			log.append("INSTRUCTIONS : "+newline);
			log.append("Drag the mouse to paint the relief (mouse left button) or erase it  (mouse right button) "+newline);
		}
		
	}
    

}
