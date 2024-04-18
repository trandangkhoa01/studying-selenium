package anotation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class demoAnotation {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Parameters("browser")
	@BeforeClass
	//OPtional dùng để đặt giá trị mặt định khi không cấu hình ở xml
	public void beforeClass(@Optional("chrome") String browserName) {
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver" , projectPath + "\\webDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}else if (browserName.equals("Edge") ) {
			System.setProperty("webdriver.edge.driver" , projectPath + "\\webDriver\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else {
			throw new RuntimeException("browser name invalid");
		}
		
	}

	@Parameters("url") // truyền giá trị vào function test
	@Test(invocationCount = 1) // số lần testcase được lặp lại
	public void TC_01(String url) throws InterruptedException {
		driver.get(checkUrl(url));
		Thread.sleep(1000);
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String checkUrl(String url) {
		if(url.toLowerCase().equals("facebook")) {
			url = "https://www.facebook.com/";
		}else if(url.toLowerCase().equals("youtube")) {
			url = "https://www.youtube.com/watch?v=c6o4nv4-oz4&list=RDc6o4nv4-oz4&start_radio=1";
		}else {
			throw new RuntimeException("invalid url");
		}
		return url;
	}
}
