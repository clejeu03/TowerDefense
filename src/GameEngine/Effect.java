/**
 * Project - TowerDefense</br>
 * <b>Class - Effect</b></br>
 * <p>The Effect class stands for all the consequences of a special effect missile.</br>
 * It has begin time and duration, and it's type determine the action to do on the target while not attempting the end time.</br>
 * </p> 
 * <b>Creation :</b> 20/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Unit
 * @see Missile
 * @see ArmyManager
 */
package GameEngine;

public class Effect {

	private int id;
	private TowerManager.AttackTypes type;
	private Unit target;
	private long beginTime;
	private int duration;
	private int intensity; //Optionnal
	
	/**
	 * Constructor of the Effect class
	 * @param type - enum of TowerManager.AttackType
	 * @param beginTime - currentTime
	 * @param duration
	 * @see TowerManager#AttackType
	 */
	public Effect(int id, Unit target, TowerManager.AttackTypes type, long beginTime, int duration) {
		this.id = id;
		this.type = type;
		this.duration = duration;
		this.beginTime = beginTime;
		this.intensity = 0;
		this.target = target;
	}
	/**
	 * Other constructor that can handle intensity of an effect
	 * @param target
	 * @param type
	 * @param beginTime
	 * @param duration
	 * @param intensity
	 */
	public Effect(int id, Unit target, TowerManager.AttackTypes type, long beginTime, int duration, int intensity){
		this.id = id;
		this.type = type;
		this.target = target;
		this.duration = duration;
		this.beginTime = beginTime;
		this.intensity = 0;
		this.intensity = intensity;
	}
	
	/**
	 * Do the modification on the targeted unit
	 * @param unit
	 */
	public void active(Unit unit){
		System.out.println("Effect start :"+this.getBeginTime()+" and duration :"+this.getDuration());
		switch(this.type){
		case SHIELD : 
			unit.setProtected(true);
			System.out.println("Unit n°"+unit.getId()+" under shield protection !");
			break;
		case FROST :
			unit.setSpeed(unit.getSpeed()- intensity);
			System.out.println("Unit n°"+unit.getId()+" frozen !");
			break;
		default :
			break;
		}
	}
	
	public TowerManager.AttackTypes getType(){
		return type;
	}
	
	public long getBeginTime(){
		return beginTime;
	}
	
	public int getDuration(){
		return duration;
	}
	public Unit getTarget(){
		return target;
	}
	public int getId(){
		return id;
	}
}
