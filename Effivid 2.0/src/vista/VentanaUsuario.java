/**
 * @author Andres Pino Gallardo y Alberto Peinado
 */
package vista;

import javax.swing.border.Border;
import modelo.Persona;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import conexion.ConexionBD;
import dao.ProductoDAO;
import modelo.Producto;
import modelo.Video;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import dao.VideoDAO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;
import java.awt.*;
import dao.PersonaDAO;
@SuppressWarnings("serial")
public class VentanaUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtRef;
	private ConexionBD conexion;
	private JButton[] buttons;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public VentanaUsuario(String nombreUsuario) 
	{
		
	this.conexion = new ConexionBD();

	//DEFINIR COMBOBOX PRODUCTO
			JComboBox<String> cbProducto = new JComboBox<String>();
			cbProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
			cbProducto.setBounds(189, 336, 297, 31);
			cbProducto.setBackground(new Color(227, 255, 235));
			PersonaDAO usuario = new PersonaDAO();
			Persona user = usuario.obtenerNombre(nombreUsuario);
			String bienvenido = user.getNombre();
			String tipo = user.getTipo();
			
			//Lista de productos
			ArrayList<Producto> lista = new ArrayList<Producto>();	
			ProductoDAO productoDAO = new ProductoDAO();
			lista = productoDAO.obtenerDenominacion();					
			cbProducto.removeAllItems();
			cbProducto.addItem("- Seleccione un producto -" );		
			for(Producto producto:lista)
			{
				String denominacion = producto.getDenominacion();
				cbProducto.addItem(denominacion);
			}
			cbProducto.setSelectedIndex(0);
			cbProducto.repaint();
			
		//DEFINIR COMBOBOX MODELO
			JComboBox<String> cbModelo = new JComboBox<String>();
			
			cbModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
			cbModelo.setBounds(189, 398, 297, 31);
			cbModelo.setBackground(new Color(227, 255, 235));

			cbModelo.addItem("- Seleccione el modelo -");

		//ASOCIAR ACTIONLISTENER AL COMBOBOX PRODUCTO
			cbProducto.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String sDenominacion = cbProducto.getSelectedItem().toString();
						
		//VACIAR Y RELLENAR COMBOBOX MODELO
				cbModelo.removeAllItems();
				if (cbProducto.getSelectedIndex()==0) {
					cbModelo.addItem("- Seleccione el modelo -");
				}
				ArrayList<Producto> lista2 = new ArrayList<Producto>();
				ProductoDAO producDAO = new ProductoDAO();
				lista2 = producDAO.obtenerProductos(sDenominacion);
				
				for(Producto produc:lista2)
				{
					String descripcion = produc.getDescripcion();
					cbModelo.addItem(descripcion);
				}
				cbModelo.setSelectedItem("- Seleccione el modelo -");
				cbModelo.repaint();
			}
			});
		
		
		setTitle("Visualización de videos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1333, 748);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(227, 255, 235));
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		txtRef = new JTextField();
		txtRef.setToolTipText("Inserte el número de referencia del producto sobre el que desee ver el vídeo");
		txtRef.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtRef.setBounds(223, 168, 249, 31);
		contentPane.add(txtRef);
		txtRef.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nº Referencia:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(99, 172, 114, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProducto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProducto.setBounds(88, 341, 76, 20);
		contentPane.add(lblProducto);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblModelo.setBounds(88, 403, 70, 20);
		contentPane.add(lblModelo);
		
		contentPane.add(cbProducto);
		contentPane.add(cbModelo);
		
		JLabel lblNewLabel_1 = new JLabel("OPCIONES PARA BUSCAR:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(0, 153, 0));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(131, 103, 341, 31);
		contentPane.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setBorder(null);
		scrollPane.setBounds(496, 23, 813, 678);
		scrollPane.setBackground(new Color(227, 255, 225));
		scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(scrollPane);
		
		//Definicion de la tabla
		table = new JTable();
		table.setRowHeight(50);
		table.setShowVerticalLines(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);
		table.setBackground(new Color(227, 255, 235));

		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PUESTO", "C\u00D3DIGO", "NOMBRE", "FECHA SUBIDA", "VIDEO"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(65);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(201);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setMaxWidth(90);
		
		// Crear un DefaultTableCellRenderer personalizado para alinear el texto centrado en la columna 0 y 
		//fondo gris y letras negrita
		DefaultTableCellRenderer puestoRenderer = new DefaultTableCellRenderer();
		Color backgroundPuesto = Color.LIGHT_GRAY;
		puestoRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		puestoRenderer.setBackground(backgroundPuesto);
		puestoRenderer.setFont(new Font("Tahoma", Font.BOLD, 18)); // Fuente en negrita
		TableColumn columnaPuesto = table.getColumnModel().getColumn(0);
		columnaPuesto.setCellRenderer(puestoRenderer);
		
		// renderer columna puesto
		DefaultTableCellRenderer codigoRenderer = new DefaultTableCellRenderer();
		Color backgroundCod = Color.green.brighter();
		codigoRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		codigoRenderer.setBackground(backgroundCod);
		codigoRenderer.setFont(new Font("Tahoma", Font.BOLD, 15)); // Fuente en negrita
		TableColumn columnaCod = table.getColumnModel().getColumn(1);
		columnaCod.setCellRenderer(codigoRenderer);
		TableColumn columnaFecha = table.getColumnModel().getColumn(3);
		columnaFecha.setCellRenderer(codigoRenderer);
		
		
		//Cabeceras
		Border emptyBorder = BorderFactory.createEmptyBorder();
		table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        // Personalizar el estilo de las cabeceras de las columnas
		        //c.setBackground(Color.green); // Cambiar el color de fondo
		        //c.setForeground(Color.WHITE); // Cambiar el color del texto
		     // Obtener la fuente actual del componente c
		        Font currentFont = c.getFont();

		        // Crear una nueva fuente con estilo de negrita
		        Font newFont = new Font(currentFont.getFontName(), Font.BOLD, currentFont.getSize());

		        // Establecer la nueva fuente en el componente c
		        c.setFont(newFont);
		        // Establecer la alineación centrada en el componente c
		        ((DefaultTableCellRenderer) c).setHorizontalAlignment(SwingConstants.CENTER);
		        
		        return c;
		    }
		});
		
		scrollPane.setViewportView(table);
		
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				
				String sProducto = "";
				String sModelo = "";
				int iRef = 0;
				sProducto = cbProducto.getSelectedItem().toString();
				sModelo = cbModelo.getSelectedItem().toString();

				ProductoDAO producDAO = new ProductoDAO();
				iRef = producDAO.obtenerRef(sModelo);
				if (iRef == 0 && sProducto.equals("- Seleccione un producto -"))
				{
					JOptionPane.showMessageDialog(null,"Rellene alguna de las dos opciones:", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {						
						rellenarVentana(iRef);//método para rellenar la jtable
						repaint();				
				}
			}	
			
		});
		btnVer.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVer.setBounds(341, 466, 131, 31);
		contentPane.add(btnVer);
		
		JButton btnCerrar = new JButton();
		btnCerrar.setOpaque(false);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCerrar.setBounds(88, 32, 33, 31);
		String imagePath = "/imagenes/cerrar.png";
		URL imageURL = getClass().getResource(imagePath);
		ImageIcon icon = new ImageIcon(imageURL);
		Image img = icon.getImage().getScaledInstance(btnCerrar.getWidth(), btnCerrar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconoEscalado = new ImageIcon(img);
		btnCerrar.setIcon(iconoEscalado);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		
		contentPane.add(btnCerrar);
		
		
		//Boton ver referencia
		JButton btnVerRef = new JButton("Ver");
		btnVerRef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sProducto = "";
				int iRef = 0;
				String sRef ="";
				sProducto = cbProducto.getSelectedItem().toString();
				sRef = Integer.toString(iRef);
				
				if (sRef.equals("") && sProducto.equals("- Seleccione un producto -"))
				{
					JOptionPane.showMessageDialog(null,"Rellene alguna de las dos opciones:", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {	
					try {
					    sRef = txtRef.getText(); // Obtener el valor del campo de texto como una cadena de caracteres
					    iRef = Integer.parseInt(sRef); // Intentar convertir la cadena a un número entero
					    rellenarVentana(iRef); // Realizar acciones con el número entero, ya que se ha ingresado un valor válido
					    repaint(); // Repintar la ventana
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"Ingresa una Referencia válida", "Error", JOptionPane.WARNING_MESSAGE);

					}							
				}				
			}
		});
		btnVerRef.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerRef.setBounds(341, 227, 131, 31);
		contentPane.add(btnVerRef);
		
		
		JLabel lblNewLabel_2 = new JLabel("<" + bienvenido + ">");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(0, 57, 9));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(131, 32, 341, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblUsuario = new JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsuario.setBounds(148, 29, 140, 13);
		contentPane.add(lblUsuario);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 0));
		panel.setBounds(0, 0, 78, 743);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(99, 283, 385, 13);
		contentPane.add(separator);
		
		if (tipo.equals("admin")) {
			JPanel panelAdmin = new JPanel();
			panelAdmin.setBorder(null);
			panelAdmin.setBackground(new Color(225, 255, 239));
			panelAdmin.setBounds(10, 532, 469, 169);
			contentPane.add(panelAdmin);
			panelAdmin.setLayout(null);
			
			JButton btnAdd = new JButton("AÑADIR VÍDEO");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (txtRef.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Seleccione Producto y puse VER");
					}else {
						VentanaSubir frame = new VentanaSubir(txtRef.getText());
						frame.setVisible(true);
					}			
				}
			});
			btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnAdd.setBounds(81, 81, 169, 44);
			panelAdmin.add(btnAdd);
			
			JButton btnEliminar = new JButton("ELIMINAR VÍDEO");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int iElemento=-1;
					int codigo_video;
					int iResultado;
					int iRespuesta;
					
					iElemento = table.getSelectedRow();
					if (iElemento == -1) {
						JOptionPane.showMessageDialog(null, "Ninguna fila seleccionada");
					}
					else {
						String iconoRuta = "/imagenes/borrar.png";
						URL urlImagen = getClass().getResource(iconoRuta);
						ImageIcon icono = new ImageIcon(urlImagen);
	
						
					iRespuesta = JOptionPane.showConfirmDialog(null, "¿Estás seguro?", "Eliminar", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icono);
					
					if (iRespuesta == 0)
						{ //si usuario dice SI
							if (iElemento >= 0) {
								//borrar archivo fisicamente del disco
								int fila = table.getSelectedRow();
								TableModel model = table.getModel();
								String nombre = model.getValueAt(fila, 2).toString();
						        File file = new File(nombre);
						        
						        if (file.delete()) {
						            System.out.println("El archivo anterior se borró exitosamente.");
						        } else {
						            System.out.println("El archivo no se pudo borrar.");
						        }
						        
						        //eliminar de la base de datos
								codigo_video = (int) table.getValueAt(iElemento, 1); //cojo el id del elemento (fila 0)
								VideoDAO videoDAO = new VideoDAO();
											
								iResultado = videoDAO.eliminarVideo(codigo_video);
								String sRef = txtRef.getText();
								int iRef = Integer.parseInt(sRef);
								rellenarVentana(iRef);
								
								
								if (iResultado == 0) {
									JOptionPane.showMessageDialog(null, "No se ha podido eliminar la fila");
								}
							}
						}
					}
				}
			});
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnEliminar.setBounds(276, 81, 183, 44);
			panelAdmin.add(btnEliminar);
			
			JLabel lblNewLabel_3 = new JLabel("Opciones de administrador:");
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setOpaque(true);
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_3.setBackground(new Color(0, 153, 0));
			lblNewLabel_3.setBounds(151, 30, 221, 26);
			panelAdmin.add(lblNewLabel_3);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(76, 10, 383, 12);
			panelAdmin.add(separator_1);
		}
		//end panel admin

		
	}
	
	//fUNCIÓN QUE RECIBE LA RUTA DEL VÍDEO Y LO REPRODUCE CON LA APP DE VIDEOS DEL ORDENADOR
	public int AbrirVideo (String sRuta){
	   
	        String rutaVideo = sRuta;
	        System.out.println(rutaVideo);
	        int error=0;
	        try {
	            if (Desktop.isDesktopSupported()) {
	                Desktop desktop = Desktop.getDesktop();
	                File file = new File(rutaVideo);
	                if (file.exists()) {
	                    desktop.open(file); // Abrir el archivo con la aplicación predeterminada
	                } else {
						JOptionPane.showMessageDialog(null, "El vídeo no existe en la carpeta");
	                    error=1;
	                }
	            } else {
	                System.out.println("La funcionalidad de apertura de escritorio no es compatible en este sistema.");
	                	error=2;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    return error;
	}
	
	//FUNCIÓN QUE MUESTRA LA TABLA CON LOS VALORES DEL PRODUCTO SELECCIONADO
	private void rellenarVentana(int iRef) {
		
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();//siempre
		modelo.setRowCount(0);//a cero
		
		TableCellEditor MiTabla = table.getCellEditor(); 
		// Crear un renderizador personalizado para la columna de botones
		TableColumn button = table.getColumnModel().getColumn(4); // Obtener la columna de botones (índice 4)
		if (MiTabla!=null) { //si MiTabla no tiene el cellEditor 
		table.getCellEditor().stopCellEditing();
		}
		button.setCellRenderer(new ButtonRenderer()); // Establecer el renderizador de la columna como ButtonRenderer
		button.setCellEditor(new ButtonEditor(new JCheckBox())); // Establecer el editor de celdas de la columna como ButtonEditor
  
		
		String sRef = Integer.toString(iRef);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		String sFecha;
		int numeroPuestoAnterior = -1;//para que solo imprima 1 vez l puesto
		int codigo_video;
		VideoDAO videoDAO = new VideoDAO();
		ArrayList<Video> lista = new ArrayList<Video>();
		
		lista = videoDAO.obtenerVideos(iRef);//obtengo y luego recorro
		txtRef.setText(sRef);
		if (lista.isEmpty()) { // Verificar si la lista está vacía
	        JOptionPane.showMessageDialog(null, "El producto no existe o no contiene vídeos", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	    } else {
			for (Video vi : lista) {
			    int totalFilas = modelo.getRowCount(); // Obtener el número total de filas en la tabla
	
				// Crear una instancia del renderizador personalizado
			    //para que no muestre la ruta completa sino solo en nombre del video
				NombreVideoRenderer nombreVideoRenderer = new NombreVideoRenderer();
	
				// Asignar el renderizador a la columna del nombre del video (suponiendo que sea la columna 2)
				table.getColumnModel().getColumn(2).setCellRenderer(nombreVideoRenderer);
							
				Object file[]=null;
						if (vi.getPuesto() != numeroPuestoAnterior) {
							
							String puesto = "Puesto "+vi.getPuesto();
					        file = new Object[]  {	  
					            puesto,	
					            codigo_video = vi.getCod_video(),			  
					            vi.getNombre(),
					            sFecha = formatoFecha.format(vi.getFecha_subida())
					        };
		
					        modelo.addRow(file);
						} else {

							file = new Object[] {

					            "", // Espacio vacío en lugar del número de puesto
					            codigo_video = vi.getCod_video(),
					            vi.getNombre(),
					            formatoFecha.format(vi.getFecha_subida()),
					        };
					        modelo.addRow(file);				
						}

			    numeroPuestoAnterior = vi.getPuesto();		    
			}
		  }
		
	}
	

	
	public class ButtonRenderer extends JButton implements TableCellRenderer {
	    public ButtonRenderer() {
	        setOpaque(false);
	    }
	    public Component getTableCellRendererComponent(JTable table, Object value,
	            boolean isSelected, boolean hasFocus, int row, int column) {
	    	//setText("Reproducir");
	    	 String imagePath = "/imagenes/video3.png";
	    		URL imageURL = getClass().getResource(imagePath);
	    		ImageIcon icon = new ImageIcon(imageURL);
	    		setIcon(icon);
	        return this;
	    }
	}
	

	public class ButtonEditor extends DefaultCellEditor {
	    protected JButton button;
	    private JTable table; // Agrega una referencia a la tabla
	    
	    public ButtonEditor(JCheckBox checkBox) {
	        super(checkBox);
	        button = new JButton();
	        button.setOpaque(true);
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 int row = table.getSelectedRow(); // Obtener el índice de la fila seleccionada en la tabla
	            	    if (row >= 0) {
	            	      String nombreVideo = (String) table.getValueAt(row, 2);
	            	      AbrirVideo(nombreVideo);
	            	      String imagePath = "/imagenes/play.jpg";
	      		    		URL imageURL = getClass().getResource(imagePath);
	      		    		ImageIcon icon = new ImageIcon(imageURL);
	      		    		button.setIcon(icon);
	            	    }	
	            	    
	            }
	        });
        
	    }
		   public Component getTableCellEditorComponent(JTable table, Object value,
		            boolean isSelected, int row, int column) {
		        this.table = table; // Asigna la referencia de la tabla a la variable local
		        return button;
		    }
	
		    public Object getCellEditorValue() {
		        return "";
		    }
	
		    public boolean stopCellEditing() {
		        cancelCellEditing(); // Llama a cancelCellEditing() para cancelar la edición y restaurar la celda a su estado inicial
		        return super.stopCellEditing();
		    }
		    
		    public void cancelCellEditing() {
			    super.cancelCellEditing();
			    fireEditingStopped();
			}
	}
		
	// RENDERIZADOR PARA QUE SOLO MUESTRE EL NOMBRE DEL VIDEO EN LA COLUMNA NOMBRE
	//PERO QUE REALMENTE SIGA ALMACENANDO TODA LA RUTA DEL VIDEO
	public class NombreVideoRenderer extends DefaultTableCellRenderer {

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        // Obtener el valor real de la celda, que es la ruta completa del video
	        String rutaCompleta = (String) value;

	        // Obtener el nombre del video a partir de la ruta completa
	        String nombreVideo = rutaCompleta.substring(rutaCompleta.lastIndexOf("\\") + 1);

	      
	        // Usar el nombre del video como valor a mostrar en la celda
	        return super.getTableCellRendererComponent(table, nombreVideo, isSelected, hasFocus, row, column);
	    }
	}
}