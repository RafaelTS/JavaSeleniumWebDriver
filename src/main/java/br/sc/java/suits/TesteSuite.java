package br.sc.java.suits;

import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.sc.java.test.RegistrationChallenge;
import br.sc.java.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({ RegistrationChallenge.class, 
				TesteRegrasCadastro.class, 
				})
public class TesteSuite {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}

}
