package model.data_structures;

public class estacionPolicia implements Comparable<estacionPolicia>{
	
	private int id;
	private String epoDescription;
	private String epoDir_Sitio;
	private String epoLatitude;
	private String epoLongitude;
	private String epoServicio;
	private String epoNombre;
	private String epoIdentifi;
	private double latitude;
	private double longitude;
	
	
	public estacionPolicia(int id2, String pDesc, String pDir_Sitio, String Latitud, String Longitud, String pServicio, String pNombre, String pIdentifi) {
		// TODO Auto-generated constructor stub
		id = id2;
		epoDescription= pDesc;
		epoDir_Sitio= pDir_Sitio;
		epoLatitude= Latitud;
		epoLongitude=Longitud;
		epoServicio= pServicio;
		epoNombre= pNombre;
		epoIdentifi= pIdentifi;
		latitude = Double.parseDouble(Latitud);
		longitude = Double.parseDouble(Longitud);

		
		
	}
	
	public int getId(){
		return id;
	}
	public String getDescription(){
		return epoDescription;
	}
	public String getDirection(){
		return epoDir_Sitio;
	}
	public String getlatitude(){
		return epoLatitude;
	}
	public String getlongitude(){
		return epoLongitude;
	}
	public String getService(){
		return epoServicio;
	}
	public String getName(){
		return epoNombre;
	}
	public String getIdentifier(){
		return epoIdentifi;
	}
	public double getLatitude(){
		return latitude;
	}
	public double getLongitude(){
		return longitude;
	}
	
	
	
	
	
	
	
	@Override
	public int compareTo(estacionPolicia o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
