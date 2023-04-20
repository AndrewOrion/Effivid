/**
 * @author Alberto Peinado Castillo y Andrés Pino
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Producto;
import modelo.Video;

public class VideoDAO 
{
private ConexionBD conexion;
	
	public VideoDAO()
	{
		this.conexion = new ConexionBD();
	}
	// *******************************************************************************************
	//FUNCIÓN MOSTRAR VÍDEOS CON REFERENCIA
	/**
	 * Función que devuelve un arraylist con los vídeos pertenecientes a la referencia del producto pasada como parámetro
	 * @param Ref: número entero que se corresponde con el número de referencia del producto
	 * @return: devuelve la lista de videos pertenecientes al número de referencia seleccionado
	 */
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
			sSQL = "SELECT nombre FROM videos WHERE ref_producto = ? ORDER BY nombre ASC;";
			pstmt = con.prepareStatement(sSQL);
			pstmt.setInt(1, Ref);
			rs = pstmt.executeQuery();
			
			//Bucle para recorrer las filas que devuelve la consulta
			while(rs.next())
			{
				int iCod_video = rs.getInt("codigo_video");
				String sNombre = rs.getString("nombre");
								
				Video vid = new Video(iCod_video, sNombre, Ref);
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
	
	
	//**************************************************************************************************
/*	//FUNCIÓN MOSTRAR VÍDEOS CON NOMBRE
		/**
		 * Función que devuelve una lista de videos correspondientes al nombre pasado como parámetro
		 * @param nombre: String recuperado de un combobox que se corresponde con el nombre del producto del que deseamos ver el vídeo
		 * @return: lista de videos que se corresponden con el nombre seleccionado en el combobox de la VentanaUsuario
		 * NOTA: esta funcion esta comentada debido a que para recuperar el nombre correctamente, deberiamos tener todos los videos organizados en carpetas en C:\ con los nombres
		 * del producto seleccionado
		 */
/*		public ArrayList <Video> obtenerVideosNombre(String nombre)
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
		}
		*/
		
		// *********************************************************************************************************
		//FUNCIÓN INSERTAR VIDEO
		/**
		 * Funcion para insertar videos en la base de datos y en la aplicacion
		 * @param video: el parametro video se corresponde con, una vez hecha la seleccion del video que queremos añadir, los elementos del modelo: video, creando uno nuevo
		 * 				recuperando todos los parametros necesarios (en este caso nombre y referencia del producto)
		 * @return: devuelve 1 si el video se ha insertado correctamente y un 0 en caso contrario.
		 */
		public int insertarVideo (Video video)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado = 0;
			String sSQL;
			
			try
			{
				sSQL = "INSERT INTO (nombre, ref_producto)" +
						"VALUES (?,?)";
				consulta = con.prepareStatement(sSQL);
				consulta.setString(1, video.getNombre());
				consulta.setInt(2, video.getRef_producto());
				
				resultado = consulta.executeUpdate();
				System.out.println("Video insertado.");
			}
			catch (SQLException e)
			{
				System.out.println("Error al realizar la consulta: " + e.getMessage());
			}
			finally
			{
				try
				{
					consulta.close();
					conexion.desconectar();
				}
				catch (SQLException e)
				{
					System.out.println("Error al liberar recursos: " + e.getMessage());
				}
			}
			
			return resultado;
		}
		
		// **************************************************************************************************************
		// FUNCIÓN ELIMINAR VIDEO SELECCIONADO 
		/**
		 * Funcion para eliminar un video seleccionado
		 * @param codigo_video: entero numerico que se corresponde con el codigo de video que queremos eliminar
		 * @return: devuelve un 1 si el video se ha borrado correctamente y un 0 en caso contrario.
		 */
		public int eliminarVideo(int codigo_video)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado = 0;
			String sSQL;
			
			try
			{
				sSQL = "DELETE FROM libros WHERE codigo_video = ?;";
				consulta = con.prepareStatement(sSQL);
				consulta.setInt(1, codigo_video);
				resultado = consulta.executeUpdate();
				
				if(resultado != 0)
				{
					System.out.println("Video borrado.");
				}
				else
				{
					System.out.println("Seleccione video para borrar.");
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error al realizar el borrado:" + e.getMessage());
			}
			finally
			{
				try
				{
					consulta.close();
					conexion.desconectar();
				}
				catch (SQLException e)
				{
					System.out.println("Error al liberar recursos:" + e.getMessage());
				}
				
			}
			return resultado;
		}
		
		// ******************************************************************************************************
		// MODIFICAR PUESTO DE VIDEO
		/**
		 * Función que modifica el puesto de un video pasado como referencia
		 * @param puesto : puesto a modificar
		 * @param codigo_video: codigo perteneciente al video del que queremos cambiar el puesto
		 * @return: devuelve 1 si se ha modificado el video o 0 en caso contrario.
		 */
		
		public int actualizarVideo (int puesto, int codigo_video)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			int resultado = 0;
			String sSQL;
			
			try
			{
				sSQL = "UPDATE videos SET puesto = ?, " +
					   "WHERE codigo_video = ?";
				consulta = con.prepareStatement(sSQL);
				consulta.setInt(1, puesto);
				consulta.setInt(2, codigo_video);
				
				resultado = consulta.executeUpdate();
				
				if (resultado != 0)
				{
					System.out.println("Video actualizada");
				}
			}
			catch (SQLException e)
			{
				System.out.println("Error al realizar la actualización: " + e.getMessage());
			}
			finally
			{
				try
				{
					consulta.close();
					conexion.desconectar();
				}
				catch(SQLException e)
				{
					System.out.println("Error al liberar recursos: " + e.getMessage());
				}
			}
			return resultado;
		}
}