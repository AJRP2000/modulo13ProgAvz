package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clasesPrograma.Compra;
import clasesPrograma.Producto;
import dao.ProductosDAO;
import exceptions.CanjeException;
import jdbc.DBManager;

public class ProductosImplementacionDAO implements ProductosDAO {

	@Override
	public ArrayList<Producto> getListaProductos() throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT * FROM Productos";
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		Producto producto = new Producto();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) { 
				producto  = new Producto();
				producto.setIdProducto(rs.getInt("IDProducto"));
				producto.setDescripcion(rs.getString("Descripcion"));
				producto.setCostoPuntos(rs.getInt("CostoPuntos"));
				resultado.add(producto);
			}
			
		} catch (SQLException e) {
			throw new CanjeException("No se pudieron encontrar productos",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		return resultado;
	}

	@Override
	public void addProductos(ArrayList<Producto> listaProductos) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql ="";
		try {
			Statement s = c.createStatement();
			
			for(int i = 0; i<listaProductos.size(); i++) {
				sql= "INSERT INTO Productos (Descripcion, CostoPuntos) VALUES ('"
						+ listaProductos.get(i).getDescripcion() + "', "
						+ listaProductos.get(i).getCostoPuntos() + " )";
				s.executeUpdate(sql);
				c.commit();
			}
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo crear el producto",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public void deleteProductos(ArrayList<Producto> listaProductos) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql ="";
		try {
			Statement s = c.createStatement();
			
			for(int i = 0; i<listaProductos.size(); i++) {
				sql= "DELETE FROM Productos WHERE IdProducto = " + listaProductos.get(i).getIdProducto();
				s.executeUpdate(sql);
				c.commit();
			}
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo borrar el producto",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

}
