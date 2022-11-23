package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clasesPrograma.Cliente;
import clasesPrograma.Usuario;
import dao.ClientesDAO;
import exceptions.CanjeException;
import jdbc.DBManager;

public class ClientesImplementacionDAO implements ClientesDAO {

	@Override
	public Cliente getCliente(Usuario usuario) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT * FROM Clientes WHERE DNI = " + usuario.getDNI();
		Cliente resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Cliente();
				resultado.setDNI(rs.getInt("DNI"));
				resultado.setNombre(rs.getString("Nombre"));
				resultado.setDireccion(rs.getString("Direccion"));
				resultado.setPuntosAcumulados(rs.getInt("PuntosAcumulados"));
			}
			
		} catch (SQLException e) {
			throw new CanjeException("No se pudo encontrar el cliente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		return resultado;
	}

	@Override
	public Cliente getPuntosAcumulados(Cliente cliente) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT * FROM Clientes WHERE DNI = " + cliente.getDNI();
		Cliente resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Cliente();
				resultado.setDNI(rs.getInt("DNI"));
				resultado.setNombre(rs.getString("Nombre"));
				resultado.setDireccion(rs.getString("Direccion"));
				resultado.setPuntosAcumulados(rs.getInt("PuntosAcumulados"));
			}
			
		} catch (SQLException e) {
			throw new CanjeException("No se pudo encontrar el cliente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		return resultado;
	}

	@Override
	public void addCliente(Cliente cliente) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "INSERT INTO Clientes (DNI, Nombre, Direccion, PuntosAcumulados) VALUES ("
				+ cliente.getDNI() + ", '"
				+ cliente.getNombre() + "', '"
				+ cliente.getDireccion() + "', "
				+ cliente.getPuntosAcumulados() + " )";
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo crear el cliente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}

	}

	@Override
	public void deleteCliente(Cliente cliente) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DELETE FROM Clientes WHERE "
				+ "DNI = " + cliente.getDNI();
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
			c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo borrar el cliente",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}
	
	public int getPuntosCliente(int dniCliente) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT puntosAcumulados FROM Clientes WHERE DNI = " + dniCliente;
		int resultado = 0;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado =rs.getInt("puntosAcumulados");
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
