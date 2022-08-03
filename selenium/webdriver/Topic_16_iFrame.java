package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_iFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;
	Actions action;
	
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

	//@Test
	public void TC_01_iFrame_Kyna() {
		driver.get("https://kyna.vn/");
		sleepSecond(1);
		
		//swith to ifram FB
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content iframe")));
		sleepSecond(1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.lfloat div._1drq")).getText(), "166K likes");
		sleepSecond(1);
		
		//back to default content
		driver.switchTo().defaultContent();
		
		sleepSecond(2);
		
		//switch to cs chat iframe
		driver.switchTo().frame(driver.findElement(By.id("cs_chat_iframe")));
		driver.findElement(By.cssSelector("div.border_overlay")).click();
		
		WebElement inputName = driver.findElement(By.cssSelector("input.input_name"));
		WebElement inputPhone = driver.findElement(By.cssSelector("input.input_phone"));
		select = new Select(driver.findElement(By.id("serviceSelect")));
		
		inputName.sendKeys("Khanh Cuong");
		inputPhone.sendKeys("0987654321");
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		sleepSecond(1);
		
		//back to default content
		driver.switchTo().defaultContent();
		
		//search for excel keyword
		driver.findElement(By.cssSelector("nav#navDesktop .live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepSecond(2);
		
		//verify search result
		List<WebElement> SearchResult = driver.findElements(By.cssSelector("div.content>h4"));
		
		System.out.println("Total: "+SearchResult.size());
		Assert.assertEquals(SearchResult.size(), 9);
		
		String keyword = "Excel";
		
		for (WebElement course : SearchResult) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
			
		}
		
		
	}

	@Test
	public void TC_02_() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("123456");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepSecond(2);
		
		WebElement pwField = driver.findElement(By.cssSelector("input#fldPasswordDispId"));
		//driver.switchTo().frame("login_page");
		Assert.assertTrue(pwField.isDisplayed());
		
		pwField.sendKeys("123456");
		sleepSecond(2);
		
		
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
