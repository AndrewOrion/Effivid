/**
 * @authors Andrés Pino y Alberto Peinado 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;
import modelo.Persona;

public class PersonaDAO 
{
private ConexionBD conexion;
	
	public PersonaDAO()
	{
		this.conexion = new ConexionBD();
	}
	
	//*******************************************************************************************************
		//FUNCIÓN MOSTRAR
	/**
	 * Función que muestra un Array con las personas existentes en la base de datos
	 * @return: devuelve la lista de personal que hay en la base de datos
	 */
		public ArrayList <Persona> obtenerPersonas()
		{
			//Obtenemos conexion a la base de datos.
			Connection con = conexion.getConexion();
			Statement consulta = null;
			ResultSet resultado = null;
			ArrayList<Persona> lista = new ArrayList<Persona>();
			String sSQL;
			
			try
			{
				sSQL = "SELECT * FROM personal";
				consulta = con.createStatement();
				resultado = consulta.executeQuery(sSQL);
				
				//Bucle para recorrer las filas que devuelve la consulta
				while(resultado.next())
				{
					int iId = resultado.getInt("id");
					int cod_empleado = resultado.getInt("cod_empleado");
					String sNombre = resultado.getString("nombre");
					String sTipo = resultado.getString("tipo");
					int iContrasena = resultado.getInt("contrasena");
					String sUsuario = resultado.getString("usuario");
					
					
					
					Persona pers = new Persona(iId, cod_empleado, sNombre,sTipo, iContrasena, sUsuario);
					lista.add(pers);
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
					resultado.close();
					consulta.close();
					conexion.desconectar();
				}
				catch (SQLException e)
				{
					System.out.println("Error al liberar recursos: " + e.getMessage());
				}
				
			}
			return lista;	
		}
		
	//*******************************************************************************************
		//FUNCIÓN QUE DEVUELVE UN ELEMENTO PEDIDO (1 persona)
		/**
		 * Funcion que devuelve 1 persona correspondiente al usuario y contraseña pasados como parametros
		 * @param usuario: String que se corresponde con la columna usuario de la base de datos. Sirve como acreditación para el Log in.
		 * @param contrasena: numerico entero que pertenece a cada usuario. Cada usuario tiene una contraseña diferente
		 * @return: devuelve la persona a la que corresponden el usuario y la contraseña que pasamos como parametro.
		 */
		public Persona obtenerPersona(String usuario, int contrasena)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			ResultSet resultado = null;
			Persona persona = null;
			
			String sSQL;
			try
			{
				sSQL = "SELECT * FROM personal where contrasena = ? AND usuario = ?";
				consulta = con.prepareStatement(sSQL);
				consulta.setInt(1, contrasena);
				consulta.setString(2, usuario);
				resultado = consulta.executeQuery();
				
				//Sólo puede devolver una fila si la hay
				if(resultado.next())
				{
					int iId = resultado.getInt("id");
					int iCod_empleado = resultado.getInt("cod_empleado");
					String sNombre = resultado.getString("nombre");
					String sTipo = resultado.getString("tipo");
					
					persona = new Persona(iId, iCod_empleado, sNombre, sTipo, contrasena, usuario);
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
			        if (resultado != null) resultado.close();
			        if (consulta != null) consulta.close();
			        conexion.desconectar();
			    }
			    catch (SQLException e)
			    {
			        System.out.println("Error al liberar recursos: " + e.getMessage());
			    }
			}
			return persona;
		}
		
		// **********************************************************************************************
		// FUNCIÓN VALIDAR LOGIN
		/**
		 * Funcion que devuelve un booleano como respuesta al usuario y contraseña pasados como parametros
		 * @param contrasena: numerico entero que pertenece a cada usuario. Cada usuario tiene una contraseña diferente.
		 * @param usuario: String que se corresponde con la columna usuario de la base de datos. Sirve como acreditación para el Log in.
		 * @return: devuelve TRUE si los parametros pasados (contraseña y usuario) pertenecen a una persona de la base de datos, FALSE en caso contrario.
		 */
		public boolean validarLogin(int contrasena, String usuario) 
		{
			
			Connection con = conexion.getConexion();
		    PreparedStatement statement = null;
		    ResultSet resultado = null;

		    try 
		    {
		        String query = "SELECT * FROM personal WHERE usuario = ? AND contrasena = ?";
		        statement = con.prepareStatement(query);
		        statement.setString(1, usuario);
		        statement.setInt(2, contrasena);
		        resultado = statement.executeQuery();
		        return resultado.next(); // Devuelve TRUE si la consulta devuelve resultados, FALSE en caso contrario.    
		    }
		    catch (SQLException e)
			{
				System.out.println("Error al realizar la consulta: " + e.getMessage());
			}
			finally
			{
			    try
			    {
			        if (resultado != null) resultado.close();
			        if (statement != null) statement.close();
			        conexion.desconectar();
			    }
			    catch (SQLException e)
			    {
			        System.out.println("Error al liberar recursos: " + e.getMessage());
			    }
			}
			return false;
		}
		
		//DEVOLVER NOMBRE COMPLETO USUARIO
		//FUNCIÓN QUE DEVUELVE UN ELEMENTO PEDIDO (1 persona)
		/**
		 * Funcion que devuelve el nombre completo de la persona que ha accedido a la aplicacion, para que se muestre en la ventanaUsuario.
		 * @param usuario: String pasado como parametro que corresponde al nombre de usuario para acceder a la aplicacion.
		 * @return: devuelve apellidos y nombre completos del usuario que ha accedido a la aplicacion
		 */
		public Persona obtenerNombre(String usuario)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			ResultSet resultado = null;
			Persona persona = null;
			
			String sSQL;
			try
			{
				sSQL = "SELECT * FROM personal where usuario = ?";
				consulta = con.prepareStatement(sSQL);
				consulta.setString(1, usuario);
				resultado = consulta.executeQuery();
				
				//Sólo puede devolver una fila si la hay
				if(resultado.next())
				{
					int iId = resultado.getInt("id");
					int iCod_empleado = resultado.getInt("cod_empleado");
					String sNombre = resultado.getString("nombre");
					String sTipo = resultado.getString("tipo");
					int contrasena = resultado.getInt("contrasena");
					persona = new Persona(iId, iCod_empleado, sNombre, sTipo, contrasena, usuario);
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
			        if (resultado != null) resultado.close();
			        if (consulta != null) consulta.close();
			        conexion.desconectar();
			    }
			    catch (SQLException e)
			    {
			        System.out.println("Error al liberar recursos: " + e.getMessage());
			    }
			}
			return persona;
		}
}