import static br.sc.java.core.DriverFactory.getDriver;
import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestesFramesEJanelas {

	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();

	}

	@After
	public void finaliza() {
		killDriver();
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
	public void mustInteractWithHiddenFrames() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJs("window.scrollBy(0,arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame1");
		dsl.clicarBotao("frameButton");
		String mensagem = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", mensagem);
		
	}


	@Test
	public void mustInteractWithWindows() {

		dsl.clicarBotao("ButtonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreve(By. tagName("textArea"), "Deu Certo?");
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escreve("textArea", "Certamente");

	}

	@Test
	public void mustInteractWithWindowsWithoutTitle() {

		dsl.clicarBotao("ButtonPopUpHard");
		System.out.print(getDriver().getWindowHandle());
		System.out.print(getDriver().getWindowHandles());
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[1]);
		dsl.escreve("textArea", "Deu Certo?");
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[0]);
		dsl.escreve("textArea", "e agora?");

	}

}