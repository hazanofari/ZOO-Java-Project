package graphics;

import java.awt.Graphics;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public interface IDrawable 
{
	public final static String PICTURE_PATH = "C:/Users/Eli/workspace/HW3_NEWNEW/src/pictures//";
	public void loadImages(String nm);
	public void drawObject (Graphics g);
	public String getColor();
}
