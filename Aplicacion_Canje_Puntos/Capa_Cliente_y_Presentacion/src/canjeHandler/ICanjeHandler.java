package canjeHandler;

import java.util.ArrayList;

import clasesPrograma.*;
import exceptions.CanjeException;

public interface ICanjeHandler {
	
	Cliente logIn(Usuario usuario) throws CanjeException;
	
	ArrayList<Producto> getProductos()throws CanjeException;
	
	void crearCompra(Compra compra)throws CanjeException;
	
	Cliente getDatosCliente(Cliente cliente)throws CanjeException;

}
