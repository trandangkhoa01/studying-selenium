package anotation;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class beforeOrAfterMethod {

	@BeforeClass
	public void beforeMethod() {
		
	}

	String fullName = "Khoa T";

	@Test(dataProvider = "data")
	public void TC_01(String value, String value1) {
		System.out.println(value +" " + value1);
	}

	//@Test(groups = "Tc1")
	public void TC_02() {
		System.out.println("Test case 02");
	}

	// beforeMethod dùng để chay hành động trước khi chạy 1 TC

	@DataProvider(name = "data")
	public Object[] data() {
		return new Object[][] { { "Khoa", "Tran" }, { "Linh", "Le" } };
	}

	// afterMethod dùng để chay hành động sau khi chạy 1 TC
	// alwaysRun sử dụng để fuction luôn chạy
	@AfterClass(alwaysRun = true)
	public void afterMethod() {
		
	}

}
