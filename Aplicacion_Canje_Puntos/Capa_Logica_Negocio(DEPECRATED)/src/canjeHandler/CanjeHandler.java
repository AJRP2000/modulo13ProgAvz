package canjeHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import clasesPrograma.*;
import dao.*;
import exceptions.CanjeException;
import implementacionDAO.ClientesImplementacionDAO;
import implementacionDAO.ComprasImplementacionDAO;
import implementacionDAO.ProductosImplementacionDAO;
import implementacionDAO.UsuariosImplementacionDAO;


public class CanjeHandler implements ICanjeHandler {

	private static UsuariosDAO usuariosDAO = new UsuariosImplementacionDAO();
	private static ComprasDAO comprasDAO = new ComprasImplementacionDAO();
	private static ProductosDAO productosDAO = new ProductosImplementacionDAO();
	private static ClientesDAO clientesDAO = new ClientesImplementacionDAO();
	
	
	public Cliente logIn(Usuario usuario) throws CanjeException {
		Cliente resultado = null;
		try {
			Usuario validar = usuariosDAO.getUsuario(usuario);
			if(validar!=null){
				resultado = clientesDAO.getCliente(validar);
			}
		} catch(CanjeException e) {
			e.printStackTrace();
			throw new CanjeException(e.getMessage());
		}
		
		return resultado;
	}

	@RequestMapping("/getProductos")
	public ArrayList<Producto> getProductos()  throws CanjeException{
		ArrayList<Producto> listaProductos = null;
		try {
			listaProductos = productosDAO.getListaProductos();
		}catch(CanjeException e) {
			e.printStackTrace();
			throw new CanjeException(e.getMessage());
		}
		
		return listaProductos;
	}

	@RequestMapping("crearCompra")
	public void crearCompra(Compra compra)  throws CanjeException{
		try {
			String idProductos = "(";
			ArrayList<Producto> productos = compra.getListaProductos();
			for(int i = 0; i<productos.size(); i++) {
				idProductos = idProductos + productos.get(i).getIdProducto() + ",";
			}
			idProductos = idProductos.substring(0, idProductos.length()-1) + ")";
			System.out.println(idProductos);
			int puntosProductos= productosDAO.getPuntosProductos(idProductos);
			System.out.println(puntosProductos);
			if(puntosProductos > clientesDAO.getPuntosCliente(compra.getDniCliente()))
				throw new CanjeException("No se pudo crear la compra porque el cliente no tiene los puntos necesarios");
			compra.setTotalPuntos(puntosProductos);
			System.out.println(compra.getTotalPuntos());
			comprasDAO.finalizarCompra(compra);
		}catch(CanjeException e) {
			e.printStackTrace();
			throw new CanjeException(e.getMessage());
		}
	}

	@RequestMapping("getDatosCliente")
	public Cliente getDatosCliente(Cliente cliente) throws CanjeException{
		Cliente resultado = null;
		try {
			int puntos = clientesDAO.getPuntosAcumulados(cliente).getPuntosAcumulados();
			resultado = new Cliente();
			resultado.setPuntosAcumulados(puntos);
			resultado.setNombre("");
			resultado.setDireccion("");
		}catch(CanjeException e) {
			e.printStackTrace();
			throw new CanjeException(e.getMessage());
		}
		return resultado;
	}

}
