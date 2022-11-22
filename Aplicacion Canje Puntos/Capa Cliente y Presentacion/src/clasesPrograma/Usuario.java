package clasesPrograma;

public class Usuario {

	private String usuario;
	private String contrasena;
	private int dni;
	
	public Usuario(String usuario, String contrasena, int dni) {
		this.usuario=usuario;
		this.contrasena=contrasena;
		this.dni=dni;
	}
	
	public Usuario() {}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getDNI() {
		return dni;
	}
	public void setDNI(int dni) {
		this.dni = dni;
	}
}
