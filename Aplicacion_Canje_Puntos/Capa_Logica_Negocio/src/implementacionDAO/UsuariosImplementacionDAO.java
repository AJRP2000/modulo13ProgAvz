package implementacionDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clasesPrograma.Cliente;
import clasesPrograma.Usuario;
import dao.UsuariosDAO;
import exceptions.CanjeException;
import jdbc.DBManager;

public class UsuariosImplementacionDAO implements UsuariosDAO {

	@Override
	public Usuario getUsuario(Usuario usuario) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "SELECT * FROM Usuarios WHERE Usuario = '" + usuario.getUsuario() +"'";
		Usuario resultado = null;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) { 
				resultado = new Usuario();
				resultado.setUsuario(rs.getString("Usuario"));
				resultado.setDNI(rs.getInt("DNI"));
				resultado.setContrasena(rs.getString("Contrasena"));
			}
			
		} catch (SQLException e) {
			throw new CanjeException("No se pudo encontrar el usuario",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e1) {}
		}
		return resultado;
	}

	@Override
	public void addUsuario(Usuario usuario) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "INSERT INTO Usuarios (Usuario, DNI, Contrasena) VALUES ('"
				+ usuario.getUsuario() + "', "
				+ usuario.getDNI() + ", '"
				+ usuario.getContrasena() + "' )";
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo crear el usuario",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}
	}

	@Override
	public void deleteUsuario(Usuario usuario) throws CanjeException {
		DBManager dbm = DBManager.getInstance();
		Connection c = dbm.connect();
		String sql = "DELETE FROM Usuarios WHERE "
				+ "Usuario = '" + usuario.getUsuario() + "'";
		try {
			Statement s = c.createStatement();
			s.executeUpdate(sql);
			c.commit();
		} catch (SQLException e) {
			try {
			c.rollback();
			} catch (SQLException e1) {}
			throw new CanjeException("No se pudo borrar el usuario",e);
		}finally {
			try {
				c.close();
			} catch(SQLException e2) {}
		}

	}

}
