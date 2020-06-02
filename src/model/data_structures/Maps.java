package model.data_structures;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.LatLngBounds;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;



public class Maps extends MapView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4545735975738385534L;

	// Objeto Google Maps
	private Map map;

	/**
	 *Constante de la latitud mínima.
	 */
	private final static double LAT_MIN=4.597714;

	/**
	 *Constante de la longitud mínima.
	 */
	private final static double LONG_MIN=-74.094723;

	/**
	 *Constante de la latitud máxima.
	 */
	private final static double LAT_MAX=4.621360;

	/**
	 *Constante de la longitud mínima.
	 */
	private final static double LONG_MAX=-74.062707;

	private GrafoNoDirigido<Integer, GeographicPoint> grafo;
	
	private RedBlackBST<Integer, estacionPolicia> estaciones;
	/**
	 * Visualizacion Google map con camino, marcas, circulos y texto de localizacion
	 * @param idReq
	 */
	public Maps(GrafoNoDirigido<Integer, GeographicPoint> pGraph, RedBlackBST<Integer,estacionPolicia> pEstaciones)
	{	
		pGraph = grafo;
		pEstaciones = estaciones;
		setOnMapReadyHandler( new MapReadyHandler() {
			public void onMapReady(MapStatus status)
			{
				if ( status == MapStatus.MAP_STATUS_OK )
				{
					map = getMap();
					MapOptions options = new MapOptions();
					// Creating a map type control options object
					MapTypeControlOptions controlOptions = new MapTypeControlOptions();
					// Changing position of the map type control
					controlOptions.setPosition(ControlPosition.TOP_RIGHT);
					// Setting map type control options
					options.setMapTypeControlOptions(controlOptions);
					// Setting map options
					map.setOptions(options);
					// Setting the map center
					map.setCenter(new LatLng(4.6097100,-74.0817500 ));
					// Setting initial zoom value
					map.setZoom(13);

					// Configuracion de localizaciones del path (circulos)
					CircleOptions vertexLocOpt= new CircleOptions(); 
					//Configuracion de la linea del camino
					PolylineOptions pathOpt = new PolylineOptions();
					pathOpt.setStrokeColor("#7517D3");	 // color de linea	
					for (int i = 0; i < grafo.getVertexSize(); i++) 
					{
						System.out.println("olamamor");

						double latitud= grafo.getVertex(i).getInfo().getlatitude();
						double longitud= grafo.getVertex(i).getInfo().getLongitude();
						LatLng ubi = new LatLng(latitud, longitud);
						
						if(latitud>=LAT_MIN && latitud<=LAT_MAX && longitud >=LONG_MIN && longitud <=LONG_MAX)
						{
							// Localizacion inicial
							Circle startLoc = new Circle(map);
							startLoc.setOptions(vertexLocOpt);
							startLoc.setCenter(ubi); 
							startLoc.setRadius(7); //Radio del circulo

							Vertex<Integer,GeographicPoint> v = grafo.getVertex(i);
							Iterator<Edge<Integer,GeographicPoint>> it = v.getAdjacencyList().iterator();

							LatLng[] arco = new LatLng[2];
							arco[0]= new LatLng(v.getInfo().getlatitude(), v.getInfo().getLongitude());
							if(!it.hasNext()) throw new NullPointerException("perro estas mureiendo"); 
							while(it.hasNext())
							{
								Edge<Integer,GeographicPoint> a = it.next();
								Polyline path = new Polyline(map); 
								arco[1]= new LatLng(a.getFinalVertex().getInfo().getlatitude(), a.getFinalVertex().getInfo().getLongitude());
								path.setOptions(pathOpt); 
								path.setPath(arco);
								path.setVisible(true);
							} 
						}
						else {
							throw new NullPointerException("no estas fresco pan amiguel");

						}
					}


					// Configuracion de localizaciones del path (circulos)
					CircleOptions verEstaciones= new CircleOptions(); 
					verEstaciones.setFillColor("#FF0000");  // color de relleno
					verEstaciones.setFillOpacity(0.5);
					verEstaciones.setStrokeWeight(1);

					if(estaciones!=null)
					{
						Iterator<estacionPolicia> es = estaciones.keysValue().iterator();

						while(es.hasNext())
						{
							estacionPolicia e = es.next();
							LatLng ubi = new LatLng(e.getLatitude(),e.getLongitude());

							Circle startLoc = new Circle(map);
							startLoc.setOptions(verEstaciones);
							startLoc.setCenter(ubi); 
							startLoc.setRadius(40); //Radio del circulo
						}
					}

					initMap( map );
				}
			}

		} );


	}

	public void initMap(Map map)
	{
		MapOptions mapOptions = new MapOptions();
		MapTypeControlOptions controlOptions = new MapTypeControlOptions();
		controlOptions.setPosition(ControlPosition.BOTTOM_LEFT);
		mapOptions.setMapTypeControlOptions(controlOptions);

		LatLngBounds bounds = new LatLngBounds(new LatLng(LAT_MIN, LONG_MIN), new LatLng(LAT_MAX,LONG_MAX));
		map.setOptions(mapOptions);
		map.fitBounds(bounds);
	}

	public void initFrame()
	{
		JFrame frame = new JFrame("Map");
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}