package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown_Ex_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	
	public void SleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_() {
		driver.get("https://demo.nopcommerce.com/register");
		SleepSecond(1);
		
		//input
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("LastName")).sendKeys("Truong");
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText("25");
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("April");
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1990");
		
		driver.findElement(By.id("Email")).sendKeys("khanhcuong" + generateRandomNumber() + "@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.id("register-button")).click();
		
		//verify
		Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed");
		
		//go to my account
		driver.findElement(By.className("ico-account")).click();
		SleepSecond(1);
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='gender-male']")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), "Khanh Cuong");
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), "Truong");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"), "25");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"), "4");
		Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), "1990");

		
		
	}

	//@Test
	public void TC_02_() {
		
	}

	//@Test
	public void TC_03_() {
		
	}

	//@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
