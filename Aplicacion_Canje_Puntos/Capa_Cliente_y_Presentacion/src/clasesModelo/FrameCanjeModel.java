package clasesModelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import clasesPrograma.*;
import clasesVista.FormularioCompra;
import clasesVista.JFrameCanje;
import exceptions.CanjeException;
import factory.TablaFactory;
import requestBuilder.RequestBuilder;

public class FrameCanjeModel {
	
	private JFrameCanje frame;
	public FrameCanjeModel(JFrameCanje frame ) {
		this.frame=frame;
	}

	
	
	//Ajusta el frame para mostrar todos los Productos
	public void panelVerProductos() {
		try {
			
			List<Producto> productos = null;
			productos = RequestBuilder.requestGetProductos();
		    
			if (productos!=null) {
				JScrollPane scrollPane = TablaFactory.crearTablaProductos(productos);
				frame.cambiarPanelTabla(scrollPane);
			}
			else
				JOptionPane.showMessageDialog(frame, "No existen Productos");
			} catch (CanjeException e) {
				e.printStackTrace();
				popUpFailure("Ha ocurrido un error: " +e.getMessage());
			} catch(Exception e) {
				e.printStackTrace();
				popUpFailure("Ha ocurrido un error inesperado");
			}
	}
	
	//Ajusta el frame para armar la compra
	public void panelArmarCanje() {
		frame.cambiarPanel(new FormularioCompra(this));
	}
	
	//Muestra los puntos acumulados
	public void mostrarPuntosAcumulados(Cliente cliente) {
		try {
			
			Cliente clienteResultado = null;
			
			clienteResultado = RequestBuilder.requestGetPuntos(cliente);
		    
			if (clienteResultado!=null) {
				JOptionPane.showMessageDialog(frame,"Usted tiene un total de: " + clienteResultado.getPuntosAcumulados() +" puntos acumulados");
				cliente.setPuntosAcumulados(clienteResultado.getPuntosAcumulados());;
				frame.setCliente(cliente);
			}
		} catch (CanjeException e) {
			e.printStackTrace();
			popUpFailure("Ha ocurrido un error: " +e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			popUpFailure("Ha ocurrido un error inesperado");
		}
		
		
	}
	
	public void popUpFailure(String texto) {
		JOptionPane.showMessageDialog(frame,texto);
	}
	
	public void borrarPantalla() {
		frame.borrarPantalla();
	}
	
	public void mensajeError(CanjeException error) {
		error.printStackTrace();
		popUpFailure("Ha ocurrido un error:" + error.getMessage());
	}
	
	public void crearCompra(ArrayList<Producto> productos) {
		try {
			String resultado = null;
			
			resultado = RequestBuilder.requestCrearCompra(frame.getCliente(), productos);
				if (resultado!=null) 
					JOptionPane.showMessageDialog(frame, resultado);
				
		} catch (CanjeException e) {
			e.printStackTrace();
			popUpFailure("Ha ocurrido un error: " +e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			popUpFailure("Ha ocurrido un error inesperado");
		}
		
		
		
	}
}
