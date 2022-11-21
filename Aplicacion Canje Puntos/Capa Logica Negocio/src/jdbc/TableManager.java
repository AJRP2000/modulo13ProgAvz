package jdbc;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	
	public void createTablaProductos() {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Productos (IDProducto INTEGER IDENTITY PRIMARY KEY, " 
				+ "Descripcion VARCHAR(100), "
				+ "CostoPuntos INT)";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaCompras() {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Compras (IdCompra INTEGER IDENTITY PRIMARY KEY, " 
				+ "DNICliente INT, "
				+ "CostoPuntos INT, "
				+ "FechaCompra DATE)";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaProductoXCompra() {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE ProductoXCompra (IdProducto INTEGER, "
				+ "IdCompra INTEGER)";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTablaClientes() {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Clientes (DNI INTEGER PRIMARY KEY, " 
				+ "Nombre VARCHAR(100), "
				+ "Direccion VARCHAR(100), "
				+ "PuntosAcumulados INT)";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public void createTablaUsuarios() {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "CREATE TABLE Usuarios (Usuario VARCHAR(50) PRIMARY KEY, "
				+ "Contrasena VARCHAR(50), "
				+ "DNI INT)";
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void dropTabla(String nombreTabla) {
		DBManager dbm= DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DROP TABLE " + nombreTabla;
		
		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void dropTablaProductos() {
		dropTabla("Productos");
	}
	
	public void dropTablaProductoXCompra() {
		dropTabla("ProductoXCompra");
	}
	
	public void dropTablaCompras() {
		dropTabla("Compras");
	}
	
	public void dropTablaClientes() {
		dropTabla("Clientes");
	}
	
	public void dropTablaUsuarios() {
		dropTabla("Usuarios");
	}
}
