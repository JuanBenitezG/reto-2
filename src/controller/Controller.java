package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo(22);
	}

	//TODO cambio
	//la opción 1 sólo carga los datos y la 2 da las peliculas buenas del director
	public void run() throws IOException 
	{
		BufferedReader lectorfrases = new BufferedReader(new InputStreamReader(System.in));
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();
			int option=0;
			try{
				option = lector.nextInt();
			}
			catch (Exception e) {
				view.printMessage("--------- \n Opcion Invalida, reinicie e ingrese un valor numérico !! \n---------");
				break;
			}
			switch(option){
			case 1:
				view.printMessage("--------- \nCargar los datos de las películas ");
				modelo = new Modelo(7); 
				try {
					modelo.cargarDatos(true);
					view.printMessage("Datos cargados");
					view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");
					modelo.cargarTablaProductoras();
					modelo.cargarTablaGenerosExistentes();
					modelo.cargarTablaDirectores();
					modelo.cargarTablaActores();
					modelo.cargarTablaPaisesExistentes();
					break;
				} catch (Exception e) {
					System.out.println("Los datos no se pudieron cargar");
				}

			case 2: 
				view.printMessage("--------- \n Indique el nombre de la productora"); 
				dato = lectorfrases.readLine();
				view.printMessage("--------- \n Indique el año de lanzamiento"); 
				String resp=lector.next();
				modelo.descucrirProductores(dato, resp);		
				view.printMessage( "\n---------");						
				break;
			
			case 3:
				view.printMessage("--------- \n Indique el género cinematográfico"); 
				modelo.buscarPelciulasGeneroTH(lector.next());
				break;
			case 4:
				view.printMessage("--------- \n Indique el directo a conocer"); 
				dato = lectorfrases.readLine();
				modelo.conocerUnDirector(dato);
				break;
			case 5:
				view.printMessage("--------- \n Indique el actor a conocer"); 
				dato = lectorfrases.readLine();
				modelo.conocerUnActor(dato);
				break;
			case 6:
				view.printMessage("--------- \n Indique pais a conocer"); 
				dato = lectorfrases.readLine();
				modelo.buscarPeliculasPaisesTH(dato);;
				break;
			
			case 7: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				lectorfrases.close();
				fin = true;
				break;	
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}


		}

	}	
}
