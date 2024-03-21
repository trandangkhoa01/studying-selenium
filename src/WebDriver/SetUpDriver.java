package WebDriver;

import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class SetUpDriver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void BeforeClass() {
	//	System.setProperty("webdriver.chrome.driver",projectPath + "\\webDriver\\chromedriver.exe");)
		driver =  new ChromeDriver();
	}
	@Test
	public void TC_01() {
		driver.get("https://dev.ecomnet.app/");
	}
}
