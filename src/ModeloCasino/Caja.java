package ModeloCasino;

public class Caja {

    public Caja(float saldoIni) {
    	this.saldoActual= saldoIni;
    	this.saldoMinimo = Float.MAX_VALUE;
    }

    public float saldoMinimo;
    private float saldoActual;

    public void incrementarSaldo(float nro) {
    	if(nro>0) {
    		this.saldoActual += nro;
    	}
        
    }

    public void reducirSaldo(float nro) {
    	if(nro>0) {
    		this.saldoActual = this.saldoActual - nro;
    	}
        
    }

    public float saldoActual(){
        return this.saldoActual;
    }

    public boolean haySaldoMinimo() {
		return this.saldoActual >= this.saldoMinimo;
        
    }

}