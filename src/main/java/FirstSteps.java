import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstSteps {

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
	public void mustInteractWithTextField() {
		dsl.escreve("elementosForm:nome", "Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", dsl.obterValorCampo("elementosForm:nome"));

	}
	
	@Test
	public void testTextFieldDuplo(){
		
		dsl.escreve("elementosForm:nome", "Rafael");
		Assert.assertEquals("Rafael", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Raquel");
		Assert.assertEquals("Raquel", dsl.obterValorCampo("elementosForm:nome"));
		
	}

	@Test
	public void mustInteractWithTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste \n quer dizer nova linha");
		Assert.assertEquals("Teste\nquer dizer nova linha", dsl.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void mustInteractWithRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void mustInteractWithChekbox() {
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));

	}

	@Test
	public void mustInteractWithCombo() {

		dsl.selecionarCombo("elementosForm:escolaridade", "superior");
		Assert.assertEquals("Superior", dsl.obterValorCampo("elementosForm:escolaridade"));
		// this line is for example
		// combo.selectByIndex(3);
		// combo.selectByVisibleText("Superior");
		// this line is for example
		// combo.deselectByVisibleText("mestrado");
	
	}

	@Test
	public void mustVerfyValuesCombo() {
		
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));

	}

	@Test
	public void mustInteractWithMultiplusCombos() {

		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

	}

	@Test
	public void mustInteractWithButtons() {

		dsl.clicarBotao("ButtonSimple");

		WebElement botao = driver.findElement(By.id("ButtonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void mustInteractWithLinks() {

		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void mustSearchTextInPage() {

		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));

	}
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
	
}
