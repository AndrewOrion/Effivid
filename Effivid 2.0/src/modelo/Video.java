package modelo;

import java.sql.Date;
import java.util.Objects;

public class Video 
{
	private int cod_video;
	private String nombre;
	private int ref_producto;
	private int puesto;
	private Date fecha_subida;
	//CONSTRUCTORS
	public Video(int cod_video, String nombre, int ref_producto, int puesto, Date fecha_subida) {
		super();
		this.cod_video = cod_video;
		this.nombre = nombre;
		this.ref_producto = ref_producto;
		this.puesto = puesto;
		this.fecha_subida = fecha_subida;
	}
	
	public Video() {
		super();
	}
	
	

	public Video(String nombre, int ref_producto, int puesto, Date fecha_subida) {
		super();
		this.nombre = nombre;
		this.ref_producto = ref_producto;
		this.puesto = puesto;
		this.fecha_subida = fecha_subida;
	}

	//Getters and setters
	public int getCod_video() {
		return cod_video;
	}
	public void setCod_video(int cod_video) {
		this.cod_video = cod_video;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRef_producto() {
		return ref_producto;
	}
	public void setRef_producto(int ref_producto) {
		this.ref_producto = ref_producto;
	}
	
	public int getPuesto() {
		return puesto;
	}
	
	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}
	
	public Date getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(Date fecha_subida) {
		this.fecha_subida = fecha_subida;
	}
	
	//HASH CODE (id_videos)
	@Override
	public int hashCode() {
		return Objects.hash(cod_video);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return cod_video == other.cod_video;
	}

	@Override
	public String toString() {
		return "Video [cod_video=" + cod_video + ", nombre=" + nombre + ", ref_producto=" + ref_producto + ", puesto="
				+ puesto + ", fecha_subida=" + fecha_subida + "]";
	}

	
	
	
	
	
}