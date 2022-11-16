package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Set_Language {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Setting system properties of Chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		// Creating an object of Chrome driver
		driver = new ChromeDriver();

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--lang= de");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Check_Language() {
		driver.get("https://www.facebook.com/");
		SleepSecond(3);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void SleepSecond(long TimeInSecond) {
		try {
			Thread.sleep(TimeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
