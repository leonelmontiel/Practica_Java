package practica2;

import java.util.ArrayList;
import java.util.List;

public class Caja {

	private List<Producto> productosRegistrados = new ArrayList<Producto>();
	private List<Producto> productosCobrados = new ArrayList<Producto>();
	private double dineroAcumulado;
	private IAbastecedor mercado;
	
	public Caja(IAbastecedor mercado) {
		this.setDineroAcumulado(0);
		this.setMercado(mercado);
	}
	
	// Getters
	
	public List<Producto> getProductosRegistrados() {
		return this.productosRegistrados;
	}
	
	public List<Producto> getProductosCobrados() {
		return this.productosCobrados;
	}

	public double getDineroAcumulado() {
		return this.dineroAcumulado;
	}
	
	// Setters

	private void setDineroAcumulado(double monto) {
		this.dineroAcumulado = monto;
	}
	
	private void setMercado(IAbastecedor mercado) {
		this.mercado = mercado;
	}
	
	//// Mensajes de paquete

	void registrarProducto(Producto producto) {
		if (this.mercado.hayStockDe(producto)) {
			this.getProductosRegistrados().add(producto);
			this.getProductosCobrados().add(producto);
			this.mercado.decrementarStockDe(producto);
		}		
	}

	void registrarProductos(List<Producto> productos) {
		for (Producto producto : productos) {
			this.registrarProducto(producto);
		}
	}
		
	Boolean productosRegistradosContiene(Producto producto) {
		return this.getProductosRegistrados().contains(producto);
	}

	Boolean productosRegistradosContiene(List<Producto> productos) {		
		return this.getProductosRegistrados().containsAll(productos);
	}

	Boolean productosCobradosContiene(Producto producto) {
		return this.getProductosCobrados().contains(producto);
	}
	
	boolean productosCobradosContiene(List<Producto> productos) {
		return this.getProductosCobrados().containsAll(productos);
	}

	double calcularMontoTotalAPagar() {
		return this.getProductosRegistrados().stream().map(p -> p.getPrecio()).reduce(Double::sum).get();
	}
	
	//// Mensajes ppublicos

	// Cobrar un producto
	public void cobrar(Producto arroz, ICliente cliente) {
		this.registrarProducto(arroz);
		cliente.recibirMontoAPagar(this.calcularMontoTotalAPagar(), this);
	}
	
	// Cobrar muchos productos
	public void cobrar(List<Producto> productos, ICliente cliente) {
		this.registrarProductos(productos);
		cliente.recibirMontoAPagar(this.calcularMontoTotalAPagar(), this);
	}	

	public void recibirPago(double montoRecibido) {
		this.incrementarDineroAcumulado(montoRecibido);
		//this.informarProductosADecrementarStock(this.getProductosRegistrados();
		this.vaciarProductosRegistrados();
	}
	
	//// Mensajes privados

	private void vaciarProductosRegistrados() {
		this.getProductosRegistrados().clear();
	}

	private void incrementarDineroAcumulado(double montoRecibido) {
		this.setDineroAcumulado(this.getDineroAcumulado() + montoRecibido);
	}

	public Boolean hayStockDe(Producto producto) {
		return this.mercado.hayStockDe(producto);
	}

}
