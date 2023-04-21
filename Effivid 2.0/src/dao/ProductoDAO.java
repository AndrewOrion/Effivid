/**
 * @authors Andrés Pino y Alberto Peinado 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionBD;

import modelo.Producto;

public class ProductoDAO 
{
private ConexionBD conexion;
	
	public ProductoDAO()
	{
		this.conexion = new ConexionBD();
	}
	//*******************************************************************************************************
		//FUNCIÓN MOSTRAR DENOMINACIÓN DEL PRODUCTO
	
	/**
	 * Funcion que devuelve un Array con todos los productos existentes en la base de datos
	 * @return: devuelve una lista de todos los productos que aparecen en la base de datos.
	 */
		public ArrayList <Producto> obtenerDenominacion()
		{
			//Obtenemos conexion a la base de datos.
			Connection con = conexion.getConexion();
			Statement consulta = null;
			ResultSet resultado = null;
			ArrayList<Producto> lista = new ArrayList<Producto>();
			String sSQL;
			
			try
			{
				sSQL = "SELECT DISTINCT denominacion FROM productos ORDER BY denominacion;";
				consulta = con.prepareStatement(sSQL);
				resultado = consulta.executeQuery(sSQL);
				
				//Bucle para recorrer las filas que devuelve la consulta
				while(resultado.next())
				{
					String sDenominacion = resultado.getString("denominacion");
				
					Producto prod = new Producto(0, sDenominacion, sDenominacion, sDenominacion, 0);
					lista.add(prod);
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
		
		// ***********************************************************************************************************************
		//FUNCIÓN MOSTRAR DENOMINACIÓN DEL PRODUCTO
		/**
		 * Funcion que devuelve una lista con los modelos de los productos correspondientes a la denominacion que pasamos como parametro
		 * @param denominacion: String pasado como parametro que indica el tipo de producto que queremos mostrar
		 * @return: devuelve un Array con todos los productos pertenecientes a la denominacion pasada como parametro.
		 */
				public ArrayList <Producto> obtenerProductos(String denominacion)
				{
					//Obtenemos conexion a la base de datos.
					Connection con = conexion.getConexion();
					PreparedStatement pstmt = null;
					ResultSet rs2 = null;
					ArrayList<Producto> lista2 = new ArrayList<Producto>();
					String sSQL2;
					
					try
					{
						sSQL2 = "SELECT Ref, descripcion FROM productos WHERE denominacion = ? ORDER BY descripcion ASC;";
						pstmt = con.prepareStatement(sSQL2);
						pstmt.setString(1, denominacion);
						rs2 = pstmt.executeQuery();
						
						//Bucle para recorrer las filas que devuelve la consulta
						while(rs2.next())
						{
							String sDescripcion = rs2.getString("descripcion");
							int iRef = rs2.getInt("Ref");
							Producto produc = new Producto(iRef, sDescripcion, sDescripcion, sDescripcion, 0);
							lista2.add(produc);
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
							pstmt.close();
							conexion.desconectar();
						}
						catch (SQLException e)
						{
							System.out.println("Error al liberar recursos: " + e.getMessage());
						}
						
					}
					return lista2;	
				}
				// *****************************************************************************************************
				// FUNCIÓN PARA OBTENER EL NUMERO DE REFERENCIA DE UN PRODUCTO
				/**
				 * Funcion que devuelve el numero de referencia segun la descripcion del producto que pasamos como parametro
				 * @param sDescripcion: String que corresponde con la descripcion del producto del que queremos obtener su numero de referencia 
				 * @return: devuelve un numerico entero (Referencia) que pertenece a la descripcion del producto que pasamos como parametro 
				 */
				public int obtenerRef(String sDescripcion) 
				{ 
					// Obtenemos una conexion a la base de datos. 
					Connection con = conexion.getConexion(); 
					PreparedStatement consulta = null; 
					ResultSet resultado = null; 
					String SQL = "SELECT Ref FROM productos WHERE descripcion=?";
					int iRef=0;
					try 
					{ 
						consulta = con.prepareStatement(SQL); 
						consulta.setString(1, sDescripcion); 
						resultado = consulta.executeQuery(); 
						// Sólo puede devolver una fila si la hay 
						if(resultado.next()) 
						{ 
							iRef = resultado.getInt("Ref"); 
						} 
					} 
					catch (SQLException e) 
					{ 
						System.out.println("Error al realizar la consulta: " +e.getMessage()); 
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
							System.out.println("Error al liberar recursos: " +e.getMessage()); 
						} 
						catch (Exception e) 
						{ 
									
						} 
					} 
					return iRef; 
				}
}