package practica2;

import java.util.HashMap;
import java.util.Map;

public class Mercado implements IAbastecedor {

	private Map<Producto, Integer> stockProductos = new HashMap<Producto, Integer>();

	@Override
	public Boolean hayStockDe(Producto producto) {
		return this.getStockProductos().containsKey(producto);
	}

	@Override
	public Map<Producto, Integer> getStockProductos() {
		return this.stockProductos;
	}

	@Override
	public void decrementarStockDe(Producto producto) {
		Integer cantidad = this.getStockProductos().get(producto).intValue();
		this.getStockProductos().replace(producto, cantidad--);
	}

}
