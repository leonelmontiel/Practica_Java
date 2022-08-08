package practica2;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CajaTest {

	private Caja caja;
	private Producto arroz;
	private Producto queso;
	private Producto mayonesa ;
	private Cliente cliente;

	@Before
	public void setUp() throws Exception {
		caja = new Caja();
		arroz = new Producto(80d);
		queso = new Producto(500d);
		mayonesa = new Producto(100d);
		cliente = new Cliente();
	}

	@Test
	public void registrarProductoTest() {		
		caja.registrarProducto(arroz);
		
		assertTrue(caja.productosRegistradosContiene(arroz));
	}
	
	@Test
	public void registrarMuchosProductosTest() {
			
		List<Producto> productos = Arrays.asList(arroz, queso, mayonesa);
		
		caja.registrarProductos(productos);
		
		assertTrue(caja.productosRegistradosContiene(productos));
	}
	
	@Test
	public void obtenerMontoTotalAPagarTest() {	
		List<Producto> productos = Arrays.asList(arroz, queso, mayonesa);
		
		caja.registrarProductos(productos);
		
		assertEquals(680d, caja.calcularMontoTotalAPagar(), 0);
	}
	
	@Test
	public void cobrarProductoTest() {
		caja.cobrar(arroz, cliente);
		
		assertTrue(caja.productosCobradosContiene(arroz));
	}

}
