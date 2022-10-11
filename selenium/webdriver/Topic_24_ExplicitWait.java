package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_ExplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	// Image name
	String laptop = "Laptop.jpg";
	String love = "Love.jpg";
	String meal = "Meal.jpg";

	// Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// Image path
	String laptopFilePath = uploadFileFolderPath + laptop;
	String loveFilePath = uploadFileFolderPath + love;
	String mealFilePath = uploadFileFolderPath + meal;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Apply 15s cho các điều kiện/ trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// @Test
	public void TC_01_Visible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 5);

		// click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Đủ thời gian để cho 1 element tiếp theo hoạt động được
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	// @Test
	public void TC_02_Invisible() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		explicitWait = new WebDriverWait(driver, 5);

		// click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait cho loading biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	// @Test
	public void TC_03_Ajax_Loading() {

		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		explicitWait = new WebDriverWait(driver, 15);

		// Wait cho Date Picker được hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));

		// Verify cho Selected Dates là không có ngày nào được chọn
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");

		// Wait cho ngày 19 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='19']")));

		// Click vào ngày 19
		driver.findElement(By.xpath("//a[text()='19']")).click();

		// Wait cho Ajax icon loading biến mất (invisible)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

		// Verify cho Selected Dates là "Wednesday, October 19, 2022"
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "Wednesday, October 19, 2022");

	}

	@Test
	public void TC_04_() {
		driver.get("https://gofile.io/uploadFiles");

		explicitWait = new WebDriverWait(driver, 45);

		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button.uploadButton")));

		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(laptopFilePath + "\n" + loveFilePath + "\n" + mealFilePath);

		// Wait cho loading icon của từng file biến mất
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#rowUploadProgress-list div.progress"))));

		// Wait cho Upload message thành công được visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Your files have been successfully uploaded']")));

		// Verify message này displayed
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']")).isDisplayed());

		// Wait + click cho Show file button được clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();

		// Wait + verify luôn: cho file name vs button download hiển thị

		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + laptop + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + love + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + meal + "']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());

		// Wait + verify luôn: cho file name vs button play hiển thị

		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + laptop + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + love + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + meal + "']/parent::a/parent::div/following-sibling::div//button[contains(@class,'contentPlay')]"))).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}