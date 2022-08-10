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

public class Topic_21_FindElement_FindElements {
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
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//		
//		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
//		driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_notEnoughTime() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		WebElement startButton = driver.findElement(By.cssSelector("div#start>button"));
		startButton.click();
		//check
		//verify
		WebElement welcomeWord = driver.findElement(By.cssSelector("div#finish>h4"));
		Assert.assertEquals(welcomeWord.getText(), "Hello World!");
		
	}

	@Test
	public void TC_02_enoughTime() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		WebElement startButton = driver.findElement(By.cssSelector("div#start>button"));
		startButton.click();
		
		//verify
		WebElement welcomeWord = driver.findElement(By.cssSelector("div#finish>h4"));
		Assert.assertEquals(welcomeWord.getText(), "Hello World!");
	}

	@Test
	public void TC_03_longTime() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		WebElement startButton = driver.findElement(By.cssSelector("div#start>button"));
		startButton.click();
		
		//verify
		WebElement welcomeWord = driver.findElement(By.cssSelector("div#finish>h4"));
		Assert.assertEquals(welcomeWord.getText(), "Hello World!");
	}

//	@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
