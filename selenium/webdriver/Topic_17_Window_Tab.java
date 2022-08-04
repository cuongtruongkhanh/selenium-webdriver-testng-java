package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

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
	public void TC_01_twoWindow() {
		driver.get("https://automationfc.github.io/basic-form/");
		String formtabID = driver.getWindowHandle();
		
		//click vao google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//Nhay sang google website
		switchWindowID(formtabID);
		

		// luc nay dang o goolge website
		driver.findElement(By.cssSelector("input.gLFyf")).sendKeys("Automation Test");
		String googleTab = driver.getWindowHandle();
		sleepSecond(2);
		
		//quay ve page automationfc
		switchWindowID(googleTab);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='SELENIUM WEBDRIVER API']")).isDisplayed());
		
	}
	
	public void switchWindowID(String parentID) {
		Set<String> allTabID = driver.getWindowHandles();
		
		for (String id : allTabID) {
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				break;
			}
				
		}
		
	}

	@Test
	public void TC_02_Multiple_Window() {
		driver.get("https://automationfc.github.io/basic-form/");
		
		//click vao google
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepSecond(3);
		
		switchMultiplePage("Google");
		System.out.println("Page title: "+ driver.getTitle());
		sleepSecond(1);
		
		switchMultiplePage("Facebook");
		System.out.println("Page title: "+ driver.getTitle());
		sleepSecond(1);
		
		switchMultiplePage("Tiki");
		System.out.println("Page title: "+ driver.getTitle());
		sleepSecond(1);
		
		switchMultiplePage("Lazada");
		System.out.println("Page title: "+ driver.getTitle());
		sleepSecond(1);
		
		switchMultiplePage("SELENIUM");
		System.out.println("Page title: "+ driver.getTitle());
		sleepSecond(1);
		
	}
	
	public void switchMultiplePage(String expectedPagetitle) {
		Set<String> allWindowID = driver.getWindowHandles();
		
		for (String id : allWindowID) {
			driver.switchTo().window(id);
			String pageTitle = driver.getTitle();
			if (pageTitle.contains(expectedPagetitle)) {
				break;
			}
		}
	}

//	@Test
	public void TC_03_() {
		
	}

//	@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
