package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor jsExecutor;
	Alert alert;

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	//@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepSecond(3);
		
		//Verify
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
		
	}

//	@Test
	public void TC_02_Hove_Kid() {
		driver.get("http://www.myntra.com/");
		
		WebElement kidsButton = driver.findElement(By.xpath("//div[@class='desktop-navLinks']//a[text()='Kids']"));
		WebElement HomeandBath = driver.findElement(By.xpath("//a[text()='Home & Bath']"));
		
		
		action.moveToElement(kidsButton).perform();
		sleepSecond(2);
		action.click(HomeandBath).perform();
		sleepSecond(2);
		
		//Verify 
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
		
		
		
	}

//	@Test
	public void TC_03_() {
		
	}

//	@Test
	public void TC_04_ClickandHove() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allSelectable = driver.findElements(By.cssSelector("li.ui-selectee"));
		
		action.clickAndHold(allSelectable.get(0)).perform();
		action.moveToElement(allSelectable.get(10)).perform();
		action.release().perform();
		sleepSecond(1);
		
		//verify
		allSelectable = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertEquals(allSelectable.size(), 9);
		
				
	}
	
//	@Test
	public void TC_05_ClickandHoveRandom() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allSelectable = driver.findElements(By.cssSelector("li.ui-selectee"));
		
		//nhan phim Control xuong
		action.keyDown(Keys.CONTROL);
		
		action.click(allSelectable.get(0)).click(allSelectable.get(2)).click(allSelectable.get(5)).click(allSelectable.get(7)).click(allSelectable.get(10)).perform();
		sleepSecond(1);
		// nha phim Control
		action.keyUp(Keys.CONTROL);
		allSelectable = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
		Assert.assertEquals(allSelectable.size(), 5);
		
	
	}

//	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		////button[text()='Double click me']
		
		WebElement dblClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		sleepSecond(2);
		
		// Scroll into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dblClickButton);
		
		
		action.doubleClick(dblClickButton).perform();
		sleepSecond(1);
		
		//Verify
		String HelloSentence = driver.findElement(By.id("demo")).getText();
		System.out.println(HelloSentence);
		
		Assert.assertEquals(HelloSentence, "Hello Automation Guys!");		
		
	}
	
//	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		WebElement RightButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		WebElement QuitOption = driver.findElement(By.xpath("//span[text()='Quit']"));
		sleepSecond(1);
		
		action.contextClick(RightButton).perform();
		action.moveToElement(QuitOption).perform();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(QuitOption).perform();
		sleepSecond(2);
		alert = driver.switchTo().alert();
		
		
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		
		//Verify Quit menu is not displayed
		Assert.assertFalse(QuitOption.isDisplayed());
		
	}
	
	@Test
	public void TC_08_DragDrop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement dragbutton = driver.findElement(By.cssSelector("div#draggable"));
		WebElement droptarget = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(dragbutton, droptarget).perform();
		
		//Verify
		
//		String dropColor = droptarget.getCssValue("background-color");
		String dropColorHex = Color.fromString(droptarget.getCssValue("background-color")).asHex().toLowerCase();
		
		Assert.assertEquals(droptarget.getText(), "You did great!");
		Assert.assertEquals(dropColorHex, "#03a9f4");

	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
