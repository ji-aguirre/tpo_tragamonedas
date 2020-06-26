package ModeloCasino;

import java.util.HashMap;
import java.util.Set;

public class Controlador {

    public Controlador() { //Constructor
    }

    public HashMap maquinas=new HashMap<Integer,Tragamonedas>(); //uso un mapa para guardar el id y el tragamonedas correspondiente

    public Tragamonedas obtenerMaquina(int idMaquina){ //Obtiene la instacia de la maquina a la cual pertenece el id
        //TODO validacion de argumentos
        Integer id = idMaquina;
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(id);
        return maquina;
    }

    public boolean iniciarJuego(float saldoInicial, int idMaquina) { //Empieza un nuevo jugador en la maquina (Pasa credito inicial del jugador)
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.iniciarSesionJugador(saldoInicial);

        return false;
    }



    public void cobrarCreditoDisponible(int idMaquina) { //"Retira" el credito del jugador (destructivo)
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.cobrarSaldoDisponible();
    }

    
    public float realizarJugada(int idMaquina) { //Genera una nueva jugada en una maquina y devuelve el monto del premio ganado (0 si no hubo premio)
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        if (maquina.mostrarCreditoJugador() < maquina.precioJugada){ //Si el jugador no tiene credito no juega
            return 0;
        }else {
            maquina.descontarCreditoJugador(maquina.precioJugada); //descuento valor de jugada
            maquina.incrementarCaja(maquina.precioJugada);

            maquina.generarJugada();
            return maquina.tienePremio(maquina.getUltimaJugada());

        }

    }

    
    public void aceptarPremio(int idMaquina, float valorPremio) { //Acepta el premio de una maquina
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        if(maquina.verificarSaldoMinimo()) {
            maquina.aceptarPremio(valorPremio);
        }else{
            System.out.println("La maquina no posee saldo para pagar el premio. ");
        }


    }


    public void crearTragamonedas(float saldoInicial, int casillas, float precioJugada) {  //Crea una nueva maquina y la agrega a lista de maquinas
        //TODO validar args
        Tragamonedas nuevaMaquina = new Tragamonedas(saldoInicial, casillas, precioJugada);
        int id = this.maquinas.size() + 1;
        nuevaMaquina.id = id;
        System.out.println(id);
        while (this.maquinas.containsKey(id)){ //Busco la siguiente clave disponible en caso de que este usada la que quiero
            id +=1;
        }
        maquinas.put(id,nuevaMaquina);
    }


    public Tragamonedas eliminarTragamonedas(int idMaquina) { //Elimina una maquina del listado de maquinas (destructivo)
        //TODO validar args
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(idMaquina);
        return (Tragamonedas) this.maquinas.remove(idMaquina);

    }


    public void agregarCreditoAlJugador(int idMaquina, float credito) { //Agrega credito al jugador actual de la maquina
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarCreditoJugador(credito);

    }


    public void agregarPremio(int idMaquina, String[] premio, float valorPremio) { //Agrega un premio a la lista de premios de una maquina
        //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarPremio(premio,valorPremio);


    }


    public boolean eliminarPremio(int idMaquina, int idPremio) { //Elimina un premio de la lista de premios de una maquina
        //TODO validar args
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
        return maquina.verificarSaldoMinimo() && maquina.mostrarCreditoJugador() >= maquina.precioJugada;

    }
    public boolean existeMaquina(int idMaquina){ //Verifica la existencia de una maquina
        return this.maquinas.containsKey(idMaquina);
    }

    public Set<Integer> listadoMaquinas(){ //Listado de las maquinas disponibles en el Hashmap
        Set<Integer> listado = this.maquinas.keySet();
        return listado;
    }



}