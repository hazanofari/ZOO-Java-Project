package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import diet.AbstractZooFactory;
import animals.Animal;
import animals.Bear;
import animals.Elephant;
import animals.Giraffe;
import animals.Lion;
import animals.Turtle;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class AddAnimalDialog extends JDialog implements ActionListener
{
	private static String[] animals_her = {"Elephant","Giraffe","Turtle"};
	private static String[] animals_car = {"Lion"};
	private static String[] animals_omn= {"Bear"};
	private String checker;
	private static int animalCounter = 0;
	private JComboBox AnimalComboBox;
	private JTextField sizeField;
	private JTextField verticalSpeedField;
	private JTextField horizontalSpeedField;
	private JComboBox animalColorBox;
	private JButton okButton;
	private static ZooPanel refToZooPanel;
	private ZooFrame refToZooFrame;
	private int animalSize = 0;
	private int verticalSpeed = 0;
	private int horizontalSpeed = 0;
	private int btn_n;
	private String [] factory_name={"meat","plants","plants meat"};
	
	
	/**
	 * 
	 * @param refFrame ref of zooframe
	 * @param refPan ref of zoopanel
	 * @param title to know animal name
	 * @param n to set
	 */
	public AddAnimalDialog(ZooFrame refFrame,ZooPanel refPan,String title,int n)
	{
		refToZooPanel = refPan;
		refToZooFrame = refFrame;
		
		this.btn_n=n;
		setTitle(title);
		setResizable(false);
		this.setSize(300, 400);
		setLayout(new BorderLayout());
		//to put the window in the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//animalCounter = refToZooPanel.getAnimallist().size();
		animalCounter = refToZooPanel.getAnimallist().size();
		setDialogItems();
	}
	
	/**
	 * 
	 * @return true or false id dialog succeeded
	 */
	private boolean  setDialogItems()
	{
		JPanel enterAnimalPanel = new JPanel(new GridLayout(11,0));
		JLabel animalType = new JLabel("animal type:");
		enterAnimalPanel.add(animalType);
		if(btn_n==0){
		AnimalComboBox = new JComboBox(animals_car);
		enterAnimalPanel.add(AnimalComboBox);
		}
		else if(btn_n==1){
			AnimalComboBox = new JComboBox(animals_her);
			enterAnimalPanel.add(AnimalComboBox);
			}
		else if(btn_n==2){
			AnimalComboBox = new JComboBox(animals_omn);
			enterAnimalPanel.add(AnimalComboBox);
			}
		JLabel animalSize = new JLabel("animal size (50-300):");
		enterAnimalPanel.add(animalSize);
		
		sizeField = new JTextField();
		enterAnimalPanel.add(sizeField);
		
		JLabel verticalSpeed = new JLabel("vertical speed, pixel per second");
		enterAnimalPanel.add(verticalSpeed);
		
		verticalSpeedField = new JTextField();
		enterAnimalPanel.add(verticalSpeedField);
		
		JLabel horizontalSpeed = new JLabel("horizontal speed, pixel per second");
		enterAnimalPanel.add(horizontalSpeed);
		
		horizontalSpeedField = new JTextField();
		enterAnimalPanel.add(horizontalSpeedField);
		
		JLabel animalColorLabel = new JLabel("animal color:");
		enterAnimalPanel.add(animalColorLabel);
		
		String[] optionalColors ={"natural","red","blue"};
		animalColorBox = new JComboBox(optionalColors);
		enterAnimalPanel.add(animalColorBox);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		enterAnimalPanel.add(okButton);
		
		enterAnimalPanel.setBorder(BorderFactory.createTitledBorder(""));
		enterAnimalPanel.setPreferredSize(new Dimension(300, 200));
		add(enterAnimalPanel,"Center");
		return true;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
	
		String erorrMessage = "";
		
		if(e.getSource() == okButton)
		{
			try
			{
				animalSize = Integer.parseInt(sizeField.getText()); 
				verticalSpeed = Integer.parseInt(verticalSpeedField.getText()); 
				horizontalSpeed = Integer.parseInt(horizontalSpeedField.getText());
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(this,"wrong input try again");
				return;
			}
			
			if(animalSize > 300 || animalSize < 50)
			{
				erorrMessage += "animal size have to be between 50-300\n";
			}
			if(verticalSpeed<1 || verticalSpeed > 10)
			{
				erorrMessage += "vertical speed need to be between 1 - 10\n";
			}
			if(horizontalSpeed<1 || horizontalSpeed > 10)
			{
				erorrMessage += "horizontal speed need to be between 1 - 10\n";
			}
			
			if(erorrMessage != "")
			{
				JOptionPane.showMessageDialog(this,erorrMessage);
				return;
			}
			
			String color = ""; 
			if (ZooPanel.getAnimallist().size() >= 10)
			{
				JOptionPane.showMessageDialog(this,"there are all ready 10 animals");
				return;
			}
			
			AbstractZooFactory f=AbstractZooFactory.createAnimalFactory(factory_name[btn_n]);
			Animal an=f.produceAnimal(this.AnimalComboBox.getSelectedItem().toString());
			an.setParams(this.AnimalComboBox.getSelectedItem().toString(),animalSize,horizontalSpeed,verticalSpeed,animalColorBox.getSelectedItem().toString(),refToZooPanel);
			an.setWeight();
			AddAnimalDialog.refToZooPanel.addAnimal(an);
			animalCounter = ZooPanel.getAnimallist().size();
			
			
			}
		}
		
	

	
	/**
	 * animal counter 
	 */
	public static void decAnimalCounter(){
		animalCounter = ZooPanel.getAnimallist().size();
	}
}
