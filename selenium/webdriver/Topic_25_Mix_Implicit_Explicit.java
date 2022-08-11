package webdriver;
import java.util.Date;
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


	public String getTimeStam() {
		Date date = new Date();
		return date.toString();
		
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

//	@Test
	public void TC_01_Element_Found() {
		explicitWait = new WebDriverWait(driver, 20);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//implicitWait: apply cho find Element/find Elements
		//explicitWait: apply cho cac dieu kien cua element: invisibility, visibility, presence, ...
		
		driver.get("https://www.facebook.com/");
		
		//implicitwait:
		System.out.println("Start time:" + getTimeStam());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("End time:" + getTimeStam());
		
		//explicitwait:
		System.out.println("Start time:" + getTimeStam());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("End time:" + getTimeStam());
                       
	}

//	@Test
	public void TC_02_No_Element_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// implicitWait: apply cho find Element/find Elements
		// explicitWait: apply cho cac dieu kien cua element: invisibility, visibility,
		// presence, ...

		driver.get("https://www.facebook.com/");

		// implicitwait:
		System.out.println("Start time:" + getTimeStam());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End time:" + getTimeStam());
		}
		
	}


	@Test
	public void TC_03_No_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);
		driver.get("https://www.facebook.com/");
		
	
		//explicitwait:
		System.out.println("Start time explicit:" + getTimeStam());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			System.out.println("End time explicit:" + getTimeStam());
			e.printStackTrace();
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
