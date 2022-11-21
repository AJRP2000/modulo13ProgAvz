package dao;

import java.util.ArrayList;

import clasesPrograma.Producto;
import exceptions.CanjeException;

public interface ProductosDAO {

	ArrayList<Producto> getListaProductos() throws CanjeException;
	
	void addProductos(ArrayList<Producto> listaProductos) throws CanjeException;
	
	void deleteProductos(ArrayList<Producto> listaProductos) throws CanjeException;
	
}
