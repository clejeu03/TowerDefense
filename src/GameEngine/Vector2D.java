/**
 * Project - TowerDefense</br>
 * <b>Class - Vector2D</b></br>
 * <p> Class created for the unique use of the missile path.
 * </p> 
 * <b>Creation :</b> 10/05/2013</br>
 * @author K. Akyurek, A. Beauprez, T. Demenat, C. Lejeune - <b>IMAC</b></br>
 * @see Missile
 * @see WarManager#moveMissile(Missile)
 * 
 */
package GameEngine;

import java.awt.Point;

public class Vector2D{
	
	private float A;
	private float B;
	
	public Vector2D(float A, float B){
		this.A = A ;
		this.B = B ;
	}
	public Vector2D(){
		this.A = 0;
		this.B = 0;
	}
	public double lenght(){
		return Math.sqrt(this.A*this.A + this.B*this.B);
	}
	public boolean equals(Vector2D v){
		if(this.A == v.A && this.B == v.B)
			return true;
		return false;
	}
	public double dotProduct(Vector2D v){
		Vector2D vector = new Vector2D(this.A*v.A, this.B*v.B);
		return vector.A + vector.B;
	}
	public Vector2D normalize() {
		float norm = (float)this.lenght();
		return new Vector2D(this.A/norm, this.B/norm);
	}
	public Point pointPlusVector(Point p){
		return new Point(p.x+(int)this.A, p.y+(int)this.B);//This cast must create imprecision
	}
	
	@Override
	public String toString(){
		return "<"+this.A+", "+this.B+">";
	}
}
