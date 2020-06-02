package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    public void printMenu()
		{
			System.out.println("1. Cargar informacion comparendos, vertices y arcos en el grafo");
			System.out.println("2. Cargar informacion estaciones de policia");
			System.out.println("3. Generar mapa.");
			System.out.println("4. Generar Mapa con estaciones de policia.");
			System.out.println("5. Guardar grafo en un archivo de formato Json.");
			System.out.println("6. Cargar grafo previamente guardado.");
			System.out.println("7. Exit");
			System.out.println("------------------------------------------------------------------------");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
			System.out.println("------------------------------------------------------------------------");

		}
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			for( int i = 0; i<0; i++){
				System.out.println("");
				
			}
		}
}
