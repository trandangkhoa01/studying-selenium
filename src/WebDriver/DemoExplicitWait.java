package WebDriver;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	String OS = System.getProperty("os.name");
	JavascriptExecutor jsExcutor;
	String image1 = "image1.jpg";
	String image2 = "images2.jpg";
	String image3 = "images3.jpg";
	String image4 = "images4.jpg";
	String image1Path = projectPath + File.separator + "uploadFile" + File.separator + image1;
	String image2Path = projectPath + File.separator + "uploadFile" + File.separator + image2;
	String image3Path = projectPath + File.separator + "uploadFile" + File.separator + image3;
	String image4Path = projectPath + File.separator + "uploadFile" + File.separator + image4;

	@BeforeClass
	public void beforeClass() {
		if (OS.contains("Mac OS")) {
			System.setProperty("webdriver.edge.driver", projectPath + "/webDriver/msedgedriver");
		} else {
			System.setProperty("webdriver.edge.driver", projectPath + "\\webDriver\\msedgedriver.exe");
		}
		driver = new EdgeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		jsExcutor = (JavascriptExecutor) driver;

	}
	
	//@Test
	public void TC_01_Wait_Alert_Present() {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		//Chờ cho elemnt có thể click -- >  elementToBeClickable : truyền vào elemnt hoặc locator
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn-danger")));
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		
		//Chờ cho alert hiển thị --> alertIsPresent : không cần truyền giá trị. Có thể thay hàm switchTo().alert()
		
		Assert.assertEquals(explicitWait.until(ExpectedConditions.alertIsPresent()).getText(), "I am an alert box!");
		driver.switchTo().alert().accept();
	}
	
	//@Test
	public void TC_02_RadioButton() {
		driver.get("https://material.angularjs.org/latest/demo/radioButton");
		
		//Chờ cho danh sách option cửa checkbox, radio button, dropdown load đủ số lượng: truyền vào locator và int => có thể dùng thay cho findElements
		List<WebElement> FavoriteFruitList =explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//label[text() ='Favorite Fruit:']/parent::p/following-sibling::md-radio-group[@aria-labelledby='favoriteFruit']//md-radio-button"), 3));
		for (WebElement item : FavoriteFruitList) {
			if (item.getAttribute("value").equals("Banana")) {
				item.click();
			}
		}

		//Chờ cho attribute của 1 Element có chứa giá trị: attributeToBe -> Truyền vào locator, attribute name, expected value
		explicitWait.until(ExpectedConditions.attributeToBe(By.xpath("//label[text() ='Favorite Fruit:']/parent::p/following-sibling::md-radio-group[@aria-labelledby='favoriteFruit']//md-radio-button[@value='Banana']"), "aria-checked", "true"));
		Assert.assertEquals(driver.findElement(By.xpath(
				"//label[text() ='Favorite Fruit:']/parent::p/following-sibling::md-radio-group[@aria-labelledby='favoriteFruit']//md-radio-button[@value='Banana']"))
				.getAttribute("aria-checked"), "true");

	}
	
	//@Test
	public void TC_3_Handle_Frame()  {
		driver.get("https://skills.kynaenglish.vn/");
		
		//Chờ cho 1 frame/iframe có thể switch - .frameToBeAvailableAndSwitchToIt : Dùng thay thế cho SwitchTo().
		explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe#cs_chat_iframe")));
		jsExcutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div.button_text")));
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Khoa Vip Pro");
		driver.findElement(By.cssSelector("input.input_phone ")).sendKeys("0612542147");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.name("message")).sendKeys("Hi am Khoa");
		driver.switchTo().defaultContent();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.fancybox-skin")));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fancybox-skin")).isDisplayed());
	}
	
	//@Test
	public void TC_04_Other_Function() {
		//Chờ cho 1 elemnt được chọn -> default checkbox và radio button : input type = checkbox or input type = radio
		explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
		
		//Chờ cho text của 1 element là "value"
		explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "value"));
	}
	
	
	//@Test
	public void TC_05_Demo_DayPicker() {
		driver.get("https://demos.telerik.com/aspnet-ajax/calendar/overview/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='24']"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("body>div>div.raDiv")));
		Assert.assertEquals(driver.findElement(By.cssSelector("span.picked-date>span")).getText(), "Wednesday, April 24, 2024");;
		
	}
	
	@Test
	public void TC_06_Demo_UploadFile() {
		driver.get("https://gofile.io/uploadFiles");
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
		By uploadFile = By.xpath("//input[@type = 'file']");
		driver.findElement(uploadFile).sendKeys(image1Path + "\n"+ image2Path + "\n"+ image3Path +"\n"+ image4Path);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.mainUploadInitInfo div.spinner-border")));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress-bar"))));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.mainUploadSuccess div.border-success")).getText().contains("Your files have been successfully uploaded"));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("div#filesContentTableContent"))));
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+image1+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+image2+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+image3+ "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text() = '"+image4+ "']")).isDisplayed());
		
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
