package vista;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import javax.swing.JOptionPane;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.PageAttributes.MediaType;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import modelo.Video;
import dao.VideoDAO;
import java.awt.Color;
import javax.swing.ImageIcon;

public class VentanaSubir extends JFrame {

	
	private JPanel contentPane;
	private JTextField textArchivo;
	private JTextField textDestino;
	private File archivoSeleccionado;
	private File archivoDestino;
	JSpinner spinnerPuesto = new JSpinner();
	JLabel lblRef = new JLabel("");
	private int control=0;
	
	public VentanaSubir(String sRef) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 690, 422);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione el vídeo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(117, 163, 130, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblArchivo = new JLabel("Archivo:");
		lblArchivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArchivo.setBounds(117, 202, 64, 29);
		contentPane.add(lblArchivo);
		
		textArchivo = new JTextField();
		textArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArchivo.setBounds(191, 202, 358, 26);
		contentPane.add(textArchivo);
		textArchivo.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestino.setBounds(117, 253, 64, 29);
		contentPane.add(lblDestino);
		
		textDestino = new JTextField();
		textDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDestino.setColumns(10);
		textDestino.setBounds(191, 255, 358, 26);
		contentPane.add(textDestino);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(536, 323, 116, 29);
		contentPane.add(btnCancelar);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//SELECCIONAR VIDEO A INSERTAR
				try 
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} 
				catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}		
				int iSeleccion;
				int iRespuesta;
				
		        JFileChooser ExploradorArchivos = new JFileChooser();
		        ExploradorArchivos.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));

		        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de video", "mp4", "avi", "mov", "wmv", "flv","mkv");
		        
		        ExploradorArchivos.setFileFilter(filtro);
		        
		     
		        // Explorador de archivos para seleccionar un video
		        iSeleccion = ExploradorArchivos.showOpenDialog(null);
		        
		        // Si se selecciona un video, elegir donde guardarlo
		        if (iSeleccion == JFileChooser.APPROVE_OPTION) 
		        {
		        		archivoSeleccionado = ExploradorArchivos.getSelectedFile();
			            //System.out.println(archivoSeleccionado.getName());
		 		       textArchivo.setText(archivoSeleccionado.getName());

		        }
		       /* else if (iSeleccion == JFileChooser.CANCEL_OPTION) {
		        }*/
			}
		});
		btnExplorar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExplorar.setBounds(559, 202, 93, 29);
		contentPane.add(btnExplorar);
		
		JButton btnDestino = new JButton("Destino");
		btnDestino.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				//GUARDADO DEL VIDEO SELECCIONADO
			        JFileChooser ExploradorArchivos2 = new JFileChooser("c:\\videos\\");

			        int iSeleccionado;
			        String ArchivoCopiado;
			        
			        ArchivoCopiado = textArchivo.getText();
			        if (ArchivoCopiado.length() >= 4) {
			            ArchivoCopiado = ArchivoCopiado.substring(0, ArchivoCopiado.length() - 4);
			        }
			        
			        // Seleccion de donde guardarlo
			        ExploradorArchivos2.setFileSelectionMode(JFileChooser.FILES_ONLY);
			        
			        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos de video", "mp4", "avi", "mov", "wmv", "flv", "mkv");
			        ExploradorArchivos2.setFileFilter(filtro2);
			        
			        //copiado del nombre del archivo anterior
			        File archivo = new File(ArchivoCopiado);
			        ExploradorArchivos2.setSelectedFile(archivo);
			        
			        // Explorador de archivos para guardar un archivo
			        iSeleccionado = ExploradorArchivos2.showSaveDialog(null);
			    
		        // Una vez pulsado el boton de guardar se recoge la ruta absoluta del video
		        if (iSeleccionado == JFileChooser.APPROVE_OPTION) 
		        {
		           archivoDestino = ExploradorArchivos2.getSelectedFile();
		           
		            System.out.println("Archivo guardado como: " + archivoDestino.getAbsolutePath());
		            textDestino.setText(archivoDestino.getAbsolutePath());
		        }
			 }
		});
		btnDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDestino.setBounds(559, 253, 93, 29);
		contentPane.add(btnDestino);
		
		JButton btnGuardar = new JButton("Guardar");
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String sExtension;
				String sDestino;
				String sOrigen;
				String sNombreArchivoCompleto;
				int iPuesto = (int) spinnerPuesto.getValue();
				
				if(textArchivo.getText().equals("") || textDestino.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Seleccione archivo y destino", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if (textDestino.getText().equals(""))
				{
					
					JOptionPane.showMessageDialog(null, "Seleccione destino del archivo", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else 
				{
					if (iPuesto!=0) 
					{
						String sNombre;
						int iCodigo_video;
						Date fecha;
						int iRef_producto;	
						int iResultado=0;
						String sRef_producto = lblRef.getText();
		
						sDestino = textDestino.getText();
						sOrigen = textArchivo.getText();
						sExtension = "." + archivoSeleccionado.getName().substring(archivoSeleccionado.getName().lastIndexOf(".") + 1);
						String sExtensionNueva =".mp4";
						
						sNombreArchivoCompleto = sDestino + sExtension;
		
						// Copiar el archivo seleccionado a la nueva ubicación
						try 
						{
							Path fuente = Paths.get(archivoSeleccionado.getAbsolutePath());
							Path destino = Paths.get(sNombreArchivoCompleto);
						      
							long size = Files.size(fuente);

						    Files.copy(fuente, destino, StandardCopyOption.REPLACE_EXISTING);
						    
							//String inputFilePath = archivoSeleccionado.getAbsolutePath();
						    String sNombreArchivoNuevo = sDestino + sExtensionNueva;
						  
							String cmd ="cmd.exe /c ";
							String ruta = "c:\\Andrew\\JAVA\\ffmpeg\\bin\\ffmpeg -i \"" + destino.toString() + "\" -c:v libx264 -b:v 1.5M -c:a aac -b:a 128k \"" + sNombreArchivoNuevo + "\"";
							
							if (!sExtension.equalsIgnoreCase(".mp4")) 
							{
								try 
								{
								    ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "\"\"", "c:\\Andrew\\JAVA\\ffmpeg\\bin\\ffmpeg", "-i", destino.toString(), "-c:v", "libx264", "-b:v", "1.5M", "-c:a", "aac", "-b:a", "128k", sNombreArchivoNuevo);
									pb.redirectErrorStream(true);
								    Process p = pb.start();
								    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
								    
								    String line;
								    while ((line = br.readLine()) != null) 
								    {
								    	 if (line.contains("time=")) 
								    	 {
							                String[] parts = line.split("time=");
							                String[] timeParts = parts[1].split("\\.");
							                String[] hms = timeParts[0].split(":");
							                int hours = Integer.parseInt(hms[0]);
							                int minutes = Integer.parseInt(hms[1]);
							                int seconds = Integer.parseInt(hms[2]);
							                int totalSeconds = hours * 3600 + minutes * 60 + seconds;
			   
								    	 }
											    
								    }			    
								
								} 
								catch (IOException e1) 
								{
								    e1.printStackTrace();
								}
								//borrar archivo antiguo
								String filePath = destino.toString();
						        File file = new File(filePath);
						        
						        if (file.delete()) 
						        {
						            System.out.println("El archivo anterior se borró exitosamente.");
						        } 
						        else 
						        {
						            System.out.println("El archivo no se pudo borrar.");
						        }
							}
							dispose();
							
						    System.out.println("VIDEO SUBIDO");
						    sNombre = sNombreArchivoNuevo;
							iRef_producto = Integer.parseInt(sRef_producto);
							java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

							Video vi = new Video(sNombre,iRef_producto,iPuesto,fechaActual); 				
							VideoDAO videoDAO = new VideoDAO();
							iResultado = videoDAO.insertarVideo(vi);	
							if (iResultado == 0) 
							{
								JOptionPane.showMessageDialog(null, "No se ha podido insertar vídeo");
							}
						} 
						catch (IOException e2) 
						{
						    System.out.println("Error al copiar el archivo: " + e2.getMessage());
						} 		
 
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Seleccione el nº de puesto");
					}
				}
				//textArchivo.setText("");
				//textDestino.setText("");
			 }
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(397, 323, 116, 29);
		contentPane.add(btnGuardar);
		
		JLabel lblPuesto = new JLabel("Puesto nº:");
		lblPuesto.setHorizontalAlignment(SwingConstants.LEFT);
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPuesto.setBounds(117, 110, 97, 29);
		contentPane.add(lblPuesto);
		
		
		spinnerPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerPuesto.setBounds(232, 111, 50, 27);
		contentPane.add(spinnerPuesto);
		
		JLabel lblNewLabel_1 = new JLabel("REFERENCIA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(117, 52, 114, 37);
		contentPane.add(lblNewLabel_1);
		
		
		lblRef.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRef.setBounds(232, 57, 83, 26);
		lblRef.setText(sRef);
		contentPane.add(lblRef);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 0));
		panel.setBounds(0, 0, 85, 396);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("EFFIVID");
		lblNewLabel_2.setForeground(new Color(0, 153, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(382, 21, 175, 78);
		contentPane.add(lblNewLabel_2);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/logo.png"));
		int ancho = icon.getImage().getWidth(null);
		int alto = icon.getImage().getHeight(null);
		JLabel lblLogo = new JLabel("");
		contentPane.add(lblLogo);
		lblLogo.setBounds(559, 10, 83, 94);
		Image img = icon.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH);
		lblLogo.setIcon(new ImageIcon(img));
	
	}
}