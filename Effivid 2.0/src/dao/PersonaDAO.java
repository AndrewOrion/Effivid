package dao;

import java.sql.Connection;
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
					
					
					Persona pers = new Persona(iId, cod_empleado, sNombre,sTipo);
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
		public Persona obtenerPersona(int cod_empleado)
		{
			Connection con = conexion.getConexion();
			PreparedStatement consulta = null;
			ResultSet resultado = null;
			Persona persona = null;
			
			String sSQL;
			try
			{
				sSQL = "SELECT * FROM personal where cod_empleado = ?";
				consulta = con.prepareStatement(sSQL);
				consulta.setInt(1, cod_empleado);
				resultado = consulta.executeQuery();
				
				//Sólo puede devolver una fila si la hay
				if(resultado.next())
				{
					int iId = resultado.getInt("id");
					String sNombre = resultado.getString("nombre");
					String sTipo = resultado.getString("tipo");
					
					
					persona = new Persona(iId, cod_empleado, sNombre, sTipo);
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
			return persona;
		}
		
}