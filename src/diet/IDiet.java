package diet;

import food.EFoodType;
import animals.Animal;
import food.IEdible;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public interface IDiet 
{
	/**
	 * 
	 * @param food type
	 * @return food type
	 */
	public boolean canEat(EFoodType food);
	/**
	 * 
	 * @param animal to set name
	 * @param food to set
	 * @return what can eat
	 */
	public boolean eat(Animal animal, IEdible food);
	/**
	 * 
	 * @return string
	 */
	public String toString();
}
