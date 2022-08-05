package cuenta;

import java.util.ArrayList;

public class Cuenta {
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

	public void ingreso(double monto) throws IngresoNegativoException {
		if (monto < 0) {
			throw new IngresoNegativoException("El monto no puede ser negativo");
		}
		this.incremento(monto);
	}

	public void reintegro(double monto) throws IngresoNegativoException, SaldoInsuficienteException {
		if (monto < 0) {
			throw new IngresoNegativoException("El monto no puede ser negativo");
		}
		if (this.getSaldo() < monto) {
			throw new SaldoInsuficienteException("El saldo es insuficiente para realizar el reintegro");
		}	
		this.decremento(monto);
	}

	public void transferencia(Cuenta otraCuenta, double monto) throws IngresoNegativoException, SaldoInsuficienteException {
		this.reintegro(monto);
		this.agregarAHistorialTransferencias(this, otraCuenta, monto, Transferencia.EGRESO);
		otraCuenta.recibirTransferencia(this, monto);
	}
	
	public void recibirTransferencia(Cuenta cuenta, double monto) throws IngresoNegativoException {
		this.ingreso(monto);
		this.agregarAHistorialTransferencias(cuenta, this, monto, Transferencia.INGRESO);
	}
	
	// Métodos PRIVADOS

	private void agregarAHistorialTransferencias(Cuenta cuenta, Cuenta otraCuenta, double monto, Transferencia tipoTransferencia) {
		Transaccion transaccion = new Transaccion(cuenta, otraCuenta, monto, tipoTransferencia);
		this.getHistorialTransferencias().add(transaccion);
	}

	private void incremento(Double monto) {
		Double nuevoSaldo = this.getSaldo() + monto;
		this.setSaldo(nuevoSaldo);
	}
	
	private void decremento(Double monto) {
		Double nuevoSaldo = this.getSaldo() - monto;
		this.setSaldo(nuevoSaldo);
	}

}
