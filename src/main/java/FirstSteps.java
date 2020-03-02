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
	public void OutroTeste() {
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
			
	}
	
	@Test
	public void mustInteractWithTextArea(){
		
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste\nquer dizer nova linha");
		Assert.assertEquals("Teste\nquer dizer nova linha", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
	}
	
	@Test
	public void mustInteractWithRadioButton() {
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
			
	}
	
	@Test
	public void mustInteractWithChekbox() {
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
	}
	
	@Test
	public void mustInteractWithCombo() {
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		//this line is for example
		//combo.selectByIndex(3);
		combo.selectByValue("superior");
		//combo.selectByVisibleText("Superior");
		//this line is for example
		//combo.deselectByVisibleText("mestrado");
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
		
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
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Futebol");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Futebol");
		
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
	
	}
	
	@Test
	public void mustInteractWithButtons() {
		
		WebElement botao =  driver.findElement(By.id("ButtonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}	
	@Test
	public void mustInteractWithLinks() {
		
		driver.findElement(By.linkText("Voltar")).click();
		Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());
	}
	
	@Test
	public void mustSearchTextInPage() {
		
		// in this case, you can use it, but doesn't perform well because the system will be search all the html
		//Assert.assertTrue(driver.findElement(By.tagName("Body")).getText().contains("Campo de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
			
	}
					
}
