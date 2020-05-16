package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.data_structures.Comparendo;
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
				
				view.printMessage("Se cargaron "+ modelo.getGraph().getVertexSize()+" vertices");
				view.printMessage("Se cargaron "+ modelo.getGraph().getEdgeSize()+" arcos");

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
//				view.printMessage("------------------------------------------------------------------------\n Ingrese el ID minimo que desea buscar: \n------------------------------------------------------------------------");
//				int IdMinimo = lector.nextInt();
//				view.printMessage("------------------------------------------------------------------------\n Ingrese el ID maximo que desea buscar: \n------------------------------------------------------------------------");
//				int IdMaximo = lector.nextInt();
//				Queue<Comparendo>datos= modelo.comparendosEnRango(IdMinimo, IdMaximo);
//				while(!datos.isEmpty()){
//					Comparendo buscado1=datos.dequeue();
//					view.printMessage("------------------------------------------------------------------------\n El comparendo encontrado es:\n" +buscado1.darID() +" \n "  +buscado1.darFecha() +"\n " + buscado1.darTipoServicio()+"\n "+ buscado1.darClaseVehiculo()+"\n"+buscado1.darInfraccion()+"\n"+"------------------------------------------------------------------------");
//
//				}

				break;
			case 4:
				
//				view.printMessage(modelo.giveSizeRedBlackBST()+" Es el tamanio del RedBlackBST ");
//				view.printMessage(modelo.giveHeight()+" es la altura del RedBlackBST ");
//				view.printMessage(modelo.alturaPromedio()+" es la altura promedio del RedBlackBST \n \n \n");
//				break;
			case 5:
//				view.printMessage("El programa fue cerrado.");
//				lector.close();
//				fin=true;
				break;

			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}

		}


		lector.close();

	}	
}