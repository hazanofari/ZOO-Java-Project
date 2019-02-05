package animals;

import graphics.ZooPanel;

import java.awt.Graphics;

import diet.Carnivore;
import diet.IDiet;
import diet.Omnivore;
import mobility.Point;


/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class Bear extends Animal 
{	
	private IDiet Omni;
	/**
	 * Bear-COnstructor- Initialing a Bear (by using the super class)
	 * @param name to set
	 * @param size to set
	 * @param verticalSpeed to set
	 * @param horizontalSpeed to set
	 * @param color to set
	 * @param pan to set
	 */
	public Bear(String name,int size,int verticalSpeed,int horizontalSpeed,String color,ZooPanel pan)
	{
		super(name,size,verticalSpeed,horizontalSpeed,1.5,color,pan);
		Omni = new Omnivore();
		super.setDiet(Omni);
		this.loadImages("bea");
	}
	/**
	 * Default Constructor
	 */
	public Bear(){
		Omni = new Omnivore();
		super.setDiet(Omni);
		
	}
	/**
	 * set of weight
	 */
	public void setWeight(){
		weight= size*1.5;
		this.loadImages("bea");
		
	}

	@Override
	public boolean setNewLocation(Point location) {
		location.setX(location.getX());
		location.setY(location.getY());
		return true;
	}
}

	