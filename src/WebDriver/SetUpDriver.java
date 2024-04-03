package WebDriver;

//import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class SetUpDriver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.edge.driver", projectPath +"\\webDriver\\msedgedriver.exe");
		 driver = new EdgeDriver();
		 jsExcutor = (JavascriptExecutor) driver;
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 //act = new Actions(driver);
		 
	}
	//@Test
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
	//@Test
	public void TC_02_DefaultDropdown() throws InterruptedException {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.xpath("//a[text() = 'Register']")).click();
		Thread.sleep(2000);
		WebElement dropdownDay = driver.findElement(By.xpath("//select[@name = 'DateOfBirthDay']"));
		Select selectDay = new Select(dropdownDay);
		selectDay.selectByValue("27");
		Thread.sleep(1000);
		Assert.assertEquals(selectDay.getFirstSelectedOption().getText(), "27");
		WebElement dropdownMonth = driver.findElement(By.xpath("//select[@name = 'DateOfBirthMonth']"));
		Select selectMonth = new Select(dropdownMonth);
		selectMonth.selectByVisibleText("February");
		Assert.assertEquals(selectMonth.getFirstSelectedOption().getText(), "February");
	}
	
	//@Test
	public void TC_03_CustomDropdown() throws InterruptedException {
		driver.get("https://cmb-merchant-test2.intelnet.vn/auth/login");
		driver.findElement(By.cssSelector("input#email")).sendKeys("test2.cmb@intelnet.vn");
		driver.findElement(By.cssSelector("input.InputPassword")).sendKeys("Aa@123456");
		driver.findElement(By.xpath("//span[text() = 'Đăng nhập']")).click();
		Thread.sleep(10000);
		WebElement dropdown = driver.findElement(By.cssSelector("div.css-2b097c-container"));
		dropdown.click();
		List<WebElement> dropdownValue = driver.findElements(By.cssSelector("div.css-1kyev75-menu div.TableFilterInputSelect__option"));
		System.out.println(dropdownValue.size());
		selectItem(dropdown,dropdownValue, "Chi nhánh Trần Bình Trọng");
		Assert.assertEquals(dropdown.getText(), "Chi nhánh Trần Bình Trọng");
	}
	
	//@Test
	public void TC_04_HRMTest() throws InterruptedException {
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
	
	//@Test
	public void TC_05_RadioButton() throws InterruptedException {
		driver.get("https://material.angularjs.org/latest/demo/radioButton");	
		Thread.sleep(5000);
		List<WebElement> FavoriteFruitList = driver.findElements(By.xpath("//label[text() ='Favorite Fruit:']/parent::p/following-sibling::md-radio-group[@aria-labelledby='favoriteFruit']//md-radio-button"));
		for(WebElement item : FavoriteFruitList) {
			if(item.getAttribute("value").equals("Banana")) {
				item.click();
			}
		}
		
		Assert.assertEquals(driver.findElement(By.xpath("//label[text() ='Favorite Fruit:']/parent::p/following-sibling::md-radio-group[@aria-labelledby='favoriteFruit']//md-radio-button[@value='Banana']")).getAttribute("aria-checked"),"true");
		
		
	}
	
	//@Test 
	public void TC_06_DefaultCheckBoxJsExcutor() throws InterruptedException{
		driver.get("https://material.angularjs.org/latest/demo/checkbox");
		WebElement checkbox1 = driver.findElement(By.xpath("//legend[text()='Using <ng-model>']/parent::fieldset//md-checkbox[@aria-label='Checkbox 1']"));
		Thread.sleep(1000);
		Assert.assertEquals(checkbox1.getAttribute("aria-checked"), "true");
		WebElement checkbox2 = driver.findElement(By.xpath("//legend[text()='Using <ng-model>']/parent::fieldset//md-checkbox[@aria-label='Checkbox 2']"));
		Assert.assertEquals(checkbox2.getAttribute("aria-checked"), "false");
		jsExcutor.executeScript("arguments[0].click();", checkbox2);
		Thread.sleep(1000);
		Assert.assertEquals(checkbox2.getAttribute("aria-checked"), "true");
		WebElement checkboxDisable =  driver.findElement(By.xpath("//legend[text()='Using <ng-model>']/parent::fieldset//md-checkbox[@aria-label='Disabled checkbox']"));
	}
	
	@Test
	public void TC_07_HandleAlert() throws InterruptedException {
		driver.get("https://demo.automationtesting.in/Alerts.html");	
		//demo alert with only OK button
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "I am an alert box!");
		driver.switchTo().alert().accept();
		
		//demo alert with OK and Cancel button
		driver.findElement(By.xpath("//a[text()='Alert with OK & Cancel ']")).click();
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You pressed Ok')]")).isDisplayed());
		driver.findElement(By.cssSelector("button.btn-primary")).click();
		driver.switchTo().alert().dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'You Pressed Cancel')]")).isDisplayed());
		
		//deomo alert with textbox
		driver.findElement(By.xpath("//a[text()='Alert with Textbox ']")).click();
		driver.findElement(By.cssSelector("button.btn-info")).click();
		driver.switchTo().alert().sendKeys("HI");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		String expectedString = "Hello " + "HI" + " How are you today";
		String actual = driver.findElement(By.cssSelector("p#demo1")).getText();
		System.out.println(actual);
		Assert.assertEquals(actual, expectedString);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectItem(WebElement dropdown, List<WebElement> itemList, String itemValue) {
		for(WebElement item : itemList) {
			if(item.getText().equals(itemValue)) {
				item.click();
				break;
			}
		}
	}
}


