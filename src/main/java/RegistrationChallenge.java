import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegistrationChallenge {

	private RegistrationChallengePage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new RegistrationChallengePage();
	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void makeSimpleRegister() {

		/*
		 * In this register, I'll past for almost all the fields in the componente file
		 * I'll put a name, last name, in a send key insertion, choise a sport, favorite
		 * food, degree level and others
		 */
		page.setNome("Raquel");
		page.setSobrenome("Custodio");
		page.setSexoFeminino();
		page.setComidaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Natacao");
		page.cadastrar();
		
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Raquel", page.obterNomeCadastro());
		Assert.assertEquals("Custodio", page.obterSobrenomeCadastro());
		Assert.assertEquals("Feminino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());

	}

}