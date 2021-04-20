package steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.es.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import pages.HomePage;

public class HomeSteps {
	
	private HomePage homePage = new HomePage();
	
	
	@Dado("^que acessei a pagina inicial")
	public void acessar_site() {
		
	}
	
	@Quando("^clicar no botao ver ofertas")
	public void clicar_ver_ofertas() throws InterruptedException {
		homePage.clicar_ver_ofertas();
	}
	
	@Entao("^valido a oferta \"([^\"]*)\"$")
	public void validar_ofertas(String nomeParam) {
		String nome_Oferta = homePage.obterTexto(homePage.name_orfer);
		assertTrue(nome_Oferta.contains(nomeParam));

	}

}
