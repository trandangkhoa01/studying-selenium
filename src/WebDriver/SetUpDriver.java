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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class SetUpDriver {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExcutor;
	Actions act;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.edge.driver", projectPath +"\\webDriver\\msedgedriver.exe");
		 driver = new EdgeDriver();
		 jsExcutor = (JavascriptExecutor) driver;
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
		 act = new Actions(driver);
		 
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
		selectItem(dropdownValue, "Chi nhánh Trần Bình Trọng");
		Assert.assertEquals(dropdown.getText(), "Chi nhánh Trần Bình Trọng");
		Thread.sleep(3000);
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
	
	//@Test
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
	
	
	//@Test
	public void TC_08_Action() {
		driver.get("https://www.fahasa.com/");
		act.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		act.moveToElement(driver.findElement(By.xpath("//span[text() = 'Sách Trong Nước' ]"))).perform();
		jsExcutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class = 'dropdown-menu-inner']//span[text() ='KINH TẾ']/ancestor::h3/following-sibling::ul//a[text() ='Quản Trị - Lãnh Đạo']")));
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text() = 'Quản Trị - Lãnh Đạo']")).isDisplayed());
		
	}
	
	//@Test
	public void TC_09_Popup() throws InterruptedException {
		driver.get("https://ngoaingu24h.vn/");	
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.login_")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1.modal.fade.in")).isDisplayed());
	
	}
	
	//@Test
	public void TC10_Popup2() throws InterruptedException {
		driver.get("https://dev.ecomnet.app/");
		WebElement txtUsername = driver.findElement(By.xpath("//input[@name = 'email']"));
		WebElement txtPassword = driver.findElement(By.xpath("//input[@name = 'password']"));
		WebElement btnLogin = driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']"));
		txtUsername.sendKeys("bkaadmin");
		txtPassword.sendKeys("qazwsx");
		btnLogin.click();
		driver.findElement(By.xpath("//span[text() = 'Danh sách nhóm']")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#kt_modal_add_group")).isDisplayed());
		driver.findElement(By.xpath("//button[text() =' Tạo nhóm ']")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#kt_modal_add_group")).isDisplayed());
		driver.findElement(By.cssSelector("input[placeholder = 'Tên nhóm']")).sendKeys("Group Test");
		driver.findElement(By.xpath("//span[text() ='Tạo nhóm']")).click();
	}
	
	//@Test
	public void TC_11_HandelDropdownECN() throws InterruptedException {
		driver.get("https://dev.ecomnet.app/");
		WebElement txtUsername = driver.findElement(By.xpath("//input[@name = 'email']"));
		WebElement txtPassword = driver.findElement(By.xpath("//input[@name = 'password']"));
		WebElement btnLogin = driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']"));
		txtUsername.sendKeys("bkaadmin");
		txtPassword.sendKeys("qazwsx");
		btnLogin.click();
		driver.findElement(By.xpath("//span[text() = 'Danh sách nhóm']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[contains(text(),' Filter ')]")).click();
		Thread.sleep(3000);
		WebElement DropdownType = driver.findElement(By.xpath("//label[text() ='Loại']/parent::div//input"));
		String id = DropdownType.getAttribute("id");
		DropdownType.click();
		Thread.sleep(3000);
		String listId = splitAndConcatString(id);
		String xpath = "//div[contains(@id , '" + listId + "') and @aria-hidden = 'false']//li/span";
		System.out.println(xpath);
		List<WebElement> typeList =  driver.findElements(By.xpath(xpath));
		System.out.println(typeList.size());
		selectItem(typeList,"Riêng tư");
		Assert.assertEquals(DropdownType.getAttribute("value"),"Riêng tư");
		driver.findElement(By.xpath("//span[text() = ' Lưu ']")).click();
		Thread.sleep(3000);
	}
	
	//@Test
	public void TC_12_HandlePopupECN2() throws InterruptedException {
		driver.get("https://dev.ecomnet.app/");
		WebElement txtUsername = driver.findElement(By.xpath("//input[@name = 'email']"));
		WebElement txtPassword = driver.findElement(By.xpath("//input[@name = 'password']"));
		WebElement btnLogin = driver.findElement(By.xpath("//button/span[text()= 'Đăng nhập']"));
		txtUsername.sendKeys("bkaadmin");
		txtPassword.sendKeys("qazwsx");
		btnLogin.click();
		driver.findElement(By.xpath("//span[text() = 'Thư viện số']")).click();
		driver.findElement(By.xpath("//span[text() = 'Danh sách nội dung số']")).click();
		Thread.sleep(1000);
		Assert.assertFalse(driver.findElement(By.cssSelector("div#kt_modal_new_media div.modal-content")).isDisplayed());
		driver.findElement(By.xpath("//button[text() = ' Thêm mới ']")).click();
		Thread.sleep(500);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#kt_modal_new_media div.modal-content")).isDisplayed());
		driver.findElement(By.cssSelector("input[placeholder = 'Tiêu đề...']")).sendKeys("Media text");
		WebElement typeDropdown = driver.findElement(By.xpath("//span[text() ='Định dạng']/parent::label/following-sibling::div[@class = 'el-select']//input"));
		String typeDropdownId = typeDropdown.getAttribute("id");
		typeDropdown.click();
		String typeListId = splitAndConcatString(typeDropdownId);
		String xpath = "//div[contains(@id , '" + typeListId + "') and @aria-hidden = 'false']//li/span";
		List<WebElement> typeList = driver.findElements(By.xpath(xpath));
		selectItem(typeList, "PHOTO");
		Assert.assertEquals(typeDropdown.getAttribute("value"), "PHOTO");
		Thread.sleep(500);
		driver.findElement(By.cssSelector("div#kt_modal_new_media_close span")).click();
		Thread.sleep(500);
		Assert.assertFalse(driver.findElement(By.cssSelector("div#kt_modal_new_media div.modal-content")).isDisplayed());
		
	}
	
	//@Test
	public void TC_13_Handle_Popup_Not_In_DOM_TIKI() throws InterruptedException {
		driver.get("https://tiki.vn/");
		By popupCss = By.cssSelector("div[role ='dialog']");
		Assert.assertEquals(driver.findElements(popupCss).size(), 0);
		driver.findElement(By.xpath("//span[text() = 'Tài khoản']")).click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElements(popupCss).size(), 1);
		Assert.assertTrue(driver.findElement(popupCss).isDisplayed());
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[text() = 'Đăng nhập']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'error-mess' and text() = 'Email không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class = 'error-mess' and text() = 'Mật khẩu không được để trống']")).isDisplayed());
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_14_Handle_Random_Popup() throws InterruptedException {
		driver.get("https://dehieu.vn/");
		Thread.sleep(6000);
		if(driver.findElement(By.cssSelector("div.modal-dialog div.modal-content")).isDisplayed()) {
			driver.findElement(By.cssSelector(" button.close")).click();
			Thread.sleep(1000);
		}
		
		driver.findElement(By.xpath("//a[text() =' Tất cả khóa học']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("input.search-form")).sendKeys("Thiết kế tủ điện");
		driver.findElement(By.cssSelector("button.header-search")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course-item-image")).size(), 1);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String splitAndConcatString(String string) {
		String[] output = string.split("-");
		String result = output[0].concat("-").concat(output[1]).concat("-").concat(output[2]);
		return result;
		
	}
	public void selectItem(List<WebElement> itemList, String itemValue) {
		for(WebElement item : itemList) {
			if(item.getText().equals(itemValue)) {
				jsExcutor.executeScript("arguments[0].click()", item);
				break;
			}
		}
	}
}


