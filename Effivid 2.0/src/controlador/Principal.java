package controlador;

import java.awt.EventQueue;

import vista.VentanaLogin;

public class Principal {

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaLogin frame = new VentanaLogin();
						frame.setLocation(100, 30); // establecer la posición de la ventana

						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		
	}

}
