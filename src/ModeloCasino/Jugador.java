package ModeloCasino;

public class Jugador {

	private float credito; //Credito disponible al jugador
	
	
    public Jugador(float saldoIni) {
        this.credito = saldoIni;
    }
    

    public boolean agregarCredito(float nro) { //Agrega credito
    	if (nro < 0){
    		return false;
    	}else{
    		System.out.println("Agrego credito al jugador");
    		this.credito += nro;
    		return true;
    	}
        
    }

    
    public float cobrarCredito() { //Retirar credito del jugador
    	if(this.credito==0) {
    		return 0;
    	}else {
            float cobra = this.credito;
    		this.credito = 0;
    		return cobra;
    	}

    }

 
    public boolean jugar(float precioJugada) { //Cobrar una jugada
    	if(this.credito < precioJugada) {
    		return false;
    	}else {
    		this.credito = this.credito - precioJugada;
    		return true;
    	}

    }

   
    public float mostrarCredito() { //Mostrar credito del jugador
        return this.credito;
    }

}