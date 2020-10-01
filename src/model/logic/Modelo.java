package model.logic;

import model.data_structures.ArregloDinamico;





import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVReader;
import model.data_structures.Lista;
import model.data_structures.NodoTablaHash;
import model.data_structures.TablaDeHashLinearProbing;
import model.data_structures.TablaHashSeparateChaining;


public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private Lista <String> datos;
	private Lista<pelicula> datosArreglo;
	private TablaHashSeparateChaining <String, ArregloDinamico<pelicula>> tablaSCHproductoras;
	private TablaHashSeparateChaining <String, ArregloDinamico<pelicula>> tablaSCHdirectores;
	private TablaHashSeparateChaining <String, ArregloDinamico<pelicula>> tablaGeneros;
	private TablaHashSeparateChaining <String, ArregloDinamico<pelicula>> tablaActores;
	private TablaHashSeparateChaining <String, ArregloDinamico<pelicula>> tablaPaises;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ArregloDinamico(7);
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		tablaSCHproductoras = new TablaHashSeparateChaining<>(27941);
		tablaGeneros = new TablaHashSeparateChaining(601);
		tablaSCHdirectores = new TablaHashSeparateChaining(601);
		tablaActores = new TablaHashSeparateChaining(601);
		tablaPaises = new TablaHashSeparateChaining(109);

		datos = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datosArreglo.size();
	}
	public  void cargarDatos(boolean print1) throws  Exception{
		long to= System.nanoTime();
		CSVReader csvReader = new CSVReader(new FileReader( "./data/SmallMovies.csv"));
		CSVReader csvReader2 = new CSVReader(new FileReader( "./data/MoviesCastingRaw-small.csv"));
		String []nextLine;
		String [] nextLine2;
		datosArreglo= new ArregloDinamico<pelicula>(5000);
		csvReader.readNext() ;
		csvReader2.readNext();
		String[] por2=null;
		String[] por=null;

		while((nextLine=csvReader.readNext())!=null&& (nextLine2= csvReader2.readNext())!=null){

			por=nextLine[0].split(";");
			por2= nextLine2[0].split(";");
			pelicula in= new pelicula(Integer.parseInt(por[0]), por[1], por[2], por[3], por[4], por[5], 
					por[6], por[7], por[8], por[9], por[10], por[11], por[12], por[13],
					por[14], por[15], por[16],
					Float.parseFloat(por[17]) 	,Integer.parseInt(por[18]), Integer.parseInt(por[19]), Integer.parseInt(por[20]), Integer.parseInt(por[21]),por2[1],por2[3],por2[5],por2[7],por2[9],por2[12]);
			datosArreglo.addLast(in);
		}
		csvReader.close();
		long tf=System.nanoTime();
		double tiempof=(double)(tf-to)*1.0e-9;
		if(print1){
			System.out.println(" Los datos se demoraron en cargar:  "+tiempof );
			System.out.println(" A comparación del arreglo dinámico que se demora 0.3896163 " );
		}
	}
	public void cargarTablaProductoras() {
		try {

			for(int c=0;c<datosArreglo.size();c++){
				ArregloDinamico<pelicula> prod = new ArregloDinamico<pelicula>(7);
				String compañia= datosArreglo.getElement(c).getProduction_companies();
				String aux=datosArreglo.getElement(c).getRelease_date();
				String año= aux.substring(aux.length()-4);
				String llave= compañia.trim()+"-"+año.trim();
				if(datosArreglo.getElement(c)!=null){
					for (int j=c;j<datosArreglo.size();j++){
						if(datosArreglo.getElement(j).getProduction_companies().contains(compañia)&&datosArreglo.getElement(j).getRelease_date().substring(6).contains(año)){
							prod.addLast(datosArreglo.getElement(j));
							datosArreglo.deleteElement(j);
						}
					}
					tablaSCHproductoras.put(llave,prod );		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cargarDatos(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void descucrirProductores(String pProductora, String pAño) {

		String busqueda=pProductora+"-"+pAño;
		try {
			double promedio=0;



			ArregloDinamico<pelicula> ar= tablaSCHproductoras.get(busqueda);

			for(int i=0;i<ar.size();i++){
				System.out.println(ar.getElement(i).getTitle());
				promedio+=ar.getElement(i).getVote_average();
			}
			System.out.println("El número de buenas películas de "+pProductora+" en el año "+pAño+" es: "+ar.size()+" y su promedio de votos es: "+(promedio/ar.size()));
			
			
		} catch (Exception e) {
			System.out.println("La productora no tiene peliculas en ese año o no existe");
		}
	}

	public void cargarTablaActores() {
		try{
			ArregloDinamico <String> actoresExistentes = new ArregloDinamico<String>(2000);

			//carga en un arreglo los generosExistentes para obtener las llaves 
			int contadore=0;
			boolean buscando=true;

			for (int i = 0; i < datosArreglo.size(); i++) {
				for(int j=0;j<5;j++){
					buscando=true;
					if(datosArreglo.getElement(i).getActorN(j)!=null){
						String actorAct=datosArreglo.getElement(i).getActorN(j);
						for(int m=0;m<actoresExistentes.size()&&buscando;m++){
							if(actorAct.equals(actoresExistentes.getElement(m))){
								buscando=false;
							}
						}
						if(buscando){
							actoresExistentes.addLast(actorAct);
						}
					}

				}
			}


			for (int i = 0; i < actoresExistentes.size(); i++) {

				ArregloDinamico <pelicula> seleccionadas = new ArregloDinamico<pelicula>(10);

				String actorActual = actoresExistentes.getElement(i);

				for (int j = 0; j < datosArreglo.size(); j++) {

					pelicula p = datosArreglo.getElement(j);

					for(int n=0;n<5;n++)
						if(actorActual.equals(p.getActorN(n))){
							seleccionadas.addLast(p);
						}
				}
				tablaActores.put(actorActual, seleccionadas);

			}
		}	
		catch (Exception e) {
			System.out.println("DAÑAO");
		}
	}



	public void conocerUnActor(String pActor){
		long to= System.nanoTime();

		double voteAverage = 0;

		if(tablaActores.get(pActor) != null){
			ArregloDinamico <pelicula> peliculasActor =  (ArregloDinamico<pelicula>) tablaActores.get(pActor);
			System.out.println("Las peliculas del actor "+ pActor +" son:");
			for (int i = 0; i < peliculasActor.size(); i++) {

				pelicula actual = peliculasActor.getElement(i);
				System.out.println(actual.getTitle());
				voteAverage+= actual.getVote_average();
			}
			System.out.println("El total de peliculas es: "+peliculasActor.size());
			System.out.println("El promedio de votos del director es: "+voteAverage/peliculasActor.size());
		}

		
		else{
			System.out.println("Actor no encontrado");
		}
		long tf=System.nanoTime();
		double tiempof=(double)(tf-to)*1.0e-9;
		
			System.out.println(" Conocer un actor se demora:  "+tiempof );
			System.out.println(" A comparación del arreglo dinámico que se demora: 0.003353  " );
		
	}

	public void cargarTablaDirectores() {
		try{
			ArregloDinamico <String> directoresExistentes = new ArregloDinamico<String>(2000);

			//carga en un arreglo los generosExistentes para obtener las llaves 

			for (int i = 0; i < datosArreglo.size(); i++) {

				String actual = datosArreglo.getElement(i).getDirector();

				boolean buscando = true;

				for (int j = 0; j < directoresExistentes.size() && buscando; j++) {

					if(actual.equals(directoresExistentes.getElement(j))){
						buscando = false;
					}	
				}

				if (buscando){
					directoresExistentes.addLast(actual);
				}


			}

			for (int i = 0; i < directoresExistentes.size(); i++) {

				ArregloDinamico <pelicula> seleccionadas = new ArregloDinamico<pelicula>(100);

				String directorActual = directoresExistentes.getElement(i);

				for (int j = 0; j < datosArreglo.size(); j++) {

					pelicula p = datosArreglo.getElement(j);

					if(directorActual.equals(p.getDirector())){

						seleccionadas.addLast(p);
					}
				}
				tablaSCHdirectores.put(directorActual, seleccionadas);
			}
		}
		catch (Exception e) {
			e.getStackTrace();
		}
	}



	public void conocerUnDirector(String pDirector){
		long to= System.nanoTime();

		double voteAverage = 0;

		if(tablaSCHdirectores.get(pDirector) != null){
			ArregloDinamico <pelicula> peliculasDirector =  (ArregloDinamico<pelicula>) tablaSCHdirectores.get(pDirector);
			System.out.println("Las peliculas del director "+ pDirector +" son:");
			for (int i = 0; i < peliculasDirector.size(); i++) {

				pelicula actual = peliculasDirector.getElement(i);
				System.out.println(actual.getTitle());
				voteAverage+= actual.getVote_average();
			}
			System.out.println("El total de peliculas es: "+peliculasDirector.size());
			System.out.println("El promedio de votos del director es: "+voteAverage/peliculasDirector.size());
		}


		else{
			System.out.println("Director no encontrado");
		}
		long tf=System.nanoTime();
		double tiempof=(double)(tf-to)*1.0e-9;
		
			System.out.println(" Conocer un director se demora:  "+tiempof );
			System.out.println(" A comparación del arreglo dinámico que se demora: 0.0028719 " );
		
	}




	public void cargarTablaGenerosExistentes(){

		ArregloDinamico <String> generosExistentes = new ArregloDinamico<String>(580);

		//carga en un arreglo los generosExistentes para obtener las llaves 

		for (int i = 0; i < datosArreglo.size(); i++) {

			String actual = datosArreglo.getElement(i).getGenres();

			boolean buscando = true;

			for (int j = 0; j < generosExistentes.size() && buscando; j++) {

				if(actual.equals(generosExistentes.getElement(j))){
					buscando = false;
				}	
			}

			if (buscando){
				generosExistentes.addLast(actual);
			}


		}

		for (int i = 0; i < generosExistentes.size(); i++) {

			ArregloDinamico <pelicula> seleccionadas = new ArregloDinamico<pelicula>(10);

			String generoActual = generosExistentes.getElement(i);

			for (int j = 0; j < datosArreglo.size(); j++) {

				pelicula p = datosArreglo.getElement(j);

				if(generoActual.equals(p.getGenres())){
					seleccionadas.addLast(p);
				}
			}
			tablaGeneros.put(generoActual, seleccionadas);

		}
	}



	public void buscarPelciulasGeneroTH(String genre){
		long to= System.nanoTime();

		double voteAverage = 0;

		if(tablaGeneros.get(genre) != null){

			ArregloDinamico <pelicula> peliculasGenero =  (ArregloDinamico<pelicula>) tablaGeneros.get(genre);

			System.out.println("Las peliculas del genero son:");
			for (int i = 0; i < peliculasGenero.size(); i++) {

				pelicula actual = peliculasGenero.getElement(i);
				System.out.println(actual.getTitle());
				voteAverage+= actual.getVote_average();
			}
			System.out.println("El total de peliculas es: "+peliculasGenero.size());
			System.out.println("El promedio de votos del género es: "+voteAverage/peliculasGenero.size());
		}


		else{
			System.out.println("Género no encontrado");
		}
		long tf=System.nanoTime();
		double tiempof=(double)(tf-to)*1.0e-9;
		
			System.out.println(" Buscar películas de un género se demora:  "+tiempof );
			System.out.println(" A comparación del arreglo dinámico que se demora: 0.025123700000000002 " );
		
	}
	public void cargarTablaPaisesExistentes(){
		
		ArregloDinamico <String> paisesExistentes = new ArregloDinamico<String>(50);
	
		//carga en un arreglo los paises existentes para obtener las llaves 
	
			for (int i = 0; i < datosArreglo.size(); i++) {
				
				String actual = datosArreglo.getElement(i).getProduction_countries();
				
				boolean buscando = true;
				
				for (int j = 0; j < paisesExistentes.size() && buscando; j++) {
					
					if(actual.equals(paisesExistentes.getElement(j))){
						buscando = false;
					}	
				}
				
				if (buscando){
					paisesExistentes.addLast(actual);
				
				}		
			}
			
			
			for (int i = 0; i < paisesExistentes.size(); i++) {
				
				ArregloDinamico <pelicula> seleccionados = new ArregloDinamico<pelicula>(10);
				
				String paisActual = paisesExistentes.getElement(i);
				
				for (int j = 0; j < datosArreglo.size(); j++) {
					
					 pelicula p = datosArreglo.getElement(j);
					 
					 if(paisActual.equals(p.getProduction_countries())){
						 seleccionados.addLast(p);
					 }
				}
				tablaPaises.put(paisActual, seleccionados);
				
			}
	}
			public void buscarPeliculasPaisesTH(String pais){
				
				
				if(tablaPaises.get(pais) != null){
					
					ArregloDinamico <pelicula> peliculasPais = (ArregloDinamico<pelicula>) tablaPaises.get(pais);
					
					System.out.println("Las peliculas del país son:");
					for (int i = 0; i < peliculasPais.size(); i++) {
						
						pelicula actual = peliculasPais.getElement(i);
						System.out.println("Título: "+actual.getTitle()+"  "+
						"Año de producción: "+actual.getRelease_date().substring(actual.getRelease_date().length()-4, actual.getRelease_date().length())+
						"  "+"Director: "+actual.getDirector());
					}
					
				}else{
					System.out.println("Pais no encontrado");
				}
			}


}
