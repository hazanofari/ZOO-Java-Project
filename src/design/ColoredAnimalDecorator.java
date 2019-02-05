package design;

import animals.Animal;

/**
*@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
*
*/
public class ColoredAnimalDecorator implements ColoredAnimal {
	/**
	 * fields of class
	 */

	private ColoredAnimal an;
	/**
	 * 
	 * @param a is the color chosen for decoration
	 */

	public ColoredAnimalDecorator(Animal a) {
		an = a;
	}

	@Override
	 public void PaintAnimal(String color) {
		
		((Animal)an).setColor(color);
		if( ((Animal)an).getAnimalName().equals("Bear"))
			((Animal)an).loadImages("bea");
		else if (((Animal)an).getAnimalName().equals("Elephant"))
			((Animal)an).loadImages("elf");
		else if (((Animal)an).getAnimalName().equals("Giraffe"))
			((Animal)an).loadImages("grf");
		else if (((Animal)an).getAnimalName()=="Lion")
			((Animal)an).loadImages("lio");
		else if (((Animal)an).getAnimalName().equals("Turtle"))
			((Animal)an).loadImages("trt");	
			
			
	}
}
