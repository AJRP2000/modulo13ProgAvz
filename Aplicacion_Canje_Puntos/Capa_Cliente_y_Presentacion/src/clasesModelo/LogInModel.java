package clasesModelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import clasesPrograma.Cliente;
import clasesPrograma.Usuario;
import clasesVista.JFrameCanje;
import clasesVista.JFrameLogIn;
import exceptions.CanjeException;
import requestBuilder.RequestBuilder;

public class LogInModel {
	
	private JFrameLogIn frame;
	
	public LogInModel(JFrameLogIn frame) {
		this.frame=frame;
	}
	
	public void logInUsuario(Usuario usuario) {
		try {
			Cliente cliente = null;
			
			cliente = RequestBuilder.requestLogIn(usuario);
			
		    
			if (cliente!=null) {
				frame.dispose();
				JFrameCanje frameCanje = new JFrameCanje(cliente);
				frameCanje.setSize(800,650);
				frameCanje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frameCanje.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(frame, "El Usuario y/o contrasena ingresado son invalidos");
		} catch (CanjeException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error: " +e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error inesperado");
		}
	}
}
