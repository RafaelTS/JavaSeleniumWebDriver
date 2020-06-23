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

public class FirstSteps extends BaseTeste{

	private FirstStepsPage page;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new FirstStepsPage();

	}

	@After
	public void finaliza() {
		killDriver();
	}

	@Test
	public void mustInteractWithTextField() {
		page.setNome1("Teste de Escrita");
		
		//Assert.assertEquals("Teste de Escrita")page.obterNome());

	}
	
	@Test
	public void testTextFieldDuplo(){
		
		page.escrever("elementosForm:nome", "Rafael");
		Assert.assertEquals("Rafael", page.obterValorCampo("elementosForm:nome"));
		page.escrever("elementosForm:nome", "Raquel");
		Assert.assertEquals("Raquel", page.obterValorCampo("elementosForm:nome"));
		
	}

	@Test
	public void mustInteractWithTextArea() {
		page.escrever("elementosForm:sugestoes", "Teste \n quer dizer nova linha");
		Assert.assertEquals("Teste \n quer dizer nova linha", page.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void mustInteractWithRadioButton() {
		page.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(page.isRadioMarcado("elementosForm:sexo:0"));

	}

	@Test
	public void mustInteractWithChekbox() {
		page.clicarCheck("elementosForm:comidaFavorita:2");
		Assert.assertTrue(page.isCheckMarcado("elementosForm:comidaFavorita:2"));

	}

	@Test
	public void mustInteractWithCombo() {

		page.selecionarCombo("elementosForm:escolaridade", "Superior");
		Assert.assertEquals("superior", page.obterValorCampo("elementosForm:escolaridade"));
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
