
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.Select;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;	


public class FirstSteps {
	
	
	@Test
	public void OutroTeste() {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de Escrita");
		Assert.assertEquals("Teste de Escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();
	}
	
	@Test
	public void mustInteractWithTextArea(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste\nquer dizer nova linha");
		Assert.assertEquals("Teste\nquer dizer nova linha", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
		
		driver.quit();
		
	}
	
	@Test
	public void mustInteractWithRadioButton() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.quit();
				
	}
	
	@Test
	public void mustInteractWithChekbox() {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
		
	}
	
	@Test
	public void mustInteractWithCombo() {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosFom:escolaridade"));
		Select combo = new Select(element);
		//this line is for example
		//combo.selectByIndex(3);
		combo.selectByValue("superior");
		//this line is for example
		//combo.deselectByVisibleText("mestrado");
		Assert.assertEquals("superior", combo.getFirstSelectedOption().getText());
		driver.quit();
	}
	
	@Test
	public void mustVerfyValuesCombo() {
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosFom:escolaridade"));
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
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By.id("elementosFom:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");
		
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());
		driver.quit();
		
		
	}
	
	
	
}
