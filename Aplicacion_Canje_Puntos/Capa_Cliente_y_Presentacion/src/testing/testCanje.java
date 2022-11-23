package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import clasesPrograma.*;
import requestBuilder.RequestBuilder;

/**
*
* @author AJRP2000
*/
public class testCanje {

	public testCanje() {}
	
	/**
     * Test of area method, of frontEnd API Calls.
     */	
	
	@Test
	void testLogIn() {
		try {
			Usuario usuario = new Usuario("usuario", "contrasena", 0);
			Cliente cliente = RequestBuilder.requestLogIn(usuario);
			int dniEsperado = 20266;
			String nombreEsperado = "Pepe";
			String direccionEsperada = "1020 Calle";
			assertEquals(cliente.getDNI(),dniEsperado,0);
			assertEquals(cliente.getNombre(),nombreEsperado);
			assertEquals(cliente.getDireccion(), direccionEsperada);
			
		}catch(Exception e) {}
	}
	
	@Test
	void testGetProductos() {
		try {
		ArrayList<Producto> productosObtenidos = (ArrayList<Producto>) RequestBuilder.requestGetProductos();
		Gson gson = new Gson();
		String resultadoObtenido = gson.toJson(productosObtenidos);
		
		ArrayList<Producto> productosEsperados = new ArrayList<Producto>();
		Producto producto1 = new Producto(0,"Lapiz", 20);
		Producto producto2 = new Producto(1,"Papel", 50);
		Producto producto3 = new Producto(2,"Boligrafo", 70);
		Producto producto4 = new Producto(3,"Liquid Paper", 10);
		Producto producto5 = new Producto(4,"Cuaderno", 100);
		
		productosEsperados.add(producto1);
		productosEsperados.add(producto2);
		productosEsperados.add(producto3);
		productosEsperados.add(producto4);
		productosEsperados.add(producto5);
		
		String resultadoEsperado = gson.toJson(productosEsperados);
		
		assertEquals(resultadoObtenido, resultadoEsperado);
		} catch(Exception e) {}
	}
	
	@Test /*Este test depende de que el primer test sea funcional dado que no podemos saber la cantidad de puntos
	que tiene el cliente desde el frontend, por tanto hay que hacer una llamada desde el LogIn. */
	void testGetPuntos() {
		try {
			Usuario usuario = new Usuario("usuario", "contrasena", 0);
			Cliente clienteEsperado = RequestBuilder.requestLogIn(usuario);
			Cliente clienteObtenido = RequestBuilder.requestGetPuntos(clienteEsperado);
			
			assertEquals(clienteEsperado.getPuntosAcumulados(), clienteObtenido.getPuntosAcumulados());
			
		}catch (Exception e) {}
	}
	
	@Test
	void testCompra() {
		try {
			Usuario usuario = new Usuario("usuario", "contrasena", 0);
			Cliente cliente = RequestBuilder.requestLogIn(usuario);
			
			ArrayList<Producto> productos = new ArrayList<Producto>();
			Producto producto1 = new Producto(0,"", 0);
			Producto producto2 = new Producto(1,"", 0);
			Producto producto3 = new Producto(2,"", 0);
			Producto producto4 = new Producto(3,"", 0);
			Producto producto5 = new Producto(4,"", 0);
			
			productos.add(producto1);
			productos.add(producto2);
			productos.add(producto3);
			productos.add(producto4);
			productos.add(producto5);
			
			String respuesta = RequestBuilder.requestCrearCompra(cliente, productos);
			
			if(cliente.getPuntosAcumulados()> 250)
				assertEquals("Compra finalizada con exito", respuesta);
				
		}catch(Exception e) {}
		
	}
}
