package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public class ZooFrame extends JFrame implements ActionListener 
{
	private ZooPanel panel=null;
	
/**
 * Main program
 * @param args for main manu 
 */
	public static void main(String[] args){
		try
		{
			ZooFrame frame = new ZooFrame("zoo");
		}
		catch(HeadlessException ex)
		{
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * 
	 * @param t TITLE of the window
	 * @throws HeadlessException if something go wrong
	 */
	public ZooFrame(String t)throws HeadlessException{
		super(t);
		this.setLayout(new BorderLayout(4,3));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLocationRelativeTo(null);
		this.setSize(800, 600);
		
		//to put the window in the center
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//if(panel.getInstance()==null)
			panel = ZooPanel.getInstance(this);
		//else
		//	panel=panel.getInstance();
		
		setMenuBar();
		panel.loadImg();
		
		this.add(panel);
		//show the window Visible
		this.setVisible(true);
	}
	
	/**
	 * 
	 * @return true if menu bar created
	 */
	private boolean setMenuBar()
	{
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("File");
		JMenuItem mi2 = new JMenuItem("Exit");
		m1.add(mi2);
		mb.add(m1);
		mi2.setActionCommand("exit");
		mi2.addActionListener(this);
		
		m1 = new JMenu("Beckground");
		mi2 = new JMenuItem("Image");
		mi2.setActionCommand("Image Beckground");
		mi2.addActionListener(this);
		m1.add(mi2);

		
		mi2 = new JMenuItem("Green");
		m1.add(mi2);
		mi2.setActionCommand("Green Beckground");
		mi2.addActionListener(this);
		
		mi2 = new JMenuItem("None");
		m1.add(mi2);
		mi2.setActionCommand("none Beckground");
		mi2.addActionListener(this);
		mb.add(m1);
		
		m1 = new JMenu("help");
		mi2 = new JMenuItem("help");
		m1.add(mi2);
		mb.add(m1);
		mi2.setActionCommand("Home Work 3\nGUI @ Threads");
		mi2.addActionListener(this);
		
		setJMenuBar(mb);
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand() == "exit"){
			panel.shoutdown();
		}
		if(e.getActionCommand() == "Home Work 3\nGUI @ Threads"){
			JOptionPane.showMessageDialog(this,e.getActionCommand());
		}
		if(e.getActionCommand() == "Green Beckground"){
			panel.removeImge();
			panel.setBackground(null);
			panel.setBackground(Color.green);
		}
		if(e.getActionCommand() == "none Beckground"){
			panel.removeImge();
			panel.setBackground(null);
		}
		if(e.getActionCommand() == "Image Beckground"){
			panel.loadImg();
		}
		
		panel.repaint();
	}
}
