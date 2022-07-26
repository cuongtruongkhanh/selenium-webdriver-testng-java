package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Alert alert;
	
	public void sleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_Accpet_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		sleepSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		//sau khi alert hien len, switch sang alert dialog
		alert = driver.switchTo().alert();
		
		//verify title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//accept alert
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
		
	}

//	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		sleepSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		//sau khi alert hien len, switch sang alert dialog
		alert = driver.switchTo().alert();
		
		//verify title
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// accept alert
		alert.accept();	
		//alert.dismiss();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Ok");
		
	}

	@Test
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/");
		sleepSecond(2);
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//sau khi alert hien len, switch sang alert dialog
		alert = driver.switchTo().alert();
		
		//verify title
		Assert.assertEquals(alert.getText(), "I am a JS Prompt");
		
		String inputAlert = "Test";
		// accept alert
		alert.sendKeys(inputAlert);	
		sleepSecond(2);
		alert.accept();
		//You entered: Test
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + inputAlert);
		
		
	}

	@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
