import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestesFramesEJanelas {
	
	@Test
	public void mustInteractWithFrames(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alerta = driver.switchTo().alert();
		String mensagem = alerta.getText();
		Assert.assertEquals("Frame OK!", mensagem);
		alerta.accept();
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(mensagem);
		
		driver.quit();
		
	}
	
	@Test
	public void mustInteractWithWindows(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("ButtonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textArea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textArea")).sendKeys("Certamente!");
		
		driver.quit();
		
	}
	
	@Test
	public void mustInteractWithWindowsWithoutTitle(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("ButtonPopUpHard")).click();
		//System.out.println(driver.getWindowHandle());
		//System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textArea")).sendKeys("Deu certo?");
		driver.close();
		
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textArea")).sendKeys("E agora??");
		
		driver.quit();
		
		
		
		
	}	

}
