package plants;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import utilities.Validators;
import food.EFoodType;
import food.IEdible;
import graphics.IDrawable;
import graphics.ZooPanel;
import mobility.ILocatable;
import mobility.Point;

/**
*@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
*
*/
public abstract class Plant implements IEdible, ILocatable,IDrawable 
{
	private BufferedImage img;
	private double height;
	private Point location;
	private double weight;
	private ZooPanel pan;
	static private Plant instance=null;
	private String loadImg;
	/**
	 * @return the loadImg
	 */
	public String getLoadImg() {
		return loadImg;
	}
	/**
	 * @param loadImg the loadImg to set
	 */
	public void setLoadImg(String loadImg) {
		this.loadImg = loadImg;
	}
	/**
	 * Constructor
	 * @param myImg to load
	 * @param refToZooPanel ref of zoopanel
	 */
	public Plant(String myImg,ZooPanel refToZooPanel) {
		
		this.loadImg=myImg;
		pan = refToZooPanel;
		loadImages(myImg);
		int x = (pan.getWidth() - img.getWidth(null)) / 2;
		int y = (pan.getHeight() - img.getHeight(null)) / 2;
		location = new Point(x + img.getHeight()/3,y + img.getWidth()/3);
		//this.loadImg=myImg;
	}
	// singleton
	public Plant() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void drawObject(Graphics g){
		g.drawImage(img, location.getX(), location.getY(), img.getHeight()/3,img.getWidth()/3, pan);
	}
	@Override
	public void loadImages(String nm) {
		// TODO Auto-generated method stub
		try {
			this.img = ImageIO.read(new File(PICTURE_PATH + nm + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public EFoodType getFoodType() {
		return EFoodType.VEGETABLE;
	}

	/**
	 * @return The height of the plant
	 */
	public double getHeight() {

		return this.height;
	}

	@Override
	public Point getLocation() {
		return this.location;
	}

	/**
	 * @return The weight of the plant
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param newHeight - the new height
	 * @return True if assignment is successful
	 */
	public boolean setHeight(double newHeight) {

		if (Validators.IsPositive(newHeight)) {
			this.height = newHeight;
			return true;
		}
		this.height = 0;

		return false;
	}

	@Override
	public boolean setNewLocation(Point newLocation) {
		if (Point.checkBoundaries(newLocation)) {
			this.location = new Point(newLocation);
			return true;
		}

		this.location = new Point(0, 0);
		return false;
	}

	/**
	 * @param newWeight - the new weight
	 * @return True if assignment is successful
	 */
	public boolean setWeight(double newWeight) {

		if (Validators.IsPositive(newWeight)) {
			this.weight = newWeight;
			return true;
		}
		this.weight = 0;

		return false;
	}

	@Override
	public String toString() {
		return "[" + this.getClass().getSimpleName() + "] ";
	}
	



}
