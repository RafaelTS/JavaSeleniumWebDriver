import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RulesChallenge {

	/*
	 * Para executar essa prova as regras são: Validar o alerta no campo nome
	 * Validar o alerta no campo sobrenome Sexo é obrigatório N pode marcar
	 * Carne e vegetariano N pode marcar algum esporte e o que é esporte
	 */

	private WebDriver driver;
	private DSL dsl;
	private RegistrationChallengePage page;
	
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new RegistrationChallengePage(driver);

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void mustValidateMandatoryName() {
		
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateMandatoryLastName() {
		
		page.setNome("Rafael");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSex() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//continuar daqui e validar porque não tem que trocar a janela.... deve ter na aula de dsl no cmoeço

	}

	@Test
	public void mustValidateVegetarian() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setComidaVegetariano();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSports() {

		page.setNome("Rafael");
		page.setSobrenome("Teixeira");
		page.setSexoMasculino();
		page.setComidaCarne();
		page.setEsporte("Natacao", "O que eh esporte?");
		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

	}
}