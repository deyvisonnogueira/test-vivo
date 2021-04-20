package teste1;

public class Telefone {
	  
	  
	  private String numero_linha;
	  private int saldo;
	  
	  
	  public void setNumeroLinha(String numero_linha) {
		  if (numero_linha != null && numero_linha.length() == 11 && numero_linha.matches("^[0-9]+$")) {
				this.numero_linha = numero_linha;
			} else {
				throw new IllegalArgumentException(
						"Erro: O número do telefone nao pode ser nulo e deve conter 11 dígitos. Valor informado: "
								+ numero_linha);
			}
	  }
	  
	  public String getNumeroLinha() {
	  	return this.numero_linha;
	  }
	  
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
}