package practica1;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CounterTestCase {
	private Counter counter;
	private List<Integer> numeros = Arrays.asList(1, 2, 3, 6, 4, 8, 5, 1, 2, 0);

	@Before
	public void setUp() throws Exception {		
		counter = new Counter();		
	}
	
	@Test
	public void agregarNumeroTest() {
		counter.agregarNumero(6);
		
		assertTrue(counter.contieneA(6));
	}
	
	@Test
	public void agregarMuchosNumerosTest() {		
		counter.agregarNumeros(numeros);
		
		assertTrue(counter.contieneATodos(numeros));
	}
	
	@Test
	public void evenNumbersTest() { //pares
		counter.agregarNumeros(numeros);
		int amount = counter.getEvenOcurrences();
		
		assertEquals(6, amount); // contamos el 0 como par
	}
	
	@Test
	public void oddNumbersTest() { //impares
		counter.agregarNumeros(numeros);
		int amount = counter.getOddOcurrences();
		
		assertEquals(4, amount);
	}
	
	@Test
	public void multiplesNumbersTest() {
		counter.agregarNumeros(numeros);
		int amount = counter.getMultiplesOcurrencesOf(0);
		
		assertEquals(1, amount);
		
	}
}
