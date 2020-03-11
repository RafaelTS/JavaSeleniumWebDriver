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

	/*
	 * Para executar essa prova as regras são: Validar o alerta no campo nome
	 * Validar o alerta no campo sobrenome Sexo é obrigatório N pode marcar
	 * Carne e vegetariano N pode marcar algum esporte e o que é esporte
	 */

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
	public void mustValidateMandatoryName() {
		
		dsl.clicarBotao("elementosForm:cadastrar");
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateMandatoryLastName() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

	}

	@Test
	public void mustValidateSex() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
		
		//continuar daqui e validar porque não tem que trocar a janela.... deve ter na aula de dsl no cmoeço

	}

	@Test
	public void mustValidateVegetarian() {

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		
		dsl.clicarBotao("elementosForm:Cadastrar");
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

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