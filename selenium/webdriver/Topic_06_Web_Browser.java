package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser {
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
	public void TC_01_Method() {
		// close tab/browser
		driver.close();

		// Close browser immediately
		driver.quit();

		// Open URL
		driver.get("https://www.facebook.com/");

		// *********** Wait *********
		// Dung de tim 1 element
		driver.findElement(By.xpath(""));

		// dung de tim nhieu element
		driver.findElement(By.xpath(""));

		// Lay ra URL cua page hien tai
		driver.getCurrentUrl();

		// Lay ra Source Code cua page hien tai (HTML/XML/CSS)
		driver.getPageSource();

		// lay ra title cua page hien tai
		driver.getTitle();

		// *********** Windows/Tab *********
		// dung de xu ly window va tab
		// Lay ra ID cua tab dang active
		driver.getWindowHandle();

		// Lay ra ID cua tat ca cac tab/window dang co
		driver.getWindowHandles();

		// ******* Framework Cookies********
		driver.manage().deleteAllCookies();

		// ******* Framework Log********
		// driver.manage().logs().get()

		// Chờ cho findElement / findElements
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Chờ cho page load thành công
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

		// Chờ cho Javascript được thực thi thành công
		// ******** Javascript Executor *******
		// asynchronous script - Bất đồng bộ
		// sychronous script - đồng bộ
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		
		//Thực tế không dùng: Test GUI (Font/Size/Color/Position/Location..
		driver.manage().window().getPosition();
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().getSize();
		driver.manage().window().setSize(new Dimension(1920, 1080));
		
		
		//Tracking history tốt hơn - ít dùng
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		driver.navigate().to("https://www.facebook.com/");
		driver.navigate().back();
		
		// Alert
		driver.switchTo().alert();
		
		// Frame/ Iframe
		driver.switchTo().frame(0);
		
		// Windows/Tab
		driver.switchTo().window(projectPath);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();

	}
}
