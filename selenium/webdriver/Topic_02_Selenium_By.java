package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {

	// khai báo 1 biến để đại diện cho thư viện Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Bước 1: Mở Browser
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Bấm cho maximize browser lên
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_() {
		// Bước 2: Get Url
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		// Bước 3: Click vào My Account để mở trang login ra
		
		
		//Xpath
		//tagname[@attribute-name='attribute-value']
		//input[@type='email']
		//input[@id='email']
		
		//CSS Format: tagname[attribute-name='attribute-value']
		
		
		//ID
		// Tìm 1 element
		driver.findElement(By.id("email"));
		driver.findElement(By.className("validate-email"));
		driver.findElement(By.name("login[username]"));
		driver.findElement(By.tagName("input"));
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		
		driver.findElement(By.linkText("ADVANCED SEARCH"));
		driver.findElement(By.partialLinkText("ANCED SEARCH"));
		
		
		//Ctrl + Space to show suggestion
		
		// Tìm nhiều element
		//driver.findElements(null)
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		// Bước 6: Đóng trình duyệt
		driver.quit();
	}
}
