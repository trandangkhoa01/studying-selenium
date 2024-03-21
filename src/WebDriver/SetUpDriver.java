package WebDriver;

import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SetUpDriver {
	WebDriver driver;
	String projectPath = System.getProperty("userdir");
	
	@BeforeClass
	public void BeforeClass() {
		driver =  new ChromeDriver();
	}
	@Test
	public void TC_01() {
		driver.get("");
	}
}
