public class Caja {

	
	
    public Caja() {
    }


    private float saldoMinimo;

    private float saldoActual;



    public void incrementarSaldo(float nro) {
    	if(nro>0) {
    		this.saldoActual = nro;
    	}
        
    }


    public void reducirSaldo(float nro) {
    	if(nro>0) {
    		this.saldoActual = this.saldoActual - nro;
    	}
        
    }

    
    public boolean haySaldoMinimo() {
    	if(this.saldoActual == this.saldoMinimo) {
    		return true;
    	}else {
    		return false;
    	}
        
    }

}