package ModeloCasino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tragamonedas extends Premio {


    public Tragamonedas(float saldoIni, int cantCasillas,float precioJugada) {
    	this.cajaActual = new Caja(saldoIni);
    	this.cantidadCasillas = cantCasillas;
    	this.precioJugada = precioJugada;
    }


    private ArrayList<Premio> premios = new ArrayList<Premio>();

    private final String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"};

    public final int cantidadCasillas;

    public Jugador jugadorActual;

    public Caja cajaActual;

    public float precioJugada;

    public int id;

    private String[] ultimaJugada;

    public void modificarPrecioJugada(float precio){
        this.precioJugada = precio;
    }

    public void agregarPremio(String[] premio, float valor) { //TODO verificacion de datos
        Premio nuevoPremio = new Premio();
        nuevoPremio.combinacion = premio;
        nuevoPremio.valor = valor;

        if (valor < this.cajaActual.saldoMinimo){ //me fijo si el premio que voy a agregar es mas bajo que el minimo actual
            this.cajaActual.saldoMinimo = valor;
        }
        premios.add(nuevoPremio);
        nuevoPremio.id = premios.indexOf(nuevoPremio);

    }



    public void generarJugada() {
    	int[] rng = new int[this.cantidadCasillas];
    	String[] jugada = new String[this.cantidadCasillas];

    	Random r = new Random(); //Generador de numeros aleatorios

    	for(int i = 0; i< this.cantidadCasillas;i++){
    		rng[i] = r.nextInt(this.frutas.length); //RNG para determinar que fruta sale

    	}
    	for(int i = 0; i< this.cantidadCasillas;i++){
    		jugada[i] = this.frutas[rng[i]]; //Transformo la lista de RNG en strings 
    	}

        //System.out.println("Jugada: " + Arrays.toString(jugada) + " Precio: "+this.precioJugada);
        this.ultimaJugada = jugada;
    }
    public String[] getUltimaJugada(){
        return this.ultimaJugada;
    }


    public void aceptarPremio(float valorPremio) { //TODO validar args
        this.agregarCreditoJugador(valorPremio);
        //System.out.println("Agregue al jugador" + valorPremio);
        this.cajaActual.reducirSaldo(valorPremio);
    }

    public void agregarCreditoJugador(float credito){
        this.jugadorActual.agregarCredito(credito);

    }
    public void descontarCreditoJugador(float credito){
        this.jugadorActual.jugar(credito);
    }
    public void descontarCaja(float credito){
        this.cajaActual.reducirSaldo(credito);
    }
    public void incrementarCaja(float credito){
        this.cajaActual.incrementarSaldo(credito);
    }

    public float cajaActual(){
        return this.cajaActual.saldoActual();
    }


    public boolean verificarSaldoMinimo() {
        return this.cajaActual.haySaldoMinimo();
    }


    public int soyLaMaquina() {
        
        return this.id;
    }
    private void print(String[] array){ //Para debuggear
        for(String pal : array){
            System.out.println(pal);
        }
    }

    public float tienePremio(String[] jugada) {
    	//la funcion retorna el valor del premio si las cantidades de cada fruta de la jugada coinciden con las de alguno de los premios
        int cant = 0;

        for(Premio premio : this.premios){ // itero sobre todos los premios cargados
            cant = 0;
            for(int i = 0; i<this.cantidadCasillas;i++){
                if(!jugada[i].equals(premio.combinacion[i])){
                    break;
                }else{
                    cant++;
                }
            }
            if(cant == this.cantidadCasillas){
                //System.out.println("Coinciden! Hay premio" + premio.valor);
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
        this.cajaActual.incrementarSaldo(this.jugadorActual.cobrarCredito());
    }


    public void cobrarSaldoDisponible() {
        float saldoACobrar = this.jugadorActual.cobrarCredito();
        System.out.println(saldoACobrar);

        
    }

    public boolean quitarPremioDeLista(int nro) {
        for(Premio premio : this.premios){
            if (premio.id == nro){
                this.premios.remove(premio);
                return true;
            }
        }
        return false;
    }
    public float mostrarCreditoJugador(){
        return this.jugadorActual.mostrarCredito();
    }

}