package clasesVista;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import clasesModelo.FrameCanjeModel;
import exceptions.CanjeException;
import clasesPrograma.Producto;

public class FormularioCompra extends JPanel {

	private JTextArea idProductos;
	private FrameCanjeModel modelo;
	
	public FormularioCompra(FrameCanjeModel modelo) {
		this.modelo=modelo;
		initUI();
	}
	
	private void initUI() {
		setLayout(new BoxLayout( this , BoxLayout.Y_AXIS));		
		String titulo ="Realizar Canje";
		
		add(new JLabel(titulo));
		add(Box.createRigidArea(new Dimension(0,20)));
		
		panelID();
		
		add(Box.createRigidArea(new Dimension(0,10)));
		
		panelBotones();
	}
	
	private void panelID() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));	
		panel.add(new JLabel("ID"));
		idProductos = new JTextArea(3,30);
		panel.add(idProductos);
		add(panel);
	}
	
	public ArrayList<Producto> getListaProductos() throws CanjeException {
		ArrayList<Producto> resultado = null;
		try {
			String idProductos = this.idProductos.getText() + ",";
			int[] listaIdProductos = Arrays.stream(idProductos.substring(0, idProductos.length()-1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
			Producto producto = null;
			resultado = new ArrayList<Producto>();
			for(int i = 0; i <listaIdProductos.length; i++) {
				producto = new Producto(listaIdProductos[i], "", 0);
				resultado.add(producto);
				System.out.println(listaIdProductos[i]);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new CanjeException("Los Valores Ingresados no son validos");
		}
		
		return resultado;
	}
	
	private void panelBotones() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel , BoxLayout.X_AXIS));
		JButton botonContinuar = new JButton("Realizar Compra");
		botonContinuar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ArrayList<Producto> productos = getListaProductos();
					modelo.crearCompra(productos);
				}catch(CanjeException error) {
					modelo.mensajeError(error);
				}
			}
		});
		
		panel.add(botonContinuar);
		
		JButton botonCancelar= new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modelo.borrarPantalla();
			}
		});
		panel.add(botonCancelar);
		add(panel);
	}
	
}
