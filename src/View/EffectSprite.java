/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 22 mai 2013
 */
package View;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GameEngine.TowerManager;
import GameEngine.Player.PlayerType;

/**
 * @author aurelie
 *
 */
@SuppressWarnings("serial")
public class EffectSprite extends Sprite{
	TowerManager.AttackTypes type;
	int idUnit;

	/**
	 * 
	 */
	public EffectSprite(SceneView scene, int id, Point position, PlayerType playerType, TowerManager.AttackTypes type, int idUnit) {
		super(scene, id, position,false,playerType,16,16);
		this.type = type;
		this.idUnit = idUnit;
				
		//Loading the unit image (different one according the player)
		String fileName ="img/";
		
		if(type == TowerManager.AttackTypes.SHIELD){
			fileName +="protect.png";
		}
		else if(type == TowerManager.AttackTypes.FROST){
			fileName +="frost.png";
		}
		try {	
		      image = ImageIO.read(new File(fileName));
		  
		} catch (IOException e) {
		      e.printStackTrace();
		}
	}

	public int getIdUnit() {
		return idUnit;
	}

	public TowerManager.AttackTypes getType() {
		return type;
	}

}
