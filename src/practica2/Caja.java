package practica2;

import java.util.ArrayList;
import java.util.List;

public class Caja {

	private List<Producto> productosRegistrados = new ArrayList<Producto>();
	private List<Producto> productosCobrados = new ArrayList<Producto>();
	private double dineroAcumulado;
	
	public List<Producto> getProductosRegistrados() {
		return this.productosRegistrados;
	}
	
	public List<Producto> getProductosCobrados() {
		return this.productosCobrados;
	}

	public double getDineroAcumulado() {
		return this.dineroAcumulado;
	}

	private void setDineroAcumulado(double monto) {
		this.dineroAcumulado = monto;
	}
	
	////

	public void registrarProducto(Producto producto) {
		this.getProductosRegistrados().add(producto);		
	}

	public void registrarProductos(List<Producto> productos) {
		this.getProductosRegistrados().addAll(productos);	
	}
		
	public Boolean productosRegistradosContiene(Producto producto) {
		return this.getProductosRegistrados().contains(producto);
	}

	public Boolean productosRegistradosContiene(List<Producto> productos) {		
		return this.getProductosRegistrados().containsAll(productos);
	}

	public Boolean productosCobradosContiene(Producto producto) {
		return this.getProductosCobrados().contains(producto);
	}

	public double calcularMontoTotalAPagar() {
		return this.getProductosRegistrados().stream().map(p -> p.getPrecio()).reduce(Double::sum).get();
	}

	public void cobrar(Producto producto, Cliente cliente) {
		this.registrarProducto(producto);
		cliente.recibirMontoAPagar(this.calcularMontoTotalAPagar(), this);
	}

	public void recibirPago(double montoRecibido) {
		this.incrementarDineroAcumulado(montoRecibido);
		this.vaciarProductosRegistrados();
	}

	private void vaciarProductosRegistrados() {
		this.getProductosRegistrados().clear();
	}

	private void incrementarDineroAcumulado(double montoRecibido) {
		this.setDineroAcumulado(this.getDineroAcumulado() + montoRecibido);
	}


}
