package adanadanMiamiye;

public class City {
	private String name;
	private int latitude;
	private int longitude;
	private String country;
	private int population;
	
	//Constructur
	public City(String name, int latitude, int longitude, String country, int population) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.population = population;
	}
	
	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
