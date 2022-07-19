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

public class Topic_07_Web_Element_Exercise_02 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

		
	public void SleepSecond(long TimeSleep) {
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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_invalidEmail() {
		//Access to page: http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		
		//click my account button - div.footer-container a[title='My Account']
		WebElement MyAccountButton = driver.findElement(By.cssSelector("div.footer-container a[title='My Account']"));
		MyAccountButton.click();
		SleepSecond(1);
		
		//input invalid email + valid password
		WebElement EmailTextBox = driver.findElement(By.id("email"));
		WebElement PWTextBox = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.id("send2"));
		
		EmailTextBox.sendKeys("123456@123.123");
		PWTextBox.sendKeys("123456");
		SleepSecond(1);
		LoginButton.click();
		
		//Verify error message - advice-validate-email-email : Please enter a valid email address. For example johndoe@domain.com.
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
				
	}

	@Test
	public void TC_02_PassLess6() {
		//Access to page: http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		
		//click my account button - div.footer-container a[title='My Account']
		WebElement MyAccountButton = driver.findElement(By.cssSelector("div.footer-container a[title='My Account']"));
		MyAccountButton.click();
		SleepSecond(1);
		
		//input invalid email + valid password
		WebElement EmailTextBox = driver.findElement(By.id("email"));
		WebElement PWTextBox = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.id("send2"));
		
		EmailTextBox.sendKeys("khanhcuong@gmail.com");
		PWTextBox.sendKeys("12345");
		SleepSecond(1);
		LoginButton.click();
		
		//Verify error message - id: advice-validate-password-pass - Please enter 6 or more characters without leading or trailing spaces.
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}

	@Test
	public void TC_03_InvalidAccount() {
		//Access to page: http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		
		//click my account button - div.footer-container a[title='My Account']
		WebElement MyAccountButton = driver.findElement(By.cssSelector("div.footer-container a[title='My Account']"));
		MyAccountButton.click();
		SleepSecond(1);
		
		//input invalid email + valid password
		WebElement EmailTextBox = driver.findElement(By.id("email"));
		WebElement PWTextBox = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.id("send2"));
		
		EmailTextBox.sendKeys("khanhcuong@gmail.com");
		PWTextBox.sendKeys("123456");
		SleepSecond(1);
		LoginButton.click();
		
		//Verify error message - //span[text()='Invalid login or password.'] - Invalid login or password.
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}

	@Test
	public void TC_04_createNewAccount() {
		//Access to page: http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		
		//click my account button - div.footer-container a[title='My Account']
		WebElement MyAccountButton = driver.findElement(By.cssSelector("div.footer-container a[title='My Account']"));
		MyAccountButton.click();
		SleepSecond(1);
		
		//click on Create an Account button - //a[@title='Create an Account']
		WebElement CreateAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		CreateAccount.click();
		SleepSecond(1);
		
		// Register Account
		WebElement Firstname = driver.findElement(By.id("firstname"));
		WebElement Middlename = driver.findElement(By.id("middlename"));
		WebElement Lastname = driver.findElement(By.id("lastname"));
		WebElement EmailAddress = driver.findElement(By.id("email_address"));
		WebElement Password = driver.findElement(By.id("password"));
		WebElement ConfirmPW = driver.findElement(By.id("confirmation"));
		WebElement SubcribeButton = driver.findElement(By.id("is_subscribed"));
		WebElement RegisterButton = driver.findElement(By.xpath("//span[text()='Register']"));
		
		Firstname.sendKeys("Cuong");
		Middlename.sendKeys("Khanh");
		Lastname.sendKeys("Truong");
		EmailAddress.sendKeys("khanhcuong@1234567.com");
		Password.sendKeys("123456");
		ConfirmPW.sendKeys("123456");
		SubcribeButton.click();
		RegisterButton.click();
		
		SleepSecond(2);
		
		// Verify 
		// SuccessMessage
		WebElement SuccesMess = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"));
		Assert.assertEquals(SuccesMess.getText(), "Thank you for registering with Main Website Store.");
		
		//Box content - //div[@class='box-content']/p[contains(text(),'Cuong')]
		WebElement WelcomeBox = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'Cuong')]"));
		Assert.assertTrue(WelcomeBox.getText().contains("Cuong"));
		Assert.assertTrue(WelcomeBox.getText().contains("Khanh"));
		Assert.assertTrue(WelcomeBox.getText().contains("Truong"));
		Assert.assertTrue(WelcomeBox.getText().contains("khanhcuong@1234567.com"));
		
		//Log out
		driver.findElement(By.xpath("//div[@class = 'account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		SleepSecond(10);
		
		//Verify navigate to home page: http://live.techpanda.org/index.php/customer/account/login/
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/");
		
		
	}
	
	@Test
	public void TC_05_LoginSuccess() {
		//Access to page: http://live.techpanda.org/
		driver.get("http://live.techpanda.org/");
		
		//click my account button - div.footer-container a[title='My Account']
		WebElement MyAccountButton = driver.findElement(By.cssSelector("div.footer-container a[title='My Account']"));
		MyAccountButton.click();
		SleepSecond(1);
		
		WebElement EmailLogin = driver.findElement(By.id("email"));
		WebElement PasswordLogin = driver.findElement(By.id("pass"));
		
		EmailLogin.sendKeys("khanhcuong@1234567.com");
		PasswordLogin.sendKeys("123456");
		
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		SleepSecond(1);
		
		//Verify information
		//Box content - //div[@class='box-content']/p[contains(text(),'Cuong')]
		WebElement WelcomeBox = driver.findElement(By.xpath("//div[@class='box-content']/p[contains(text(),'Cuong')]"));
		Assert.assertTrue(WelcomeBox.getText().contains("Cuong"));
		Assert.assertTrue(WelcomeBox.getText().contains("Khanh"));
		Assert.assertTrue(WelcomeBox.getText().contains("Truong"));
		Assert.assertTrue(WelcomeBox.getText().contains("khanhcuong@1234567.com"));
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
