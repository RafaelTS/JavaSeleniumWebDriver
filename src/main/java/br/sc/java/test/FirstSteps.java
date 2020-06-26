package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.sc.java.core.BaseTeste;
import br.sc.java.page.FirstStepsPage;
import br.sc.java.page.RegistrationChallengePage;

public class FirstSteps extends BaseTeste{

	private FirstStepsPage pageStep;
	private RegistrationChallengePage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		pageStep = new FirstStepsPage();
		page = new RegistrationChallengePage();

	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void mustInteractWithTextField() {
		page.setNome("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", pageStep.obterNome());

	}
	
	@Test
	public void testTextFieldDuplo() {
		
		page.setNome("Rafael"); 
		Assert.assertEquals("Rafael", pageStep.obterNome()); 
		page.setNome("Raquel");
		Assert.assertEquals("Raquel", pageStep.obterNome());
	}

	@Test
	public void mustInteractWithTextArea() {
		pageStep.setSugestao("Teste \n quer dizer nova linha");
		Assert.assertEquals("Teste \n quer dizer nova linha", pageStep.obterSugestoes());

	}
	//Thread.sleep(3000); // not good
	@Test
	public void mustInteractWithRadioButton() {
		//corrigir abaixo
		page.setSexoMasculino();
		Assert.assertTrue(pageStep.sexoMasculinoMarcado());
		//Assert.assertTrue(page.isRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void mustInteractWithChekbox() {
		page.setComidaCarne();
		Assert.assertEquals("Carne", page.obterComidaCadastro());

	}

	@Test
	public void mustInteractWithCombo() {

		page.setEscolaridade("Superior");
		Assert.assertEquals("Superior", page.obterEscolaridadeCadastro());
		// this line is for example
		// combo.selectByIndex(3);
		// combo.selectByVisibleText("Superior");
		// this line is for example
		// combo.deselectByVisibleText("mestrado");
	
	}

	@Test
	public void mustVerfyValuesCombo() {
		
		Assert.assertEquals(8, page.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(page.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));

	}

	@Test
	public void mustInteractWithMultiplusCombos() {

		page.selecionarCombo("elementosForm:esportes", "Futebol");
		page.selecionarCombo("elementosForm:esportes", "Corrida");
		page.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		List<String> opcoesMarcadas = page.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());

		page.deselecionarCombo("elementosForm:esportes", "Corrida");
		opcoesMarcadas = page.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));

	}

	@Test
	public void mustInteractWithButtons() {

		page.clicarBotao("ButtonSimple");

		WebElement botao = getDriver().findElement(By.id("ButtonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));

	}

	@Test
	public void mustInteractWithLinks() {

		page.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", page.obterTexto("resultado"));
	}

	@Test
	public void mustSearchTextInPage() {

		Assert.assertEquals("Campo de Treinamento", page.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", page.obterTexto(By.className("facilAchar")));

	}
	@Test
	public void testJavascript(){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		page.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
	
}
