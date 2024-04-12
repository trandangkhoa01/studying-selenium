package WebDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class JavascriptExcutor {
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
	public void TC_01() throws InterruptedException {
		navigateToUrlByJS("http://live.techpanda.org/");
		Thread.sleep(5000);
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		Assert.assertEquals(executeForBrowser("return document.URL"), "http://live.techpanda.org/");
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		Thread.sleep(3000);
		highlightElement("//a[text()='IPhone']/parent::h2/following-sibling::div[@class = 'actions']/button");
		clickToElementByJS("//a[text()='IPhone']/parent::h2/following-sibling::div[@class = 'actions']/button");
		Thread.sleep(5000);
		Assert.assertTrue(getInnerText().contains("IPhone was added to your shopping cart."));
		Assert.assertTrue(areExpectedTextInInnertext("IPhone was added to your shopping cart."));
		clickToElementByJS("//a[text() = 'Customer Service']");
		Thread.sleep(3000);
		Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");
		scrolltoElementOnDown("//input[@id = 'newsletter']");
		Thread.sleep(3000);
		navigateToUrlByJS("http://demo.guru99.com.v4/");
		Thread.sleep(3000);
	}
	
	@Test
	public void TC_02() throws InterruptedException{
		driver.get("https://warranty.rode.com/register");
		By buttonRegister = By.xpath("//button[text() = ' Register ']");
		driver.findElement(buttonRegister).click();
		Thread.sleep(3000);
		Assert.assertEquals(getValidationMessage("//input[@id ='name']"), "Please fill out this field.");
	}
	
	@AfterClass
	public void afterclass() {
		driver.quit();
	}
	
	public Object executeForBrowser(String javascript) {
		return jsExcutor.executeScript(javascript);
	}
	public String getInnerText() {
		return (String) jsExcutor.executeScript("return document.documentElement.innerText;");
	}
	public boolean areExpectedTextInInnertext(String textExpected) {
		String textActual = (String) jsExcutor.executeScript("return document.documentElement.innerText.match('"+textExpected+ "')[0];");
		return textActual.equals(textExpected);
	}
	public void scrollToBottomPage() {
		jsExcutor.executeScript("windown.scrollBy(0,document.body.scrollHeight)");
	}
	public void navigateToUrlByJS(String url) {
		jsExcutor.executeScript("window.location ='" + url + "';");
	}
	public void highlightElement(String locator) throws InterruptedException {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExcutor.executeScript("arguments[0].setAttribute('style', arguments[1])",element, "border: 2px solid red; border-style: dashed;");
		Thread.sleep(1000);
		jsExcutor.executeScript("arguments[0].setAttribute('style', arguments[1])",element,originalStyle);
	}
	public void clickToElementByJS(String locator) {
		jsExcutor.executeScript("arguments[0].click();", getElement(locator));
	}
	public void scrolltoElementOnTop(String locator) {
		jsExcutor.executeScript("arguments[0].scrollIntoView(true)", getElement(locator));
	}
	public void scrolltoElementOnDown(String locator) {
		jsExcutor.executeScript("arguments[0].scrollIntoView(false)", getElement(locator));
	}
	public void senKeysToElement(String locator, String value) {
		jsExcutor.executeScript("arguments[0].setAttribute('value'"+value+"'",getElement(locator));
	}
	public String getValidationMessage(String locator) {
		return (String) jsExcutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
		
	}
	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExcutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined && arguments[0].naturalWidth >0'", getElement(locator));
		return status;
	}
	public void deleteAttributeInDOM(String locator, String attributeRemove) {
		jsExcutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
		
	}


	public WebElement getElement(String locator) {
		WebElement element= driver.findElement(By.xpath(locator));
		return element;
	}
}
