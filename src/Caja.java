public class Caja {

	
	
    public Caja(float saldoIni) {
    	this.saldoActual= saldoIni;
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
		return this.saldoActual == this.saldoMinimo;
        
    }

}