package model.data_structures;

public class ArregloDinamico <T extends Comparable <T>>  implements Lista <T>, Comparable <ArregloDinamico<T>> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	public T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
        elementos = (T[]) new Comparable [max] ;
        tamanoMax = max;
        tamanoAct = 0;
	}
	public void agregar( T dato )
	{
		tamanoAct++;

		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[])new Comparable [tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			
		}	
		elementos[tamanoAct-1] =  dato;
	}

	// agrega un objeto con la posicion dada


	public void addFirst( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos = (T[])new Comparable [tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			
		}	
		for ( int i = 0; i < tamanoAct; i++){

			elementos[i+1]= elementos[i];
		}
		elementos[0]=dato;
		tamanoAct++;
	}

	@Override
	public void addLast( T dato )
	{
		agregar(dato);
	}

	public T  removeFirst(){
		T r = elementos[0];
		for (int i=0;i<tamanoAct;i++){

			elementos[i]=elementos[i+1];
		}
		elementos[tamanoAct-1]=null;
		tamanoAct--;
		return r;
	}

	public T deleteElement(int pos ){
		T r= elementos[pos];
		for(int i=pos;pos<tamanoAct;pos++  ){
			elementos[pos]=elementos[pos+1];
		}
		elementos[tamanoAct-1]=null;
		tamanoAct--;
		return r;
	}

	public T firstElement(){
		return elementos[0];
	}
	public T lastElement(){
		return elementos[tamanoAct-1];
	}

	public T getElement(int pos){

		return elementos[pos];
	}

	public int darCapacidad() {
		return tamanoMax;
	}


	// borra el primer elemento de la lista

	
	// TODO implementar

	public boolean isEmpty(){
		if(elementos[0]==null){
			return true;
		}
		return false;
	}




	public int buscar(T dato) {

		T buscar=null;
		for(int i=0;i<size();i++){
			buscar=(T) elementos[i];
			if(dato.compareTo(buscar)==0){
				return i ;
			}

		}


		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		return-1;
	}

	public void exchange(int pos, int pos1){
		T cambiar= elementos[pos1] ;
		elementos[pos1]= elementos[pos];
		elementos[pos]= cambiar;

	}

	public void changeInfo(int pos, T elem){
		elementos[pos]= elem;

	}

	

	//borra el ultimo elemento de la lista
	public T removeLast(){
		tamanoAct-=1;
          
		T elemento=elementos[tamanoAct];
		elementos[tamanoAct]=null;
		return elemento;

	}

	


	@Override
	public int size() {

		return tamanoAct;


	}

	@Override

	public int isPresent(T dato) {
		int p=-1;
		T buscar=null;
		for(int i=1;i<size();i++){
			buscar=(T) elementos[i];
			if(dato.compareTo(buscar)==0){
				return i;

			}

		}


		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		return p;
	}
	
	////AYUDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
	@Override
	public void insertElement(T element, int pos) {
		// TODO Auto-generated method stub
		elementos[pos]=element;
		tamanoAct++;
	}
	@Override
	public int compareTo(ArregloDinamico<T> arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public T[] darelementos() {
		// TODO Auto-generated method stub
		return elementos;
	}
	
	
}


