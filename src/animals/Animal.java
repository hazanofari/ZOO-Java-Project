package animals;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;

import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;
import food.EFoodType;
import food.IEdible;
import graphics.IAnimalBehavior;
import graphics.IDrawable;
import graphics.ZooPanel;
import design.ColoredAnimal;
import design.ColoredAnimalDecorator;
import design.ZooObserver;
import diet.IDiet;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public abstract class Animal extends Observable implements ILocatable, IEdible,
		IDrawable, IAnimalBehavior, ColoredAnimal, Runnable, Cloneable {
	protected final int EAT_DISTANCE = 5;
	protected int size;
	protected String col;
	protected int horSpeed;
	protected int verSpeed;
	protected boolean coordChanged = true;
	protected Thread thread;
	protected int x_dir = 1;
	protected int y_dir = 1;
	protected int eatCount;
	protected ZooPanel pan;
	protected boolean threadSuspended = false;
	protected BufferedImage img1, img2;
	private String name;
	protected double weight;
	protected IDiet diet;
	private EFoodType type;
	private boolean isRun;
	private Future<?> task;
	protected Point location;
	private ZooObserver cont ; 
/**
 * 
 * @param cont for set
 */
	public void setCont(ZooObserver cont) {
		this.cont = cont;
		this.cont.addObserver(this);
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Point location) {
		this.location =new Point (location.getX(),location.getY());
	}
	/**
	 * 
	 * @param isRun for set
	 */
	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}
	public void setTask(Future<?> task) {
		this.task = task;
	}

	/**
	 * Constructor-Initializing animal
	 * @param name to set
	 * @param _size to set
	 * @param verticalSpeed to set
	 * @param horizontalSpeed to set
	 * @param weightRatio to set
	 * @param color to set
	 * @param pan ref
	 */
	public Animal(String name, int _size, int verticalSpeed,
			int horizontalSpeed, double weightRatio, String color, ZooPanel pan) {
		this.setLocation(new Point((int) (Math.random() * pan.getWidth()),
				(int) (Math.random() * pan.getHeight())));
		this.pan = pan;
		setName(name);
		size = _size;
		weight = weightRatio * size;
		verSpeed = verticalSpeed;
		horSpeed = horizontalSpeed;
		col = color;
		isRun = false;
		
	}
/**
 * Constructor default
 */
	public Animal() {
		isRun = false;

	}

	/**
	 * function setParams for conditions that no parameters in constructor
	 * @param name to set
	 * @param _size to set
	 * @param verticalSpeed to set
	 * @param horizontalSpeed to set
	 * @param color to set
	 * @param pan to set
	 */
	public void setParams(String name, int _size, int verticalSpeed,
			int horizontalSpeed, String color, ZooPanel pan) {
		this.pan = pan;
		setName(name);
		size = _size;
		verSpeed = verticalSpeed;
		horSpeed = horizontalSpeed;
		col = color;
		this.setLocation(new Point((int) (Math.random() * pan.getWidth()),
				(int) (Math.random() * pan.getHeight())));
		
	}

	/**
	 * 
	 * @param animal
	 *            animal that eat type of food
	 * @return true if animal can eat the food and food count increased
	 */
	public boolean canIEat(Animal animal) {

		// this.calcDistance(animal.getLocation());

		if (!this.eat(animal)) {
			return false;
		}
		this.eatInc();

		return true;
	}

	/**
	 * this function check if the animal can eat the food that show's on the
	 * screen
	 * 
	 * @return
	 */
	private boolean eatableFood() {
		if (pan.getPlant() == null) {
			return this.eat(pan.getSteak());
		}
		if (this.eat(pan.getPlant())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return zoo panel
	 */
	public ZooPanel getPan() {
		return pan;
	}

	/**
	 * 
	 * @param pan
	 *            - set zoo panel
	 */
	public void setPan(ZooPanel pan) {
		this.pan = pan;
	}

	/**
	 * 
	 * @return ture if animal suspended
	 */
	public boolean isThreadSuspended() {
		return threadSuspended;
	}

	/**
	 * suspend the animal
	 */
	synchronized public void setSuspended() {
		this.threadSuspended = true;
	}

	/**
	 * return true if coordinate change
	 */
	synchronized public boolean getChanges() {
		return coordChanged;
	}

	/**
	 * @param eatCount the eatCount to set
	 */
	 synchronized public void setEatCount(int eatCount) {
		this.eatCount = eatCount;
	}
	
	/**
	 * return true if set coordinate succeeded
	 */
	public boolean setChanges(boolean coordChanged) {
		notifyObservers();
		cont.update(this, "");
		
		return true;
	}

	/**
	 * print name of animal
	 */
	public String toString() {
		return "[" + this.getClass().getSimpleName() + ":  " + "is running= "
				+ this.getisRunning() + ", weight= " + this.getWeight() + ",  Color =  " +this.getColor() +"]";
	}

	/**
	 * return eat counter
	 */
	public int getEatCount() {
		return eatCount;
	}

	/**
	 * wake up method
	 */
	synchronized public void setResumed() {
		notify();
		threadSuspended = false;
	}

	/**
	 * get animal color
	 */
	public String getColor() {
		return this.col;
	}

	/**
	 * Increase animal food count
	 */
	public void eatInc() {
		this.eatCount += 1;
	}

	/**
	 * 
	 * @return the horizontal animal speed
	 */
	public int getHorSpeed() {
		return horSpeed;
	}

	/**
	 * 
	 * @param horSpeed
	 *            - set horizontal speed of animal
	 * @return true of succeeded
	 */
	public boolean setHorSpeed(int horSpeed) {
		this.horSpeed = horSpeed;
		return true;
	}

	/**
	 * 
	 * @return- vertical speed of animal
	 */
	public int getVerSpeed() {
		return verSpeed;
	}

	/**
	 * 
	 * @param verSpeed
	 *            - animal speed
	 * @return true if succeeded
	 */
	public boolean setVerSpeed(int verSpeed) {
		this.verSpeed = verSpeed;
		return true;
	}

	/**
	 * 
	 * @return diet of animal
	 */
	public IDiet getDiet() {
		return diet;
	}

	/**
	 * killer method - kill animal
	 */
	synchronized public void killer() {
		isRun = false;
		this.notify();
		task.cancel(true);

	}

	/**
	 * 
	 * @return the thread
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * 
	 * @param thread
	 *            - spacific thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	/**
	 * 
	 * @param food
	 *            - enum type //MEAT,NOTFOOD,VEGETABLE//
	 * @return true or false (in case that the animal can eat return true)
	 */
	public boolean eat(IEdible food) {
		return diet.eat(this, food);
	}

	/**
	 * this method identifies the animal type and return the food type
	 * 
	 * @return animal food type
	 */
	@Override
	public EFoodType getFoodType() {
		return EFoodType.MEAT;
	}

	/**
	 * to get animal weight
	 * 
	 * @return animal weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * this method set the animal type
	 * 
	 * @param weight - weight of animal
	 *            
	 * @return true or false (false in case of negative weight)
	 */
	public abstract void setWeight();

	/**
	 * set the name of animal
	 * 
	 * @param name
	 *            - name of animal
	 * @return true or false (false in case of null)
	 */
	public boolean setName(String name) {
		if (name == null) {
			return false;
		}
		this.name = name;
		return true;
	}

	/**
	 * return name of animal
	 * 
	 * @return name of animal
	 */
	public String getAnimalName() {
		return this.name;
	}

	/**
	 * 
	 * @return enum food type
	 */
	public EFoodType getType() {
		return type;
	}

	/**
	 * set the type of food the animal eat
	 * 
	 * @param type
	 *            of food
	 * @return true of false (success or not)
	 */
	public boolean setType(EFoodType type) {
		if (type.equals(EFoodType.MEAT) || type.equals(EFoodType.NOTFOOD)
				|| type.equals(EFoodType.VEGETABLE)) {
			this.type = type;
			return true;
		}
		return false;
	}

	/**
	 * get the size of animal
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @param size
	 *            -set the size of animal
	 * @return return true if succeeded of false if not
	 */
	public boolean setSize(int size) {
		if (size > 0) {
			this.size = size;
			return true;
		}
		return false;
	}

	/**
	 * draw object method
	 */
	public void drawObject(Graphics g) {
		if (x_dir == 1) // animal goes to the right side
			g.drawImage(img1, location.getX() - size / 5, location.getY()
					- size / 10, size / 2, size / 2, pan);
		else { // animal goes to the left side
			g.drawImage(img2, location.getX() - size / 5, location.getY()
					- size / 10, size / 2, size / 2, pan);
		}
	}

	/**
	 * - * load image method
	 */
	public void loadImages(String nm) {
		try {
			this.img1 = ImageIO.read(new File(PICTURE_PATH + nm + "_"
					+ col.charAt(0) + "_1.png"));
			this.img2 = ImageIO.read(new File(PICTURE_PATH + nm + "_"
					+ col.charAt(0) + "_2.png"));
		} catch (IOException e) {
			System.out.println("Cannot load image");
			this.img1 = null;
			this.img2 = null;
		}
	}

	/**
	 * 
	 * @return true or false if there is food
	 */
	public boolean thereIsFood() {
		if (pan.getPlant() == null && pan.getSteak() == null) {
			return false;
		}
		return true;
	}

	/**
	 * update location of animal
	 */
	private void updateLocation() {

		location.setX(location.getX() + horSpeed * x_dir);
		location.setY(location.getY() + verSpeed * y_dir);

		if (location.getX() > pan.getWidth() - size / 4)
			x_dir = -1;
		else if (location.getX() < size * 0.25)
			x_dir = 1;

		if (location.getY() > (int) (pan.getHeight()) - size / 2)
			y_dir = -1;
		else if (location.getY() < (size / 10 + 5))
			y_dir = 1;

		this.setChanges(true);

	}

	/**
	 * make center method - if there is food go to its location
	 */
	private synchronized void makeCenter() {
		int[] myOldSpeed = { horSpeed, verSpeed };
		int v_old = (int) Math.sqrt(horSpeed * verSpeed + verSpeed * verSpeed);
		int v_new = v_old;
		int pit = Math.abs((location.getY() - pan.getHeight() / 2)
				/ (location.getX() - pan.getWidth() / 2));
		int v_hor_new = v_old / (int) Math.sqrt(pit * pit + 1);
		int v_ver_new = v_hor_new * pit;
		v_new = (int) Math.sqrt(v_hor_new * v_hor_new + v_ver_new * v_ver_new);
		if (location.getX() > pan.getWidth() / 2)
			x_dir = -1;
		else
			x_dir = 1;
		if (location.getY() > pan.getHeight() / 2)
			y_dir = -1;
		else
			y_dir = 1;
		if (v_ver_new > 10) {
			v_ver_new = 10;
		} else if (v_ver_new < 1) {
			if (location.getY() != pan.getHeight() / 2)
				v_ver_new = 1;
			else
				v_ver_new = 0;
		}
		if (v_hor_new > 10) {
			v_hor_new = 10;
		} else if (v_hor_new < 1) {
			if (location.getX() != pan.getWidth() / 2)
				v_hor_new = 1;
			else
				v_hor_new = 0;
		}
		location.setX(location.getX() + v_hor_new * x_dir);
		location.setY(location.getY() + v_ver_new * y_dir);
		if ((Math.abs(location.getX() - pan.getWidth() / 2) <= 10)
				&& (Math.abs(location.getY() - pan.getHeight() / 2) <= 10)) {
			pan.theAnimalEatTheFood(this);
			verSpeed = myOldSpeed[0];
			horSpeed = myOldSpeed[1];
		}

		this.setChanges(true);
	}
/**
 * 
 * @return the condition of the animal - run or not with string
 */
	public String getisRunning() {
		return isRun == true ? "Running" : "Not Runing";
	}
/**
 * 
 * @return the condition of the animal - run or not
 */
	public boolean isRunning() {
		return isRun;
	}

	/**
	 * implementation of run method - eating plants
	 */
	public void run() {
		isRun = true;
		while (isRun) {
			try {
				Thread.sleep(100);
				synchronized (this) {
					while (threadSuspended) {
						wait();
					}
				}
			} catch (InterruptedException e) {
				return;
			}
			if (thereIsFood() && this.eatableFood()) {
				makeCenter();
			} else {
				updateLocation();
			}
			 setChanged();
	 		 notifyObservers();
	 		 clearChanged();
	 		 
		}

	}
/**
 * 
 * @param diet to set
 * @return new diet
 */
	public boolean setDiet(IDiet diet) {
		if (diet != null) {
			this.diet = diet;
			return true;
		}
		return false;
	}
/**
 * 
 * @param color to set
 */
	public void setColor(String color) {
		col = color;

	}
/**
 * paint animal function
 */
	public void PaintAnimal(String color) {

		// ColoredAnimalDecorator a= new ColoredAnimalDecorator(this);
		System.out.println("Color changed to: " + color);
		// a.PaintAnimal(color);

	}
/**
 * 
 * @param location2 to calc
 * @return location
 */
	public double calcDistance(Point location2) {
		double dx = this.location.getX() - location2.getX();
		double dy = this.location.getY() - location2.getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		
	}
	
	/**
	 * clone function
	 */
	public Object clone(){
		Object clone = null;
		try{
			clone = super.clone();
		} catch (CloneNotSupportedException e ){
			e.printStackTrace();
		}
		return clone;
	}
	
	
	
	
}
