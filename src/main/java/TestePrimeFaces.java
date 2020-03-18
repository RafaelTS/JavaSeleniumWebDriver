import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestePrimeFaces {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl = new DSL(driver);

	}

	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime(){
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		//dsl.clicarRadio(By.xpath("//*[@id='j_idt721:console']/tbody/tr/td[1]/div"));//com xpath do chrome
		dsl.clicarRadio(By.xpath("//input[@id='j_idt721:console:0']/../..//span"));//com xpath do curso
		Assert.assertTrue(dsl.isRadioMarcado("j_idt721:console:0"));
		dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));//com xpath do curso
		Assert.assertTrue(dsl.isRadioMarcado("j_idt721:console:1"));
	
	}
	@Test
	public void deveInteragirComSelectPrime(){
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.selecionarComboPrime("j_idt721:console", "Xbox One");
		Assert.assertEquals("PS4", dsl.obterTexto("j_idt721:console_items"));
	}
}
