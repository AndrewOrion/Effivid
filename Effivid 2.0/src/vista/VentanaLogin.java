package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;

import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

import dao.PersonaDAO;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textPassword;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaLogin() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1348, 772);
	    setLocationRelativeTo(null); // Centra la ventana en la pantalla

		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(227, 255, 235));
		panel.setBorder(new LineBorder(new Color(128, 255, 128), 5));
		panel.setBounds(0, 0, 1348, 772);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(10, 132, 0));
		panel_1.setBounds(0, 0, 109, 772);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("EFFIVID");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(10, 132, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
		lblNewLabel.setBounds(975, 39, 363, 102);
		panel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(214, 254, 227));
		panel_2.setBorder(new LineBorder(new Color(10, 132, 0), 5, true));
		panel_2.setBounds(441, 249, 578, 341);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("USUARIO:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(40, 94, 99, 41);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("CONTRASEÑA:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(40, 175, 130, 34);
		panel_2.add(lblNewLabel_2);

		textUsuario = new JTextField();
		textUsuario.setBounds(174, 94, 299, 32);
		panel_2.add(textUsuario);
		textUsuario.setColumns(10);
//QUITAR!
textUsuario.setText("ruizabalos_i");
		JButton btnNewButton = new JButton("ACEPTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sUsuario;
				String sContrasena;
				PersonaDAO personaDAO;
				
				
				sUsuario = textUsuario.getText();
				sContrasena = textPassword.getText();
				
				 if(sUsuario.equals("") || sContrasena.equals(""))
				 {
		        	System.out.println("El campo de usuario está vacío"); 
					 JOptionPane.showMessageDialog(null, "Los campos Usuario y Contraseña deben estar rellenos", "Error", JOptionPane.WARNING_MESSAGE);
					 return;
				 }
				
				 try 
				 {
					 	int iContrasena = Integer.valueOf(sContrasena);
					 	personaDAO= new PersonaDAO();
						boolean loginValido = personaDAO.validarLogin(iContrasena, sUsuario);
						
				        if (loginValido)
				        {
				            textUsuario.setText("");
				            textPassword.setText("");
					        VentanaUsuario ventana = new VentanaUsuario(sUsuario);
							ventana.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosed(WindowEvent e) {
									// habilitar la ventana anterior cuando se cierra VentanaUsuario
									setEnabled(true);
									setFocusable(true);
									toFront();
								}
							});
							setEnabled(false);
							setFocusable(false);
							ventana.setVisible(true);
				        }
				        else 
				        {
				            // si el login es inválido
				            JOptionPane.showMessageDialog(null, "Usuario o contraseña inválidos");
				        }
				 } 
				 catch (NumberFormatException ex) 
				 	{
					 	// Controlar que la contraseña solo pueda contener números
						 JOptionPane.showMessageDialog(null, "La contraseña debe contener solo números");
						 return;      
				 	}
				}
			});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(150, 263, 109, 41);
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("SALIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(364, 263, 109, 41);
		panel_2.add(btnNewButton_1);

		textPassword = new JPasswordField();
		textPassword.setBounds(174, 175, 299, 29);
		panel_2.add(textPassword);
//QUITAR!
textPassword.setText("229");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}