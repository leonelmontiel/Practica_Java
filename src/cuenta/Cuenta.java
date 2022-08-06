package cuenta;

import java.util.ArrayList;

public class Cuenta {
	private static final String SALDO_INSUFICIENTE_REINTEGRO = "El saldo es insuficiente para realizar el reintegro";
	private static final String MONTO_NEGATIVO = "El monto no puede ser negativo";
	private String nombre;
	private Integer nroCuenta;
	private Double interes;
	private Double saldo;
	private ArrayList<Transaccion> historialTransferencias = new ArrayList<Transaccion>();
	
	// Constructor por defecto
	public Cuenta(){}
	
	// Constructor copia
	public Cuenta(Cuenta cuentaOriginal) {
		this.setNombre(cuentaOriginal.getNombre());
		this.setNroCuenta(cuentaOriginal.getNroCuenta());
		this.setInteres(cuentaOriginal.getInteres());
		this.setSaldo(cuentaOriginal.getSaldo());
	}

	// Constructor con todos sus parámetros
	public Cuenta(String nombre, Integer nroCuenta, Double interes, Double saldo) {
		this.setNombre(nombre);
		this.setNroCuenta(nroCuenta);
		this.setInteres(interes);
		this.setSaldo(saldo);
	}
	
	// SETTERS
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setNroCuenta(Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
	
	private void setInteres(Double interes) {
		this.interes = interes;
	}
	
	private void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	// GETTERS

	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getNroCuenta() {
		return nroCuenta;
	}
	
	public Double getInteres() {
		return interes;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public ArrayList<Transaccion> getHistorialTransferencias() {
		return this.historialTransferencias;
	}
	
	// Métodos PÚBLICOS

	public void ingresarDinero(double monto) throws MontoNegativoExcpetion {
		this.validarMontoPositivo(monto);
		this.incrementarSaldo(monto);
	
	}	

	public void reintegrarDinero(double monto) throws MontoNegativoExcpetion, SaldoInsuficienteException {
		this.validarReintegro(monto);
		this.decrementarSaldo(monto);		
	}

	public void transferirDinero(Cuenta otraCuenta, double monto) throws MontoNegativoExcpetion, SaldoInsuficienteException {
		this.reintegrarDinero(monto);
		this.agregarAHistorialTransferencias(this, otraCuenta, monto, Transferencia.EGRESO);
		otraCuenta.recibirTransferencia(this, monto);
	}
	
	public void recibirTransferencia(Cuenta cuenta, double monto) throws MontoNegativoExcpetion {
		this.ingresarDinero(monto);
		this.agregarAHistorialTransferencias(cuenta, this, monto, Transferencia.INGRESO);
	}
	
	// Métodos PRIVADOS

	private void agregarAHistorialTransferencias(Cuenta cuenta, Cuenta otraCuenta, double monto, Transferencia tipoTransferencia) {
		Transaccion transaccion = new Transaccion(cuenta, otraCuenta, monto, tipoTransferencia);
		this.getHistorialTransferencias().add(transaccion);
	}

	private void incrementarSaldo(Double monto) {
		Double nuevoSaldo = this.getSaldo() + monto;
		this.setSaldo(nuevoSaldo);
	}
	
	private void decrementarSaldo(Double monto) {
		Double nuevoSaldo = this.getSaldo() - monto;
		this.setSaldo(nuevoSaldo);
	}
	
	private void validarMontoPositivo(double monto) throws MontoNegativoExcpetion {
		if (monto < 0) throw new MontoNegativoExcpetion(MONTO_NEGATIVO);
	}
	
	private void validarSaldoSuficiente(double monto) throws SaldoInsuficienteException {
		if (this.getSaldo() < monto) throw new SaldoInsuficienteException(SALDO_INSUFICIENTE_REINTEGRO);
	}
	
	private void validarReintegro(double monto) throws MontoNegativoExcpetion, SaldoInsuficienteException {
		this.validarMontoPositivo(monto);
		this.validarSaldoSuficiente(monto);
	}	

}
