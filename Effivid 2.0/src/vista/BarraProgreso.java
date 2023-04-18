package vista;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class BarraProgreso extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BarraProgreso( Path fuente, File archivoSeleccionado, String sNombreArchivoCompleto) 
	{
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) 
			{
				 // Mostrar la barra de progreso
			    progressBar.setVisible(true);
			    
			    // Calcular el tamaño del archivo
			    long fileSize = 0;
				try 
				{
					fileSize = Files.size(fuente);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    // InputStream para leer el archivo
			    InputStream in = null;
				try 
				{
					in = new BufferedInputStream(new FileInputStream(archivoSeleccionado));
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    // OutputStream para escribir el archivo
			    OutputStream out = null;
				try 
				{
					out = new BufferedOutputStream(new FileOutputStream(sNombreArchivoCompleto));
				} 
				catch (FileNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    byte[] buffer = new byte[1024];
			    int iTamano;
			    long lProgreso = 0;
			    
			    // Copiar el archivo y actualizar la barra de progreso
			    try 
			    {
					while ((iTamano = in.read(buffer)) != -1) 
					{
					    out.write(buffer, 0, iTamano);
					    lProgreso += iTamano;
					    int progress = (int) (lProgreso * 100 / fileSize);
					    progressBar.setValue(progress);
					}
				} 
			    catch (IOException e1) 
			    {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    // Cerrar los streams
			    try 
			    {
					in.close();
				} catch (IOException e1) 
			    {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    try 
			    {
					out.close();
				} catch (IOException e1) 
			    {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    
			    if (progressBar.getValue() == 100)
			    {
			    	JOptionPane.showMessageDialog(null, "Archivo copiado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
			    	dispose();
			    }
			}
		});
		setTitle("Copiando");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 217);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Copiando archivo...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(31, 27, 147, 23);
		contentPane.add(lblNewLabel);
		
		
		progressBar.setBackground(new Color(240, 240, 240));
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressBar.setBounds(57, 75, 444, 37);
		contentPane.add(progressBar);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		btnNewButton.setBounds(227, 135, 95, 35);
		contentPane.add(btnNewButton);
	}
}