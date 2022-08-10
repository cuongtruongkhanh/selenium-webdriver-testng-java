package webdriver;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Implicit_Explicit {
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
	
		driver.manage().window().maximize();
		

	}

	@Test
	public void TC_01_Element_Found() {
		explicitWait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//implicitWait: apply cho find Element/find Elements
		//explicitWait: apply cho cac dieu kien cua element: invisibility, visibility, presence, ...
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		driver.get("https://www.facebook.com/");
		
		//implicitwait:
		System.out.println("Start time:" + timestamp);
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("End time:" + timestamp);
		
		//explicitwait:
		System.out.println("Start time:" + timestamp);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("End time:" + timestamp);
                       
	}

//	@Test
	public void TC_02_() {
		
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
