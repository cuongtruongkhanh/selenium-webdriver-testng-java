package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
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
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Defind_Element() {
		//thao tac 1 lan thi khong can khai bao bien
		driver.findElement(By.id("send2")).click();
		
		//thao tac 2 lan tro len thi can khai bao bien de tranh lap lai
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("123@123");;
		driver.findElement(By.id("email")).isDisplayed();			
		
		
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("12345");
		emailTextbox.isDisplayed();
				
	}
		
	@Test
	public void TC_02_Element_Method() {
		
		WebElement element = driver.findElement(By.id("Email"));
		
		// Xoa du lieu trong nhung field cho phep nhap
		// luon luon clear het du lieu truoc khi thao tac tren field
		// Textbo/  TextArea / Editable Dropdow
		element.clear();
		
		// Nhap du lieu trong nhung field cho phep nhap
		element.sendKeys("");
		element.sendKeys(Keys.ENTER);
		
		//Find element tu element cha
		driver.findElement(By.id("")).findElement(By.className("")).findElement(By.xpath(""));
		
		// Get attribute placeholder
		element.getAttribute("");
		// Search entire store here
		driver.findElement(By.id("search")).getAttribute("placeholder");
		
		// Khong khai bao bien, verify truc tiep
		Assert.assertEquals(driver.findElement(By.id("search")).getAttribute("placeholder"), "Search entire store here");
		
		// Khai bao bien
		String SearchTextboxPlaceholder = driver.findElement(By.id("search")).getAttribute("placeholder");
		Assert.assertEquals(SearchTextboxPlaceholder, "Search entire store here");		
		
		Assert.assertEquals(false, false);
		
		//Assert belong to TestNG
		
		// GUI: Font/ Size/ Color/ Pixel
		element.getCssValue("font-family");
		
		// GUI: Position/ Location/ Size of Element
		element.getLocation();
		element.getRect();
		element.getSize();
		
		//Framework: Attach screenshot to report HTML
		element.getScreenshotAs(OutputType.FILE);
		
		//Ham Assert
		// Kiem tra du lieu Dung 
		// Email text box hien thi
		Assert.assertTrue(element.isDisplayed());
		
		// Email text box KHONG hien thi
		Assert.assertFalse(element.isDisplayed());
		
		//Kiem tra tuyet doi
		//div.page-title>h1
		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(), "Login or Create an Account");
		
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
