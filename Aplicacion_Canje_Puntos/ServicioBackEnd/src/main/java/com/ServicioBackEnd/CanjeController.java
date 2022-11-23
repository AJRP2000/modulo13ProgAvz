package com.ServicioBackEnd;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import canjeHandler.CanjeHandler;
import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;
import exceptions.CanjeException;
import factory.JSONToObjectFactory;

@Controller
public class CanjeController {

	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un cliente o un error.
	@GetMapping(value = "/logIn", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity logIn(@RequestParam(name="usuario") String usuario,
								@RequestParam(name="contrasena") String contrasena) {
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			Usuario usuarioObjeto = new Usuario(usuario,contrasena,0);
			Cliente cliente = canjeHandler.logIn(usuarioObjeto);
			return ResponseEntity.accepted().body(cliente);
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un array o un error.
	@GetMapping(value = "/getProductos",
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
	
	
	@GetMapping(value = "/crearCompra",
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> crearCompra(@RequestParam(name="compraJson") String compraJson) { 
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			JSONToObjectFactory factory = JSONToObjectFactory.getInstance();
			Compra compra = factory.convertCompra(compraJson);
			
			canjeHandler.crearCompra(compra);
			return ResponseEntity.accepted().body("Compra finalizada con exito");
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@SuppressWarnings({ "rawtypes" }) //No sabemos si devolvera un Cliente o un error.
	@GetMapping(value = "/getPuntos", 
	        		produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity getPuntos(@RequestParam(name="dniCliente") int dniCliente) {
		try {
			CanjeHandler canjeHandler = new CanjeHandler();
			Cliente clienteObjeto = new Cliente();
			clienteObjeto.setDNI(dniCliente);
			Cliente resultado = canjeHandler.getDatosCliente(clienteObjeto);
			return ResponseEntity.accepted().body(resultado);
		}catch(CanjeException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
