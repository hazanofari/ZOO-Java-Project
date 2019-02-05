package design;

import java.util.ArrayList;
import java.util.List;

import plants.Plant;
import meat.Steak;
import animals.Animal;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class ZooMemento {
	/**
	 * fields of class
	 */
	private List<Animal> aninalList = new ArrayList<Animal>();
	private Steak Steak ;
	private Plant Plant;

	/**
	 * Constructor of class
	 * @param aninalList list of animals
	 * @param Steak the steak
	 * @param Plant all plants
	 */
	public ZooMemento(List<Animal> aninalList,Steak Steak,Plant Plant) {
		this.setAninalList(aninalList);
		this.setSteak(Steak);
		this.setPlant(Plant);
	}

	/**
	 * @return the aninalList
	 */
	public List<Animal> getAninalList() {
		return aninalList;
	}

	/**
	 * @param aninalList the aninalList to set
	 */
	public void setAninalList(List<Animal> aninalList) {
		this.aninalList = aninalList;
	}

	/**
	 * @return the steak
	 */
	public Steak getSteak() {
		return Steak;
	}

	/**
	 * @param steak the steak to set
	 */
	public void setSteak(Steak steak) {
		Steak = steak;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return Plant;
	}

	/**
	 * @param plant the plant to set
	 */
	public void setPlant(Plant plant) {
		Plant = plant;
	}


}
