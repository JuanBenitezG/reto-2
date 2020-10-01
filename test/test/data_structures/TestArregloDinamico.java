package test.data_structures;

import model.data_structures.ArregloDinamico;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=25;

	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);

	}

	public void setUp2() {

		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(i);
		}
	}

	@Test
	public void testArregloDinamico() {
		setUp1();
		assertNotNull( "el arreglo dinamico no debería ser nulo.", arreglo);
		assertEquals("El número de datos es incorrecto.", 0 ,arreglo.size());
		// TODO
	}

	@Test
	public void testgetElement() {


		setUp2();
		arreglo.getElement(0);
		Comparable comp3=0;
		assertEquals("Se esperaba otro elemento",comp3,arreglo.getElement(0));


		// TODO
	}

	@Test
	public void exchange(){

		setUp2();
		System.out.println(""+ arreglo.getElement(1));
		System.out.println(""+ arreglo.getElement(17));

		arreglo.exchange(1, 17);
		assertEquals("El dato intecambiado es incorrecto.", 17 ,arreglo.getElement(1));
		assertEquals("El dato intecambiado es incorrecto.", 1 ,arreglo.getElement(17));

	}
	
	
	
	
	
	
	@Test
	public void addLast(){
		setUp2();
		Comparable comp= 772;
		arreglo.addLast(comp);
		assertEquals("El dato intecambiado es incorrecto.",comp ,arreglo.lastElement());
	}
	
	
	public void addfIRST(){
		setUp2();
		Comparable comp= 772;
		arreglo.addFirst(comp);
		assertEquals("El dato intecambiado es incorrecto.",comp ,arreglo.firstElement());
	}
	
	
	@Test
	public void getElement(){
		setUp2();
		Comparable comp= 772;
		arreglo.insertElement(comp, 5);


		assertEquals("El dato intecambiado es incorrecto.",comp ,arreglo.getElement(5));

	}
	@Test
	public void insertElement(){
		setUp2();
		Comparable comp= 772;
		arreglo.agregar(comp);


		assertEquals("El dato intecambiado es incorrecto.",comp ,arreglo.lastElement());	
	}
	@Test
	public void firstElement(){

		setUp2();
		Comparable comp= arreglo.firstElement();;

		assertEquals("El dato intecambiado es incorrecto.",0 ,arreglo.firstElement());	
	}

	@Test

	public void LastElement(){

		setUp2();
		Comparable comp= arreglo.firstElement();;

		assertEquals("El dato intecambiado es incorrecto.",49 ,arreglo.lastElement());	
	}

	@Test

	public void deleteElementpos(){
		setUp2();
		Comparable comp3=6;
		
		
		
		assertEquals("El dato intecambiado es incorrecto.",comp3 ,arreglo.deleteElement(6));	
		
	}


	@Test	

	public void removeFirst(){

		//Borra pimer elemento
		setUp2();
		arreglo.removeFirst();
		Comparable comp3=1;

		assertEquals("Se esperaba otro elemento",comp3,arreglo.getElement(0));
		//Borra ultimo elemento
	}
	@Test	
	public void removelAST(){
		setUp2();
		arreglo.removeLast();

		assertEquals("Se esperaba otro elemento",48,arreglo.lastElement());
		


	}

	@Test
	public void size(){
		
		setUp2();
		int t= arreglo.size();

		assertEquals("Se esperaba otro elemento",50,t);
		
		
	}
	
	
	

	@Test

	public void changeInfo(){

		setUp2();
		arreglo.changeInfo(5 , 9);

		assertEquals("El dato intecambiado es incorrecto.",9 ,arreglo.getElement(5));	
	}
	@Test

	public void Isempty(){
		setUp2();
		
		assertFalse("el arreglo no deberia estar vacio", arreglo.isEmpty());
	}

}
