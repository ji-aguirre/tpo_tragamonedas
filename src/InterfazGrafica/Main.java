package InterfazGrafica;
import ModeloCasino.Controlador;


public class Main {

	public static void main(String[] args) {
		Controlador controlador = new Controlador();

		VentanaPrincipal miVentanaPrincipal= new VentanaPrincipal(controlador);
		miVentanaPrincipal.maquinasDisponibles = controlador.listadoMaquinas();
		
		miVentanaPrincipal.setSize(400, 300);
		miVentanaPrincipal.setLocation(450,230);
		miVentanaPrincipal.setVisible(true);
	}
}