package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Ex_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void sleepSecond(long timeSleep) {
		try {
			Thread.sleep(timeSleep*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//@Test
	public void TC_01_() {
		driver.get("https://www.fahasa.com/customer/account/create");
		
		// Tab dang nhap
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-item.popup-login-tab-login")).click();
		
		By buttonLogin = By.cssSelector("button.fhs-btn-login");
		//Verify button Dang nhap bi disable
		Assert.assertFalse(driver.findElement(buttonLogin).isEnabled());
		
		// input
		driver.findElement(By.id("login_username")).sendKeys("cuong@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		//Verify button Dang nhap duoc enable
		Assert.assertTrue(driver.findElement(buttonLogin).isEnabled());
		
		//Verify background color is Red - #C92127
		String loginButtonBackgroundColour = Color.fromString(driver.findElement(buttonLogin).getCssValue("background-color")).asHex().toUpperCase();
		
		Assert.assertEquals(loginButtonBackgroundColour, "#C92127");
		//rgb(201, 33, 39) - rgba(201, 33, 39, 0.03)
		
		
	}

	@Test
	public void TC_02_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		List<WebElement> allCheckbox = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));
		
		//Select all checkbox
		for (WebElement CheckboxItem : allCheckbox) {
			if(!CheckboxItem.isSelected()) {
				CheckboxItem.click();	
			}
			Assert.assertTrue(CheckboxItem.isSelected());
			System.out.println(CheckboxItem.getAttribute("value") + "is selected");		
		}
		
		
		//Deselect all checkbox
		for (WebElement CheckboxItem : allCheckbox) {
			if(CheckboxItem.isSelected()) {
			CheckboxItem.click();
			}
			Assert.assertFalse(CheckboxItem.isSelected());
			System.out.println(CheckboxItem.getAttribute("value") + "is deselected");
			
		}
		
	
		sleepSecond(1);
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
