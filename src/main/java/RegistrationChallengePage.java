import org.openqa.selenium.WebDriver;

public class RegistrationChallengePage {
	
	private DSL dsl;
	
	public RegistrationChallengePage(WebDriver driver){
		dsl = new DSL(driver);
	}
	
	public void setNome(String nome){
		dsl.escreve("elementosForm:nome", nome);
		
	}
	
	public void setSobrenome(String sobrenome){
		dsl.escreve("elementosForm:sobrenome", sobrenome);
		
	}
	public void setSexoMasculino(){
		dsl.clicarRadio("elementosForm:sexo:0");
	}
	public void setComidaPizza(){
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setEscolaridade(String valor){
		dsl.selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String valor){
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultado(){
		return dsl.obterTexto("resultado");
	}
	
	public String obterNome(){
		return dsl.obterTexto("descNome");
	}

}
