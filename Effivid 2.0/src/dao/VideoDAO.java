package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.sql.Date;
import conexion.ConexionBD;
import modelo.Video;

public class VideoDAO 
{
private ConexionBD conexion;
	
	public VideoDAO()
	{
		this.conexion = new ConexionBD();
	}
	
	//FUNCIÓN MOSTRAR VÍDEOS CON REFERENCIA
	public ArrayList <Video> obtenerVideos(int Ref)
	{
		//Obtenemos conexion a la base de datos.
		Connection con = conexion.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Video> lista = new ArrayList<Video>();
		String sSQL;	
		try
		{
			sSQL = "SELECT puesto, nombre, fecha_subida FROM videos WHERE ref_producto = ? ORDER BY puesto ASC;";
			pstmt = con.prepareStatement(sSQL);
			pstmt.setInt(1, Ref);
			rs = pstmt.executeQuery();
			
			//Bucle para recorrer las filas que devuelve la consulta
			while(rs.next())
			{
			//	int iCod_video = rs.getInt("codigo_video");
				int iPuesto = rs.getInt("puesto");
				String sNombre = rs.getString("nombre");
				Date fechaActual = rs.getDate("fecha_subida");

				Video vid = new Video(sNombre, Ref, iPuesto, fechaActual);
				lista.add(vid);
			}
		}
		catch (SQLException e)
		{
			System.out.println("Error al realizar la consulta: " + e.getMessage());
		}
		finally
		{
			try
			{
				 if (rs != null) 
				 {
			            rs.close();
				 }
				pstmt.close();
				conexion.desconectar();
			}
			catch (SQLException e)
			{
				System.out.println("Error al liberar recursos: " + e.getMessage());
			}
			
		}
		return lista;	
	}
	/*
	//FUNCIÓN MOSTRAR VÍDEOS CON NOMBRE
		public ArrayList <Video> obtenerVideosNombre(String nombre)
		{
			//Obtenemos conexion a la base de datos.
			Connection con = conexion.getConexion();
			PreparedStatement pstmt2 = null;
			ResultSet rs2 = null;
			ArrayList<Video> lista2 = new ArrayList<Video>();
			String sSQL2;
			
			try
			{
				sSQL2 = "SELECT * FROM videos WHERE nombre LIKE '%?%' ORDER BY nombre ASC;";
				pstmt2 = con.prepareStatement(sSQL2);
				pstmt2.setString(1, nombre);
				rs2 = pstmt2.executeQuery();
				
				//Bucle para recorrer las filas que devuelve la consulta
				while(rs2.next())
				{
					
					int iRef = rs2.getInt("ref_producto");
					int iCod_video = rs2.getInt("codigo_video");
					
									
					Video video = new Video(iCod_video, nombre, iRef);
					lista2.add(video);
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error al realizar la consulta: " + e.getMessage());
			}
			finally
			{
				try
				{
					 if (rs2 != null) 
					 {
				            rs2.close();
					 }
					pstmt2.close();
					conexion.desconectar();
				}
				catch (SQLException e)
				{
					System.out.println("Error al liberar recursos: " + e.getMessage());
				}
				
			}
			return lista2;	
		}*/
}