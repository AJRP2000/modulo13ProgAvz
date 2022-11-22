package clasesModelo;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import clasesPrograma.Usuario;
import clasesVista.JFrameCanje;
import clasesVista.JFrameLogIn;
import exceptions.CanjeException;
import repositorioRemoto.repositorioRemoto;

public class LogInModel {
	
	private JFrameLogIn frame;
	private String path = repositorioRemoto.getPath();
	
	public LogInModel(JFrameLogIn frame) {
		this.frame=frame;
	}
	
	public void logInUsuario(Usuario usuario) {
		try {
			boolean resultado=usuariosBO.checkUser(usuario);
			if (resultado==true) {
				frame.dispose();
				JFrameCanje frameCanje = new JFrameCanje();
				frameCanje.setSize(800,650);
				frameCanje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frameCanje.setVisible(true);
			}
			else
				JOptionPane.showMessageDialog(frame, "El Usuario y/o contrasena ingresado son invalidos");
		} catch(CanjeException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Ha ocurrido un error: " + e.getMessage());
		}
	}
}
