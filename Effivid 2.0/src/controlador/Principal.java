package controlador;

import java.awt.EventQueue;
import java.awt.SystemTray;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.AWTException;
import javax.swing.ImageIcon;

import vista.VentanaLogin;

public class Principal {

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
			
						VentanaLogin frame = new VentanaLogin();
						//frame.setLocation(100, 30); // establecer la posición de la ventana
						frame.setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
						
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}

}
