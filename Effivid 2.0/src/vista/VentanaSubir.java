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
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import modelo.Video;
import dao.VideoDAO;

public class VentanaSubir extends JFrame {

	
	private JPanel contentPane;
	private JTextField textArchivo;
	private JTextField textDestino;
	private File archivoSeleccionado;
	private File archivoDestino;
	JSpinner spinnerPuesto = new JSpinner();
	JLabel lblRef = new JLabel("");
	public VentanaSubir(String sRef) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1039, 662);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Seleccione el vídeo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(84, 351, 169, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblArchivo = new JLabel("Archivo:");
		lblArchivo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArchivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArchivo.setBounds(84, 390, 64, 29);
		contentPane.add(lblArchivo);
		
		textArchivo = new JTextField();
		textArchivo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textArchivo.setBounds(158, 392, 374, 26);
		contentPane.add(textArchivo);
		textArchivo.setColumns(10);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDestino.setBounds(84, 457, 64, 29);
		contentPane.add(lblDestino);
		
		textDestino = new JTextField();
		textDestino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textDestino.setColumns(10);
		textDestino.setBounds(158, 459, 374, 26);
		contentPane.add(textDestino);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(526, 550, 116, 29);
		contentPane.add(btnCancelar);
		
		JButton btnExplorar = new JButton("Explorar");
		btnExplorar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//SELECCIONAR VIDEO A INSERTAR
								
				int iSeleccion;
				int iRespuesta;
				
		        JFileChooser ExploradorArchivos = new JFileChooser();
		        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de video", "mp4", "avi", "mov", "wmv", "flv","mkv");
		        
		        ExploradorArchivos.setFileFilter(filtro);
		        
		        
		        // Explorador de archivos para seleccionar un video
		        iSeleccion = ExploradorArchivos.showOpenDialog(null);
		        
		        // Si se selecciona un video, elegir donde guardarlo
		        if (iSeleccion == JFileChooser.APPROVE_OPTION) 
		        {
		        		archivoSeleccionado = ExploradorArchivos.getSelectedFile();
			            //System.out.println(archivoSeleccionado.getName());
		        }
		                 
		       textArchivo.setText(archivoSeleccionado.getName());
	        
			}
		});
		btnExplorar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExplorar.setBounds(549, 390, 93, 29);
		contentPane.add(btnExplorar);
		
		JButton btnDestino = new JButton("Destino");
		btnDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//GUARDADO DEL VIDEO SELECCIONADO
			       
		        JFileChooser ExploradorArchivos2 = new JFileChooser();
		        int iSeleccionado;
		        String ArchivoCopiado;
		        
		        ArchivoCopiado = textArchivo.getText();
		        ArchivoCopiado = ArchivoCopiado.substring(0, ArchivoCopiado.length() - 4);
		        
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
		btnDestino.setBounds(549, 457, 93, 29);
		contentPane.add(btnDestino);
		
		JButton btnGuardar = new JButton("Guardar");
		JProgressBar progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(true);
		progressBar.setVisible(false);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				String sExtension;
				String sDestino;
				String sOrigen;
				String sNombreArchivoCompleto;
				int iPuesto = (int) spinnerPuesto.getValue();
				
				if(textArchivo.getText().equals("") || textDestino.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Seleccione archivo y destino", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else if (textDestino.getText().equals("")){
					
					JOptionPane.showMessageDialog(null, "Seleccione destino del archivo", "Error", JOptionPane.WARNING_MESSAGE);
				}
				else {
					if (iPuesto!=0) {
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
						      
						  /*  BarraProgreso ventana = new BarraProgreso(fuente, archivoSeleccionado, sNombreArchivoCompleto);
						    ventana.setVisible(true);*/

						    Files.copy(fuente, destino, StandardCopyOption.REPLACE_EXISTING);
						    
//String inputFilePath = archivoSeleccionado.getAbsolutePath();
String sNombreArchivoNuevo = sDestino + sExtensionNueva;
String cmd ="cmd.exe /c";
String ruta = "c:\\Andrew\\JAVA\\ffmpeg\\bin\\ffmpeg -i \"" + destino.toString() + "\" -c:v libx264 -b:v 1.5M -c:a aac -b:a 128k \"" + sNombreArchivoNuevo + "\"";
//System.out.println(cmd);
Process process = Runtime.getRuntime().exec(cmd+ruta);
//process.waitFor();
						    System.out.println("VIDEO SUBIDO");
						    sNombre = sNombreArchivoNuevo;
							iRef_producto = Integer.parseInt(sRef_producto);
							java.sql.Date fechaActual = new java.sql.Date(System.currentTimeMillis());

							Video vi = new Video(sNombre,iRef_producto,iPuesto,fechaActual); 				
							VideoDAO videoDAO = new VideoDAO();
							iResultado = videoDAO.insertarVideo(vi);	
							if (iResultado == 0) {
								JOptionPane.showMessageDialog(null, "No se ha podido insertar vídeo");
							}
								       
						    dispose();
						} 
						catch (IOException e2) 
						{
						    System.out.println("Error al copiar el archivo: " + e2.getMessage());
						} 		
					//	rellenarVentana();
 
					}
					else {
						JOptionPane.showMessageDialog(null, "Seleccione el nº de puesto");
					}
				
						
					
				}
				//textArchivo.setText("");
				//textDestino.setText("");
			  
			 }
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(355, 550, 116, 29);
		contentPane.add(btnGuardar);
		
		JLabel lblPuesto = new JLabel("Puesto nº:");
		lblPuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPuesto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPuesto.setBounds(84, 259, 97, 29);
		contentPane.add(lblPuesto);
		
		
		spinnerPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		spinnerPuesto.setBounds(204, 260, 50, 27);
		contentPane.add(spinnerPuesto);
		
		JLabel lblNewLabel_1 = new JLabel("REFERENCIA:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(84, 86, 136, 37);
		contentPane.add(lblNewLabel_1);
		
		
		lblRef.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRef.setBounds(205, 86, 83, 26);
		lblRef.setText(sRef);
		contentPane.add(lblRef);
	
	}
}