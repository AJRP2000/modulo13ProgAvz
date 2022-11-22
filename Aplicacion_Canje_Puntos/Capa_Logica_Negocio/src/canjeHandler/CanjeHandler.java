package canjeHandler;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import clasesPrograma.*;
import dao.*;
import exceptions.CanjeException;
import implementacionDAO.ClientesImplementacionDAO;
import implementacionDAO.ComprasImplementacionDAO;
import implementacionDAO.ProductosImplementacionDAO;
import implementacionDAO.UsuariosImplementacionDAO;

@Controller
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
		}catch(CanjeException e) {
			e.printStackTrace();
			throw new CanjeException(e.getMessage());
		}
		return resultado;
	}

}
