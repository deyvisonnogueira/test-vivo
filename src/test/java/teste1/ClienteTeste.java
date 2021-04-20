package teste1;


import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


import org.junit.Before;
import org.junit.Test;

public class ClienteTeste {
	
	private Cliente cliente01;
	private Telefone telefone01;
	private Conta conta01;
	
	/* Preparação do cenário de teste*/
	@Before
	public void setup() {
		cliente01 = new Cliente();
		telefone01 = new Telefone();
		conta01 = new Conta();
		cliente01.setNome("Deyvison Nogueira Rodrigues");
		/*Criação do telefone*/
		telefone01.setNumeroLinha("19981064930");
		telefone01.setSaldo(0);
		
		/*Criação da Conta*/
		conta01.setSaldo(100);
		conta01.setRecarga(30);
		
		cliente01.setConta(conta01);
		cliente01.setLinha(telefone01);
		
	}
	
	/*
	 * Teste com sucesso
	 * */
	@Test
	public void testRecarregar() {
		
		/*Execução*/
		boolean teste = cliente01.recarregar();
		
		/*Verificação*/
		assertTrue(teste);
		
	}
	
	/*
	 * Teste com saldo insuficiente
	 * */
	@Test
	public void testRecarregarSaldoInsuficente() {
		
		/*Execução*/
		cliente01.getConta().setSaldo(10);
		boolean teste = cliente01.recarregar();
		
		/*Verificação*/
		assertThat(teste, is(false));
		
	}
	
	/*
	 * Teste com recarga insuficiente
	 * */
	@Test
	public void testRecarregarCargaInsuficente() {
		
		/*Execução*/
		cliente01.getConta().setSaldo(10);
		boolean teste = cliente01.recarregar();
		
		/*Verificação*/
		assertThat(teste, is(false));
		
	}
	
	/*
	 * Teste com valor de revarga negativo
	 * */
	@Test(expected = IllegalArgumentException.class)
	public void testRecarregarCargaNegativa() {
		
		/*Execução*/
		cliente01.getConta().setRecarga(-10);
		boolean teste = cliente01.recarregar();
		
		/*Verificação*/
		assertThat(teste, is(false));
		
	}

}
