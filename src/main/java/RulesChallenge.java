import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class RulesChallenge {
	
	/*Para executar essa prova  as regras são:
	 *Validar o alerta no campo nome
	 *Validar o alerta no campo sobrenome
	 *Sexo é obrigatório 
	 *N pode marcar Carne e vegetariano
	 *N pode marcar algum esporte e o que é esporte
	 * */
	
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
	public void mustValidateMandatoryName() {
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alerta.getText());
		
	}
	
	@Test
	public void mustValidateMandatoryLastName() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alerta.getText());
		
	}
	
	@Test
	public void mustValidateSex() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teixeira");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alerta.getText());
		
	}
	
	@Test
	public void mustValidateVegetarian() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teixeira");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alerta.getText());
		
	}
	
	
	@Test
	public void mustValidateSports() {
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Rafael");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teixeira");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alerta.getText());
		
	}
}