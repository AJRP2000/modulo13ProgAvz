package factory;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import clasesPrograma.Producto;
import tableModels.ProductoTableModel;

public class TablaFactory {

	public static JScrollPane crearTablaProductos(List<Producto> programas) {
		ProductoTableModel modeloTabla = new ProductoTableModel(programas);
		JTable tabla = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tabla);
		return scrollPane;
	}
}
