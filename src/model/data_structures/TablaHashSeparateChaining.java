package model.data_structures;

public class TablaHashSeparateChaining <K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos <K, V> {



	/**
	 * Atributos
	 */

	//Arreglo que representa la tabla de hash

	private ArregloDinamico<NodoTablaHash<K,V> > tabla;

	//Tamaño de  la tabla
	private int tamano;

	//numero de duplas en la tabla
	private int size;


	//método constructor de la tabla
	public TablaHashSeparateChaining(int tam){

		tamano = tam;
		tabla = new ArregloDinamico<NodoTablaHash<K,V>> (tamano);
		size = 0;

	}

	public int dartamano(){
		return tamano;
	}
	public int darPosicion(K llave){

		int a = Math.abs(llave.hashCode());

		int m = tamano;

		int posicion = a%m;

		//Asegurar que minimo salga 1, ArregloDinamico		

		return posicion;

	}

	
	
	@Override
	public void put(K llave, V valor) {
		// TODO Auto-generated method stub
		int pos = darPosicion(llave);
		
		NodoTablaHash n1 = new NodoTablaHash (llave, valor);
		if(tabla.getElement(pos)==null){

			tabla.insertElement(n1, pos);
			size++;
		}
		else{

			NodoTablaHash n2 = tabla.getElement(pos);		
			while(n2.darSiguiente()!=null){
				n2=n2.darSiguiente();
			}
			n2.cambiarSiguiente(n1);
			size++;

		}

	}

	@Override
	public V get(K llave) {

		int pos = darPosicion(llave);
		
		NodoTablaHash n = tabla.getElement(pos);
		
		if(n==null){
			return null;
		}
		boolean buscando = true;

		while(buscando){
			int comparacion = llave.compareTo((K) n.darLlave());

			if(comparacion == 0){

				buscando = false;
				return (V) n.darValor();
			}

			else if(n.darSiguiente()==null){

				buscando = false;
			}

			else{
				n = n.darSiguiente();
			}
		}

		return null;


	}
	
	

	@Override
	public V remove(K llave) {
		// TODO Auto-generated method stub

		int pos = darPosicion(llave);

		NodoTablaHash n = tabla.getElement(pos);
		V retornar =null;
		if(llave.compareTo((K) n.darLlave())==0){
			retornar=(V)n.darValor();
			if(n.darSiguiente()==null){
				tabla.changeInfo(pos, null);
			}
			else{
				NodoTablaHash s=n;
				s.cambiarAnterior(null);
				tabla.changeInfo(pos,s.darSiguiente());
			}
		}
		else{
			while(n.darSiguiente()!=null){
				n=n.darSiguiente();
				if(llave.compareTo((K) n.darLlave())==0){
					retornar=(V)n.darValor();
					if(n.darSiguiente()==null){
						tabla.changeInfo(pos, null);
					}
					else{
						NodoTablaHash s=n;
						s.cambiarAnterior(null);
						tabla.changeInfo(pos,s.darSiguiente());
				}
			}
		}
		}
		size--;
		return retornar;
	}




	@Override
	public boolean contains(K llave) {
		// TODO Auto-generated method stub

		int pos = darPosicion(llave);

		NodoTablaHash n = tabla.getElement(pos);

		boolean buscando = true;

		while(buscando){

			int comparacion = llave.compareTo((K) n.darLlave());

			if(comparacion == 0){

				buscando = false;
				return true;
			}

			else if(n.darSiguiente()==null){

				buscando = false;
				return false;
			}

			else{
				n = n.darSiguiente();
			}
		}



		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size == 0){
			return true;
		}
		else
		{
			return false;
		}


	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Lista<K> keySet() {
		// TODO Auto-generated method stub
		ArregloDinamico <K> llaves = new ArregloDinamico <K>(size);

		for (int i = 0; i < tamano; i++) {

			if(tabla.getElement(i) != null){

				NodoTablaHash n = tabla.getElement(i);

				llaves.addLast( (K) n.darLlave()); 

				boolean buscando = true;

				while(buscando){

					if(n.darSiguiente() != null){
						n = n.darSiguiente();
						llaves.addLast( (K) n.darLlave()); 
					}
					else{
						buscando = false;
					}

				}
			}
		}

		return llaves;
	}

	@Override
	public Lista<V> valueSet() {
		// TODO Auto-generated method stub

		ArregloDinamico <V> valores = new ArregloDinamico <V>(size);


		for (int i = 0; i < tamano; i++) {



			if(tabla.getElement(i) != null){

				NodoTablaHash n = tabla.getElement(i);

				valores.addLast( (V) n.darValor()); 

				boolean buscando = true;

				while(buscando){

					if(n.darSiguiente() != null){
						n = n.darSiguiente();
						valores.addLast( (V) n.darValor());  
					}
					else{
						buscando = false;
					}

				}
			}


		}

		return valores;


	}

}
