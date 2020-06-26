package br.sc.java.page;

import br.sc.java.core.BasePage;

public class FirstStepsPage extends BasePage {
	
	public String obterNome() {
		return obterValorCampo("elementosForm:nome");
	}
	
	public void setSugestao(String sugestao){
		escrever("elementosForm:sugestoes", sugestao);
	}
	
	public String obterSugestoes() {
		return obterValorCampo("elementosForm:sugestoes");
	}
	public boolean sexoMasculinoMarcado() {
		return isRadioMarcado("elementosForm:sexo:0");
	}
}
