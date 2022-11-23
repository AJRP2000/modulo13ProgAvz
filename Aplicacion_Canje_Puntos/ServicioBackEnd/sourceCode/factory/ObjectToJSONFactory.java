package factory;

import com.google.gson.Gson;

import clasesPrograma.Cliente;
import clasesPrograma.Compra;
import clasesPrograma.Producto;
import clasesPrograma.Usuario;

public class ObjectToJSONFactory {

	private static ObjectToJSONFactory instancia;
	private static Gson gson;
	
	private ObjectToJSONFactory() {
		gson = new Gson();
	}
	
	public static ObjectToJSONFactory getInstance() {
		if(instancia==null)
			instancia = new ObjectToJSONFactory();
		return instancia;
	}
	
	public String convertCliente(Cliente cliente) {
		String resultado = gson.toJson(cliente);
		return resultado;
	}

	public String convertUsuario(Usuario usuario) {
		String resultado = gson.toJson(usuario);
		return resultado;
	}

	public String convertProducto(Producto producto) {
		String resultado = gson.toJson(producto);
		return resultado;
	}

	public String convertCompra(Compra compra) {
		String resultado = gson.toJson(compra);
		resultado = resultado.replace("[", "%5B");
		resultado = resultado.replace("]", "%5D");
		resultado = resultado.replace("{", "%7B");
		resultado = resultado.replace("}", "%7D");
		resultado = resultado.replace("\"", "%22");
		return resultado;
	}

}
