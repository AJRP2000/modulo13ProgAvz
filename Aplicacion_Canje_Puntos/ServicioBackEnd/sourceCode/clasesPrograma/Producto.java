package clasesPrograma;

public class Producto {

	private int idProducto;
	private String descripcion;
	private int costoPuntos;
	
	public Producto(int idProducto, String descripcion, int costoPuntos) {
		this.idProducto=idProducto;
		this.descripcion=descripcion;
		this.costoPuntos=costoPuntos;
	}
	
	public Producto() {}
	
	public int getCostoPuntos() {
		return costoPuntos;
	}
	
	public void setCostoPuntos(int costoPuntos) {
		this.costoPuntos=costoPuntos;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion=descripcion;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
}
