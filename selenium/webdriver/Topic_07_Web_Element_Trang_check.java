package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Trang_check {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextBox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextBox.isDisplayed()) {
			emailTextBox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
			
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));	
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 radio is displayed");
		} else {
			System.out.println("Age under 18 radio is not displayed");
			}
		
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));	
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textbox is displayed");
		} else {
			System.out.println("Education textbox is not displayed");
			}
		
		WebElement name5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));	
		if (name5Text.isDisplayed()) {
	
			System.out.println("Name 5 text is displayed");
		} else {
			System.out.println("Name 5 text is not displayed");
			}
		}

//	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextBox = driver.findElement(By.cssSelector("#mail"));
		if (emailTextBox.isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is disabled");	
	}
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is disabled");	
	}
		
		WebElement educationTextarea = driver.findElement(By.cssSelector("#edu"));	
		if (educationTextarea.isEnabled()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textbox is enable");
		} else {
			System.out.println("Education textbox is displayed");
			}
		
		WebElement jobRole01 = driver.findElement(By.cssSelector("#job1"));	
		
		if (jobRole01.isEnabled()) {
		
			System.out.println("Job Role 01 is enable");
		} else {
			System.out.println("Job Role 01 is displayed");
			}
		
		WebElement jobRole02 = driver.findElement(By.cssSelector("#job2"));	
		
		if (jobRole02.isEnabled()) {
		
			System.out.println("Job Role 02 is enable");
		} else {
			System.out.println("Job Role 02 is displayed");
			}
		
		WebElement checkboxDevelopment = driver.findElement(By.cssSelector("#development"));	
		
		if (checkboxDevelopment.isEnabled()) {
		
			System.out.println("Development is enable");
		} else {
			System.out.println("Development is displayed");
			}
		
		WebElement slider1 = driver.findElement(By.cssSelector("#slider-1"));	
		
		if (slider1.isEnabled()) {
		
			System.out.println("Slider 01 is enable");
		} else {
			System.out.println("Slider 01 is displayed");
			}
		
	}

// @Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		ageUnder18Radio.click();
		
		WebElement javaCheckbox = driver.findElement(By.cssSelector("#java"));
		javaCheckbox.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 is selected");
		} else {
			System.out.println("Age Under 18 is deselected");

		}
		
		if (javaCheckbox.isSelected()) {
			System.out.println("Java 18 is selected");
		} else {
			System.out.println("Java 18 is deselected");
		}
			
			ageUnder18Radio.click();
			javaCheckbox.click();
			
			Assert.assertTrue(ageUnder18Radio.isSelected());
			Assert.assertFalse(javaCheckbox.isSelected());
			
		if (ageUnder18Radio.isSelected()) {
				System.out.println("Age Under 18 is selected");
		} else {
				System.out.println("Age Under 18 is deselected");

			}
			
		if (javaCheckbox.isSelected()) {
				System.out.println("Java 18 is selected");
		} else {
				System.out.println("Java 18 is deselected");
			}
			
		}
	 
	
	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepInSecond(5);
		
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("automation");
		WebElement passwordTextbox = driver.findElement(By.id("new_password"));
		

		//Lowercase
		passwordTextbox.sendKeys("auto");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		
		//Uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AUTO");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());

		
		//Number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		
		//Special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("@#$");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		
		//8 char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345678");
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		//Combine
		passwordTextbox.clear();
		passwordTextbox.sendKeys("Auto123!@#");
		sleepInSecond(1);
		Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}