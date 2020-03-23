package br.sc.java.test;
import static br.sc.java.core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.sc.java.core.BaseTeste;
import br.sc.java.core.DSL;
import br.sc.java.page.RegistrationChallengePage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTeste{

	private DSL dsl;
	private RegistrationChallengePage page;

	@Parameter
	public String nome;
	@Parameter(value = 1)
	public String sobrenome;
	@Parameter(value = 2)
	public String sexo;
	@Parameter(value = 3)
	public List<String> comidas;
	@Parameter(value = 4)
	public String[] esportes;
	@Parameter(value = 5)
	public String msg;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new RegistrationChallengePage();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] { 
			{ "", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio" },
			{ "Rafael", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio" },
			{ "Rafael", "Teixeira", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio" },
			{ "Rafael", "Teixeira", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{ "Rafael", "Teixeira", "Masculino", Arrays.asList("Carne"), new String[]{"Karate","O que eh esporte?"}, "Voce faz esporte ou nao?" }
		});
	}

	@Test
	public void deveValidarRegras() {

		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} 
		if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if (comidas.contains("Carne"))
			page.setComidaCarne();
		if (comidas.contains("Pizza"))
			page.setComidaPizza();
		if (comidas.contains("Vegetariano"))
			page.setComidaVegetariano();
		page.setEsporte(esportes);
		page.cadastrar();
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());

	}

}
