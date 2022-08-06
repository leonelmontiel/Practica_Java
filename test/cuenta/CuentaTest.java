package cuenta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CuentaTest {

	private Cuenta cuenta;
	private Cuenta otraCuenta;

	@Before
	public void setUp() throws Exception {		
		cuenta = new Cuenta("Leonel", 3215, 2.0, 200000.0);
		otraCuenta = new Cuenta("Elias", 5123, 0.2, 0.0);
	}

	@Test
	public void creacionDeCuentaTest() {		
		assertEquals("Leonel", cuenta.getNombre());
		assertEquals(3215, cuenta.getNroCuenta(), 0);
		assertEquals(2.0, cuenta.getInteres(), 0);
		assertEquals(200000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void creacionDeCuentaCopiaTest() {
		Cuenta cuentaCopia = new Cuenta(cuenta);
		
		assertEquals(cuenta.getNombre(), cuentaCopia.getNombre());
		assertEquals(cuenta.getNroCuenta(), cuentaCopia.getNroCuenta(), 0);
		assertEquals(cuenta.getInteres(), cuentaCopia.getInteres(), 0);
		assertEquals(cuenta.getSaldo(), cuentaCopia.getSaldo(), 0);
	}
	
	@Test
	public void ingresoOK() throws MontoNegativoExcpetion {
		cuenta.ingresarDinero(25000.0);
		
		assertEquals(225000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void ingresoNoPuedeSerNegativoTest() {
	    Exception exception = assertThrows(MontoNegativoExcpetion.class, () -> {
	    	cuenta.ingresarDinero(-25000.0);
	    });

	    String expectedMessage = "El monto no puede ser negativo";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    assertEquals(200000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void reintegroOKTest() throws MontoNegativoExcpetion, SaldoInsuficienteException {
		cuenta.reintegrarDinero(25000.0);
		
		assertEquals(175000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void reintegroMontoNegativoTest() throws MontoNegativoExcpetion {
		Exception exception = assertThrows(MontoNegativoExcpetion.class, () -> {
	    	cuenta.ingresarDinero(-25000.0);
	    });

	    String expectedMessage = "El monto no puede ser negativo";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    assertEquals(200000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void reintegroSaldoInsuficienteTest() throws SaldoInsuficienteException {
		Exception exception = assertThrows(SaldoInsuficienteException.class, () -> {
	    	cuenta.reintegrarDinero(300000.0);
	    });

	    String expectedMessage = "El saldo es insuficiente para realizar el reintegro";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    assertEquals(200000.0, cuenta.getSaldo(), 0);
	}
	
	@Test
	public void transferenciaOKTest() throws MontoNegativoExcpetion, SaldoInsuficienteException {
		cuenta.transferirDinero(otraCuenta, 25000.0);
		
		assertEquals(175000.0, cuenta.getSaldo(), 0);
		assertEquals(25000.0, otraCuenta.getSaldo(), 0);
	}
	
	@Test
	public void transferenciaFAILTest() throws MontoNegativoExcpetion {
		Exception exception = assertThrows(MontoNegativoExcpetion.class, () -> {
			cuenta.transferirDinero(otraCuenta, -25000.0);
	    });

	    String expectedMessage = "El monto no puede ser negativo";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    assertEquals(200000.0, cuenta.getSaldo(), 0);
		assertEquals(0.0, otraCuenta.getSaldo(), 0);
		
	}

}