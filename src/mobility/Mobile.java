package mobility;


/**
 *@author Eli Moshkovich 308240019 && Nofar Hazan 205774094
 *
 */
public abstract class Mobile implements ILocatable {

	protected Point location;
	private double totalDistance;
/**
 * Constructor of class
 * @param location to set
 */
	public Mobile(Point location) {
		this.setNewLocation(location);
		this.setTotalDistance(0);
	}
	/**
	 * default Constructor
	 */
	public Mobile(){};

	/**
	 * @param distance
	 * @return True if addition is successful
	 * @see Mobile#setTotalDistance(double)
	 */
	private boolean addToTotalDistance(double distance) {
		return this.setTotalDistance(distance + this.totalDistance);
	}

	/**
	 * @param location to calc
	 * @return distance between objects current location and given location
	 */
	public double calcDistance(Point location) {
		double dx = this.location.getX() - location.getX();
		double dy = this.location.getY() - location.getY();
		return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}

	@Override
	public Point getLocation() {
		return location;
	}

	/**
	 * @return The total distance
	 */
	public double getTotalDistance() {
		return totalDistance;
	}

	/**
	 * Moves the object to a new location
	 * 
	 * @param newLocation
	 *            - The new location
	 * @return The distance the object traveled (0 if did not move)
	 */
	public double move(Point newLocation) {
		double distance = 0;
		if (Point.checkBoundaries(newLocation)) {
			distance = this.calcDistance(newLocation);
			this.addToTotalDistance(distance);
			this.setNewLocation(newLocation);
		}
		return distance;
	}

	@Override
	public boolean setNewLocation(Point newLocation) {
		if (Point.checkBoundaries(newLocation)) {
			this.location = new Point(newLocation);
			return true;
		}
		if (newLocation == null) {
			this.location = new Point(0, 0);
			return false;
		}
		return false;
	}

	/**
	 * @param totalDistance
	 * @return True if assignment is successful
	 */
	private boolean setTotalDistance(double totalDistance) {
		if (utilities.Validators.IsPositive(totalDistance)) {
			this.totalDistance = totalDistance;
			return true;
		}
		return false;
	}
}
