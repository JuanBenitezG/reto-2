package model.data_structures;

public class TablaDeHashLinearProbing  <K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos <K,V>{

	/**
	 * Atributos
	 */
	
	/**
	 * Arreglo que representa la tabla
	 */
	private ArregloDinamico< NodoTablaHash <K , V >> tabla;
	
	
	/**
	 * Numero de elementos presentes en la tabla
	 */
	
	private int ocupacion;
	
	/**
	 * Numero de espacios en la tabla
	 */
	
	private int tamano = 1;
	
	
	private static double FACTOR_MAXIMO_DE_CARGA = 0.75;
	
	
	
	
	/**
	 * Numero actual de rehash-es
	 * 
	 */
	private int rehashes;
	
	
	public TablaDeHashLinearProbing(int tam)
	{
		ocupacion = 0;
		rehashes = 0;
		tamano = tam;
		tabla = new ArregloDinamico< NodoTablaHash <K , V >>(tamano);
		inicializarTablaNull(tabla);
		
	}
	
	/**
	 * llena la tabla de valores null para identificar buckets libres
	 */
	 public void inicializarTablaNull(ArregloDinamico <NodoTablaHash <K , V >> pTabla){
		 
		 for (int i = 0; i < pTabla.size(); i++) {
			 pTabla.insertElement(null, i);
		}
	 }
	
	
	@Override
	public void put(K llave, V valor) {
		// TODO Auto-generated method stub
		
		int pos = darPosicion(llave);
		
		NodoTablaHash n1 = new NodoTablaHash(llave, valor);
		
		if(tabla.getElement(pos) != null)
		{
			//variable que indica el limite de la busqueda
			
			//variable que indica cuando parar
			boolean posVacia = true;
			
			//variable que indica el limite de la busqueda
			int limiteSuperior = tabla.size();
			//variable que indica donde inicia la busqueda
			int limiteInferior = pos+1;
			
			if(pos == tabla.size()-1){
				
				limiteInferior = 0;
			}
			
			for (int i = limiteInferior; i < limiteSuperior && posVacia; i++) {
					
					if(tabla.getElement(i) == null){
						
						tabla.insertElement(n1, pos);
						
						ocupacion++;
						
						posVacia = false;
					}
					
					else if(i == tabla.size()-1 && posVacia){
						 limiteInferior = 0;
						 limiteSuperior = pos;
					}
			}
		}
		
		else
		{
			tabla.insertElement(n1, pos);
			
			ocupacion++;
		}
		
	
		
		if(darFactorActualCarga() > FACTOR_MAXIMO_DE_CARGA){
			
			rehash();
		}
		
	}
	
	
	public double darFactorActualCarga(){
		return ocupacion/tamano;
	}
	
	
	
	public int darPosicion(K llave){

		int a = (llave.hashCode() & 0x7fffffff);

		int m = tamano;

		int posicion = a%m;

		//Asegurar que minimo salga 1, ArregloDinamico		

		return posicion;

	}
	
	
	@Override
	public V get(K llave) {

		int pos = darPosicion(llave);
		
		if(tabla.getElement(pos)== null){
			return null;
		}
		else{
			return tabla.getElement(pos).darValor();
		}
		
		
	}

	@Override
	public V remove(K llave) {
		
		V valorGuardado = null;
		
		int pos = darPosicion(llave);
		
		if(tabla.getElement(pos)== null){
			return null;
		}
		else{
			
			valorGuardado = tabla.getElement(pos).darValor();
			tabla.insertElement(null, pos);
		}
		
		return valorGuardado;
	}

	@Override
	public boolean contains(K llave) {
		
		int pos = darPosicion(llave);
		
		if(tabla.getElement(pos) == null){
			return false;
		}
		else{
			return true;
		}
		
		
	}

	@Override
	public boolean isEmpty() {
	
		return (ocupacion == 0);
	}

	/**
	 * Ocupacion de la tabla
	 */
	@Override
	public int size() {
		
		return ocupacion;
	}
	
	/**
	 * Tamaño de la tabla
	 */
	public int darTamano(){
		return tamano;
	}
	
	@Override
	public Lista<K> keySet() {
		
		ArregloDinamico<K> llaves = new ArregloDinamico<K>(ocupacion);
		
		K llaveActual = null;
		
		for (int i = 0; i < tabla.size(); i++) {

			if(tabla.getElement(i) != null){
				
				llaveActual = tabla.getElement(i).darLlave();
				
				llaves.addLast(llaveActual);
			}
			
		}
		
		return llaves;
		
	}

	@Override
	public Lista<V> valueSet() {
		
		ArregloDinamico<V> valores = new ArregloDinamico<V>(ocupacion);
		
		V valorActual = null;
		
		for (int i = 0; i < tabla.size(); i++) {

			if(tabla.getElement(i) != null){
				
				valorActual = tabla.getElement(i).darValor();
				
				valores.addLast(valorActual);
			}
			
		}
		
		return valores;
	}
	
	
	
	
	
	public void rehash()
	{
		rehashes++;
		
		int nuevoTam = proximoPrimo(tamano*2);
		
		tamano = nuevoTam;
		
		ocupacion = 0;
		
		ArregloDinamico <NodoTablaHash <K,V>> nuevaTabla = new ArregloDinamico <NodoTablaHash <K,V>>(nuevoTam);
		
		inicializarTablaNull(nuevaTabla);
		
		NodoTablaHash actual = null;
		
		
		for (int i = 0; i < tabla.size(); i++) {
			
			actual = tabla.getElement(i);
			
			if(actual != null){
				
				int nuevaUbicacion = darPosicion((K)actual.darLlave());
				
				nuevaTabla.insertElement(actual, nuevaUbicacion);
				
				ocupacion++;
				
			}
			
		}
		
		tabla = nuevaTabla;
		
		//dado el caso que el nuevoTam no fue suficiente volveria a realizar rehash
		
		if(darFactorActualCarga() > FACTOR_MAXIMO_DE_CARGA){
			
			rehash();
		}
		
		
		
	}
	
	public int darNumRehashes(){
		
		return rehashes;
	}
	
	
	
	public int proximoPrimo(int n){
		
		boolean buscando = true;
		
		for(int i=n; i <(n)*(n) && buscando ; i++ ){
			
			if(esPrimo(i)){
				buscando = false;
				return i;
			}
		}
		
		return n;
	}
	
	public boolean esPrimo(int x){
		
		for(int i=x-1; i>1; i--){
			if((x % i) == 0 ){
				return false;
			}
		}
		return true;
	}

}