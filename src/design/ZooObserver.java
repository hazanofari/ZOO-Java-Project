package design;

import graphics.ZooPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import animals.Animal;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class ZooObserver extends Thread implements Observer {
	
	private List<Observable> list_of_observables;
	private ZooPanel panel;
	
	/**
	 * Constructor
	 * @param panel ref of zoopanel in this class
	 */
	
	public ZooObserver(ZooPanel panel) {
		list_of_observables= new ArrayList<Observable>();
		this.panel=panel;
	}
	/**
	 * 
	 * @param a for adding new observable
	 */
	
	public void addObserver(Observable a){
		list_of_observables.add(a);
		
	}
	/**
	 * @return the list_of_observables
	 */
	public List<Observable> getList_of_observables() {
		return list_of_observables;
	}


	@Override
	synchronized public void update(Observable animal, Object stringrek) {
		(animal).notifyObservers();
		notify();
	}
	
	
	/**
	 *  function for checking if animal can eat another
	 */
	public void run() {
		while (true) {
			panel.repaint();
			Observable curr, other;
			synchronized (this) {
				try {
					
					wait();
				} catch (InterruptedException e) {
					return;
				}
				for (int i = 0; i < list_of_observables.size(); i++) {
					curr = list_of_observables.get(i);
					for (int j = 0; j < list_of_observables.size() && curr != null; j++) {
						other =  list_of_observables.get(j);

						if (other != null
								&& !(((Animal)curr).getAnimalName().equals(((Animal)other)
										.getAnimalName()))) {
							if ((((Animal)curr).calcDistance(((Animal)other).getLocation())) < ((Animal)other)
									.getSize()) {
								if (((Animal)curr).getDiet().canEat(((Animal)other).getFoodType())) {
									if (((Animal)curr).getWeight() >= 2 * ((Animal)other)
											.getWeight()) {
										panel.setTotalEatCount(panel.getTotalEatCount()- ((Animal)other).getEatCount());
										((Animal)other).killer();
										list_of_observables.remove(other);
										
										this.panel.getAnimallist().remove(j);
										panel.callback(((Animal)curr));
									}
								}
							}
						}
					}
				}
			}
			panel.repaint();
			//panel.findPrey();
			
		}
	}

	
	

}
