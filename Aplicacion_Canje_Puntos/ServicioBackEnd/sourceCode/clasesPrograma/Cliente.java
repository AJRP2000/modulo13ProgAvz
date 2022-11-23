package clasesPrograma;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Cliente {
	private int dni;
	private String nombre;
	private String direccion;
	private int puntosAcumulados;
	
	public Cliente(int dni, String nombre, String direccion, int puntosAcumulados) {
		this.dni=dni;
		this.nombre=nombre;
		this.direccion=direccion;
		this.puntosAcumulados=puntosAcumulados;
	}
	
	public Cliente()  {}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDNI() {
		return dni;
	}
	public void setDNI(int dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

}
