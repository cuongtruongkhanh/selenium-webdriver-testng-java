package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_Sendkey {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// image name
	String vietnam = "Vietnam.jpg";
	String thailand = "Thailand.jpg";
	String indonesia = "Indonesia.jpg";

	// Upload file folder
	String uploadFileFolederPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// image path
	String vietnamfilePath = uploadFileFolederPath + vietnam;
	String thailandfilePath = uploadFileFolederPath + thailand;
	String indonesiafilePath = uploadFileFolederPath + indonesia;

	public void sleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep * 1000);
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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_UploadFile() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		// input[type ='file']
		driver.findElement(By.cssSelector("input[type ='file']")).sendKeys(vietnamfilePath + "\n" + thailandfilePath + "\n" + indonesiafilePath);
//		driver.findElement(By.cssSelector("input[type ='file']")).sendKeys(thailandfilePath);
//		driver.findElement(By.cssSelector("input[type ='file']")).sendKeys(indonesiafilePath);
		sleepSecond(2);

		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("tbody.files button.btn-primary"));

		for (WebElement uploadButton : uploadButtons) {
			// start upload
			uploadButton.click();
			sleepSecond(3);

		}

		// verify upload sucess
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailand + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());

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
