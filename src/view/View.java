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
			System.out.println("1. Cargar datos");
			System.out.println("2. Encontrar películas de una productora");
			System.out.println("3. Conocer un género cinematográfico ");
			System.out.println("4. Conocer un director ");
			System.out.println("5. Conocer un actor ");
			System.out.println("6. Conocer un pais ");
			System.out.println("7 .  Exit ");
			
			
			
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
