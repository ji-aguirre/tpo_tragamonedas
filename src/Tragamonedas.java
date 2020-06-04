import java.util.ArrayList;
import java.util.Random;
import Premio;
import Jugador;
import Caja;
import java.util.Scanner;

public class Tragamonedas extends Premio {


    public Tragamonedas(float saldoIni, int cantCasillas) {
    	this.cajaActual = new Caja(saldoIni);
    	this.cantidadCasillas = cantCasillas;
    }


    private ArrayList<Premio> premios = new ArrayList<Premio>();

    private final String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"};

    private final int cantidadCasillas;

    public Jugador jugadorActual;

    public Caja cajaActual;

    public float precioJugada;

    public int id;

    private float buscarMinimoPremio(){

    }
    private int[] contarOcurrencias(String[] listado){ //funcion auxiliar para contar las ocurrencias de cada fruta en un arreglo
        int[] ocurrencias = new int[this.frutas.length];
        for(int i = 0; i<this.frutas.length;i++){
            for(String palabra:listado){
                if (palabra == this.frutas[i]){
                        ocurrencias[i] += 1;
                }
            }
        }
        return ocurrencias;

    }
    public void agregarPremio(String[] premio, float valor) { //TODO verificacion de datos
        Premio nuevoPremio = new Premio();
        nuevoPremio.combinacion = premio;
        nuevoPremio.valor = valor;
        if (valor < this.cajaActual.saldoMinimo){ //me fijo si el premio que voy a agregar es mas bajo que el minimo actual
            this.cajaActual.saldoMinimo = valor;
        }
        premios.add(nuevoPremio);

    }



    public String[] generarJugada() {
    	int[] rng = new int[this.cantidadCasillas];
    	String[] jugada = new String[this.cantidadCasillas];
    	this.jugadorActual.jugar(this.precioJugada); // le descuenta el precio de la jugada al credito del jugador
        this.cajaActual.incrementarSaldo(this.precioJugada);

    	Random r = new Random(); //Generador de numeros aleatorios

    	for(int i = 0; i< this.cantidadCasillas;i++){
    		rng[i] = r.nextInt(this.frutas.length); //RNG para determinar que fruta sale

    	}
    	for(int i = 0; i< this.cantidadCasillas;i++){
    		jugada[i] = this.frutas[rng[i]]; //Transformo la lista de RNG en strings 
    	}

    	float valorPremio = this.tienePremio(jugada); //Si es 0 sgnifica que no hay premio

        if (valorPremio != 0){
        	if (this.aceptarPremio()){
        		this.jugadorActual.agregarCredito(valorPremio);
        		this.cajaActual.reducirSaldo(valorPremio);

        	}

        }
        return jugada;
    }


    public boolean aceptarPremio() {

        Scanner lector = new Scanner(System.in);
        String aceptaPremio = lector.nextLine();
        return aceptaPremio == "si";
    }


    public float saldoActual() {
        return this.jugadorActual.mostrarCredito();
    	
    }


    public boolean verificarSaldoMinimo() {

        return this.cajaActual.haySaldoMinimo();
    }


    public int soyLaMaquina() {
        
        return this.id;
    }


    public float tienePremio(String[] jugada) {
    	//la funcion retorna true si las cantidades de cada fruta de la jugada coinciden con las de alguno de los premios
        for(Premio premio : this.premios){ // itero sobre todos los premios cargados
            if (premio.combinacion == jugada){ //me fijo si la combinacion ganadora es igual a la de la jugada. HAY QUE VER COMO HACER PARA LOS QUE ESTAN DESORDENADOS
                return premio.valor;
            }
        }
        return 0;
    }


    public boolean iniciarSesionJugador(float saldoInicial) {
        if(saldoInicial < 0){
            return false;
        }else {
            this.jugadorActual = new Jugador(saldoInicial);
        }
        return false;
    }

    
    public void terminarSesionJugador() {
        //TODO
    }


    public void cobrarSaldoDisponible() {
        float saldoACobrar = this.jugadorActual.cobrarCredito();

        
    }


    public boolean quitarPremioDeLista(int nro) {
        
        return false;
    }

}