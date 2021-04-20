package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DriverFactory;

public class BasePage {
	
	private final By BTN_SALVAR = By.className("btn-primary");
	private final By MSG_SUCCESS = By.className("alert-success");
	private final By MSG_ERROR = By.className("alert-danger");
	
	
	
	
	public void clicar(By by) {
		buscarElemento(by).click();
	}


	public void escrever(By by, String texto) {
		buscarElemento(by).clear();
		buscarElemento(by).sendKeys(texto);
	}
	
	
	public void validaElementoVisivelBy(By by) {
		assertTrue(buscarElemento(by).isDisplayed());
	}
	
	

	
	
	public void clicarSalvar() {
		clicar(BTN_SALVAR);
	}
	
	public void validaMsgSucesso() {
		validaElementoVisivelBy(MSG_SUCCESS);
	}
	
	public void validaMsgErro() {
		validaElementoVisivelBy(MSG_ERROR);
	}

	public WebElement buscarElemento(By by) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 10);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	public void validaUrl(String expectedUrl) {
		assertEquals(expectedUrl, DriverFactory.getDriver().getCurrentUrl());
	}

	public void validarTexto(By by, String textoEsperado) {
		assertEquals(textoEsperado, obterTexto(by));
	}






	public String obterValorCampo(By by) {
		return buscarElemento(by).getAttribute("value");
	}

	public boolean isRadioOuCheckMarcado(By by) {
		return buscarElemento(by).isSelected();
	}

	/********* Combo ************/

	public void selecionarCombo(By by, String valor) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}

	public void deselecionarCombo(By by, String valor) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(By by) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}

	public List<String> obterValoresCombo(By by) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for (WebElement opcao : allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}

	public int obterQuantidadeOpcoesCombo(By by) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}

	public boolean verificarOpcaoCombo(By by, String opcao) {
		WebElement element = buscarElemento(by);
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for (WebElement option : options) {
			if (option.getText().equals(opcao)) {
				return true;
			}
		}
		return false;
	}

	public void clicarLink(String link) {
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}

	/********* Textos ************/

	public String obterTexto(By by) {
		return buscarElemento(by).getText();
	}

	/********* Alerts ************/

	public String alertaObterTexto() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}

	public String alertaObterTextoEAceita() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;

	}

	public String alertaObterTextoENega() {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;

	}

	public void alertaEscrever(String valor) {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}

	/********* Frames e Janelas ************/

	public void entrarFrame(String id) {
		DriverFactory.getDriver().switchTo().frame(id);
	}

	public void sairFrame() {
		DriverFactory.getDriver().switchTo().defaultContent();
	}

	public void trocarJanela(String id) {
		DriverFactory.getDriver().switchTo().window(id);
	}

	/********* Java Script ************/
	public Object executaJS(String comando, Object... parametros) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		return js.executeScript(comando, parametros);
		// dsl.executaJS("arguments[0].style.border = arguments[2]", element, "blue",
		// "solid 4px red"); --exemplo para alterar cor do input
	}


	public WebElement getTableElement(String colunaBusca, String valor, String colunaBotao, By idTabela) {
		// procurar coluna do registro
		WebElement tabela = DriverFactory.getDriver().findElement(idTabela);
		int idColuna = obterIndiceColuna(colunaBusca, tabela);

		// encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);

		// procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		// clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
		return celula;

	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); i++) {
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i + 1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); i++) {
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i + 1;
				break;
			}
		}
		return idColuna;
	}
}
