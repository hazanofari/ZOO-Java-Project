package animals;

import graphics.ZooPanel;
import diet.Herbivore;
import diet.IDiet;
import mobility.Point;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class Giraffe extends Animal
{	
	private IDiet Herbi;
	/**
	 * Giraffe-Constructor- Initialing a Giraffe (by using the super class
	 * @param name to set
	 * @param size to set
	 * @param verticalSpeed to set
	 * @param horizontalSpeed to set
	 * @param color to set
	 * @param pan to set
	 */
	public Giraffe(String name,int size,int verticalSpeed,int horizontalSpeed,String color,ZooPanel pan) 
	{
		super(name,size,verticalSpeed,horizontalSpeed,2.2,color,pan);
		Herbi = new Herbivore();
		super.setDiet(Herbi);
		this.loadImages("grf");
	}
	/**
	 * Default Constructor
	 */
	public Giraffe(){
		Herbi = new Herbivore();
		super.setDiet(Herbi);
		
	}
	
	/**
	 * set of weight
	 */
	public void setWeight(){
		weight= size*2;
		this.loadImages("grf");
	}

	@Override
	public boolean setNewLocation(Point location) {
		location.setX(location.getX());
		location.setY(location.getY());
		return true;
	}
	
}
