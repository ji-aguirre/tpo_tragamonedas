package InterfazGrafica;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VentanaJugar extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPrecioJugada;
	private JButton btnJugar, btnCobrar, btnAceptarPremio, btnSorteado1, btnSorteado2, btnSorteado3, btnCredito;
	
	public VentanaJugar() {
		configurar();
		Eventos();
	}
	
	private void Eventos() {
		
		
	}

	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		
		lblPrecioJugada = new JLabel("Precio jugada = $5");
		lblPrecioJugada.setBounds(130, 20, 130, 30);
		btnSorteado1 = new JButton("Banana");
		btnSorteado1.setBounds(60, 80, 60, 60);
		btnSorteado2 = new JButton("Manzana");
		btnSorteado2.setBounds(140, 80, 60, 60);
		btnSorteado3 = new JButton("Pera");
		btnSorteado3.setBounds(220, 80, 60, 60);
		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(40,170,130,30);
		btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(40, 215, 130, 30);
		btnAceptarPremio = new JButton("Aceptar premio");
		btnAceptarPremio.setBounds(180, 215, 130, 30);
		btnCredito = new JButton("Crédito: $1000");
		btnCredito.setBounds(180, 170, 130, 30);

		c.add(lblPrecioJugada);
		c.add(btnJugar);
		c.add(btnSorteado1);
		c.add(btnSorteado2);
		c.add(btnSorteado3);
		c.add(btnCobrar);
		c.add(btnAceptarPremio);
		c.add(btnCredito);
	}

	

	
	
}
