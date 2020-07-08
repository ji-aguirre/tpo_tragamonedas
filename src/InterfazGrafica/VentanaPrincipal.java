package InterfazGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ModeloCasino.Controlador;

public class VentanaPrincipal extends JFrame implements ActionListener,ChangeListener {
	
	/**
	 * Para sacar warning
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblMaquinasDisponibles;
	private JComboBox<Integer> cboMaquinasDisponibles;
	private JButton btnJugar, btnAgregarMaquina, btnSalir, btnConfigurar;

	public Set<Integer> maquinasDisponibles;
	public Controlador control;

	public VentanaPrincipal(Controlador control){
		this.control = control;
		configurar();
		eventos(this.control);
	}

	
	private void eventos(Controlador controlador) {
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
				if (cboMaquinasDisponibles.getItemCount() > 0) {
					VentanaJugar v = new VentanaJugar(controlador, (Integer) cboMaquinasDisponibles.getSelectedItem());

					v.setSize(350, 300);
					v.setLocation(450, 230);
					v.setVisible(true);
					v.setTitle(cboMaquinasDisponibles.getSelectedItem().toString());
				}
			}
		});
		
		btnConfigurar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				if (cboMaquinasDisponibles.getItemCount() > 0 ) {
					VentanaConfigurar v = new VentanaConfigurar(controlador, (Integer) cboMaquinasDisponibles.getSelectedItem());
					v.refCboPrincipal = cboMaquinasDisponibles;
					v.setSize(400, 300);
					v.setLocation(450, 230);
					v.setVisible(true);
					v.setTitle(cboMaquinasDisponibles.getSelectedItem().toString());
				}
			}
		});

		btnAgregarMaquina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva

				JPanel panelInput = new JPanel(new GridLayout(3,2));

				JLabel lblSaldoIni = new JLabel("Saldo inicial:");
				JTextField inputSaldoIni = new JTextField();

				JLabel lblCantCasillas = new JLabel("Cantidad de Casillas:");
				JTextField inputCantCasillas = new JTextField();

				JLabel lblPrecioJugada = new JLabel("Precio de Jugada:");
				JTextField inputPrecioJugada = new JTextField();

				panelInput.add(lblSaldoIni);
				panelInput.add(inputSaldoIni);

				panelInput.add(lblCantCasillas);
				panelInput.add(inputCantCasillas);

				panelInput.add(lblPrecioJugada);
				panelInput.add(inputPrecioJugada);

				int input = JOptionPane.showConfirmDialog(null,panelInput,"Agregar nueva maquina",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (input == 0){
					cboMaquinasDisponibles.addItem(controlador.crearTragamonedas(Float.parseFloat(inputSaldoIni.getText()),Integer.parseInt(inputCantCasillas.getText()),Float.parseFloat(inputPrecioJugada.getText())));

				}



			}
		});
	}


	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.RED);
		this.setTitle("Casino Virtual");
		
		lblMaquinasDisponibles = new JLabel("Máquinas Disponibles");
		lblMaquinasDisponibles.setBounds(40, 40, 130, 30);
		cboMaquinasDisponibles = new JComboBox<Integer>();
		cboMaquinasDisponibles.setBounds(30, 80, 150, 30);
		this.maquinasDisponibles = control.listadoMaquinas();
		for(Integer maquina : this.maquinasDisponibles) {
			cboMaquinasDisponibles.addItem(maquina);
		}

		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(200, 80, 150, 30);
		btnJugar.setBackground(Color.RED);
		btnAgregarMaquina = new JButton("Agregar Máquina");
		btnAgregarMaquina.setBounds(200, 120, 150, 30);
		btnAgregarMaquina.setBackground(Color.WHITE);
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(200,160,150,30);
		btnSalir.setBackground(Color.RED);
		btnConfigurar = new JButton("Configurar");
		btnConfigurar.setBounds(200,200,150,30);
		btnConfigurar.setBackground(Color.WHITE);
		
		Image imagen = ImageIO.read(getClass().getResource("/resouces/casino.jpg"));
		Image redimension = imagen.getScaledInstance(32,32,Image.SCALE_SMOOTH);

		ImageIcon icono = new ImageIcon(redimension);
		JButton casilla = new JButton(icono);
		casilla.setSize(32,32);

		c.add(casilla);
		
		
		c.add(lblMaquinasDisponibles);
		c.add(cboMaquinasDisponibles);
		c.add(btnJugar);
		c.add(btnAgregarMaquina);
		c.add(btnSalir);
		c.add(btnConfigurar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void stateChanged(ChangeEvent e) {

	}
}
