package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD 
{
	// Parámetros de la conexión
			private static String database="efficold";
			private static String usuario="root";
			private static String contraseña="1235";
			private static String url="jdbc:mysql://localhost/"+database;

			private Connection conexion = null;
			
			public Connection getConexion()
			{
				if (conexion!=null)
				{
					return conexion;
				}
				try
				{
					// Registramos el driver de MySQL controlando la
					//  exepcion ClassNotFoundException
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					// Obtenemos la conexión a través del DriverManager.
					// Este código es susceptible de enviar una excepción
					//  SQLException si no se establece la conexión
					conexion = DriverManager.getConnection(url,usuario,contraseña);
					System.out.println("Conexión a base de datos correcta.");
				}
				catch (ClassNotFoundException e)
				{
					//e.printStackTrace();
					System.out.println("El driver no se ha instalado");
				}
				catch (SQLException e)
				{
					System.out.println("Error de base de datos.");
					e.printStackTrace();
				}
				return conexion;
			}
			
			public void desconectar()
			{
				try
				{
					this.conexion.close();
					System.out.println("Conexión cerrada.");
				}
				catch (SQLException e)
				{
					System.out.println("Error cerrando la conexión.");
					e.printStackTrace();
				}
			}
}
