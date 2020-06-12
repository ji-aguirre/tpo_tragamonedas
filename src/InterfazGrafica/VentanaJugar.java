package InterfazGrafica;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaJugar extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPrecioJugada;
	private JButton btnJugar, btnCobrar, btnAceptarPremio;
	
	public VentanaJugar() {
		configurar();
		Eventos();
	}
	
	private void Eventos() {
		
		
	}

	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		this.setTitle("JUGANDO");
		
		lblPrecioJugada = new JLabel("Precio jugada = $5");
		lblPrecioJugada.setBounds(40, 40, 130, 30);
		btnJugar = new JButton("JUGARRRRR");
		btnJugar.setBounds(40,80,130,30);
		
		c.add(lblPrecioJugada);
		c.add(btnJugar);
		
		
	}

	

	
	
}
