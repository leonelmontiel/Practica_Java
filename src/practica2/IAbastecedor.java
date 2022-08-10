package practica2;

import java.util.Map;

public interface IAbastecedor {

	Boolean hayStockDe(Producto arroz);

	Map<Producto, Integer> getStockProductos();

	void decrementarStockDe(Producto producto);

}
