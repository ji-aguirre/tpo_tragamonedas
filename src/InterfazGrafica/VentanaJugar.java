package InterfazGrafica;

import ModeloCasino.Controlador;
import ModeloCasino.Premio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;


public class VentanaJugar extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel lblPrecioJugada;
	private JButton btnJugar, btnCobrar, btnAgregarCredito, btnCredito,btnPremios;

	private JPanel panelCasillas;

	private final String[] frutas = {"/resources/banana.png","/resources/frutilla.png","/resources/guinda.png","/resources/manzana.png","/resources/sandia.png","/resources/uva.png"};

	private Controlador controlador;
	private Integer seleccion;
	
	public VentanaJugar(Controlador controlador, Integer seleccion) {
		this.controlador = controlador;
		this.seleccion = seleccion;
		try {
			configurar();
		}catch (Exception e){
			System.out.println("No encontro imagen.");
		}
		Eventos(controlador,seleccion);
	}




	private void configurar() throws IOException {
		Container c = this.getContentPane();
		c.setLayout(null);
		int cantCasillas = controlador.mostrarCantCasillas(seleccion);

		lblPrecioJugada = new JLabel("Precio de la jugada: " + controlador.mostrarPrecioJugada(seleccion));
		lblPrecioJugada.setBounds(30, 0, 280, 30);
		lblPrecioJugada.setAlignmentX(SwingConstants.CENTER);
		lblPrecioJugada.setAlignmentY(SwingConstants.CENTER);


		//Creo automaticamente la fila de casillas

		panelCasillas = new JPanel(new FlowLayout(FlowLayout.CENTER,2,2));
		panelCasillas.setSize(280,128);

		Random random = new Random();
		for(int i = 0; i<cantCasillas;i++){

			Image imagen = ImageIO.read(getClass().getResource(frutas[random.nextInt(frutas.length)]));
			Image redimension = imagen.getScaledInstance(280/cantCasillas,280/cantCasillas,Image.SCALE_SMOOTH); //Aca habria que encontrar la manera de escalar las imagenes en base a la cantidad de casillas

			ImageIcon icono = new ImageIcon(redimension);
			JLabel casilla = new JLabel(icono);
			//casilla.setSize(280/cantCasillas,128/cantCasillas);

			panelCasillas.add(casilla);
		}
		panelCasillas.setLocation(30,30);

		panelCasillas.setBackground(Color.DARK_GRAY);
		c.add(panelCasillas);


		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(30,160,130,30);
		btnJugar.setBackground(Color.RED);
		btnCobrar = new JButton("Cobrar");
		btnCobrar.setBounds(30, 190, 130, 30);
		btnAgregarCredito = new JButton("Agregar Credito");
		btnAgregarCredito.setBounds(180, 190, 130, 30);
		btnCredito = new JButton("Crédito: " + controlador.mostrarCreditoMaquina(seleccion));
		btnCredito.setBounds(180, 160, 130, 30);
		btnPremios = new JButton("Ver Premios");
		btnPremios.setBounds(30,220,130,30);



		c.add(lblPrecioJugada);
		c.add(btnJugar);
		c.add(btnCobrar);
		c.add(btnAgregarCredito);
		c.add(btnCredito);
		c.add(btnPremios);
	}

	private void Eventos(Controlador controlador, Integer seleccion) {
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				if (controlador.puedeJugar(seleccion)) {
					float premio = controlador.realizarJugada(seleccion);
					btnCredito.setText("Credito: " + controlador.mostrarCreditoMaquina(seleccion));
					for (int i = 0;i<controlador.mostrarCantCasillas(seleccion);i++){
						JLabel boton = (JLabel) panelCasillas.getComponent(i);
						Image imagen = null;
						try {
							imagen = ImageIO.read(getClass().getResource("/resources/"+controlador.mostrarUltimaJugada(seleccion)[i]+".png"));
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
						Image redimension = imagen.getScaledInstance(280/controlador.mostrarCantCasillas(seleccion),280/controlador.mostrarCantCasillas(seleccion),Image.SCALE_SMOOTH);

						ImageIcon icono = new ImageIcon(redimension);
						boton.setIcon(icono);
					}

					if (premio != 0 && controlador.haySaldoMinimo(seleccion)){
						int respuesta = JOptionPane.showConfirmDialog(null,"Acepta el premio de " + premio + "?","Acepta premio?",JOptionPane.YES_NO_OPTION);
						if (respuesta == 0){
							controlador.aceptarPremio(seleccion,premio);
							btnCredito.setText("Credito: "+controlador.mostrarCreditoMaquina(seleccion));
						}
					}
					else if(premio != 0 && !controlador.haySaldoMinimo(seleccion)){
						JOptionPane.showMessageDialog(null,"La maquina no posee saldo suficiente para pagar el premio. ","Saldo insuficiente",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null,"No tenes credito para continuar jugando. Cargale mas.");
				}


			}
		});
		btnCobrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				String mensaje = "Se cobraron " + controlador.cobrarCreditoDisponible(seleccion) + "$. ";
				JOptionPane.showConfirmDialog(null,mensaje,"Cobrar Credito",JOptionPane.OK_OPTION);
				btnCredito.setText("Credito: " + controlador.mostrarCreditoMaquina(seleccion));

			}
		});
		btnAgregarCredito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { //Abro una ventana nueva
				JPanel inputPanel = new JPanel(new GridLayout(2,1));

				JLabel lblMensaje = new JLabel("Cuanto credito quiere agregar?");
				JTextField textInput = new JTextField("0");

				inputPanel.add(lblMensaje);
				inputPanel.add(textInput);

				if(0==JOptionPane.showConfirmDialog(null,inputPanel,"agregar credito?",JOptionPane.OK_CANCEL_OPTION)){
					controlador.agregarCreditoAlJugador(seleccion,Float.parseFloat(textInput.getText()));
					btnCredito.setText("Credito: " + controlador.mostrarCreditoMaquina(seleccion));
				}


			}
		});

		btnPremios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel premios = new JPanel(new GridLayout(controlador.mostrarListadoPremios(seleccion).size(),1,0,2));
				int j = 0;
				for(String[] string : controlador.mostrarListadoPremios(seleccion)){
					JLabel lbl = new JLabel(Arrays.toString(string) + " Valor: " + controlador.mostrarValorPremio(seleccion,j));
					lbl.setHorizontalTextPosition(SwingConstants.CENTER);
					j++;
					premios.add(lbl);
				}
				JOptionPane.showMessageDialog(null,premios,"Premios disponibles",JOptionPane.PLAIN_MESSAGE);
			}
		});

	}

	
	
}
