package mobility;

/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public interface ILocatable {

	/**
	 * @return The current location
	 */
	public Point getLocation();
/**
 * set new location for animal
 * @param location to set
 * @return location
 */
	public boolean setNewLocation(Point location) ;
}