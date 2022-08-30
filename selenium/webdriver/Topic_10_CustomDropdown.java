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

public class Topic_10_CustomDropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	public void sleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		// Luôn khởi tạo sau driver
		explicitWait = new WebDriverWait(driver, 15);

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// chon item so 5
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "5");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "5");
		// refresh page
		driver.navigate().refresh();

		// chon item so 10
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "10");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "10");
		driver.navigate().refresh();

		// chon item so 5
		selectItemInCustomDropdown("span#number-button", "ul#number-menu>li>div", "19");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "19");
		driver.navigate().refresh();
	}

	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		// driver.findElement(By.cssSelector("span#number-button")).click();
		driver.findElement(By.cssSelector(parentLocator)).click();
		// chờ cho tất cả các item con bên trong được load ra => WebDriverWait
		// explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Lấy hết item ra và lưu vào 1 biến Element
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			if (actualTextItem.equals(expectedTextItem)) {
				// True -> Scroll đến mép trên của Element
				// False -> Scroll đến mép dưới của Element
				System.out.println(item.getText());
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepSecond(1);
				item.click();
				sleepSecond(1);
				break;
			}

		}

	}

	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String expectedTextItem) {
		// driver.findElement(By.cssSelector("span#number-button")).click();
		// driver.findElement(By.cssSelector(parentLocator)).click();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(expectedTextItem);
		// chờ cho tất cả các item con bên trong được load ra => WebDriverWait
		// explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu>li>div")));
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// Lấy hết item ra và lưu vào 1 biến Element
		List<WebElement> allDropdownItems = driver.findElements(By.cssSelector(childLocator));

		for (WebElement item : allDropdownItems) {
			String actualTextItem = item.getText();
			if (actualTextItem.equals(expectedTextItem)) {
				// True -> Scroll đến mép trên của Element
				// False -> Scroll đến mép dưới của Element
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepSecond(1);
				item.click();
				sleepSecond(1);
				break;
			}
		}

	}

//	@Test
	public void TC_02_JQuery_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		// driver.findElement(By.xpath("//button[text()='CHẤP NHẬN']")).click();
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.className("container")));
		sleepSecond(2);

		// k xử lý được vì get text
		selectItemInCustomDropdown("div.choose-car.d-flex button", "div.choose-car.d-flex div.dropdown-menu.show", "CIVIC E (Trắng ngọc)");
		// Assert.assertTrue(driver.findElement(By.cssSelector("div.choose-car.d-flex button#selectize-input")).getText().contains("CITY G"));
		System.out.println(driver.findElement(By.cssSelector("div.choose-car.d-flex button#selectize-input")).getText());

	}

//	@Test
	public void TC_03_JQuery_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdown("#root", "div.visible.menu.transition>div>span", "Christian");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).getText(), "Christian");

	}

//	@Test
	public void TC_04_Vue_dropdown() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		// First Option
		selectItemInCustomDropdown("div.btn-group", "ul.dropdown-menu a", "First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");

		// Second Option
		selectItemInCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

		// Third Option
		selectItemInCustomDropdown("div.btn-group", "ul.dropdown-menu a", "Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");

	}

//	@Test
	public void TC_05_Semantic() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		enterItemInCustomDropdown("input.search", "div.visible.menu.transition>div>span", "Anguilla");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Anguilla");

		enterItemInCustomDropdown("input.search", "div.visible.menu.transition>div>span", "Belgium");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");

		enterItemInCustomDropdown("input.search", "div.visible.menu.transition>div>span", "Azerbaijan");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Azerbaijan");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
