package InterfazGrafica;

import ModeloCasino.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class VentanaConfigurar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnAgregarPremio, btnSacarPremio, btnEliminarMaquina;
	private JComboBox<String> cboPremios;
	private JPanel panelGral;

	private JButton btn1,btn2,btn3,btn4,btn5,btn6;
	private JLabel lblAgregados;

	private Controlador controlador;
	private int seleccion;

	private final String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"}; //Frutas de la maquina

	private ArrayList<String> nuevoPremio;
	private int cantCasillas;

	public JComboBox refCboPrincipal;

	VentanaConfigurar(Controlador controlador, int seleccion){
		this.controlador = controlador;
		this.seleccion = seleccion;
		this.cantCasillas = controlador.mostrarCantCasillas(seleccion);

		configurar();
		eventos(controlador);
	}


	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);

		btnAgregarPremio = new JButton("Agregar premio");
		btnAgregarPremio.setBounds(40, 30, 150, 30);
		btnEliminarMaquina = new JButton("Eliminar máquina");
		btnEliminarMaquina.setBounds(40, 90, 150, 30);
		btnSacarPremio = new JButton("Sacar premio");
		btnSacarPremio.setBounds(40, 150, 150, 30);
		cboPremios = new JComboBox<String>();
		for(String[] premio : controlador.mostrarListadoPremios(seleccion)){
			cboPremios.addItem(Arrays.toString(premio));
		}
		cboPremios.setBounds(200, 150, 150, 30);

		btn1 = new JButton(frutas[0]);
		btn2 = new JButton(frutas[1]);
		btn3 = new JButton(frutas[2]);
		btn4 = new JButton(frutas[3]);
		btn5 = new JButton(frutas[4]);
		btn6 = new JButton(frutas[5]);


		c.add(btnAgregarPremio);
		c.add(btnEliminarMaquina);
		c.add(btnSacarPremio);
		c.add(cboPremios);

		
	}

	private void eventos(Controlador controlador) {
		btnSacarPremio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cboPremios.getItemCount() > 0) {
					controlador.eliminarPremio(seleccion, cboPremios.getSelectedIndex());
					cboPremios.removeItem(cboPremios.getSelectedItem());
					JOptionPane.showMessageDialog(null, "El premio seleccionado se elimino. ", "Premio eliminado", JOptionPane.OK_OPTION);
				}
			}
		});
		btnEliminarMaquina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.eliminarTragamonedas(seleccion);
				refCboPrincipal.removeItem(seleccion);


			}
		});
		btnAgregarPremio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva

				panelGral = new JPanel(new GridLayout(3,1,5,10));

				JPanel panelSuperior = new JPanel(new GridLayout(2,1,10,5));
				JLabel lblAgregarAlPremio = new JLabel("Tocar los botones para agregar fruta al premio.");
				panelSuperior.add(lblAgregarAlPremio);
				lblAgregados = new JLabel("");
				lblAgregados.setHorizontalTextPosition(SwingConstants.CENTER);

				panelSuperior.add(lblAgregados);

				nuevoPremio = new ArrayList<String>();


				JPanel panelBotones = new JPanel(new GridLayout(1,6,5,10));


				panelBotones.add(btn1);
				panelBotones.add(btn2);
				panelBotones.add(btn3);
				panelBotones.add(btn4);
				panelBotones.add(btn5);
				panelBotones.add(btn6);

				JPanel panelInferior = new JPanel(new GridLayout(1,2));
				JLabel lblValorPremio = new JLabel("Valor del premio: ");
				JTextField txtValorPremio = new JTextField();
				panelInferior.add(lblValorPremio);
				panelInferior.add(txtValorPremio);


				panelGral.add(panelSuperior);
				panelGral.add(panelBotones);
				panelGral.add(panelInferior);

				int opcion = JOptionPane.showConfirmDialog(null,panelGral,"Agregar nuevo premio a la maquina " + seleccion,JOptionPane.OK_CANCEL_OPTION);
				if (opcion == 0){
					String[] array = new String[controlador.mostrarCantCasillas(seleccion)];
					int i = 0;
					for(String elem: nuevoPremio){
						array[i] = elem;
						i++;
					}
					System.out.println(Float.parseFloat(txtValorPremio.getText()));
					controlador.agregarPremio(seleccion,array,Float.parseFloat(txtValorPremio.getText()));
					cboPremios.addItem(Arrays.toString(array));

				}
				cantCasillas = controlador.mostrarCantCasillas(seleccion);

			}
		});
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn1.getText());
					nuevoPremio.add(btn1.getText());
					cantCasillas--;
				}

			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn2.getText());
					nuevoPremio.add(btn2.getText());
					cantCasillas--;
				}

			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn3.getText());
					nuevoPremio.add(btn3.getText());
					cantCasillas--;
				}

			}
		});
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn4.getText());
					nuevoPremio.add(btn4.getText());
					cantCasillas--;
				}

			}
		});
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn5.getText());
					nuevoPremio.add(btn5.getText());
					cantCasillas--;
				}

			}
		});
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cantCasillas>0) {
					lblAgregados.setText(lblAgregados.getText() +" "+ btn6.getText());
					nuevoPremio.add(btn6.getText());
					cantCasillas--;
				}

			}
		});



	}

}
