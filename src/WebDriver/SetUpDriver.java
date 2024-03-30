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
		 driver.manage().window().maximize();
		 //act = new Actions(driver);
		 
	}
	@Test
	public void TC_01_VerifyUsernameIsTrue() throws InterruptedException {
		 driver.get("https://dev.ecomnet.app/");
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
		driver.get("https://dev.ecomnet.app/");
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
	
	@Test
	public void TC_03_HRMTest() throws InterruptedException {
		Random rad = new Random();
		String number = String.valueOf(rad.nextInt(9999));
		String username = "Khoatran"+number;
		String password = "Khoa0195@";
		String userID;
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(string(),' Login ')]")).click();
		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		driver.findElement(By.xpath("//button[text()=' Add ']")).click();
		driver.findElement(By.name("firstName")).sendKeys("Tran");
		driver.findElement(By.name("middleName")).sendKeys("Dang");
		driver.findElement(By.name("lastName")).sendKeys("aohKT");
		userID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		driver.findElement(By.xpath("//label[contains(string(),'Username')]/parent::div/parent::div//input")).sendKeys(username);
		driver.findElement(By.xpath("//label[contains(string(),'Password')]/parent::div/parent::div//input")).sendKeys(password);
		driver.findElement(By.xpath("//label[contains(string(),'Confirm Password')]/parent::div/parent::div//input")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		Thread.sleep(15000);
		driver.findElement(By.cssSelector(".oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//ul[@role = 'menu']//a[text() = 'Logout']")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(string(),' Login ')]")).click();
		driver.findElement(By.xpath("//span[text() = 'My Info']")).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/parent::div//input")).getAttribute("value"),userID);
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name = 'firstName']")).getAttribute("value"), "Tran");
		Assert.assertEquals(driver.findElement(By.cssSelector("input.orangehrm-middlename")).getAttribute("value"), "Dang");
		Assert.assertEquals(driver.findElement(By.cssSelector("input.orangehrm-lastname")).getAttribute("value"),"aohKT");
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
