package model.data_structures;

public class GeographicPoint implements Comparable<GeographicPoint> {
	
	private double longitude;
	private double latitude;
	
	
	public GeographicPoint(double pLongitude, double pLaititude) {
		// TODO Auto-generated constructor stub
		longitude=pLongitude;
		latitude=pLaititude;
	}
	
	public double getlatitude(){
		return latitude;
	}
	public double getLongitude(){
		return longitude;
	}
	
	@Override
	public int compareTo(GeographicPoint arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
