package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sc.java.core.BasePage;


public class TesteAjax {
	
	private BasePage page;

	
	@Before
	public void inicializa() {
		getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		page = new BasePage();

	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void testeAjax() {
		
		page.escrever("j_idt720:name", "Teste");
		page.clicarBotao("j_idt720:j_idt723");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt720:display"), "Teste"));
		Assert.assertEquals("Teste", page.obterTexto("j_idt720:display"));
	}

}
