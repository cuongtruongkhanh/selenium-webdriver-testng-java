package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By_usingChrome {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Setting system properties of Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		// Creating an object of Chrome driver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//access to URL
		driver.get("http://live.techpanda.org/");
		
	}

	@Test
	public void TC_01_() {
		// find element
		driver.findElement(By.xpath("//a[@title='Advanced Search']"));
		driver.findElement(By.cssSelector("a[title='Advanced Search']"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
