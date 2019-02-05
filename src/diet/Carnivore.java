package diet;

import animals.Animal;
import food.EFoodType;
import food.IEdible;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class Carnivore implements IDiet 
{
	/**
	 * CanEat- Check if the food for the animal matches the food it can to eat
	 * @param food
	 * 			-The kind of the food to the animal 
	 * @return -True: If the food matches to the animal
	 * 			-False: If the food does not match to the animal
	 * 
	 */
	public boolean canEat(EFoodType food)
	{
		if ((food==(EFoodType.MEAT)))
			return true;
		return false;
	}
	
	/**
	 * toString- 
	 * Prints the simple name of the class
	 */
	public String toString()
	{
		return "[" + this.getClass().getSimpleName() + "]";
	}
	
	/**
	 * eat - call the function CatEat, if True - calling the actions the animal do after eating(change weight, making sound)
	 * @param animal to feed
	 * @param food - the food sent to the animal
	 * @return True- if the animal eat False- If the animal didn't eat
	 */
	public boolean eat(Animal animal, IEdible food)
	{
		if(canEat(food.getFoodType()))
		{
			animal.setWeight();
			return true;
		}
		return false;
	}
	
}
