package cuenta;

public class Transaccion {
	private Cuenta origen;
	private Cuenta destino;
	private Double monto;
	private Transferencia tipoTransferencia;
	
	public Transaccion(Cuenta origen, Cuenta destino, Double monto, Transferencia tipoTransferencia) {
		this.setOrigen(origen);
		this.setDestino(destino);
		this.setMonto(monto);
		this.setTipoTransferencia(tipoTransferencia);
	}

	public Cuenta getOrigen() {
		return origen;
	}

	public Cuenta getDestino() {
		return destino;
	}
	
	public Double getMonto() {
		return monto;
	}	

	public Transferencia getTipoTransferencia() {
		return tipoTransferencia;
	}
	
	private void setOrigen(Cuenta origen) {
		this.origen = origen;
	}

	private void setDestino(Cuenta destino) {
		this.destino = destino;
	}


	private void setMonto(Double monto) {
		this.monto = monto;
	}


	private void setTipoTransferencia(Transferencia tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}

}
