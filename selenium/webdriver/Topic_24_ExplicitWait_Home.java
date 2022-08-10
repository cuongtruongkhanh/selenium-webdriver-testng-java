package webdriver;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait_Home {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	//image name
	String vietnam ="Vietnam.jpg";
	String thailand ="Thailand.jpg";
	String indonesia ="Indonesia.jpg";
	
	//Upload file folder
	String uploadFileFolederPath = projectPath + File.separator + "uploadFiles" + File.separator; 
	
	//image path
	String vietnamfilePath = uploadFileFolederPath + vietnam;
	String thailandfilePath = uploadFileFolederPath + thailand;
	String indonesiafilePath = uploadFileFolederPath + indonesia;
	

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
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 20);
		jsExecutor = (JavascriptExecutor)driver;
		
	}

//	@Test
	public void TC_01_Not_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 3);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Thieu thoi gian
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

//	@Test
	public void TC_02_Enough_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 5);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Du thoi gian
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

//	@Test
	public void TC_03_Long_Time() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		explicitWait = new WebDriverWait(driver, 50);
		
		driver.findElement(By.cssSelector("div#start>button")).click();
		//Du thoi gian
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#finish>h4")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
	}

//	@Test
	public void TC_04_ExplicitWait_Ajax() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait until calendar is displayed
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div.calendarContainer")));
		
		//Verify No selected date
		WebElement noSelectedDate = driver.findElement(By.cssSelector("div.RadAjaxPanel>span"));
		Assert.assertEquals(noSelectedDate.getText(), "No Selected Dates to display.");
		
		//Click chon ngay
		driver.findElement(By.xpath("//td[@title = 'Tuesday, August 09, 2022']")).click();
		
		//wait until rad is not visible
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("body>div.RadAjax>div.raDiv")));
		
		//Can phai tim lai 1 lan nua vi sau khi click da load lai
		Assert.assertEquals(driver.findElement(By.cssSelector("div.RadAjaxPanel>span")).getText(), "Tuesday, August 9, 2022");
		
	}
	
	@Test
	public void TC_06_uploadFile() {
		driver.get("https://gofile.io/welcome");
		
		String welcomePageID = driver.getWindowHandle();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.preloader.flex-column.justify-content-center>img")));
		driver.findElement(By.xpath("//p[contains(text(),'Upload Files')]")).click();
		
		//Wait cho button upload duoc hien thi
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-sm-auto>button.uploadButton")));
		
		//upload file
		driver.findElement(By.cssSelector("input#uploadFile-Input")).sendKeys(vietnamfilePath + "\n" + thailandfilePath + "\n" + indonesiafilePath);
		
		//Wait cho cac element upload bien mat
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div#rowUploadProgress-list div.progress")));
		
		//Wait cho thong bao upload succss hien len
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success>h5")));
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.callout-success>h5")).isDisplayed());
		
		
		//click vao link download
		driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).click();
		
		//wait
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.preloader.flex-column.justify-content-center>img")));
		
		//Switch new window
		switchtoWindow(welcomePageID);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowFolder")));
		
		//verify
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + vietnam + "']")).getText(), vietnam);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + thailand + "']")).getText(), thailand);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='contentName' and text()='" + indonesia + "']")).getText(), indonesia);
		
		//click select all
		driver.findElement(By.cssSelector("i.fa-square")).click();
		
		//wait cho tool bar visibility
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span#rowFolder-toolbar-checkboxToggle-count")));
		
		//click vao delete
		driver.findElement(By.cssSelector("button.contentDelete")).click();
		driver.findElement(By.cssSelector(" button.swal2-confirm")).click();
		
		//verify delete thanh cong
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.swal2-content>div#swal2-content")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.swal2-confirm")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.swal2-content>div#swal2-content")).getText(), "1 items have been deleted");
		System.out.println(driver.findElement(By.cssSelector("div.swal2-content>div#swal2-content")).getText());
		
		//click ok
		driver.findElement(By.cssSelector("button.swal2-confirm")).click();
		
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//h5[contains(text(),'This file does not exist.')]")).isDisplayed());
		
		
	}
	
	public void switchtoWindow(String pageID) {
		Set<String> allPageID = driver.getWindowHandles();
		
		for (String currentpageID : allPageID) {
			if(!currentpageID.equals(pageID)) {
				driver.switchTo().window(currentpageID);
				break;
			}
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
