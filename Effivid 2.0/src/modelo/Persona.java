package modelo;

import java.util.Objects;

public class Persona 
{
	private int ID;
	private int cod_empleado;
	private String nombre;
	private String tipo;
	private int contrasena;
	private String usuario;
	
	//CONSTRUCTORS
	public int getID() {
		return ID;
	}
	
	public Persona(int iD, int cod_empleado, String nombre, String tipo, int contrasena, String usuario) {
		super();
		ID = iD;
		this.cod_empleado = cod_empleado;
		this.nombre = nombre;
		this.tipo = tipo;
		this.contrasena = contrasena;
		this.usuario = usuario;
	}
	
	//Getters and setters
	public void setID(int iD) {
		ID = iD;
	}
	public int getCod_empleado() {
		return cod_empleado;
	}
	public void setCod_empleado(int cod_empleado) {
		this.cod_empleado = cod_empleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getContrasena() {
		return contrasena;
	}
	public void setContrasena(int contrasena) {
		this.contrasena = contrasena;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	//Hash code and equals
	@Override
	public int hashCode() {
		return Objects.hash(cod_empleado);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return cod_empleado == other.cod_empleado;
	}
	
	//To String
	@Override
	public String toString() {
		return "Persona [ID=" + ID + ", cod_empleado=" + cod_empleado + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", contrasena=" + contrasena + ", usuario=" + usuario + "]";
	}
	
	
	
}