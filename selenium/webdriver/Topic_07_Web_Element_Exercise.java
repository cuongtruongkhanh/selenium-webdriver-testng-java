package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Web_Element_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	public void sleepSecond(long TimeSleep) {
		try {
			Thread.sleep(TimeSleep * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Displayed() {
		// truy cap vao trang web: https://automationfc.github.io/basic-form/index.html
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepSecond(1);
		// kiem tra phan tu tren trang hien thi
		// Email - div.container>label[for='mail']
		WebElement EmailTextBox = driver.findElement(By.cssSelector("#mail"));

		if (EmailTextBox.isDisplayed()) {
			EmailTextBox.sendKeys("Automation Test");
			System.out.println("Email Textbox is displayed");
		} else {
			System.out.println("Email Textbox is NOT displayed");
		}

		// Age under 18
		WebElement AgeUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (AgeUnder18Radio.isDisplayed()) {
			AgeUnder18Radio.click();
			System.out.println("Age under 18 Radio is displayed");
		} else {
			System.out.println("Age under 18 Radio is NOT displayed");
		}

		// Education
		// kiem tra phan tu tren trang k hien thi
		WebElement educationTextArea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Test");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is NOT displayed");
		}

		// kiem tra User 5 k hien thi
		WebElement name5text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (name5text.isDisplayed()) {

			System.out.println("Name user 5 is displayed");
		} else {
			System.out.println("Name user 5 is NOT displayed");
		}

	}

	@Test
	public void TC_02_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepSecond(1);

		// kiem tra Email enable
		WebElement EmailTextBox = driver.findElement(By.cssSelector("#mail"));

		if (EmailTextBox.isEnabled()) {
			System.out.println("Email Textbox is enable");
		} else {
			System.out.println("Email Textbox is disble");
		}

		// Age under 18
		WebElement AgeUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		if (AgeUnder18Radio.isEnabled()) {
			System.out.println("Age under 18 Radio is enable");
		} else {
			System.out.println("Age under 18 Radio is disable");
		}

		// Education
		WebElement educationTextArea = driver.findElement(By.cssSelector("#edu"));
		if (educationTextArea.isEnabled()) {
			System.out.println("Education is enable");
		} else {
			System.out.println("Education is disable");
		}

		// job role 1/job role 2
		WebElement dropdownJob1 = driver.findElement(By.cssSelector("select#job1"));
		if (dropdownJob1.isEnabled()) {
			System.out.println("Drop down job 1 is enable");
		} else {
			System.out.println("Drop down job 1 is disable");
		}

		WebElement dropdownJob2 = driver.findElement(By.cssSelector("select#job2"));
		if (dropdownJob2.isEnabled()) {
			System.out.println("Drop down job 2 is enable");
		} else {
			System.out.println("Drop down job 2 is disable");
		}

		// development check box - input#development
		WebElement DevelopmentCheckbox = driver.findElement(By.cssSelector("input#development"));
		if (DevelopmentCheckbox.isEnabled()) {
			System.out.println("development check box is enable");
		} else {
			System.out.println("development check box is disable");
		}

		// slider 01 check enable
		WebElement Slider01 = driver.findElement(By.cssSelector("#slider-1"));
		if (Slider01.isEnabled()) {
			System.out.println("Slider 01 is enable");
		} else {
			System.out.println("Slider 01 job 1 is disable");
		}

		// password check disable - #disable_password
		WebElement PasswordDisable = driver.findElement(By.cssSelector("#disable_password"));
		if (PasswordDisable.isEnabled()) {
			System.out.println("PW is enable");
		} else {
			System.out.println("PW job 1 is disable");
		}

		// check age Radio button - #radio-disabled
		WebElement RadiobuttonDisable = driver.findElement(By.cssSelector("#radio-disabled"));
		if (RadiobuttonDisable.isEnabled()) {
			System.out.println("Age Radio button is enable");
		} else {
			System.out.println("Age Radio button is disable");
		}

		// check biography - #bio
		WebElement BiographyTextArea = driver.findElement(By.cssSelector("#bio"));
		if (BiographyTextArea.isEnabled()) {
			System.out.println("Bio Graphy is enable");
		} else {
			System.out.println("Bio Graphy is disable");
		}

		// Job role 3
		WebElement dropdownJob3 = driver.findElement(By.cssSelector("#job3"));
		if (dropdownJob3.isEnabled()) {
			System.out.println("Drop down job 3 is enable");
		} else {
			System.out.println("Drop down job 3 is disable");
		}

		// Interest checkbox is disable
		WebElement InterestCheckbox = driver.findElement(By.cssSelector("#check-disbaled"));
		if (InterestCheckbox.isEnabled()) {
			System.out.println("Interest checkbox is enable");
		} else {
			System.out.println("Interest checkbox is disable");
		}

		// slider 01 check enable
		WebElement Slider02 = driver.findElement(By.cssSelector("#slider-2"));
		if (Slider02.isEnabled()) {
			System.out.println("Slider 01 is enable");
		} else {
			System.out.println("Slider 01 job 1 is disable");
		}

	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepSecond(1);

		// Age under 18
		WebElement AgeUnder18Radio = driver.findElement(By.cssSelector("#under_18"));
		WebElement LanguageJava = driver.findElement(By.cssSelector("#java"));

		AgeUnder18Radio.click();
		LanguageJava.click();
		// check Age under 18 selected
		if (AgeUnder18Radio.isSelected()) {
			System.out.println("Age under 18 is selected");
		} else {
			System.out.println("Age under 18 is NOT selected");
		}

		// check Language Java is selected
		if (LanguageJava.isSelected()) {
			System.out.println("Language Java is selected");
		} else {
			System.out.println("Language Java is NOT selected");
		}

		// unselected java
		LanguageJava.click();

		// check Language Java is unselected
		if (LanguageJava.isSelected()) {
			System.out.println("Language Java is selected");
		} else {
			System.out.println("Language Java is NOT selected");
		}

	}

	@Test
	public void TC_04_Mailchimp() {
		driver.get("https://login.mailchimp.com/signup/");
		sleepSecond(1);

		// Nhap Email
		driver.findElement(By.id("email")).sendKeys("khanhcuong@gmail.com");
		driver.findElement(By.id("new_username")).click();

		// Nhap PW
		// Nhap 1 number
		WebElement NewPassword = driver.findElement(By.cssSelector("#new_password"));
		WebElement ConfirmCheckBox = driver.findElement(By.cssSelector(".dijit"));

		NewPassword.sendKeys("1234");
		ConfirmCheckBox.click();

		WebElement Check1number = driver.findElement(By.cssSelector(".number-char.completed"));

		if (Check1number.isDisplayed()) {
			System.out.println("1 number is Displayed");
		} else {
			System.out.println("1 number is NOT Displayed");
		}
		// Clear PW
		NewPassword.clear();
		ConfirmCheckBox.click();

		// Nhap lower case
		NewPassword.sendKeys("cuong");
		ConfirmCheckBox.click();

		WebElement CheckLowerCase = driver.findElement(By.cssSelector(".lowercase-char.completed"));

		if (CheckLowerCase.isDisplayed()) {
			System.out.println("1 Lower case is Displayed");
		} else {
			System.out.println("1 Lower case is NOT Displayed");
		}

		NewPassword.clear();
		ConfirmCheckBox.click();

		// Nhap Upper case
		NewPassword.sendKeys("CUONG");
		ConfirmCheckBox.click();

		WebElement CheckUpperCase = driver.findElement(By.cssSelector(".uppercase-char.completed"));

		if (CheckUpperCase.isDisplayed()) {
			System.out.println("1 Upper case is Displayed");
		} else {
			System.out.println("1 Upper case is NOT Displayed");
		}

		NewPassword.clear();
		ConfirmCheckBox.click();

		// Nhap Special Character
		NewPassword.sendKeys("!@#");
		ConfirmCheckBox.click();

		WebElement SpecialCase = driver.findElement(By.cssSelector(".special-char.completed"));

		if (SpecialCase.isDisplayed()) {
			System.out.println("SpecialCase is Displayed");
		} else {
			System.out.println("SpecialCase is NOT Displayed");
		}

		NewPassword.clear();
		ConfirmCheckBox.click();

		// More than 8 char
		NewPassword.sendKeys("123456789");
		ConfirmCheckBox.click();

		WebElement MoreThan8 = driver.findElement(By.xpath("//li[@class='8-char completed']"));

		if (MoreThan8.isDisplayed()) {
			System.out.println("MoreThan8 char is Displayed");
		} else {
			System.out.println("MoreThan8 char is NOT Displayed");
		}

		NewPassword.clear();
		ConfirmCheckBox.click();
		
		// Confirmation - //h4[text()="Your password is secure and you're all set!"]
		NewPassword.sendKeys("Cuong123@");
		ConfirmCheckBox.click();
		
		WebElement CorrectInput = driver.findElement(By.xpath("//h4[text()=\"Your password is secure and you're all set!\"]"));
		
		if (CorrectInput.isDisplayed()) {
			System.out.println("User input correctly");
		} else {
			System.out.println("User input incorrectly");
		}

		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
