package ModeloCasino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Controlador {

    public Controlador() { //Constructor
    }

    public HashMap maquinas=new HashMap<Integer,Tragamonedas>(); //uso un mapa para guardar el id y el tragamonedas correspondiente

    private Tragamonedas obtenerMaquina(int idMaquina){ //Obtiene la instacia de la maquina a la cual pertenece el id
        Integer id = idMaquina;
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(id);
        return maquina;
    }

    public void iniciarJuego(float saldoInicial, int idMaquina) { //Empieza un nuevo jugador en la maquina (Pasa credito inicial del jugador)
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.iniciarSesionJugador(saldoInicial);

    }

    public float cobrarCreditoDisponible(int idMaquina) { //"Retira" el credito del jugador (destructivo)
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.cobrarSaldoDisponible();
    }

    public float realizarJugada(int idMaquina) { //Genera una nueva jugada en una maquina y devuelve el monto del premio ganado (0 si no hubo premio)
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        return maquina.jugar();

    }

    public void aceptarPremio(int idMaquina, float valorPremio) { //Acepta el premio de una maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.aceptarPremio(valorPremio);



    }


    public int crearTragamonedas(float saldoInicial, int casillas, float precioJugada) {  //Crea una nueva maquina y la agrega a lista de maquinas
        Tragamonedas nuevaMaquina = new Tragamonedas(saldoInicial, casillas, precioJugada);
        int id = this.maquinas.size() + 1;
        nuevaMaquina.id = id;
        //System.out.println(id);
        while (this.maquinas.containsKey(id)){ //Busco la siguiente clave disponible en caso de que este usada la que quiero
            id +=1;
        }
        maquinas.put(id,nuevaMaquina);
        iniciarJuego(0,id);
        return id;
    }


    public Tragamonedas eliminarTragamonedas(int idMaquina) { //Elimina una maquina del listado de maquinas (destructivo)
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(idMaquina);
        return (Tragamonedas) this.maquinas.remove(idMaquina);

    }


    public void agregarCreditoAlJugador(int idMaquina, float credito) { //Agrega credito al jugador actual de la maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarCreditoJugador(credito);

    }


    public void agregarPremio(int idMaquina, String[] premio, float valorPremio) { //Agrega un premio a la lista de premios de una maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarPremio(premio, valorPremio);

    }


    public boolean eliminarPremio(int idMaquina, int idPremio) { //Elimina un premio de la lista de premios de una maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.quitarPremioDeLista(idPremio);
    }

    public float mostrarCreditoMaquina(int idMaquina){ //Devuelve el credito actual de la maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.mostrarCreditoJugador();
    }

    public float mostrarCajaMaquina(int idMaquina){ //Devuelve el monto de la caja actual de la maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.cajaActual();
    }

    public String[] mostrarUltimaJugada(int idMaquina){ //Devuelve la ultima jugada realizada (para mostrar en pantalla)
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.getUltimaJugada();

    }

    public boolean puedeJugar(int idMaquina){ //Verifica que se pueda realizar una jugada en la maquina
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.mostrarCreditoJugador() >= maquina.precioJugada;

    }
    public boolean existeMaquina(int idMaquina){ //Verifica la existencia de una maquina
        return this.maquinas.containsKey(idMaquina);
    }

    public Set<Integer> listadoMaquinas(){ //Listado de las maquinas disponibles en el Hashmap
        Set<Integer> listado = this.maquinas.keySet();
        return listado;
    }

    public float mostrarPrecioJugada(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.precioJugada;
    }

    public int mostrarCantCasillas(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.cantidadCasillas;
    }
    public ArrayList<String[]> mostrarListadoPremios(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        return maquina.listarPremios();
    }
    public boolean haySaldoMinimo(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.verificarSaldoMinimo();
    }
    public float mostrarValorPremio(int idMaquina,int idPremio){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.getValorPremio(idPremio);

    }




}