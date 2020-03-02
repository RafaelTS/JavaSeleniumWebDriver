import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestesFramesEJanelas {
	
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
	public void mustInteractWithFrames(){
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alerta = driver.switchTo().alert();
		String mensagem = alerta.getText();
		Assert.assertEquals("Frame OK!", mensagem);
		alerta.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(mensagem);
		
	}
	
	@Test
	public void mustInteractWithWindows(){
		
		driver.findElement(By.id("ButtonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textArea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textArea")).sendKeys("Certamente!");
		
	}
	
	@Test
	public void mustInteractWithWindowsWithoutTitle(){
		
		driver.findElement(By.id("ButtonPopUpHard")).click();
		//System.out.println(driver.getWindowHandle());
		//System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textArea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textArea")).sendKeys("E agora??");
		
	}	

}
