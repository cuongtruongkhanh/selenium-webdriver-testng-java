package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

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
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		
		

	}

//	@Test
	public void TC_01_Visible_Displayed_Visibility() {
		driver.get("https://www.facebook.com/");
		
		//Có trên UI, có trong HTML
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		
	}

//	@Test
	public void TC_02_Insivible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		
		//k có trên UI, có trong HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

//	@Test
	public void TC_02_Insivible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		
		//k có trên UI, k có trong HTML
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	
//	@Test
	public void TC_03_Presence_I() {
		driver.get("https://www.facebook.com/");

		// Có trên UI, có trong HTML
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}

//	@Test
	public void TC_03_Presence_II() {
		//K có trên UI, có trong HTML
		driver.get("https://www.facebook.com/");
		
		//k có trên UI, có trong HTML
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		WebElement reEnterEmailAddressTextBox = driver.findElement(By.name("reg_email_confirmation__"));
		
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextBox));
		
		
	
	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
