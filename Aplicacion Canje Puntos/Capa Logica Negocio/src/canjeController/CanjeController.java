package canjeController;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import canjeHandler.CanjeHandler;
import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;
import exceptions.CanjeException;

@Controller
public class CanjeController {

	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@RequestMapping(value = "/logIn",method = RequestMethod.GET, 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity logIn(@RequestHeader(value="usuario") Usuario usuario){
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			Cliente cliente = canjeHandler.logIn(usuario);
			return ResponseEntity.accepted().body(cliente);
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un array o un error.
	@RequestMapping(value = "/getProductos",method = RequestMethod.GET, 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity getProductos() {
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			ArrayList<Producto> productos = canjeHandler.getProductos();
			return ResponseEntity.accepted().body(productos);
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/crearCompra",method = RequestMethod.GET, 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> crearCompra(@RequestHeader(value="compra") Compra compra) {
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			canjeHandler.crearCompra(compra);
			return ResponseEntity.accepted().body("Compra finalizada con exito");
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un Integer o un error.
	@RequestMapping(value = "/getPuntos",method = RequestMethod.GET, 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity getDatosCliente(@RequestHeader(value="cliente") Cliente cliente) {
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			Cliente resultado = canjeHandler.getDatosCliente(cliente);
			return ResponseEntity.accepted().body(resultado.getPuntosAcumulados());
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
