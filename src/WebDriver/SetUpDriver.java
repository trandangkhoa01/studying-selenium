package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class SetUpDriver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions act;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.edge.driver", projectPath +"\\webDriver\\msedgedriver.exe");
		 driver = new EdgeDriver();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.get("https://dev.ecomnet.app/");
		 driver.manage().window().maximize();
		 act = new Actions(driver);
		 
	}
	@Test
	public void TC_01_VerifyUsernameIsTrue() throws InterruptedException {
		 WebElement txtUsername = driver.findElement(By.xpath("//input[@name = 'email']"));
		 WebElement txtPassword = driver.findElement(By.xpath("//input[@name = 'password']"));
		 WebElement btnLogin = driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']"));
		 txtUsername.sendKeys("bkaadmin");
		 txtPassword.sendKeys("qazwsx");
		 btnLogin.click();
		 driver.findElement(By.cssSelector(".cursor-pointer")).click();
		 Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'DEV Official')]")).isDisplayed());
		 Assert.assertEquals(driver.findElement(By.xpath("//div[text() ='DEV Official ']/following-sibling::a")).getText(),"bkaadmin");
		 driver.findElement(By.xpath("//span[text()='Danh sách nhóm']")).click();
		 WebElement searchbox = driver.findElement(By.xpath("//input[contains(@placeholder,'Enter để tìm kiếm...')]"));
		 searchbox.sendKeys("Khoa");
		 Thread.sleep(3000);
		 //searchbox.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		 searchbox.sendKeys(Keys.ENTER);
		 Thread.sleep(3000);
}
	
//	@Test
//	public void TC_02_Search() {
//		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("bkaadmin");;
//		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("qazwsx");;
//		driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']")).click();

//	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
