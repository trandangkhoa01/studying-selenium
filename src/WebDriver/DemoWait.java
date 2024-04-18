package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	String OS = System.getProperty("os.name");
	
	@BeforeClass
	public void beforeClass() {
		if(OS.contains("Mac OS")){
			System.setProperty("webdriver.edge.driver", projectPath + "/webDriver/msedgedriver");
		}else {
			System.setProperty("webdriver.edge.driver", projectPath + "\\webDriver\\msedgedriver.exe");
		}
		driver = new EdgeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	//@Test
	public void TC_01_Wait_Element_Visibility() {
		driver.get("https://www.facebook.com/");
		//Chờ cho element hiển thị trên UI --> visibilityOfElementLocated()
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		driver.findElement(By.cssSelector("input#email")).sendKeys("ABC");
		sleepInSecon(1);
	}
	
	//@Test
	public void TC_02_Elment_Not_Displayed() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text() ='Create new account']")).click();
		//Chờ cho Element không hiển thị --> invisibilityOfElementLocated
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name ='reg_email_confirmation__']")));
		driver.findElement(By.xpath("//input[@name ='reg_email__']")).sendKeys("phovinh13@gmail.com");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name ='reg_email_confirmation__']")));
		driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']")).sendKeys("AAAAAA");
		sleepInSecon(1);
	}
	
	//@Test
	public void TC_02_Wait_Element_Presence() {
		driver.get("https://www.facebook.com/");
		//Chờ Element tồn tại trong DOM --> precenceOfElementLocated
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() ='Create new account']")));
		driver.findElement(By.xpath("//a[text() ='Create new account']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name ='reg_email_confirmation__']")));
	}
	
	//@Test
	public void TC_04_Element_Staleness() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[text() ='Create new account']")).click();
		WebElement confimPassword = driver.findElement(By.xpath("//input[@name ='reg_email_confirmation__']"));
		driver.findElement(By.xpath("//div[text() = 'Sign Up']/parent::div/preceding-sibling::img")).click();
		explicitWait.until(ExpectedConditions.stalenessOf(confimPassword));
		driver.findElement(By.cssSelector("input#email")).sendKeys("ABC");
		
	}
	
	@Test
	public void Demo_implicitwait() {
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@data-testid = 'open-registration-form-button']")).click();
		driver.findElement(By.cssSelector("input[name = 'firstname']")).sendKeys("AAAA");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();	
	}
	public void sleepInSecon(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
