package controlador;

import java.awt.EventQueue;

import vista.VentanaLogin;

public class Principal {

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

}
