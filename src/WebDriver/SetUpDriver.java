package WebDriver;

//import java.util.ArrayList;
import java.util.*;
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
	//Actions act;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.edge.driver", projectPath +"\\webDriver\\msedgedriver.exe");
		 driver = new EdgeDriver();
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.get("https://dev.ecomnet.app/");
		 driver.manage().window().maximize();
		 //act = new Actions(driver);
		 
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
		 driver.findElement(By.cssSelector(".cursor-pointer")).click();
		 driver.findElement(By.xpath("//a[text() = 'Đăng xuất']")).click();
	}
	@Test
	public void TC_02_Search() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name = 'email']")).sendKeys("bkaadmin");;
		driver.findElement(By.xpath("//input[@name = 'password']")).sendKeys("qazwsx");;
		driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']")).click();
		driver.findElement(By.xpath("//span[text()='Thư viện số']")).click();
		driver.findElement(By.xpath("//span[text()='Danh sách nội dung số']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text() = ' Filter ']")).click();
		driver.findElement(By.xpath("//label[text()= 'Bán nội dung']/parent::div//input[@placeholder = 'Select']")).click();
		List<WebElement> typeList = new ArrayList<>();
		typeList = driver.findElements(By.cssSelector("#el-id-8321-806 li span"));
		
		for(WebElement tmp : typeList) {
			if(tmp.getText().equals("Có bán nội dung")) {
				tmp.click();
				System.out.print("hi");
			}
		}
		driver.findElement(By.xpath("//span[text() = ' Lưu ']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//h2[text()= 'Filter danh sách']")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
