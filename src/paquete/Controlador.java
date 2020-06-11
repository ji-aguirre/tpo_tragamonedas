package paquete;

import java.util.HashMap;
import java.util.Scanner;

public class Controlador {

	
    public Controlador() {
    }


    public HashMap maquinas=new HashMap<Integer,Tragamonedas>(); //uso un mapa para guardar el id y el tragamonedas correspondiente

    public Tragamonedas obtenerMaquina(int idMaquina){ //TODO validacion de argumentos
        Integer id = idMaquina;
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(id);
        return maquina;
    }

    public boolean iniciarJuego(float saldoInicial, int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.iniciarSesionJugador(saldoInicial);

        return false;
    }



    public void cobrarCreditoDisponible(int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.cobrarSaldoDisponible();
    }

    
    public boolean realizarJugada(int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        if (maquina.mostrarCreditoJugador() < maquina.precioJugada){ //Si el jugador no tiene credito no juega
            return false;
        }else {
            maquina.jugadorActual.jugar(maquina.precioJugada); //descuento valor de jugada
            maquina.cajaActual.incrementarSaldo(maquina.precioJugada);

            String[] jugada = maquina.generarJugada();
            float resp = maquina.tienePremio(jugada);
            if (resp > 0) {
                this.aceptaPremio(idMaquina, resp);
            }else{
                return false;
            }
        }
        return true;

    }

    
    public void aceptaPremio(int idMaquina, float valorPremio) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        Scanner lector = new Scanner(System.in);
        String aceptaPremio = lector.nextLine();
        if (aceptaPremio.equals("yes")){
            if(maquina.verificarSaldoMinimo()) {
                maquina.aceptarPremio(valorPremio);
            }else{
                System.out.println("La maquina no posee saldo para pagar el premio. ");
            }
        }

    }


    public void crearTragamonedas(float saldoInicial, int casillas, float precioJugada) { //TODO validar args
        Tragamonedas nuevaMaquina = new Tragamonedas(saldoInicial, casillas, precioJugada);
        int id = this.maquinas.size() + 1;
        nuevaMaquina.id = id;
        System.out.println(id);
        while (this.maquinas.containsKey(id)){ //Busco la siguiente clave disponible en caso de que este usada la que quiero
            id +=1;
        }
        maquinas.put(id,nuevaMaquina);
    }


    public Tragamonedas eliminarTragamonedas(int idMaquina) { //TODO validar args
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(idMaquina);
        return (Tragamonedas) this.maquinas.remove(idMaquina);

    }


    public void agregarCreditoAlJugador(int idMaquina, float credito) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarCreditoJugador(credito);

    }


    public void agregarPremio(int idMaquina, String[] premio, float valorPremio) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.agregarPremio(premio,valorPremio);


    }


    public boolean eliminarPremio(int idMaquina, int idPremio) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        if (maquina.quitarPremioDeLista(idPremio)) {
            return true;
        } else {
            return false;
        }
    }
    public float mostrarCreditoMaquina(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.mostrarCreditoJugador();
    }
    public float mostrarCajaMaquina(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.cajaActual();
    }
    public boolean puedeJugar(int idMaquina){
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        return maquina.verificarSaldoMinimo() && maquina.mostrarCreditoJugador() >= maquina.precioJugada;

    }
    public boolean existeMaquina(int idMaquina){
        return this.maquinas.containsKey(idMaquina);
    }


}