public class Controlador {

	
    public Controlador() {
    }


    public Tragamonedas[] maquinas;


    public int[] idUnicos;


    public boolean iniciarJuego(float saldoInicial, int id) {
        
        return false;
    }


    public boolean cobrarCreditoDisponible(int idMaquina) {
        
        return false;
    }

    
    public boolean realizarJugada(int idMaquina) {
        
        return false;
    }

    
    public void aceptaPremio(int idMaquina, boolean acepta) {
        
    }


    public boolean crearTragamonedas(float saldoInicial, int casillas) {
        
        return false;
    }


    public boolean eliminarTragamonedas(int idMaquina) {
        
        return false;
    }


    public boolean agregarCreditoAlJugador(int idMaquina, float credito) {
        
        return false;
    }


    public Premio agregarPremio(int idMaquina, String[] premio) {
        
        return null;
    }


    public boolean eliminarPremio(int idMaquina, int idPremio) {
        
        return false;
    }

}