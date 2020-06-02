package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import com.sun.glass.events.ViewEvent;

import model.data_structures.Comparendo;
import model.data_structures.GeographicPoint;
import model.data_structures.GrafoNoDirigido;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;
	private static final String vertexPath= "./data/bogota_vertices.txt";
	private static final String EdgesPath= "./data/bogota_arcos.txt";
	private static final String EstacionesPath= "./data/estacionpolicia.geojson.json";
	private static final String GraphSavepath = "./data/grafoImpreso.json";


	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws ParseException 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;


		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				view.printMessage("------------------------------------------------------------------------\n Se esta cargando la informacion \n------------------------------------------------------------------------");
				Comparendo maximo= modelo.cargarInfoComparendos();
				try {
					modelo.cargarInfoVertex(vertexPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					view.printMessage("El archivo de carga de vertices no fue encontrado o no pudo ser leido");;
				}
				try {
					modelo.cargarInfoEdges(EdgesPath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					view.printMessage("El archivo de carga de arcos no fue encontrado o no pudo ser leido");;
				}

				view.printMessage("Se cargaron "+ modelo.getGraphRead().getVertexSize()+" vertices");
				view.printMessage("Se cargaron "+ modelo.getGraphRead().getEdgeSize()+" arcos");

				if(maximo!=null){
					view.printMessage("El comparendo con mayor ID es:" + maximo.darID()+ "\n "+maximo.darFecha() + "\n "+ maximo.darLocalidad()+ "\n "+ maximo.darInfraccion()  );


				}
				break;
			case 2:
				view.printMessage("------------------------------------------------------------------------\n Se esta cargando la informacion con las estaciones de policia: \n------------------------------------------------------------------------");
				modelo.cargarInfoEstacionesPolicia(EstacionesPath);
				view.printMessage("El numero de estaciones de policia cargadas es: "+ modelo.getEstacionesDePolicia().giveSize());
				break;

			case 3:

				view.printMessage("------------------------------------------------------------------------\n Se esta generando el mapa: \n------------------------------------------------------------------------");
				modelo.generateMap();
				break;
			case 4:

				view.printMessage("Se esta generando el mapa con las estaciones de policia");
				modelo.generateMapWithPoliceStations();
				break;
			case 5:

				view.printMessage("Guardar grafo (persistir en un archivo JSON) ");
				try 
				{
					modelo.saveGraphJson(GraphSavepath);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					view.printMessage("El archivo de impresion del grafo no pudo ser creado");
				};
				view.printMessage("El grafo fue guardado en un archivo en formato Json. La ruta del archivo es"+ GraphSavepath);
				break;
			case 6:

				view.printMessage("Cargar Grafo (Solo disponible si el grafo fue previamente guardado en un archivo formato JSON)");
				try 
				{
					modelo.loadGraphJson(GraphSavepath);
					GrafoNoDirigido<Integer, GeographicPoint> graphLoaded = modelo.getGraphWrite();
					view.printMessage("El numero de vertices del grafo cargado es: "+ graphLoaded.getVertexSize() );
					view.printMessage("El numero de arcos del grafo cargado es: "+ graphLoaded.getEdgeSize() );

				} catch (IOException e) {
					// TODO Auto-generated catch block
					view.printMessage("El archivo: "+ GraphSavepath+" no pudo ser encontrado.");
				}
				view.printMessage("El grafo fue cargado correctamente");
				break;

			case 7:
				view.printMessage("El programa fue cerrado.");
				lector.close();
				fin=true;
				break;

			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}

		}


		lector.close();

	}	
}