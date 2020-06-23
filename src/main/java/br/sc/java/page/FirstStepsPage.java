package br.sc.java.page;

import org.openqa.selenium.By;

import br.sc.java.core.BasePage;

public class FirstStepsPage extends BasePage {
	
	public void setNome1(String nome) {
		escrever("elementosForm:nome", nome);
		
	}
	public String obterNome() {
		return obterTexto(By.id("nome"));
				
	}

}
