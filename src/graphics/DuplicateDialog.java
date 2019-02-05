///**
// * 
// */
//package graphics;
//
//import java.awt.Color;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.ArrayList;
//
//import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//import javax.swing.JSlider;
//
//import mobility.Point;
//import animals.Animal;
//import design.ColoredAnimalDecorator;
//
///**
// * @author Eli
// *
// */
//public class DuplicateDialog extends JDialog implements ItemListener,
//		ActionListener {
//	private static final int speed_MIN = 1;
//	private static final int speed_MAX = 10;
//	private static final int speed_INIT = 10;
//	private ArrayList<Animal> all_animals;
//	private JPanel p1, p2, p3, dummyPanel;
//	private JButton ok;
//	private JComboBox AnimList;
//	private  JSlider horizontal_speed;
//	private  JSlider vertical_speed;
//	private Animal saveAnimal;
//	private ButtonGroup bg;
//	protected ZooPanel pan;
//	private Animal a;
//
//	public DuplicateDialog(String title, ZooPanel panel) {
//		super(new JFrame(), title, true);
//		pan = panel;
//		all_animals = pan.getAnimallist();
//
//		setSize(700, 200);
//
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
//				/ 2 - this.getSize().height / 2);
//
//		p1 = new JPanel();
//		p2 = new JPanel();
//		p3 = new JPanel();
//		dummyPanel = new JPanel();
//
//		p1.setLayout(new GridLayout(4, 1));
//		AnimList = new JComboBox(all_animals.toArray());
//		
//		AnimList.setSelectedIndex(0);
//		AnimList.addItem("No Animal");
//		AnimList.addActionListener(this);
//
//		p1.add(AnimList);
//		p1.add(dummyPanel);
//		p1.add(dummyPanel);
//
//		ok = new JButton("OK");
//		ok.addActionListener(this);
//		ok.setBackground(Color.lightGray);
//		p1.add(ok);
//
//		p3.setLayout(new GridLayout(1, 2, 10, 10));
//
//		p1.setBorder(BorderFactory
//				.createTitledBorder("Select animal to clone"));
//		p2.setBorder(BorderFactory
//				.createTitledBorder("speed may be changed"));
//
//		p2.setLayout(new GridLayout(1, 2));
//		bg = new ButtonGroup();
//
//		 horizontal_speed = new JSlider(JSlider.HORIZONTAL,
//				speed_MIN, speed_MAX, speed_INIT);
//		//horizontal_speed.addChangeListener(this);
//		horizontal_speed.setMajorTickSpacing(10);
//		horizontal_speed.setMinorTickSpacing(1);
//		horizontal_speed.setPaintTicks(true);
//		horizontal_speed.setPaintLabels(true);
//		Font font1 = new Font("Serif", Font.ITALIC, 15);
//		horizontal_speed.setFont(font1);
//		
//		 vertical_speed = new JSlider(JSlider.HORIZONTAL,
//				speed_MIN, speed_MAX, speed_INIT);
//		//horizontal_speed.addChangeListener(this);
//		vertical_speed.setMajorTickSpacing(10);
//		vertical_speed.setMinorTickSpacing(1);
//		vertical_speed.setPaintTicks(true);
//		vertical_speed.setPaintLabels(true);
//		Font font2 = new Font("Serif", Font.ITALIC, 15);
//		vertical_speed.setFont(font2);
//		
//		p3.add(p1);
//		p3.add(p2);
//		p2.add(vertical_speed);
//		p2.add(horizontal_speed);
//		add(p3);
//		
//		
//		setVisible(true);
//
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == ok ) {
//					saveAnimal = all_animals.get(AnimList.getSelectedIndex());
//					Animal new_animal= (Animal) saveAnimal.clone();
//					new_animal.setLocation(new Point(0,0));
//					new_animal.setHorSpeed(horizontal_speed.getValue());
//					new_animal.setVerSpeed(vertical_speed.getValue());
//					pan.addAnimal(new_animal);
//					//
//					if(all_animals.size()>5)
//						((Animal)new_animal).setRun(false);
//					
//						
//					System.out.println(((Animal)new_animal).isRunning());
//					((Animal)new_animal).setResumed();
//					//
//					setVisible(false);
//				
//		}
//	}
//
//	@Override
//	public void itemStateChanged(ItemEvent arg0) {
//	}
//}
//
//	
//
//
/**
 * 
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import mobility.Point;
import animals.Animal;
import design.ColoredAnimalDecorator;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class DuplicateDialog extends JDialog implements ItemListener,
		ActionListener {

	private ArrayList<Animal> all_animals;
	private JPanel mainPanel, speedPanel, selectPanel;
	private JButton ok;
	private JComboBox AnimList;
	private JSlider horizontal_speed;
	private JSlider vertical_speed;
	private Animal saveAnimal;
	private ButtonGroup bg;
	protected ZooPanel pan;
	private Animal a;
	private JLabel lbl_hor;
	private JLabel lbl_ver;
/**
 * Constructor
 * @param title to know what kind of decoration
 * @param panel ref to zoopanel
 */
	public DuplicateDialog(String title, ZooPanel panel) {
		super(new JFrame(), "Duplicate an animal", true);
		pan = panel;
		all_animals = pan.getAnimallist();

		setSize(900, 300);
		setResizable(false);

		setBackground(new Color(100, 230, 255));
		mainPanel = new JPanel();
		add(mainPanel);

		mainPanel.setLayout(new GridLayout(1, 2));

		selectPanel = new JPanel();
		selectPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Select Animal to clone"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		selectPanel.setLayout(new BorderLayout());

		AnimList = new JComboBox<String>();
		AnimList = new JComboBox(all_animals.toArray());
		AnimList.addItem("No animal");
		AnimList.addActionListener(this);
		selectPanel.add("North", AnimList);

		ok = new JButton("OK");
		ok.addActionListener(this);
		selectPanel.add("South", ok);
		mainPanel.add(selectPanel);

		speedPanel = new JPanel();
		speedPanel.setLayout(new GridLayout(4, 1));
		speedPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Speed may be changed..."),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		lbl_hor = new JLabel("Horizontal speed", JLabel.CENTER);
		speedPanel.add(lbl_hor);

		horizontal_speed = new JSlider(1, 10);
		horizontal_speed.setMajorTickSpacing(2);
		horizontal_speed.setMinorTickSpacing(1);
		horizontal_speed.setPaintTicks(true);
		horizontal_speed.setPaintLabels(true);
		speedPanel.add(horizontal_speed);

		lbl_ver = new JLabel("Vertical speed", JLabel.CENTER);
		speedPanel.add(lbl_ver);

		vertical_speed = new JSlider(1, 10);
		vertical_speed.setMajorTickSpacing(2);
		vertical_speed.setMinorTickSpacing(1);
		vertical_speed.setPaintTicks(true);
		vertical_speed.setPaintLabels(true);
		speedPanel.add(vertical_speed);

		mainPanel.add(speedPanel);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			saveAnimal = all_animals.get(AnimList.getSelectedIndex());
			if (saveAnimal != null) {
				Animal new_animal = (Animal) saveAnimal.clone();
				new_animal.setLocation(new Point(0, 0));
				new_animal.setHorSpeed(horizontal_speed.getValue());
				new_animal.setVerSpeed(vertical_speed.getValue());
				pan.addAnimal(new_animal);
				if (all_animals.size() > 5)
					((Animal) new_animal).setRun(false);
				System.out.println(((Animal) new_animal).isRunning());
				((Animal) new_animal).setResumed();
				setVisible(false);
			}
		} else {

	    	int index;
	    	if((index = AnimList.getSelectedIndex()) != 0) {
	    		try { a = pan.getAnimallistByIndex(index-1); } 
	    		catch (Exception e1) 
	    		{ System.out.println("Duplicate error!"); a = null; }
		    	if(a!=null) {
		    		horizontal_speed.setValue(a.getHorSpeed());
		    		vertical_speed.setValue(a.getVerSpeed());
		    	}
	    	}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
	}
}
