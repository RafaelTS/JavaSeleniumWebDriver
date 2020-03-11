import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestesFramesEJanelas {

	private WebDriver driver;
	private DSL dsl;

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
	public void mustInteractWithFrames() {

		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String mensagem = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", mensagem);
		dsl.sairFrame();
		dsl.escreve("elementosForm:nome", mensagem);

	}

	@Test
	public void mustInteractWithWindows() {

		dsl.clicarBotao("ButtonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreve(By. tagName("textArea"), "Deu Certo?");
		driver.close();
		dsl.trocarJanela("");
		dsl.escreve("textArea", "Certamente");

	}

	@Test
	public void mustInteractWithWindowsWithoutTitle() {

		dsl.clicarBotao("ButtonPopUpHard");
		System.out.print(driver.getWindowHandle());
		System.out.print(driver.getWindowHandles());
		dsl.trocarJanela((String)driver.getWindowHandles().toArray()[1]);
		dsl.escreve("textArea", "Deu Certo?");
		dsl.trocarJanela((String)driver.getWindowHandles().toArray()[0]);
		dsl.escreve("textArea", "e agora?");

	}

}