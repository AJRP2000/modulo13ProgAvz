package testing;

import java.util.ArrayList;
import clasesPrograma.Cliente;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;
import dao.ClientesDAO;
import dao.ProductosDAO;
import dao.UsuariosDAO;
import implementacionDAO.ClientesImplementacionDAO;
import implementacionDAO.ProductosImplementacionDAO;
import implementacionDAO.UsuariosImplementacionDAO;
import jdbc.TableManager;

public class testTableManager {
	public static void main(String [] args) {
		TableManager tm = new TableManager();
		ClientesDAO clientesDAO = new ClientesImplementacionDAO();
		UsuariosDAO usuariosDAO = new UsuariosImplementacionDAO();
		ProductosDAO productosDAO = new ProductosImplementacionDAO();
		
		//Paso 1: Borramos todas las tablas
		tm.dropTablaClientes();
		tm.dropTablaCompras();
		tm.dropTablaProductos();
		tm.dropTablaProductoXCompra();
		tm.dropTablaUsuarios();
		System.out.println("Borrado");
		
		//Paso 2: Creamos todas las tablas
		tm.createTablaClientes();
		tm.createTablaCompras();
		tm.createTablaProductos();
		tm.createTablaProductoXCompra();
		tm.createTablaUsuarios();
		insertarProductos(productosDAO);
		insertarUsuarioYCliente(usuariosDAO, clientesDAO);
		System.out.println("Creado");
	}
	
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
