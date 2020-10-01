package model.data_structures;

import com.sun.org.apache.xalan.internal.xsltc.dom.FilteredStepIterator;

import model.logic.pelicula;

public interface Lista <T extends Comparable<T>>   {

	void addFirst(T element);
	void addLast(T element);
	void insertElement(T element, int pos);
	T removeFirst( );
	T removeLast( );
	T deleteElement( int pos);
	T firstElement( );
	T lastElement();
	T getElement( int pos);
	int size( );
	boolean isEmpty( );
	int isPresent (T element);
	void exchange (int pos1, int pos2);
	void changeInfo (int pos, T elem);
	T[] darelementos();
}
