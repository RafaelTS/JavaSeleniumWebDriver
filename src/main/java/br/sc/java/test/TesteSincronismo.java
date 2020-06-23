package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.sc.java.core.BasePage;

public class TesteSincronismo {

	private BasePage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new BasePage();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		page.clicarBotao("buttonDelay");
		Thread.sleep(5000); // not good
		page.escrever("novoCampo", "Deu Certo?");

	}

	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		// este código pode ir no inicializa, caso a espera precise do tempo em todas as
		// execuções
		getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		page.clicarBotao("buttonDelay");
		page.escrever("novoCampo", "Deu Certo?");
		// aqui embaixo, caso o código já esteja pronto ele vai finalizar o tempo de
		// execução
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

	}

	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		page.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		// nesse caso vai esperar somente o campo que eu to esperando
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		page.escrever("novoCampo", "Deu Certo?");

	}

}
