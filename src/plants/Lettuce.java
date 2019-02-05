package plants;

import food.EFoodType;
import graphics.ZooPanel;

import java.awt.Graphics;

/**
*@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
*
*/
public class Lettuce extends Plant 
{
	
	static private Lettuce instance=null;
	
	/**
	 * Lettuce constructor
	 */
	private Lettuce(ZooPanel refToZooPanel) 
	{
		super("lettuce",refToZooPanel);
	}
	
	/**
	 * function singleton
	 * @param refToZooPanel ref
	 * @return instance
	 */

	public static Plant getInstance(ZooPanel refToZooPanel) {
		if (instance == null)
			synchronized (Lettuce.class) {
				if (instance == null)
					instance = new Lettuce(refToZooPanel);
			}
		return instance;
	}
}