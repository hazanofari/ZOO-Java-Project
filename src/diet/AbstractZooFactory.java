package diet;

import animals.Animal;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public interface AbstractZooFactory {
	/**
	 * 
	 * @param type for implemention in fiuter
	 * @return which kind animal 
	 */
	public Animal produceAnimal(String type);
	/**
	 * 
	 * @param foodtype to set
	 * @return the right Factory according to param
	 */
	public static AbstractZooFactory createAnimalFactory(String foodtype){
		if (foodtype.equals("plants")){
			return new HerbivoreFactory();
		}
		else if (foodtype.equals("plants meat")){
				return new OmnivoreFactory();
			}
		else if (foodtype.equals("meat")){
			return new CarnivoreFactory();
		}
		return null;
	}

}
