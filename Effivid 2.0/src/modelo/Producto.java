package modelo;

import java.util.Objects;

public class Producto 
{
	private int Ref;
	private String descripcion;
	private String denominacion;
	private String modelo;
	private int personas;
	
	//Constructors
	public Producto(int ref, String descripcion, String denominacion, String modelo, int personas) 
	{
		super();
		Ref = ref;
		this.descripcion = descripcion;
		this.denominacion = denominacion;
		this.modelo = modelo;
		this.personas = personas;
	}
	
	public Producto() 
	{
		super();
	}
	
	public Producto (int ref) {
		this.Ref=ref;
	}

	//Getters and setters
	public int getRef() {
		return Ref;
	}
	public void setRef(int ref) {
		Ref = ref;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getPersonas() {
		return personas;
	}
	public void setPersonas(int personas) {
		this.personas = personas;
	}
	
	//Hash code (Ref)
	@Override
	public int hashCode() {
		return Objects.hash(Ref);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Ref == other.Ref;
	}
	
	//To String
	@Override
	public String toString() 
	{
		return "Producto [Ref=" + Ref + ", descripcion=" + descripcion + ", denominacion=" + denominacion + ", modelo="
				+ modelo + ", personas=" + personas + "]";
	}
	
	
	
}
