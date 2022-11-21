package dao;

import clasesPrograma.Cliente;
import clasesPrograma.Usuario;
import exceptions.CanjeException;

public interface ClientesDAO {
	
	Cliente getCliente(Usuario usuario) throws CanjeException;
	
	int getPuntosAcumulados(Cliente cliente) throws CanjeException;
	
	void addCliente(Cliente cliente) throws CanjeException;
	
	void deleteCliente(Cliente cliente) throws CanjeException;

}
