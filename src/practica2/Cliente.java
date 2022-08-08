package practica2;

public class Cliente {

	public void recibirMontoAPagar(double montoAPagar, Caja caja) {
		this.pagarTotal(montoAPagar, caja);
	}

	private void pagarTotal(double montoAPagar, Caja caja) {
		caja.recibirPago(montoAPagar);		
	}

}
