package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.sc.java.core.BaseTeste;
import br.sc.java.page.RegistrationChallengePage;

public class TestAlert extends BaseTeste {
	
	private RegistrationChallengePage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new RegistrationChallengePage();
	}
	
	@Test
	public void mustInteractWithSimpleAlert() {
		
		page.clicarBotao("alert");
		String texto = page.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		
		page.escrever("elementosForm:nome", texto);
	}
	
	@Test
	public void mustInteractWithConfirmAlert() {
		
		page.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", page.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", page.alertaObterTextoEAceita());
		
		page.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", page.alertaObterTextoENega());
		Assert.assertEquals("Negado", page.alertaObterTextoENega());
				
	}
	
	@Test
	public void mustInteractWithPromptAlert() {
		
		page.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", page.alertaObterTexto());
		page.alertaEscrever("12");
		Assert.assertEquals("Era 12?", page.alertaObterTextoEAceita());
	}
}
