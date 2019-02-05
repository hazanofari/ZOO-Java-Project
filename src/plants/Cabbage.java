package plants;

import food.EFoodType;
import graphics.ZooPanel;
/**
*@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
*
*/
public class Cabbage extends Plant 
{
	
	static private Cabbage instance=null;
	/**
	 * cabbage contractor
	 */
	private Cabbage(ZooPanel refToZooPanel) 
	{
		super("Cabbage",refToZooPanel);
	}
	
	/**
	 * function singleton
	 * @param refToZooPanel ref
	 * @return instance
	 */

		public static Plant getInstance(ZooPanel refToZooPanel) {
			if (instance == null)
				synchronized (Cabbage.class) {
					if (instance == null)
						instance = new Cabbage(refToZooPanel);
				}
			return instance;
		}
}
