package ModeloCasino;

public class Jugador {

	private float credito;
	
	
    public Jugador(float saldoIni) {
        this.credito = saldoIni;
    }
    

    public boolean agregarCredito(float nro) {
    	if (nro < 0){
    		return false;
    	}else{
    		System.out.println("Agrego credito al jugador");
    		this.credito += nro;
    		return true;
    	}
        
    }

    
    public float cobrarCredito() {
    	if(this.credito==0) {
    		return 0;
    	}else {
            float cobra = this.credito;
    		this.credito = 0;
    		return cobra;
    	}

    }

 
    public boolean jugar(float precioJugada) {
    	if(this.credito < precioJugada) {
    		return false;
    	}else {
    		this.credito = this.credito - precioJugada;
    		return true;
    	}

    }

   
    public float mostrarCredito() {
        return this.credito;
    }

}