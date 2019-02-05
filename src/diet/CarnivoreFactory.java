/**
 * 
 */
package diet;

import animals.Animal;
import animals.Lion;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class CarnivoreFactory implements AbstractZooFactory {

	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Lion")){
			return new Lion();
		}
		return null;
	}

}
