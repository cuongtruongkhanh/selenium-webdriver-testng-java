package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea_TrangChecked {
	WebDriver driver;
	String EmployeeID, FirstName, LastName;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		FirstName = "Khanh Cuong";
		LastName = "Truong";
	}

	@Test
	public void TC_01_Login() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

	}

	@Test
	public void TC_02_Open_Add_Employee() {
		
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		driver.findElement(By.name("firstName")).sendKeys(FirstName);
		driver.findElement(By.name("lastName")).sendKeys(LastName);
		
		sleepInSecond(8);
		driver.findElement(By.cssSelector("button.oxd-button.orangehrm-left-space")).click();

		sleepInSecond(8);
		String firstnameConfirm =  driver.findElement(By.name("firstName")).getAttribute("value");
		String lastNameConfirm =  driver.findElement(By.name("lastName")).getAttribute("value");
		
		//Confirm
		Assert.assertEquals(firstnameConfirm, FirstName);
		Assert.assertEquals(lastNameConfirm, LastName);

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}