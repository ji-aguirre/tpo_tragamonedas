import java.util.ArrayList;
import java.util.Random
import Premio
import Jugador
import Caja
import java.util.Scanner

public class Tragamonedas extends Premio {


    public Tragamonedas(float saldoIni, int cantCasillas) {
    	this.cajaActual = new Caja(saldoIni)
    	this.cantidadCasillas = cantCasillas
    }


    private ArrayList<Premio> premios = new ArrayList<Premio>();

    private String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"};

    private int cantidadCasillas;

    public Jugador jugadorActual;

    public Caja cajaActual;

    public float precioJugada;

    public int id;


    public Premio agregarPremio(String[] premio) {

     return null;
    }

    public void quitarPremio() {
        
    }


    public String[] generarJugada() {
    	int[] rng = new int[this.cantidadCasillas]
    	String[] jugada = new String[this.cantidadCasillas]

    	Random r = new Random()
    	for(int i = 0; i< this.cantidadCasillas;i++){
    		rng[i] = r.nextInt(this.frutas.lenght); //RNG para determinar que fruta sale 

    	}
    	for(int i = 0; i< this.cantidadCasillas;i++){
    		jugada[i] = this.frutas[rng[i]]; //Transformo la lista de RNG en strings 
    	}

    	float valorPremio = this.tienePremio(jugada) //Si es 0 sgnifica que no hay premio

        if (valorPremio != 0){
        	if this.aceptarPremio(){
        	
        		this.jugadorActual.agregarCredito(valorPremio)
        		this.cajaActual.incrementarSaldo(valorPremio)

        	}

        }
        return jugada;
    }


    public boolean aceptarPremio() {

        Scanner lector = new Scanner(System.in);
        String aceptaPremio = lector.nextLine()
        if aceptaPremio == "si"{

        	return false
        }
        return false
    }


    public void saldoActual() {
    	
    }


    public boolean verificarSaldoMinimo() {


        
        return false;
    }


    public int soyLaMaquina() {
        
        return 0;
    }


    public float tienePremio(String[] jugada) {
    	//la funcion retorna true si las cantidades de cada fruta de la jugada coinciden con las de alguno de los premios
        
        return false;
    }


    public boolean iniciarSesionJugador(float saldoInicial) {
    	this.jugadorActual = new Jugador(saldoInicial)
        
        return false;
    }

    
    public void terminarSesionJugador() {
        
    }


    public void cobrarSaldoDisponible() {
        
    }


    public boolean quitarPremioDeLista(int nro) {
        
        return false;
    }

}