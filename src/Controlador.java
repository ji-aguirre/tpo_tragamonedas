import java.util.HashMap;
import Tragamonedas;
public class Controlador {

	
    public Controlador() {
    }


    public HashMap maquinas=new HashMap<Integer,Tragamonedas>();
    private Tragamonedas obtenerMaquina(int idMaquina){ //TODO validacion de argumentos
        Integer id = idMaquina;
        Tragamonedas maquina = (Tragamonedas) this.maquinas.get(id);
        return maquina;
    }

    public boolean iniciarJuego(float saldoInicial, int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        return false;
    }



    public boolean cobrarCreditoDisponible(int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);
        maquina.cobrarSaldoDisponible();
        return true;
    }

    
    public boolean realizarJugada(int idMaquina) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        maquina.generarJugada();

        return false;
    }

    
    public void aceptaPremio(int idMaquina, boolean acepta) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

    }


    public boolean crearTragamonedas(float saldoInicial, int casillas) { //TODO validar args
        
        return false;
    }


    public boolean eliminarTragamonedas(int idMaquina) { //TODO validar args
        this.maquinas.remove(idMaquina);
        return false;
    }


    public boolean agregarCreditoAlJugador(int idMaquina, float credito) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        return false;
    }


    public Premio agregarPremio(int idMaquina, String[] premio) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);


        return null;
    }


    public boolean eliminarPremio(int idMaquina, int idPremio) { //TODO validar args
        Tragamonedas maquina = this.obtenerMaquina(idMaquina);

        return false;
    }

}