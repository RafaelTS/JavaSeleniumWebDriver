package br.sc.java.page;

import org.openqa.selenium.By;

import br.sc.java.core.BasePage;

public class FirstStepsPage extends BasePage {
	
	public void setNome(String nome){
		escrever("elementosForm:nome", nome);
		
	}
	
	public String obterNome() {
		return obterValorCampo("elementosForm:nome");
	}
	
	public void setSugestao(String sugestao){
		escrever("elementosForm:sugestoes", sugestao);
	}
	
	public String obterSugestoes() {
		return obterValorCampo("elementosForm:sugestoes");
	}
	public boolean isSexoMasculinoMarcado() {
		return isRadioMarcado("elementosForm:sexo:0");
	}
	
	public boolean isCheckCarneMarcado() {
		return isCheckMarcado("elementosForm:comidaFavorita:0");
	}
	
	public void clicarBotaoVoltar() {
		clicarLink("Voltar");
	}
	
	public String obterTextoBotaoVoltar() {
		return obterTexto(By.id("resultado"));
	}
	
	public String obterNivelEnsino() {
		return obterValorCombo("elementosForm:escolaridade");
	}
	public void clicarBotaoCliqueMe() {
		clicarBotao("ButtonSimple");
	}
	
	public String obterMensagemCliqueMe() {
		return obterValueElemento("ButtonSimple");
		//return selecionarNomeBotao("ButtonSimple");
		
		
		
		
//		WebElement botao = getDriver().findElement(By.id("ButtonSimple"));
//		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
//		
		
//		public void selecionarCombo(String id, String valor) {
//			WebElement element = getDriver().findElement(By.id(id));
//			Select combo = new Select(element);
//			combo.selectByVisibleText(valor);
//		
	}
}
