package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
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
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		
		
//		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		
		System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		driver = new EdgeDriver();
		
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();

	}

//	@Test
	public void TC_01_OrangeHRM() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/dashboard");
		
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		//verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#task-list-group-panel-container span")).getText(), "3 month(s)");
		
		//click PIM
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule>b")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		//input name
		driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys("Peter Mac");
		driver.findElement(By.id("searchBtn")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		//verify
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Peter Mac']")).isDisplayed());
		
		
	}

	@Test
	public void TC_02_TestProject() {
		driver.get("https://blog.testproject.io/");
		String keyword = "Selenium";

		
		//check popup
		if(driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			//close popup
			driver.findElement(By.cssSelector("div.close-mailch")).click();
		}
		
		WebElement searchForm = driver.findElement(By.cssSelector("section#search-2 input.search-field"));
		//action.moveToElement(searchForm).perform();
		
		searchForm.sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.page-title")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> allTitle = driver.findElements(By.cssSelector("h3.post-title"));
		
		for (WebElement postTitle : allTitle) {
			String title = postTitle.getText();
			System.out.println(title);	
			Assert.assertTrue(title.contains(keyword));
		}
		
		
	}

//	@Test
	public void TC_03_() {
		
	}

//	@Test
	public void TC_04_() {
		
	}
	
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		
		ExpectedCondition<Boolean>jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery!= null) && (jQuery.active == 0);");
			}
			
		};
		
		ExpectedCondition<Boolean>jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
