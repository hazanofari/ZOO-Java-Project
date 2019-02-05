package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.imageio.ImageIO;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.*;

import design.ZooMemento;
import design.ZooObserver;
import meat.Steak;
import food.EFoodType;
import plants.Cabbage;
import plants.Lettuce;
import plants.Plant;
import animals.Animal;
import graphics.IDrawable;

import java.awt.*;

/**
 * @author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class ZooPanel extends JPanel implements ActionListener {
	// data member of class ZooPanel

	private ZooObserver controller;
	private static ArrayList<Animal> animallist;
	private static Plant plant = null;
	private static Steak steak = null;
	private int TotalEatCount = 0;
	private JPanel panel1;
	private JPanel panel2;
	// private JPanel panel3;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	// decorate
	private JButton button8;
	private JButton button9;
	private JComboBox animal_clone;
	private ZooFrame frame;
	private BufferedImage img = null;
	final int MAX_RUNNING_ANIMALS = 5;
	final int MAX_ANIMALS = 10;
	static private ZooPanel instance = null;
	// Pool
	Executor pool = Executors.newFixedThreadPool(MAX_RUNNING_ANIMALS);
	// private Animal an;
	private static ArrayList<Animal> natural_array;
	private Animal[] animals;
	private DecorateDialog pic_dialog;
	private DuplicateDialog duplication;
	// memento
	private JButton button10;
	private JButton button11;
	private List<ZooMemento> mementoList = new ArrayList<ZooMemento>();
	private ZooMemento state;

	/**
	 * @return the totalEatCount
	 */
	public int getTotalEatCount() {
		return TotalEatCount;
	}

	/**
	 * @param totalEatCount
	 *            the totalEatCount to set
	 */
	public void setTotalEatCount(int totalEatCount) {
		TotalEatCount = totalEatCount;
	}

	/**
	 * 
	 * @return animal list
	 */
	public static ArrayList<Animal> getAnimallist() {
		return animallist;
	}
/**
 * 
 * @return natural animals list
 */
	public static ArrayList<Animal> getNaturalAnimallist() {
		return natural_array;
	}

	/**
	 * 
	 * @param f
	 *            - main frame
	 */
	private ZooPanel(ZooFrame f) {

		// copy the reference;
		frame = f;
		animallist = new ArrayList<Animal>();

		// creation of Thread controller
		controller = new ZooObserver(this);

		// start of Thread controller
		controller.start();

		setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout());

		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 10, 0,0));
		
		

		button1 = new JButton("Add Animal");
		button2 = new JButton("Sleep");
		button3 = new JButton("Wake Up");
		button4 = new JButton("Clear");
		button5 = new JButton("Food");
		button6 = new JButton("Info");
		
		button8 = new JButton("Decorate");
		button9 = new JButton("Duplicate");
		button10 = new JButton("Save state");
		button11 = new JButton("Restor state");
		button7 = new JButton("Exit");

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		button10.addActionListener(this);
		button11.addActionListener(this);

		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		panel2.add(button5);
		panel2.add(button6);
		
		panel2.add(button8);
		panel2.add(button9);
		panel2.add(button10);
		panel2.add(button11);
		panel2.add(button7);

	
		add(panel2, "South");


	}

	/**
	 * 
	 * @param a
	 *            - animal that eats
	 */
	public void callback(Animal a) {
		synchronized (this) {
			a.eatInc();
			TotalEatCount++;
		}
	}

	/**
	 * 
	 * @param a
	 *            -animal that eats food
	 */
	public void theAnimalEatTheFood(Animal a) {
		a.eatInc();
		synchronized (this) {
			this.TotalEatCount++;
		}
		plant = null;
		steak = null;
	}

	/**
	 * 
	 * @return steak
	 */
	public Steak getSteak() {
		return steak;
	}

	/**
	 * 
	 * @return plant
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * 
	 * @param a
	 *            - animal that added to panel
	 */
	public synchronized void addAnimal(Animal a) {
		Future<?> task = ((ExecutorService) pool).submit(a);
		a.setTask(task);
		animallist.add(a);
		a.setCont(controller);
		System.out.println(a.getAnimalName() + "+" + a.getColor());

	}
/**
 * 
 * @return how many animals is running at this moment
 */
	public int HowManyRun() {
		int count_run = 0;
		int count_not_run = 0;
		for (Animal animal : animallist) {
			if (animal.isRunning()) {
				count_run++;
			} else {
				count_not_run++;
			}
		}
		// System.out.print("run:"+count_run +" not run:"+count_not_run);
		return count_run;
	}

	/**
	 * loadimg method
	 */
	public void loadImg() {
		try {
			img = ImageIO.read(getClass().getResourceAsStream(
					"/pictures/savanna.jpg"));
		} catch (IOException e) {
			System.out.println("Cannot load image");
		}
	}

	/**
	 * remove img method
	 */
	public void removeImge() {
		img = null;

	}

	/**
	 * paint component method
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (img != null) {
			this.setBackground(null);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
		synchronized (this) {
			for (Animal animal : animallist) {
				if (animal != null && animal.isRunning() == true) {
					animal.drawObject(g);
				}
			}
		}

		if (plant != null) {
			plant.drawObject(g);
		}
		if (steak != null) {
			steak.drawObject(g);
		}
	}
/**
 * function for shoutdown thread in pool
 */
	public void shoutdown() {
		((ExecutorService) pool).shutdown();
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == button1) {
			Object[] options = { "Carnivore", "Herbivore", "Omnivore" };
			int n = JOptionPane.showOptionDialog(frame,
					"please choose animal factory.", "animal factory",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			AddAnimalDialog dial = new AddAnimalDialog(frame, this,
					"Add an animal to Zoo", n);
			dial.setVisible(true);

		}

		if (event.getSource() == button2) {
			for (Animal animal : animallist) {
				if (animal.isRunning())
					animal.setSuspended();
			}
		}
		if (event.getSource() == button3) {
			for (Animal animal : animallist) {
				if (animal.isRunning())
					animal.setResumed();
			}
		}
		if (event.getSource() == button4) {
			// synchronized (this) {
			// int slot;
			// for (slot = Math.min(MAX_RUNNING_ANIMALS, animallist.size());
			// slot > 0; slot--) {
			// if (animallist.get(slot - 1).isRunning()) {
			// animallist.get(slot - 1).killer();
			// animallist.remove(slot - 1);
			// }
			//
			// }
			// // animallist.clear();
			// TotalEatCount = 0;
			// this.repaint();
			// }
			Clear();
		}
		if (event.getSource() == button5) {

			String[] options = { "Meat", "Cabbage", "Lettuce" };
			int choise = JOptionPane.showOptionDialog(null,
					"Please choose food", "Click a button",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			switch (choise) {
			case 0:
				plant = null;
				steak = Steak.getInstance(this);
				break;
			case 1:
				steak = null;
				plant = Cabbage.getInstance(this);
				break;
			case 2:
				steak = null;
				plant = Lettuce.getInstance(this);
				break;
			}
		}
		if (event.getSource() == button6) {
			JFrame f = new JFrame();

			int index_animal = 0;

			String data[][] = { { "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" },
					{ "", "", "", "", "", "", "" } };

			for (int i = 0; i < animallist.size() + 1; i++) {
				int j = 0;
				if (index_animal >= animallist.size()) {
					data[i][0] = "Total: " + animallist.size();
					data[i][1] = "Run:  " + HowManyRun() + "  NotRun:  "
							+ (animallist.size() - HowManyRun());
					data[i][2] = "---------";
					data[i][3] = "---------";
					data[i][4] = "---------";
					data[i][5] = "---------";
					data[i][6] = "" + TotalEatCount;
					break;
				}
				data[i][j++] = animallist.get(index_animal).getAnimalName();
				data[i][j++] = animallist.get(index_animal).getisRunning();
				data[i][j++] = animallist.get(index_animal).getColor();
				data[i][j++] = animallist.get(index_animal).getWeight() + "";
				data[i][j++] = animallist.get(index_animal).getHorSpeed() + "";
				data[i][j++] = animallist.get(index_animal).getVerSpeed() + "";
				data[i][j++] = animallist.get(index_animal).getEatCount() + "";
				index_animal++;
			}

			// "state",
			String column[] = { "Animal", "State", "Color", "Weight",
					"Hor.speed", "Ver.speed", "Eat counter" };

			JTable jt = new JTable(data, column);
			jt.setBounds(30, 40, 200, 300);
			JScrollPane panel_crit = new JScrollPane(jt);
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			f.setLocation(dim.width / 2 - f.getSize().width / 2, dim.height / 2
					- f.getSize().height / 2);
			f.add(panel_crit);
			f.setSize(500, 400);
			f.setVisible(true);
		}
		if (event.getSource() == button7) {
			for (Animal animal : animallist) {
				animal.killer();
				AddAnimalDialog.decAnimalCounter();
			}
			animallist.clear();
			shoutdown();

		}

		if (event.getSource() == button8) {
			deco();
		}

		if (event.getSource() == button9) {
			if (animallist.size() == 0) {
				JOptionPane.showMessageDialog(this,
						"no animal to duplicate !");
				return;
			}
			if (animallist.size() == 10) {
				JOptionPane.showMessageDialog(this,
						"there is already 10 animals !");
				return;
			}
			duplication = new DuplicateDialog("", this);
		}
		if (event.getSource() == button10) {
			if (mementoList.size() == 3) {
				JOptionPane.showMessageDialog(this,
						"You have  already saved 3 states !");
			} else {
				Steak s = null;
				Plant p = null;

				ArrayList<Animal> new_list = new ArrayList<Animal>();
				Animal an;
				for (Animal a : animallist) {
					an = (Animal) a.clone();
					an.setLocation(a.getLocation());
					new_list.add(an);

				}

				if (steak != null)
					s = steak.getInstance(this);
				if (plant != null)
					p = Lettuce.getInstance(this);

				state = new ZooMemento(new_list, s, p);
				addMemento(state);
				JOptionPane.showMessageDialog(this, "Saved !");

			}
		}
		if (event.getSource() == button11) {
			if (mementoList.size() == 0) {
				JOptionPane.showMessageDialog(this,
						"You have not saved states !");
			} else {
				String[] options = { "State 1", "State 2", "State 3", "Cancel" };
				int choise = JOptionPane.showOptionDialog(null,
						"Please choose state", "Click a button",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, options,
						options[0]);
				switch (choise) {
				case 0:
					getMemento(0);
					mementoList.remove(0);
					break;
				case 1:
					if(mementoList.size()<=1)
					{
						JOptionPane.showMessageDialog(this,"You dont have this state saved !");
					}
					else{
					getMemento(1);
					mementoList.remove(1);
					}
					break;
				case 2:
					if(mementoList.size()<=2)
					{
						JOptionPane.showMessageDialog(this,"You dont have this state saved !");
					}
					else{
					getMemento(2);
					mementoList.remove(2);
					}
					break;
				case 3:
					break;
				}
				//repaint();
			}
		}
	}
/**
 * function making decoration 
 */
	synchronized public void deco() {

		boolean flag = false;
		natural_array = null;
		natural_array = new ArrayList<Animal>();
		for (Animal a : animallist) {
			if (a.getColor().equalsIgnoreCase("natural")) {
				flag = true;
				natural_array.add(a);
			}
		}
		if (!flag) {
			JOptionPane.showMessageDialog(null,
					"you have not animals for decoration", "Message",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		animals = new Animal[natural_array.size()];
		animals = natural_array.toArray(animals);
		pic_dialog = new DecorateDialog("pic color", this);

	}
/**
 * function for singleton of zoopanel
 * @param f according to constructor 
 * @return instance
 */
	public static ZooPanel getInstance(ZooFrame f) {
		if (instance == null)
			synchronized (ZooPanel.class) {
				if (instance == null)
					instance = new ZooPanel(f);
			}

		return instance;
	}
/**
 * add of memento to list
 * @param state to know what state to add
 */
	synchronized public void addMemento(ZooMemento state) {
		int i;
		if (mementoList.size() >= 3) {
			for (i = 0; i < mementoList.size() - 1; i++) {
				mementoList.set(i, mementoList.get(i + 1));
			}
			mementoList.set(i, state);
		} else if (mementoList.size() < 3)
			mementoList.add(mementoList.size(), state);

	}
/**
 * function for getting specific memento
 * @param index by index
 */
	synchronized public void getMemento(int index) {
		Clear();
		Clear();
		ZooMemento ss = mementoList.get(index);
		for (Animal a : ss.getAninalList()) {
			addAnimal(a);
		}
		steak = ss.getSteak();
		plant = ss.getPlant();



	}
/**
 * clear function of the screen
 */
	public void Clear() {
		synchronized (this) {
			int slot;
			for (slot = Math.min(MAX_RUNNING_ANIMALS, animallist.size()); slot > 0; slot--) {
				if (animallist.get(slot - 1).isRunning()) {
					animallist.get(slot - 1).killer();
					animallist.remove(slot - 1);
				}
			}
			this.steak = null;
			this.plant = null;
			//animallist.clear();
			TotalEatCount = 0;
			this.repaint();
		}
	}
/**
 *  
 * @param slot for index
 * @return specific animal by index
 */
	public static Animal getAnimallistByIndex(int slot) {
		return animallist.get(slot);
	}
}
