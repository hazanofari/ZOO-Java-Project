/**
 * 
 */
package diet;

import animals.Animal;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class HerbivoreFactory implements AbstractZooFactory {
	@Override
	public Animal produceAnimal(String type) {
		if (type.equals("Giraffe")){
			return new Giraffe();
		}
		else if
			(type.equals("Elephant")){
				return new Elephant();
		}
		else if
		(type.equals("Turtle")){
			return new Turtle();
	}
		return null;
	}

}
