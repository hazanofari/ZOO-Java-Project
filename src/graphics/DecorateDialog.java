package graphics;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import design.ColoredAnimalDecorator;
import animals.Animal;
/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class DecorateDialog extends JDialog implements ItemListener, ActionListener{
	
	private ArrayList<Animal> natural_an;
	private JPanel p1,p2,p3,dummyPanel;
    private JButton ok;
    private JComboBox AnimList;
    private JRadioButton[] rb;
    private String saveColor;
    private String saveAnimal;
    private ButtonGroup bg;
    protected ZooPanel pan;
    private Animal an;
	/**
	 * Constructor
	 * @param title to know what kind of dialog
	 * @param panel ref of zoopanel
	 */
	public DecorateDialog(String title,ZooPanel panel){
		super(new JFrame(),title,true);
		pan=panel;
		natural_an = pan.getNaturalAnimallist();
		an = null;
    	setSize(900,300);
    	setResizable(false);
    	
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		dummyPanel = new JPanel();
		
		p1.setLayout(new GridLayout(4,1));
		AnimList = new JComboBox(natural_an.toArray());
		AnimList.setSelectedIndex(0);
		AnimList.addActionListener(this);

		p1.add(AnimList);		
		p1.add(dummyPanel);
		p1.add(dummyPanel);
		
		ok = new JButton("OK");
		ok.addActionListener(this);
		ok.setBackground(Color.lightGray);
		p1.add(ok);	
		
		p3.setLayout(new GridLayout(1,2,10,10));
		
		p1.setBorder(BorderFactory.createTitledBorder("Select animal to decorate"));
		p2.setBorder(BorderFactory.createTitledBorder("Choose decoration color"));
		
		p2.setLayout(new GridLayout(1,3));
		rb = new JRadioButton[2];
		bg = new ButtonGroup();
		
	    rb[0] = new JRadioButton("Red",false);
	    rb[0].addItemListener(this);
	    rb[0].setName("red");
	    bg.add(rb[0]);
	    p2.add(rb[0]);
	    
	    rb[1] = new JRadioButton("Blue",false);
	    rb[1].addItemListener(this);
	    rb[1].setName("blue");
	    bg.add(rb[1]);
	    p2.add(rb[1]);
		
		p3.add(p1);
		p3.add(p2);
		
		add(p3);
		
		setVisible(true);
	}

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		saveAnimal = AnimList.getSelectedItem().toString();
		
		if(e.getSource() == ok && saveColor != null){
			for(Animal a : natural_an){
				System.out.println(a.toString() + saveAnimal);
				if(a.toString().equals(saveAnimal)){
					//a.PaintAnimal(saveColor);
					ColoredAnimalDecorator an= new ColoredAnimalDecorator(a);
					an.PaintAnimal(saveColor);
					break;
				}
			}
			
			setVisible(false);
		}
		
	}
	
	/**
	 *  function to string
	 */
	public String toString(){
		
		return saveAnimal.toString();
		
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		for(int i=0;i<rb.length;i++){
			if(rb[i].isSelected())
		    {
				saveColor = rb[i].getName();
				break;
	        }
			
		}
	}
}
