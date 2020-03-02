import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestAlert {
	
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
	public void mustInteractWithSimpleAlert() {
		
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void mustInteractWithConfirmAlert() {
		
		driver.findElement(By.id("confirm")).click();
		Alert confirme = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", confirme.getText());
		confirme.accept();
		Assert.assertEquals("Confirmado", confirme.getText());
		confirme.accept();
	
		driver.findElement(By.id("confirm")).click();
		confirme = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", confirme.getText());
		confirme.dismiss();
		Assert.assertEquals("Negado", confirme.getText());
		confirme.dismiss();
		
	}
	
	@Test
	public void mustInteractWithPromptAlert() {
		
		driver.findElement(By.id("prompt")).click();
		Alert prompt = driver.switchTo().alert();
		Assert.assertEquals("prompt", prompt.getText());
		prompt.sendKeys("12");
		prompt.accept();
		Assert.assertEquals("Era 12?", prompt.getText());
		prompt.accept();
		Assert.assertEquals(":D", prompt.getText());
		prompt.accept();
		
	}
}
