package practica1;

import java.util.ArrayList;
import java.util.List;

public class Counter {

	private List<Integer> numeros = new ArrayList<Integer>();
	
	public List<Integer> getNumeros() {
		return this.numeros ;
	}
	
	public void agregarNumero(int numero) {
		this.getNumeros().add(numero);
	}

	public void agregarNumeros(List<Integer> numeros2) {
		this.getNumeros().addAll(numeros2);	
	}	

	public Boolean contieneATodos(List<Integer> numeros) {
		return this.getNumeros().containsAll(numeros);
	}

	public Boolean contieneA(int numero) {
		return this.getNumeros().contains(numero);
	}

	public int getEvenOcurrences() {	
		return this.getNumeros().stream().filter(n ->this.tieneRestoCero(n, 2)).toList().size();
	}

	public int getOddOcurrences() {		
		return this.getNumeros().size() - this.getEvenOcurrences();
	}

	public int getMultiplesOcurrencesOf(int numero) {	
		Integer multiplesOcurrences;
		if (this.noEsCero(numero)) {
			multiplesOcurrences = this.countMultplesOcurrences(numero);
		} else {
			multiplesOcurrences = this.countZeros();
		}
		return multiplesOcurrences;
	}

	private int countZeros() {
		return this.getNumeros().stream().filter(n -> n == 0).toList().size();
	}

	private int countMultplesOcurrences(int numero) {
		return this.getNumeros().stream().filter(n -> this.esMultiploDe(n, numero)).toList().size();
	}

	private Boolean noEsCero(Integer n) {
		return n != 0;
	}

	private Boolean esMultiploDe(Integer numero1, int numero2) {
		return (tieneRestoCero(numero1, numero2));
	}

	private boolean tieneRestoCero(Integer numero1, int numero2) {
		return (numero1 % numero2) == 0;
	}

	

}
