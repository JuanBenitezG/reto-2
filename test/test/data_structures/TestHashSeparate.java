package test.data_structures;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.ArregloDinamico;
import model.data_structures.Lista;
import model.data_structures.TablaHashSeparateChaining;


public class TestHashSeparate {
	private TablaHashSeparateChaining<String, Integer> tabla;
	private static int TAMANO=3;

	@Before
	public void setUp1() {
		tabla= new TablaHashSeparateChaining(TAMANO);
	}

	public void setUp2() {

		tabla.put("hola", 4);
		
	}

	@Test
	public void testHashSeparate() {
		setUp1();
		assertNotNull( "el arreglo dinamico no debería ser nulo.", tabla);
		assertEquals("El número de datos es incorrecto.", 0 ,tabla.size());
		// TODO
	}
	@Test
	public void testPut() {
		setUp1();
		setUp2();
		Comparable comp3=4;
		
		assertEquals("El número de datos es incorrecto.", comp3 ,(Integer)tabla.get("hola"));


	}

	@Test
	public void testGet(){
		setUp1();
		setUp2();
		Comparable comp3=4;
		
		assertEquals("El número de datos es incorrecto.", comp3 ,(Integer)tabla.get("hola"));

	}

	@Test
	public void testRemove(){
		setUp1();
		setUp2();
		Integer a=tabla.remove("hola");
		assertEquals("El número de datos es incorrecto", null ,(Integer)tabla.get("hola"));

	}
	
	@Test
	public void testContains (){
		setUp1();
		setUp2();	
		assertEquals("El resultado es incorrecto", true ,tabla.contains("hola"));
	}

	@Test
	public void testIsEmpty ( ){
		setUp1();
		assertEquals("El número de datos es incorrecto", true ,tabla.isEmpty());

	}

	@Test
	public void testSize ( ){
		setUp1();
		setUp2();
		assertEquals("El número de datos es incorrecto", 1 ,tabla.size());

	}

	@Test
	public void testKeySet(){
		setUp1();
		setUp2();
		
		ArregloDinamico<String> a= (ArregloDinamico<String>) tabla.keySet();
		assertEquals("La llave no es correcta", "hola" ,a.getElement(0));

	}

	@Test
	public void testValueSet(){
		setUp1();
		setUp2();
		Comparable comp3=4;
		ArregloDinamico<Integer> a= (ArregloDinamico<Integer>) tabla.valueSet();
		
		assertEquals("La llave no es correcta", comp3 , a.getElement(0));
	}

}
