package br.sc.java.page;
import org.openqa.selenium.By;

import br.sc.java.core.BasePage;

public class RegistrationChallengePage extends BasePage  {
	
	public void setNome(String nome){
		escrever("elementosForm:nome", nome);
		
	}
	
	public void setSobrenome(String sobrenome){
		escrever("elementosForm:sobrenome", sobrenome);
		
	}
	public void setSexoMasculino(){
		clicarRadio("elementosForm:sexo:0");
	}
	
	public void setSexoFeminino(){
		clicarRadio("elementosForm:sexo:1");
	}
	
	public void setComidaCarne(){
		clicarRadio("elementosForm:comidaFavorita:0");
	}
	
	public void setComidaPizza(){
		clicarRadio("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaVegetariano(){
		clicarRadio("elementosForm:comidaFavorita:3");
	}
	
	public void setEscolaridade(String valor){
		selecionarCombo("elementosForm:escolaridade", valor);
	}
	
	public void setEsporte(String... valores){
		for(String valor: valores)
			selecionarCombo("elementosForm:esportes", valor);
	}
	
	public void cadastrar(){
		clicarBotao("elementosForm:cadastrar");
	}
	
	public String obterResultadoCadastro() {
		return obterTexto(By.xpath("//*[@id='resultado']/span"));
	}

	public String obterNomeCadastro() {
		return obterTexto(By.xpath("//*[@id='descNome']/span"));
	}

	public String obterSobrenomeCadastro() {
		return obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
	}

	public String obterSexoCadastro() {
		return obterTexto(By.xpath("//*[@id='descSexo']/span"));
	}

	public String obterComidaCadastro() {
		return obterTexto(By.xpath("//*[@id='descComida']/span"));
	}

	public String obterEscolaridadeCadastro() {
		return obterTexto(By.xpath("//*[@id='descEscolaridade']/span"));
	}

	public String obterEsportesCadastro() {
		return obterTexto(By.xpath("//*[@id='descEsportes']/span"));
	}	
	
}