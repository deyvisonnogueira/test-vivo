package teste1;

public class Cliente {
	   
	   
	   private String nome_cliente;
	   private Telefone telefone; 
	   private Conta conta;
	   
	   
	   public void setNome(String nome_cliente) {
		   if (nome_cliente.matches("^[aA-zZ]+(( [aA-zZ]+)+)?$")) {
				this.nome_cliente = nome_cliente;
			} else {
				throw new IllegalArgumentException(
						"Erro: O Nome não poder ser nulo e deve possuir pelo menos 1 caracteres. Valor informado: "
								+ nome_cliente);
			}
	   }
	   
	   public String getNome() {
	   	return this.nome_cliente;
	   }
	   
	   public void setLinha(Telefone telefone) {
	   	this.telefone = telefone;
	   }
	   
	   public Telefone getTelefone() {
	   	return this.telefone;
	   }
	   
	   public void setConta(Conta conta) {
	   	this.conta = conta;
	   }
	   
	   public Conta getConta() {
	   	return this.conta;
	   }
	   
	   public boolean recarregar() {
		   /* verifica se saldo é suficiente */
		   if(this.conta.getSaldo() >= this.conta.getRecarga()) {
			   this.conta.setSaldo( this.getConta().getSaldo() - this.conta.getRecarga() );
			   return true;
		   }else {
			   return false;
		   }
		   
	   }


}