package clasesVista;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import clasesModelo.FrameCanjeModel;
import clasesPrograma.Cliente;

public class JFrameCanje extends JFrame {
	
	private Cliente cliente;
	private FrameCanjeModel canjeModelo;
	private JMenuBar menuBar;
	private JMenu productoMenu, compraMenu, clienteMenu;
	private JMenuItem verProductos;
	private JMenuItem armarCanje;
	private JMenuItem verPuntos;
	
	public JFrameCanje(Cliente cliente) {
		this.setCliente(cliente);
		this.canjeModelo = new FrameCanjeModel(this);
		initUI();
	}
	
	private void initUI() {
		setLayout(new FlowLayout());
		setTitle("Aplicacion Canje Puntos");
		
		
		//Crear la barra de menu
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		//Crear Menu Productos
		productoMenu = new JMenu("Programas");
		
		
		verProductos = new JMenuItem("Ver Todos los Productos");
		verProductos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canjeModelo.panelVerProductos();
			}
		});
		productoMenu.add(verProductos);
		
		menuBar.add(productoMenu);
		
		//Crear Menu Compras
		compraMenu = new JMenu("Compras");
		
		armarCanje = new JMenuItem("Hacer un Canje de Puntos");
		armarCanje.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canjeModelo.panelArmarCanje();
			}
		});
		compraMenu.add(armarCanje);
		
		menuBar.add(compraMenu);
		
		//Crear Menu Cliente
		clienteMenu = new JMenu("Usuario");

		verPuntos = new JMenuItem("Ver puntos acumulados");
		verPuntos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canjeModelo.mostrarPuntosAcumulados(cliente);
			}
		});
		clienteMenu.add(verPuntos);
		
		menuBar.add(clienteMenu);
	}
	
	//Crea una tabla en el frame usando la tabla parametro que recibe.
	public void cambiarPanelTabla(JScrollPane tabla) {
		getContentPane().removeAll();
		getContentPane().add(tabla);
		JButton botonCancelar = new JButton("Volver al Inicio");
		botonCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarPantalla();
				
			}
		});
		getContentPane().add(botonCancelar);
		repaint();
        printAll(getGraphics());
	}
	
	public void borrarPantalla() {
		getContentPane().removeAll();
		repaint();
        printAll(getGraphics());
	}
	
	//Llena el frame con el panel que recibe de parametro.
	public void cambiarPanel(JPanel panel) {
		getContentPane().removeAll();
		getContentPane().add(panel);
		repaint();
        printAll(getGraphics());
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
