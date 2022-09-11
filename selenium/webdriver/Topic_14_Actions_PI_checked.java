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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Actions_PI_checked {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		action = new Actions(driver);

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement aegTextbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(aegTextbox).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

	}

	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		WebElement kidLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"));
		action.moveToElement(kidLink).perform();
		sleepInSecond(3);
		action.click(driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());

	}

	@Test
	public void TC_03_Hover() {
		driver.get("https://www.fahasa.com/");
		WebElement iconMenu = driver.findElement(By.cssSelector("span.icon_menu"));
		action.moveToElement(iconMenu).perform();
		sleepInSecond(1);
		WebElement SachTrongNuoc = driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"));
		action.moveToElement(SachTrongNuoc).perform();
		sleepInSecond(1);
		WebElement VanHoc = driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//span[text()='VĂN HỌC']"));
		action.moveToElement(VanHoc).perform();
		sleepInSecond(1);
		action.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Ngôn Tình']"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.fahasa.com/sach-trong-nuoc/van-hoc-trong-nuoc/ngon-tinh.html");

	}

	public void TC_04_Click_and_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(10)).release().perform();
		sleepInSecond(3);
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 9);

	}

	public void TC_05_Click_and_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);

		// Nhấn phím Control xuống
		action.keyDown(Keys.CONTROL).perform();

		// Click chọn
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(7)).click(allNumbers.get(10)).perform();

		// Nhả phím Control ra
		action.keyUp(Keys.CONTROL).perform();

		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 5);
	}

	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement doubleClickMeText = driver.findElement(By.xpath("//button[text()='Double click me']"));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickMeText);
		sleepInSecond(3);
		action.doubleClick(doubleClickMeText).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

	}

	public void TC_07_Double_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		WebElement deleteBefor = driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
		action.moveToElement(deleteBefor).perform();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-hover")).isDisplayed());

		action.click(deleteBefor).perform();
		sleepInSecond(3);

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();

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