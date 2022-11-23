package tableModels;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clasesPrograma.Producto;

public class ProductoTableModel extends AbstractTableModel {

	private List<Producto> productos;
	private static final int idProducto = 0;
	private static final int descripcion = 1;
	private static final int costoPuntos =2;
	
	private String[] headers = {"IdProducto","Descripcion","Costo en Puntos"};

	public ProductoTableModel(List<Producto> productos) {
		this.productos=productos;
		
	}

	@Override
	public int getRowCount() {
		return productos.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return headers[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Producto producto = productos.get(rowIndex);
		switch (columnIndex) {
			case idProducto: return producto.getIdProducto();
			case descripcion: return producto.getDescripcion();
			case costoPuntos: return producto.getCostoPuntos();
		}
		return null;
	}
}
