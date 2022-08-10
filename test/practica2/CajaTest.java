package practica2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class CajaTest {

	private Caja caja;
	private Producto arroz;
	private Producto queso;
	private Producto mayonesa ;
	private ICliente cliente;
	private IAbastecedor mercado;
	private Map<Producto, Integer> stockProductos = new HashMap<Producto, Integer>();

	@Before
	public void setUp() throws Exception {			
		arroz = mock(Producto.class);
		queso = mock(Producto.class);
		mayonesa = mock(Producto.class);
		cliente = spy(Cliente.class);
		mercado = spy(Mercado.class);
		
		caja = new Caja(mercado);
		
		when(arroz.getPrecio()).thenReturn(80d);
		when(queso.getPrecio()).thenReturn(500d);
		when(mayonesa.getPrecio()).thenReturn(100d);
	}

	@Test
	public void registrarProductoTest() {
		stockProductos.put(arroz, 1);
		
		when(mercado.getStockProductos()).thenReturn(stockProductos);
		
		caja.registrarProducto(arroz);
		
		assertTrue(caja.productosRegistradosContiene(arroz));
	}
	
	@Test
	public void registrarMuchosProductosTest() {			
		inicializarStockProductosMock();
		
		List<Producto> productos = Arrays.asList(arroz, queso, mayonesa);		
		
		caja.registrarProductos(productos);
		
		assertTrue(caja.productosRegistradosContiene(productos));
	}
	
	@Test
	public void obtenerMontoTotalAPagarTest() {	
		inicializarStockProductosMock();	
		
		List<Producto> productos = Arrays.asList(arroz, queso, mayonesa);
		
		caja.registrarProductos(productos);
		
		assertEquals(680d, caja.calcularMontoTotalAPagar(), 0);
	}
	
	@Test
	public void cobrarProductoTest() {
		stockProductos.put(arroz, 1);
		
		when(mercado.getStockProductos()).thenReturn(stockProductos);
		
		caja.cobrar(arroz, cliente);
		
		verify(cliente).recibirMontoAPagar(80d, caja); // cliente -> Spy
		
		assertTrue(caja.productosCobradosContiene(arroz)); //El producto queda en el historial de cobro
		assertFalse(caja.productosRegistradosContiene(arroz)); //Al cobrar, el producto se borra de la cola actual
		assertEquals(80d, caja.getDineroAcumulado(), 0); //Acumulamos el dinero del producto en la caja
	}
	
	@Test
	public void cobrarMuchosProductosTest() {
		inicializarStockProductosMock();
		
		List<Producto> productos = Arrays.asList(arroz, queso, mayonesa);		
		
		caja.cobrar(productos, cliente);
		
		verify(cliente).recibirMontoAPagar(680d, caja); // cliente -> Spy
		
		assertTrue(caja.productosCobradosContiene(productos));
		assertFalse(caja.productosRegistradosContiene(productos));
		assertEquals(680d, caja.getDineroAcumulado(), 0);
	}

	
	@Test
	public void noHayStockDeProductoTest() {		
		when(mercado.getStockProductos()).thenReturn(stockProductos);
		
		assertFalse(caja.hayStockDe(arroz));		
	}
	
	@Test
	public void hayStockDeProductoTest() {		
		stockProductos.put(arroz, 1);		
		
		when(mercado.getStockProductos()).thenReturn(stockProductos);
		
		assertTrue(caja.hayStockDe(arroz));		
	}
	
	////////


	private void inicializarStockProductosMock() {		
		stockProductos.put(arroz, 1);
		stockProductos.put(queso, 1);
		stockProductos.put(mayonesa, 1);	
		when(mercado.getStockProductos()).thenReturn(stockProductos);
	}
}
