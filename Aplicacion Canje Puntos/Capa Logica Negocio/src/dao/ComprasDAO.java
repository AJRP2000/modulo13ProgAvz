package dao;

import clasesPrograma.Compra;
import exceptions.CanjeException;

public interface ComprasDAO {
	
	void finalizarCompra(Compra compra) throws CanjeException;
	
	Compra mostrarCompra(Compra compra) throws CanjeException;
}
