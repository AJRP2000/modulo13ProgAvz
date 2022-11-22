package clasesPrograma;

import java.util.ArrayList;

public class Compra {
	private ArrayList<Producto> listaProductos;
	private int idCompra;
	private int dniCliente;
	private String fecha;
	private int totalPuntos;

	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public void addProducto(Producto producto) {
		listaProductos.add(producto);
	}
	
	public void removeProducto(Producto producto) {
		listaProductos.remove(producto);
	}
	
	public int getPuntosTotal() {
		int puntos = 0;
		for(int i = 0; i<listaProductos.size(); i++) {
			puntos = puntos + listaProductos.get(i).getCostoPuntos();
		}
		return puntos;
	}

	public int getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(int dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}
	
	public int getTotalPuntos() {
		return totalPuntos;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
}
