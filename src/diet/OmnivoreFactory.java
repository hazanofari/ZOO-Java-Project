/**
 * 
 */
package diet;

import animals.Animal;
import animals.Bear;
import animals.Giraffe;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class OmnivoreFactory implements AbstractZooFactory{
	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Bear")){
			return new Bear();
		}
		return null;
	}
}

