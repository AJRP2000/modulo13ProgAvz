package requestBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.parser.ParseException;

import clasesPrograma.*;
import exceptions.CanjeException;
import factory.JSONToObjectFactory;
import factory.ObjectToJSONFactory;

public class RequestBuilder {
			
	private static final String path = "http://localhost:8080/";
	
	public static Cliente requestLogIn(Usuario usuario) throws Exception {
		
		Cliente resultado = null;
		StringBuilder result = new StringBuilder();
		String parametros = "?usuario=" + usuario.getUsuario()+"&contrasena=" + usuario.getContrasena();
		URL url = new URL(path + "logIn" + parametros);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
		    resultado = jsonToObjectFactory.convertCliente(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new CanjeException (result.toString());
	    }
	    
	    return resultado;
	}
	
	public static List<Producto> requestGetProductos() throws Exception {
		
		List<Producto> productos = null;
		StringBuilder result = new StringBuilder();
		//String parametros = "?usuario=" + usuario.getUsuario()+"&contrasena=" + usuario.getContrasena();
		URL url = new URL(path + "getProductos" /*+ parametros */);
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
		    productos = jsonToObjectFactory.convertListaProductos(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new CanjeException (result.toString());
	    }
	    
	    return productos;
	}
	
	public static String requestCrearCompra(Cliente cliente, ArrayList<Producto> productos) throws Exception {
		
		Compra compra = new Compra();
		compra.setDniCliente(cliente.getDNI());
		compra.setListaProductos(productos);
		compra.setTotalPuntos(0);
		ObjectToJSONFactory factory = ObjectToJSONFactory.getInstance();
		String json = factory.convertCompra(compra);

		
		String resultado = null;
		
		StringBuilder result = new StringBuilder();
		String parametros = "?compraJson=" + json;
		URL url = new URL(path + "crearCompra" + parametros );
		System.out.println(path + "crearCompra" + parametros );
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    resultado = result.toString();
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new CanjeException (result.toString());
	    }
	    System.out.println(path + "crearCompra" + parametros );
			if (resultado==null) 
				throw new CanjeException (result.toString());
			
		return resultado;
	}
	
	public static Cliente requestGetPuntos(Cliente cliente) throws Exception {
		Cliente clienteResultado = null;
		
		StringBuilder result = new StringBuilder();
		String parametros = "?dniCliente=" + cliente.getDNI();
		URL url = new URL(path + "getPuntos" + parametros );
		//System.out.println(path + "logIn" + parametros);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    if(conn.getResponseCode() < HttpURLConnection.HTTP_BAD_REQUEST) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getInputStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
		    if(result.toString() !="" && result != null) {
		    JSONToObjectFactory jsonToObjectFactory = JSONToObjectFactory.getInstance();
		    clienteResultado = jsonToObjectFactory.convertCliente(result.toString());
		    }
		}
	    else {
	    	//System.out.println("Mostrando error: ");
	    	try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(conn.getErrorStream()))) {
		          	for (String line; (line = reader.readLine()) != null; ) {
		            result.append(line);
		            }
		    	}
	    	throw new CanjeException (result.toString());
	    }
	    
	    return clienteResultado;
	}

}
