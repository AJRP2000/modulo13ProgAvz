package canjeHandler;

import java.util.ArrayList;

import clasesPrograma.*;
import dao.*;
import exceptions.CanjeException;

public class CanjeHandler implements ICanjeHandler {

	UsuariosDAO usuariosDAO;
	ComprasDAO comprasDAO;
	ProductosDAO productosDAO;
	ClientesDAO clientesDAO;
	
	public CanjeHandler(UsuariosDAO usuariosDAO, ComprasDAO comprasDAO, ProductosDAO productosDAO, ClientesDAO clientesDAO) {
		this.usuariosDAO=usuariosDAO;
		this.comprasDAO=comprasDAO;
		this.productosDAO=productosDAO;
		this.clientesDAO=clientesDAO;
	}
	
	@Override
	public Cliente logIn(Usuario usuario) {
		Cliente resultado = null;
		try {
			Usuario validar = usuariosDAO.getUsuario(usuario);
			if(validar!=null){
				resultado = clientesDAO.getCliente(validar);
			}
		} catch(CanjeException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public ArrayList<Producto> getProductos() {
		ArrayList<Producto> listaProductos = null;
		try {
			listaProductos = productosDAO.getListaProductos();
		}catch(CanjeException e) {
			e.printStackTrace();
		}
		
		return listaProductos;
	}

	@Override
	public void crearCompra(Compra compra) {
		try {
			comprasDAO.finalizarCompra(compra);
		}catch(CanjeException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Cliente getDatosCliente(Cliente cliente) {
		Cliente resultado = null;
		try {
			int puntos = clientesDAO.getPuntosAcumulados(cliente);
			resultado = new Cliente();
			resultado.setPuntosAcumulados(puntos);
		}catch(CanjeException e) {
			e.printStackTrace();
		}
		return resultado;
	}

}
