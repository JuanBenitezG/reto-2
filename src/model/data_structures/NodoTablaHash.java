package model.data_structures;

public class NodoTablaHash <K , V > implements Comparable <NodoTablaHash<K,V>>{
	
     K llave;
     V valor;
 	private int Size;
     
     //Siguente Nodo
     NodoTablaHash<K,V>   siguiente;
     
     //Anterior Nodo
     NodoTablaHash<K,V>   anterior;

     
     public NodoTablaHash(K pllave, V pvalor){
    	 
    	 llave = pllave;
    	 valor = pvalor; 
    	 siguiente = null;
    	 anterior = null;
     }

   

     public NodoTablaHash darSiguiente(){
    	
    	 return siguiente;
     }
     
     public void cambiarSiguiente( NodoTablaHash next){
    	 
    	 siguiente = next;
     }
     
     public NodoTablaHash darAnterior(){
     	
    	 return anterior;
     }
     
     public void cambiarAnterior( NodoTablaHash last){
    	 
    	 anterior = last;
     }
     
     
     
     
    public K darLlave(){
    	return llave;
    }
    
    public V darValor(){
    	return valor;
    }

    public void cambiarValor(V value1){
    	valor = value1;
    }
     
	@Override
	public int compareTo(NodoTablaHash<K, V> o) {
		
		if(this.llave.equals(o.darLlave()) )
		{
			return 0;
		}
		else
		{
			return 1;
		}
		
	}


	
}
