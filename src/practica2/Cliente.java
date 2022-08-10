package practica2;

public class Cliente implements ICliente {

	public void recibirMontoAPagar(double montoAPagar, Caja caja) {
		this.pagarTotal(montoAPagar, caja);
	}

	public void pagarTotal(double montoAPagar, Caja caja) {
		caja.recibirPago(montoAPagar);		
	}

}
