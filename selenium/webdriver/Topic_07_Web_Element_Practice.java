package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Practice {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Element_Method() {

		// WebElement element = driver.findElement(By.id("Email"));

		// Kiem tra tuyet doi
		// div.page-title>h1
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText().toLowerCase(),
				"login or create an account");
		Assert.assertTrue(
				driver.findElement(By.xpath("//h1[contains(text(),'Login or Create an Account')]")).isDisplayed());

		// Kiem tra tuong doi
		String benefit = driver.findElement(By.cssSelector("ul.benefits")).getText();
		Assert.assertTrue(benefit.contains("Faster checkout"));
		Assert.assertTrue(benefit.contains("Save multiple shipping addresses"));
		Assert.assertTrue(benefit.contains("View and track orders and more"));

	}

	@Test
	public void TC_02_checkDisplay_Enable() {
		// press button //span[@class='label' and text()='Account']
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		// Check div.skip-content.skip-active a[title='My Account']
		WebElement Accountbutton = driver
				.findElement(By.cssSelector("div.skip-content.skip-active a[title='My Account']"));
		// Check Account button display or not
		Assert.assertTrue(Accountbutton.isDisplayed());
		Assert.assertTrue(Accountbutton.isEnabled());

	}

	@Test
	public void TC_03_CheckTitle() {
		Assert.assertEquals(driver.getTitle(), "Customer Login");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
