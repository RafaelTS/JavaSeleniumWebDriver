import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class RegistrationChallenge {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void makeSimpleRegister() {

		/*
		 * In this register, I'll past for almost all the fields in the componente file
		 * I'll put a name, last name, in a send key insertion, choise a sport, favorite
		 * food, degree level and others
		 */

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

	}

	@Test
	public void makeSimpleRegisterByTeacher() {
		/*
		 * In this register, There're the teacher correction about the up correction
		 */

		dsl.escreve("elementosForm:nome", "Rafael");
		dsl.escreve("elementosForm:sobrenome", "Teixeira");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "superior");
		dsl.selecionarCombo("elementosForm:esportes", "natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Rafael"));
		Assert.assertEquals("Sobrenome: Teixeira", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

	}

}
