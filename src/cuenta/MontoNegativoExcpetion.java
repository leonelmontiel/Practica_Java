package cuenta;

@SuppressWarnings("serial")
class MontoNegativoExcpetion extends Exception {
	
	public MontoNegativoExcpetion(String mensaje) {
		super(mensaje);
	}

}

@SuppressWarnings("serial")
class SaldoInsuficienteException extends Exception {
	
	public SaldoInsuficienteException(String mensaje) {
		super(mensaje);
	}

}