/**
 * Project : TowerDefense
 * By Aurélie Beauprez, Thomas Demenat, Keven Akyurek and Cecilia Lejeune
 * Copyright IMAC 2013 - All Rights Reserved
 *
 * File created on 21 mai 2013
 */
package Dispatcher;

import GameEngine.TowerManager;

/**
 * @author aurelie
 *
 */
public class AddEffectOrder extends Order{

	private TowerManager.AttackTypes type;
	private int idUnit;
	/**
	 * 
	 */
	public AddEffectOrder(int id, TowerManager.AttackTypes type, int idUnit) {
		super(id);
		this.type = type;
		this.idUnit = idUnit;
	}
	public TowerManager.AttackTypes getType() {
		return type;
	}
	public int getIdUnit() {
		return idUnit;
	}
}
