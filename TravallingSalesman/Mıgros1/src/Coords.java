/**
 * This class is used to create Coordinates for all places
 * @author Ömer Oðuz
 *
 */
public class Coords {
	
	
	private double xCoord;
	private double yCoord;
	
	public Coords(double xCoord, double yCoord) {
		super();
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**
	 * This returns the current x coordinate of this place
	 * @return This city's x Coordinate
	 */
	public double getxCoord() {
		return xCoord;
	}
	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}
	
	/**
	 * This returns the current y coordinate of this place
	 * @return This city's x Coordinate
	 */
	public double getyCoord() {
		return yCoord;
	}
	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}
	
}
