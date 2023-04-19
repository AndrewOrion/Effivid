package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class VentanaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField textPassword;

	

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1348, 772);
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
		
		JButton btnNewButton = new JButton("ACEPTAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textUsuario.getText().equals("admin") &&
						textPassword.getText().equals("admin")) {
					textUsuario.setText("");
					textPassword.setText("");
					VentanaUsuario frame = new VentanaUsuario();
			        frame.setLocation(100, 40); // establecer la posición de la ventana

					frame.setVisible(true);
				}
				else {
					VentanaUsuario frame = new VentanaUsuario();
			        frame.setLocation(100, 40); // establecer la posición de la ventana
					frame.setVisible(true);
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	}
}
