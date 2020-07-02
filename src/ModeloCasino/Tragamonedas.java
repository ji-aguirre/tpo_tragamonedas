package ModeloCasino;

import java.util.ArrayList;
import java.util.Random;

public class Tragamonedas extends Premio {


    public Tragamonedas(float saldoIni, int cantCasillas,float precioJugada) {
    	this.cajaActual = new Caja(saldoIni);
    	this.cantidadCasillas = cantCasillas;
    	this.precioJugada = precioJugada;
    }


    private ArrayList<Premio> premios = new ArrayList<Premio>(); //Listado de premios de la maquina

    private final String[] frutas = {"banana","frutilla","guinda","manzana","sandia","uva"}; //Frutas de la maquina

    public final int cantidadCasillas; //Cantidad de casillas de la maquina

    public Jugador jugadorActual; //Billetera del jugador actual en la maquina (volatil)

    public Caja cajaActual; //Caja de la maquina

    public float precioJugada; //Precio de la jugada de la maquina

    public int id; // ID unico de la maquina

    private String[] ultimaJugada; //Mantiene la ultima jugada realizada (para mostrar en cualquier momento)

    public void modificarPrecioJugada(float precio){
        this.precioJugada = precio;
    }

    public void agregarPremio(String[] premio, float valor) { //TODO verificacion de datos
        Premio nuevoPremio = new Premio();
        nuevoPremio.combinacion = premio;
        nuevoPremio.valor = valor;

        if (nuevoPremio.valor < this.cajaActual.saldoMinimo){ //me fijo si el premio que voy a agregar es mas bajo que el minimo actual
            System.out.println("valor "+valor);
            this.cajaActual.saldoMinimo = valor;
        }
        System.out.println("saldo minimo "+this.cajaActual.saldoMinimo);
        premios.add(nuevoPremio);
        nuevoPremio.id = premios.indexOf(nuevoPremio);

    }



    public void generarJugada() {
    	int[] numeros = new int[this.cantidadCasillas];
    	String[] jugada = new String[this.cantidadCasillas];

    	Random rng = new Random(); //Generador de numeros aleatorios

    	for(int i = 0; i< this.cantidadCasillas;i++){
    		numeros[i] = rng.nextInt(this.frutas.length); //RNG para determinar que fruta sale


    	}
    	for(int i = 0; i< this.cantidadCasillas;i++){
    		jugada[i] = this.frutas[numeros[i]]; //Transformo la lista de RNG en strings
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


    public float cobrarSaldoDisponible() {
        float saldoACobrar = this.jugadorActual.cobrarCredito();
        return saldoACobrar;

        
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
    public ArrayList<String[]> listarPremios(){
        ArrayList<String[]> listaPremios = new ArrayList<String[]>();
        for(Premio prem : premios){
            listaPremios.add(prem.combinacion.clone());
        }
        return listaPremios;
    }

    public float getValorPremio(int idPremio){
        return premios.get(idPremio).valor;

    }

}