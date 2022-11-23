package dao;

import clasesPrograma.Usuario;
import exceptions.CanjeException;

public interface UsuariosDAO {

	Usuario getUsuario(Usuario usuario) throws CanjeException;
	
	void addUsuario(Usuario usuario) throws CanjeException;
	
	void deleteUsuario(Usuario usuario) throws CanjeException;
}
