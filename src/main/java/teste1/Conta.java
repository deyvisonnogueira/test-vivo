package teste1;


public class Conta {
	  
	  
	  private int saldo;
	  private int vl_recarga;
	  
	  public void setSaldo(int saldo) {
		if(saldo < 0) {
			throw new IllegalArgumentException("Erro: O Saldo não poder ser negativo. Valor informado:" + saldo);
		}else {
			this.saldo = saldo;
		}
	  	
	  }
	  
	  public int getSaldo() {
	  	return this.saldo;
	  }
	  
	  public void setRecarga(int vl_recarga) {
		  if(vl_recarga < 0) {
				throw new IllegalArgumentException("Erro: O valor de recarga não poder ser negativo. Valor informado:" + vl_recarga);
			}else {
				this.vl_recarga = vl_recarga;
			}
	  }
	  
	  public int getRecarga() {
	  	return this.vl_recarga;
	  }
	  
	  
}