package canjeHandler;

import java.util.ArrayList;

import clasesPrograma.*;

public interface ICanjeHandler {
	
	Cliente logIn(Usuario usuario);
	
	ArrayList<Producto> getProductos();
	
	void crearCompra(Compra compra);
	
	Cliente getDatosCliente(Cliente cliente);

}
