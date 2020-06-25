package InterfazGrafica;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.Container;

public class VentanaConfigurar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAgregarPremio, btnSacarPremio, btnEliminarMaquina;
	private JComboBox<String> cboPremios;
	
	VentanaConfigurar(){
		configurar();
		eventos();
	}


	private void eventos() {
		// TODO Auto-generated method stub
		
	}

	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);

		btnAgregarPremio = new JButton("Agregar premio");
		btnAgregarPremio.setBounds(30, 30, 130, 30);
		btnEliminarMaquina = new JButton("Eliminar máquina");
		btnEliminarMaquina.setBounds(30, 90, 130, 30);
		btnSacarPremio = new JButton("Sacar premio");
		btnSacarPremio.setBounds(30, 150, 130, 30);
		cboPremios = new JComboBox<String>();
		cboPremios.addItem("Ban,Per,Man");
		cboPremios.addItem("Ban,Ban,Ban");
		cboPremios.addItem("Per,Per,Per");
		cboPremios.addItem("Man,Man,Man");
		cboPremios.setBounds(200, 30, 130, 30);


		c.add(btnAgregarPremio);
		c.add(btnEliminarMaquina);
		c.add(btnSacarPremio);
		c.add(cboPremios);



		
	}

}
