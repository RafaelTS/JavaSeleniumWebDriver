import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class RegistrationChallenge {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void makeSimpleRegister() {

		/*
		 * In this register, I'll past for almost all the fields in the componente file
		 * I'll put a name, last name, in a send key insertion, choise a sport, favorite
		 * food, degree level and others
		 */
		dsl.escreve("elementosForm:nome", "Rafael");
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Rafael"));
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		Assert.assertEquals("Sobrenome: Teixeira", dsl.obterTexto("descSobrenome"));
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));

		dsl.clicarCheck(("elementosForm:comidaFavorita:2"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));

		dsl.selecionarCombo("elementosForm:escolaridade", "superior");
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));

		dsl.selecionarCombo("elementosForm:esportes", "natacao");
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

		dsl.clicarBotao("elementosForm:cadastrar");
		
	}

	@Test
	public void makeSimpleRegisterByTeacher() {
		/*
		 * In this register, There're the teacher correction about the up correction
		 */

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "superior");
		dsl.selecionarCombo("elementosForm:esportes", "natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Rafael"));
		Assert.assertEquals("Sobrenome: Teixeira", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

	}

}
