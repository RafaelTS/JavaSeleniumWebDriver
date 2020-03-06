import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;	


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
	public void mustInteractWithTextArea(){
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
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
	}
	
	@Test
	public void mustInteractWithCombo() {
		
		dsl.selecionarCombo("elementosForm:escolaridade", "superior");
		//this line is for example
		//combo.selectByIndex(3);
		//combo.selectByVisibleText("Superior");
		//this line is for example
		//combo.deselectByVisibleText("mestrado");
		Assert.assertEquals("Superior", dsl.obterValorCampo("elementosForm:escolaridade"));
		
	}
	
	@Test
	public void mustVerfyValuesCombo() {
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option: options){
			
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		
		}
		Assert.assertTrue(encontrou);
		
	}
	@Test
	public void mustInteractWithMultiplusCombos() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Futebol");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Futebol");
		
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	
	}
	
	@Test
	public void mustInteractWithButtons() {
		
		dsl.clicarBotao("ButtonSimple");
		
		WebElement botao =  driver.findElement(By.id("ButtonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}	
	@Test
	public void mustInteractWithLinks() {
		
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void mustSearchTextInPage() {
		
		// in this case, you can use it, but doesn't perform well because the system will be search all the html
		//Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
			
	}
					
}
