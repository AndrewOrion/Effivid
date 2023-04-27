package controlador;

import java.awt.EventQueue;
import java.awt.SystemTray;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import vista.VentanaLogin;

public class Principal {

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						try 
						{
							UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						} 
						catch (ClassNotFoundException | InstantiationException | IllegalAccessException
								| UnsupportedLookAndFeelException e1) 
						{
							e1.printStackTrace();
						}
						VentanaLogin frame = new VentanaLogin();
						
						frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
						
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}

}
