package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import canjeHandler.CanjeHandler;
import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;
import dao.ClientesDAO;
import dao.ComprasDAO;
import implementacionDAO.ClientesImplementacionDAO;
import implementacionDAO.ComprasImplementacionDAO;

/**
*
* @author AJRP2000
*/
class testCanje {

	public testCanje() {}
	
	/**
     * Test of area method, of BackEnd Transactions.
     */
	@Test
	void test() {
		
		ComprasDAO comprasDAO = new ComprasImplementacionDAO();
		ClientesDAO clientesDAO = new ClientesImplementacionDAO();
		
		//PASO 0: Insertar los productos, usuario y cliente a usar para testear
		CanjeHandler canjeHandler = new CanjeHandler();
		//insertarProductos(productosDAO);
		//insertarUsuarioYCliente(usuariosDAO, clientesDAO);
		
		//PASO 1: Crear usuario y hacer login
		Usuario usuario = new Usuario("usuario", "contrasena", 20266);
		try {
			Cliente cliente = canjeHandler.logIn(usuario);
		
		//PASO 2: Con el cliente cargado, revisar la lista de productos
			ArrayList<Producto> productos = canjeHandler.getProductos();
		
		//PASO 3: Armar la compra
			Compra compra = new Compra();
			compra.setListaProductos(productos);
			compra.setDniCliente(cliente.getDNI());
		
		//PASO 4: Insertar la compra
			canjeHandler.crearCompra(compra);
		
		//PASO 5: Verificar los datos de la compra
			Compra resultado = comprasDAO.mostrarCompra(compra);
			assertEquals(compra.getPuntosTotal(), resultado.getTotalPuntos(), 0);
			assertEquals(compra.getDniCliente(), resultado.getDniCliente(),0);
			
		//PASO 6: Verificar que se actualizo la cantidad de puntos del usuario.
			Cliente resultadoCliente = clientesDAO.getPuntosAcumulados(cliente);
			assertEquals(cliente.getPuntosAcumulados()-compra.getPuntosTotal(), resultadoCliente.getPuntosAcumulados(),0);
			
		} catch(Exception e) {}
	}
}
