package br.sc.java.core;

import static br.sc.java.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTeste {

	@After
	public void finaliza() {
		killDriver();
	}

}
