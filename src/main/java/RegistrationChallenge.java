import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;

public class RegistrationChallenge {
	
	@Test
	public void makeSimpleRegister() {
		
		/*In this register, I'll past for almost all the fields in the componente file
		 * I'll put a name, last name, in a send key insertion, choise a sport, favorite food, degree level and others
		 * */
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		Assert.assertEquals("Rafael", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teixeira");
		Assert.assertEquals("Teixeira", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		combo.selectByValue("superior");
		Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());
		
		WebElement elementSport = driver.findElement(By.id("elementosForm:esportes"));
		Select comboSport = new Select(elementSport);
		comboSport.selectByVisibleText("O que eh esporte?");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		
		
		driver.quit();
		
	
	}
}
