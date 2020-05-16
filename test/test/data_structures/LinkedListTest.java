package test.data_structures;

import static org.junit.Assert.*;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;

import model.data_structures.LinkedList;

public class LinkedListTest {
	
		private LinkedList<Integer>lista;

		@Before
		public void setUp1(){
			lista = new LinkedList<Integer>();
			lista.put(1);
			lista.put(5);
			lista.put(10);
			
		}

		public void setUp2() {
			lista.put(2000);
			lista.put(123);
		}


		@Test
		public void testDarElemento() throws ParseException 
		{
			setUp1();
			// TODO
			assertEquals("No es el elemento esperado",(Integer)1,lista.get(1));
			assertNull("No es el elemento esperado",lista.get(2));


		}
		@Test
		public void borrarElemento() throws ParseException{
			setUp1();
			lista.delete(1);
			assertNull("No se encontro el elemento esperado", lista.get(1));
			lista.delete(5);
			assertNull("No se encontro el elemento esperado", lista.get(5));
		}
		@Test
		public void agregarElemento() throws ParseException{
			setUp1();
			lista.put(999);
			assertEquals((Integer)999,lista.get(999));
					
		}


	}


