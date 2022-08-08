package practica2;

public class Producto {

	private double precio;

	public Producto(double precio) {
		this.setPrecio(precio);
	}

	private void setPrecio(double precio) {
		this.precio = precio;
	}

	public Double getPrecio() {
		return this.precio;
	}

}
