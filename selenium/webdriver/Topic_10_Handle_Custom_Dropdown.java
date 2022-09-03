package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Handle_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait expliciWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Luôn khởi tạo sau Driver -> nó cần giá trị driver để khởi tạo exlicitWait lên
		// Wait cho việc "tìm" element: finElement/ findElements
		expliciWait = new WebDriverWait(driver, 15);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Chọn Item 10
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");
		driver.navigate().refresh();

		// Chọn Item 5
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		driver.navigate().refresh();

		// Chọn Item 15
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");

	}

	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		sleepInSecond(1);
		expliciWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();

			if (actualTextItem.equals(expectedTextItem)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	};

	@Test
	public void TC_02_() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("div.ui", "div.item>span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
	}

	@Test
	public void TC_03_() {

	}

	@Test
	public void TC_04_() {

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