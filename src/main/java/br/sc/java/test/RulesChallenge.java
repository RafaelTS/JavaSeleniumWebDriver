package br.sc.java.test;

import static br.sc.java.core.DriverFactory.getDriver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import br.sc.java.core.BaseTeste;
import br.sc.java.page.RegistrationChallengePage;

public class RulesChallenge extends BaseTeste {

	/*
	 * Para executar essa prova as regras são: Validar o alerta no campo nome
	 * Validar o alerta no campo sobrenome Sexo é obrigatório N pode marcar Carne e
	 * vegetariano N pode marcar algum esporte e o que é esporte
	 */

	private RegistrationChallengePage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new RegistrationChallengePage();

	}

	@Test
	public void mustValidateMandatoryName() {

		page.cadastrar();
		Assert.assertEquals("msg", page.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateMandatoryLastName() {

		page.setNome("Rafael");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", page.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSex() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", page.alertaObterTextoEAceita());

		// continuar daqui e validar porque não tem que trocar a janela.... deve ter na
		// aula de page no cmoeço

	}

	@Test
	public void mustValidateVegetarian() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", page.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSports() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", page.alertaObterTextoEAceita());

	}
}