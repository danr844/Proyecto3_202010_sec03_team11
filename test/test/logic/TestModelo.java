package test.logic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.data_structures.Comparendo;
import model.data_structures.RedBlackBST;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {

	private Modelo modelo = new Modelo();
	private Comparendo nueva;
	private Comparendo nueva2;
	private String fechaS;
	private String fechaS2;
	private static final String vertexPath= "./data/bogota_vertices.txt";
	private static final String EdgesPath= "./data/bogota_arcos.txt";
	private static final String EstacionesPath= "./data/estacionpolicia.geojson.json";
	private static final String GraphSavepath = "./data/grafoImpreso.json";

	SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
	public RedBlackBST<Integer,Comparendo> tree = new RedBlackBST<>();





	@Before
	public void setUp1() throws ParseException {
		
		fechaS = "2018/01/17";
		fechaS2 = "2018/01/18";
		Date fecha = parser.parse(fechaS);
		Date fecha2 = parser.parse(fechaS2);

		nueva = new Comparendo(1234, fecha, "hola2", "hola3", "hola4", "hola5", "hola", "hola7", "Barrios Unidos", "Chia", 0, 0, 0);
		nueva2 = new Comparendo(0000, fecha2, "0009", "0008", "0007", "0006", "0005", "0004", "Fontibon", "Mosquera", 0, 0, 0);
		tree.put(nueva.darID(),nueva);
		tree.put(nueva2.darID(),nueva2);

	}


	@Test
	public void testModelo() throws ParseException {
		setUp1();
		assertTrue(modelo!=null);
	}

	@Test
	public void testDarTamano() throws ParseException {
		// TODO
		setUp1();
		assertEquals("No tiene el tamaño esperado", 2, tree.size(tree.giveRoot()));

	}

	@Test
	public void testAgregar() throws ParseException 
	{
		// TODO Completar la prueba
		setUp1();
		assertEquals("No tiene el tamaño esperado", 2, tree.size(tree.giveRoot()));
		String fecha1 = "2019/02/13";
		Date fecha = parser.parse(fecha1);
		Comparendo nueva3 = new Comparendo(1, fecha, "hola2", "hola3", "hola4", "hola5", "hola", "hola7", "Suba", "Cota", 0, 0, 0);
		tree.put(nueva3.darID(), nueva3);
		assertEquals("No tiene el tamaño esperado", 3, tree.size(tree.giveRoot()));


	}

	@Test
	public void testNovacio() throws ParseException
	{
		setUp1();
		// TODO Completar la prueba
		assertNotNull("El objeto no deberia ser null1", tree.get(nueva.darID()));
		assertNotNull("El objeto no deberia ser null1", tree.get(nueva2.darID()));
		assertNull("El objeto no deberia ser distinto de null", tree.get(nueva.darID()+1));
	}

	@Test
	public void testCargarInfoVerticesYArcos() throws ParseException, IOException {
		setUp1();
		modelo.cargarInfoVertex(vertexPath);
		modelo.cargarInfoEdges(EdgesPath);
		assertNotNull("la informacion no fue cargada", modelo.getGraphRead().getEdgeSize());
	}
	@Test
	public void testCargarInfoEstacionesDePolicia() throws ParseException, IOException {
		setUp1();
		modelo.cargarInfoEstacionesPolicia(EstacionesPath);
		assertNotNull("la informacion no fue cargada", modelo.getEstacionesDePolicia().giveSize());
	}
	@Test
	public void testCargarEImprimirGson() throws ParseException, IOException {
		setUp1();
		modelo.saveGraphJson(GraphSavepath);
		modelo.loadGraphJson(GraphSavepath);
		assertNotNull("la informacion no fue cargada", modelo.getGraphWrite().getEdgeSize());
	}

}
