package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.sc.java.core.BasePage;

public class TestePrimeFaces {
	
	private BasePage page;

	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		page = new BasePage();

	}

	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveInteragirComRadioPrime(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		//page.clicarRadio(By.xpath("//*[@id='j_idt721:console']/tbody/tr/td[1]/div"));//com xpath do chrome
		page.clicarRadio(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));//com xpath do curso
		Assert.assertTrue(page.isRadioMarcado("j_idt721:console:0"));
		page.clicarRadio(By.xpath("//label[.='PS4']/..//span"));//com xpath do curso
		Assert.assertTrue(page.isRadioMarcado("j_idt721:console:1"));
	
	}
	@Test
	public void deveInteragirComSelectPrime(){
		getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		page.selecionarComboPrime("j_idt721:console", "Xbox One");
		Assert.assertEquals("PS4", page.obterTexto("j_idt721:console_items"));
	}
}
