package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_Exercise_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Verify_URL() {
		// truy cap vao trang web
		driver.get("http://live.techpanda.org/");
		// click vao my account - div.footer a[title='My Account']
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// verify Login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		// click create an account button - //span[text()='Create an Account']
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// verify Register Page -
		// http://live.techpanda.org/index.php/customer/account/create/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		// truy cap vao trang web
		driver.get("http://live.techpanda.org/");
		// click vao my account - div.footer a[title='My Account']
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// verify title Login page
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		// click create an account button - //span[text()='Create an Account']
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// Verify title - Create New Customer Account
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_Navigation() {
		// truy cap vao trang web
		driver.get("http://live.techpanda.org/");
		// click vao my account - div.footer a[title='My Account']
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// click create an account button - //span[text()='Create an Account']
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// verify Register Page -
		// http://live.techpanda.org/index.php/customer/account/create/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		//back lai trang login truoc do
		driver.navigate().back();
		// verify Login page
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		//forward toi trang Register Page
		driver.navigate().forward();
		// Verify title - Create New Customer Account
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_Page_Source() {
		// truy cap vao trang web
		driver.get("http://live.techpanda.org/");
		// click vao my account - div.footer a[title='My Account']
		driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
		// verify Login page chua Login or Create an Account
		String LoginPageSource = driver.getPageSource();
		Assert.assertTrue(LoginPageSource.contains("Login"));
		Assert.assertTrue(LoginPageSource.contains("Create an Account"));

		// click create an account button - //span[text()='Create an Account']
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		//Verify Register Page chua Create an Account
		String RegisterPageSource = driver.getPageSource();
		Assert.assertTrue(RegisterPageSource.contains("Create an Account"));
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
