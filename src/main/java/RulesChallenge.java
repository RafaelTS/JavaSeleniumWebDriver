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
	
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void mustValidateMandatoryName() {
		
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateMandatoryLastName() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSex() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//continuar daqui e validar porque não tem que trocar a janela.... deve ter na aula de dsl no cmoeço

	}

	@Test
	public void mustValidateVegetarian() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSports() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:0");
		dsl.selecionarCombo("elementosForm:esportes", "natacao");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

	}
}