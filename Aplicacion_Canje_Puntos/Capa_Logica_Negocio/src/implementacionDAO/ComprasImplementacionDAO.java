package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import dao.ComprasDAO;
import exceptions.CanjeException;
import jdbc.DBManager;

public class ComprasImplementacionDAO implements ComprasDAO {

	@Override
	public void finalizarCompra(Compra compra) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "INSERT INTO Compras (DNICliente, CostoPuntos, FechaCompra) VALUES ("
				+ compra.getDniCliente() + ", "
				+ compra.getPuntosTotal() + ", "
				+ "CURRENT_DATE)";
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
			String sql2 = "SELECT MAX(IdCompra) IdCompra FROM Compras";
			ResultSet rs = s.executeQuery(sql2);
			if(rs.next()) { 
				compra.setIdCompra(rs.getInt("IdCompra"));
			}
			String sql3;
			for(int i = 0; i<compra.getListaProductos().size(); i++) {
				sql3 = "INSERT INTO ProductoXCompra (IdProducto, IdCompra) VALUES ("
						+ compra.getListaProductos().get(i).getIdProducto() + ", "
						+ compra.getIdCompra() + ")";
				s.executeUpdate(sql3);
				c.commit();
			}
			c.commit();
			String sql5 = "UPDATE Clientes SET PuntosAcumulados = PuntosAcumulados - " + compra.getPuntosTotal() 
					+ " WHERE DNI = " + compra.getDniCliente();
			s.executeUpdate(sql5);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo crear la compra",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public Compra mostrarCompra(Compra compra) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT * FROM Compras WHERE IdCompra = " + compra.getIdCompra();
		Compra resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Compra();
				resultado.setIdCompra(rs.getInt("IdCompra"));
				resultado.setDniCliente(rs.getInt("DNICliente"));
				resultado.setTotalPuntos(rs.getInt("CostoPuntos"));
				resultado.setFecha(rs.getString("FechaCompra"));
			}
			
		} catch (SQLException e) {
			throw new CanjeException("No se pudo encontrar la compra",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		return resultado;
	}

}
