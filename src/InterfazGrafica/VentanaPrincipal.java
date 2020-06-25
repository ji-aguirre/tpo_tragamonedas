package InterfazGrafica;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class VentanaPrincipal extends JFrame implements ActionListener,ChangeListener {
	
	/**
	 * Para sacar warning
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblMaquinasDisponibles;
	private JComboBox<String> cboMaquinasDisponibles;
	private JButton btnJugar, btnAgregarMaquina, btnSalir, btnConfigurar;

	public VentanaPrincipal(){
		configurar();
		eventos();
	}
	
	
	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		btnJugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				VentanaJugar v = new VentanaJugar();
				v.setSize(350, 300);
				v.setLocation(450,230);
				v.setVisible(true);
				v.setTitle((String) cboMaquinasDisponibles.getSelectedItem());
				btnJugar.setEnabled(false); //Una vez q abri una ventana no puedo volver a hacerlo
											//el boton queda gris e inabilitado...sirve para que no 
											//me abran muchas ventanas.
			}
		});
		
		btnConfigurar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				VentanaConfigurar v = new VentanaConfigurar();
				v.setSize(400, 300);
				v.setLocation(450,230);
				v.setVisible(true);
				v.setTitle((String) cboMaquinasDisponibles.getSelectedItem());
				btnConfigurar.setEnabled(false); //Una vez q abri una ventana no puedo volver a hacerlo
												 //el boton queda gris e inabilitado...sirve para que no 
												 //me abran muchas ventanas.
			}
		});
	}


	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		this.setTitle("Casino Pirulo");
		
		lblMaquinasDisponibles = new JLabel("Máquinas Disponibles");
		lblMaquinasDisponibles.setBounds(40, 40, 130, 30);
		cboMaquinasDisponibles = new JComboBox<String>();
		cboMaquinasDisponibles.setBounds(30, 80, 150, 30);
		cboMaquinasDisponibles.addItem("Juan");
		cboMaquinasDisponibles.addItem("Joaco");
		cboMaquinasDisponibles.addItem("Somos máquinas");
		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(200, 80, 150, 30);
		btnAgregarMaquina = new JButton("Agregar Máquina");
		btnAgregarMaquina.setBounds(200, 120, 150, 30);
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(200,160,150,30);
		btnConfigurar = new JButton("Configurar");
		btnConfigurar.setBounds(200,200,150,30);
		
		c.add(lblMaquinasDisponibles);
		c.add(cboMaquinasDisponibles);
		c.add(btnJugar);
		c.add(btnAgregarMaquina);
		c.add(btnSalir);
		c.add(btnConfigurar);
	}


	

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
