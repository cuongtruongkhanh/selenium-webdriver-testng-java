package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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
	
	
	//@Test
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
	

	@Test
	public void TC_03_LivePanda() {
		driver.get("http://live.techpanda.org/");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepSecond(2);
		
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		String successMessage = driver.findElement(By.cssSelector("li.success-msg")).getText();
		Assert.assertEquals(successMessage, "The product Sony Xperia has been added to comparison list.");
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2[@class='product-name']/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		successMessage = driver.findElement(By.cssSelector("li.success-msg")).getText();
		Assert.assertEquals(successMessage, "The product Samsung Galaxy has been added to comparison list.");
		
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		
		switchWindowID(parentID);
		driver.manage().window().maximize();
		String comparePageID = driver.getWindowHandle();
		
		String compareText = driver.findElement(By.cssSelector("div.page-title>h1")).getText();
		Assert.assertEquals(compareText.toLowerCase(), "compare products");
		
		driver.findElement(By.xpath("//span[text()='Close Window']")).click();
		
		switchWindowID(comparePageID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		sleepSecond(2);
		
		alert = driver.switchTo().alert();
		sleepSecond(2);
		System.out.println(alert.getText());
		alert.accept();
		sleepSecond(3);
		
		//verify clear compare
		String clearMessage = driver.findElement(By.cssSelector("li.success-msg")).getText();
		
		Assert.assertEquals(clearMessage, "The comparison list was cleared.");
		
	}

//	@Test
	public void TC_04_() {
		
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

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
