package graphics;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public interface IAnimalBehavior 
{
	public String getAnimalName();
	public int getSize();
	public void eatInc();
	public int getEatCount();
	public boolean getChanges ();
	public void setSuspended();
	public void setResumed();
	public boolean setChanges (boolean state);
}
