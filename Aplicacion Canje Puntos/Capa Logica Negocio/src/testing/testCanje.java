package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import canjeHandler.CanjeHandler;
import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;
import dao.ClientesDAO;
import dao.ComprasDAO;
import dao.ProductosDAO;
import dao.UsuariosDAO;
import implementacionDAO.ClientesImplementacionDAO;
import implementacionDAO.ComprasImplementacionDAO;
import implementacionDAO.ProductosImplementacionDAO;
import implementacionDAO.UsuariosImplementacionDAO;

class testCanje {

	@Test
	void test() {
		
		UsuariosDAO usuariosDAO = new UsuariosImplementacionDAO();
		ComprasDAO comprasDAO = new ComprasImplementacionDAO();
		ProductosDAO productosDAO = new ProductosImplementacionDAO();
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
		} catch(Exception e) {}
	}
	
	//Funcion que insertara la lista productos que utilizaremos para testear
	static void insertarProductos(ProductosDAO productosDAO) {
		ArrayList<Producto> productos = new ArrayList<Producto>();
		Producto producto1 = new Producto(1,"Lapiz", 20);
		Producto producto2 = new Producto(2,"Papel", 50);
		Producto producto3 = new Producto(3,"Boligrafo", 70);
		Producto producto4 = new Producto(4,"Liquid Paper", 10);
		Producto producto5 = new Producto(5,"Cuaderno", 100);
		
		productos.add(producto1);
		productos.add(producto2);
		productos.add(producto3);
		productos.add(producto4);
		productos.add(producto5);
		
		try {
		productosDAO.addProductos(productos);
		}catch(Exception e) {}
	}
	
	//Funcion que insertara los datos del usuario y cliente para testear
	static void insertarUsuarioYCliente(UsuariosDAO usuariosDAO, ClientesDAO clientesDAO) {
		Usuario usuario = new Usuario("usuario", "contrasena", 20266);
		Cliente cliente = new Cliente(20266, "Pepe", "1020 Calle", 4000);
		
		try {
			usuariosDAO.addUsuario(usuario);
			clientesDAO.addCliente(cliente);
		}catch(Exception e) {}
	}

}
