import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;	


public class FirstSteps {
	
	@Test
	public void Test() {
		WebDriver driver = new FirefoxDriver();
		driver.get("www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
	}

}
