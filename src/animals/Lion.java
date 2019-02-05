package animals;

import diet.Carnivore;
import diet.IDiet;
import food.EFoodType;
import food.IEdible;
import graphics.ZooPanel;
import mobility.Point;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class Lion extends Animal
{
	private IDiet carni;
	/**
	 * Lion- Constructor- Initialing a Lion (by using the super class)
	 * @param name to set
	 * @param size to set
	 * @param verticalSpeed to set
	 * @param horizontalSpeed to set
	 * @param color to set
	 * @param pan ref
	 */
	public Lion(String name,int size,int verticalSpeed,int horizontalSpeed,String color,ZooPanel pan)
	{
		super(name,size,verticalSpeed,horizontalSpeed,0.8,color,pan);
		carni = new Carnivore();
		super.setDiet(carni);
		this.loadImages("lio");
	}
	/**
	 * Default Constructor
	 */
	public Lion(){
		carni = new Carnivore();
		super.setDiet(carni);
		
		
	}
	
	/**
	 * set of weight
	 */
	public void setWeight(){
		weight= size*0.8;
		this.loadImages("lio");
	}
	
	
	@Override
	public boolean setNewLocation(Point location) {
		location.setX(location.getX());
		location.setY(location.getY());
		return true;
	}
	
}
