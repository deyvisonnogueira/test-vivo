package core;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import cucumber.api.Scenario;

/*
 * Classe que contém os métodos a serem chamados antes e/ou depois de cada execução.
*/
public class Hooks {	

	@cucumber.api.java.Before
	public void inicio() {
		DriverFactory.getDriver().get("https://advantageonlineshopping.com/");
		DriverFactory.getDriver().manage().window().maximize();
	}

	@cucumber.api.java.After
	public void fim(Scenario cenario) throws IOException {

		TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target/screenshots/" + cenario.getName() + ".jpg"));

		if (Propriedades.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}
}
