/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
package meat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mobility.ILocatable;
import mobility.Point;
import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;

public class Steak implements IEdible,IDrawable {

	private BufferedImage img;
	private Point location;
	private ZooPanel pan;
	static private Steak instance=null;
	/**
	 * Constructor of class Steak
	 * @param refToZooPanel ref to zoopanel
	 */
	private Steak(ZooPanel refToZooPanel){
		pan = refToZooPanel;
		loadImages(null);
		int x = (pan.getWidth() - img.getWidth(null)) / 2;
		int y = (pan.getHeight() - img.getHeight(null)) / 2;
		location = new Point(x + 175,y + 100);
	}
	
	@Override
	public void loadImages(String nm) {
		try {
			this.img = ImageIO.read(new File(PICTURE_PATH + "meat.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public void drawObject(Graphics g) {
		g.drawImage(img, location.getX(), location.getY(), 50,50, pan);
	}
	@Override
	public String getColor() {
		return null;
	}
	@Override
	public EFoodType getFoodType() {
		return EFoodType.MEAT;
	}
	
	
	
	/**
	 * Steak is singleton
	 * @param refToZooPanel ref to zoopanel
	 * @return instance 
	 */
	public static Steak getInstance(ZooPanel refToZooPanel){
		if (instance==null)
			synchronized (ZooPanel.class){
				if(instance==null)
					instance= new Steak(refToZooPanel);
			}
		return instance;
	}
	
	
}
