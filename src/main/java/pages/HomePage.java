package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

	private final By BTN_SEE_ORFER =  By.id("see_offer_btn");
	public final By name_orfer =  By.id("Description");
	
	public void clicar_ver_ofertas() throws InterruptedException {
		Thread.sleep(5000);
		clicar(BTN_SEE_ORFER);
	}
	
}
