package testing;

import jdbc.TableManager;

public class testTableManager {
	public static void main(String [] args) {
		int crear=1;
		TableManager tm = new TableManager();
		if (crear==1) {
			tm.createTablaClientes();
			tm.createTablaCompras();
			tm.createTablaProductos();
			tm.createTablaProductoXCompra();
			tm.createTablaUsuarios();
			System.out.println("Creado");
			}
		else {
			tm.dropTablaClientes();
			tm.dropTablaCompras();
			tm.dropTablaProductos();
			tm.dropTablaProductoXCompra();
			tm.dropTablaUsuarios();
			System.out.println("borrado");
		}
	}
}
