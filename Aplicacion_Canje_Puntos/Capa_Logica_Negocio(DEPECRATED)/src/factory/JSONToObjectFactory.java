package factory;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import clasesPrograma.*;

public class JSONToObjectFactory {
	
	private static JSONToObjectFactory instancia;
	private static JSONParser parser;
	
	private JSONToObjectFactory() {
		parser = new JSONParser();
	}
	
	public static JSONToObjectFactory getInstance() {
		if(instancia==null)
			instancia = new JSONToObjectFactory();
		return instancia;
	}
	
	public Cliente convertCliente(String jsonString) throws ParseException {
	    JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Cliente resultado = new Cliente(((Long)json.get("dni")).intValue(), 
	    		json.get("nombre").toString(), 
	    		json.get("direccion").toString(), 
	    		((Long)json.get("puntosAcumulados")).intValue());
	    return resultado;
	}
	
	public Usuario convertUsuario(String jsonString) throws ParseException {
		JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Usuario resultado = new Usuario(json.get("usuario").toString(), 
	    		json.get("contrasena").toString(), 
	    		((Long)json.get("dni")).intValue());
	    return resultado;
	}
	
	public Producto convertProducto(String jsonString) throws ParseException {
		JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Producto resultado = new Producto( ((Long)json.get("idProducto")).intValue(),
	    		json.get("descripcion").toString(),
	    		((Long)json.get("costoPuntos")).intValue() );
		
		return resultado;
	}
	
	public Producto convertProducto(JSONObject json) {
		Producto resultado = new Producto( ((Long)json.get("idProducto")).intValue(),
		    		json.get("descripcion").toString(),
		    		((Long)json.get("costoPuntos")).intValue() );
			
		return resultado;
	}
	
	public ArrayList<Producto> convertListaProductos(String jsonString) throws ParseException{
		ArrayList<Producto> resultado = new ArrayList<Producto>();
		JSONArray jsonArray = (JSONArray) parser.parse(jsonString.toString());
		for(int i = 0; i<jsonArray.size(); i++) {
			resultado.add(convertProducto((JSONObject)jsonArray.get(i)));
		}
		return resultado;
		
	}
	
	public Compra convertCompra(String jsonString) throws ParseException {
		jsonString = jsonString.replace("%5B", "[");
		jsonString = jsonString.replace("%5D", "]");
		jsonString = jsonString.replace("%7B", "{");
		jsonString = jsonString.replace("%7D", "}");
		jsonString = jsonString.replace("%22", "\"");
		
		JSONObject json = (JSONObject) parser.parse(jsonString.toString());
	    Compra resultado = new Compra();
	    resultado.setDniCliente(((Long)json.get("dniCliente")).intValue());
	    resultado.setIdCompra(((Long)json.get("idCompra")).intValue());
	    resultado.setFecha(json.get("dniCliente").toString());
	    resultado.setTotalPuntos(((Long)json.get("totalPuntos")).intValue());
	    String jsonListaProductos = json.get("listaProductos").toString();
	    ArrayList<Producto> listaProductos = convertListaProductos(jsonListaProductos);
	    resultado.setListaProductos(listaProductos);
		
		return resultado;
	}

}
