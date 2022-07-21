package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Textbox_TextArea_Exercise_01 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String emailAddress, LoginUrl, UserID, Password, CustomerName, Gender, DOB_input, DOB_output;
	String Address_input, Address_output, City, State, pinNumber, phoneNumber, CustomerID ;
	String new_city, new_state, new_pin, new_phoneNumber, new_email;

	public void SleepSecond(long TimeSleep) {
		try {
			Thread.sleep(TimeSleep*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//Khoi tao du lieu
		//input Email
		emailAddress = "KC" + generateRandomNumber() + "@gmail.com";
		CustomerName = "Khanh Cuong";
		Gender = "male";
		DOB_input = "04/25/1990";
		DOB_output = "1990-04-25";
		Address_input = "123 PO Box\n Los Angeles\n United State";
		Address_output = "123 PO Box Los Angeles United State";
		City = "Hawai";
		State = "Los Angeles";
		pinNumber = "126589";
		phoneNumber = "0987654321";
		new_city = "Washiton DC";
		new_state = "DC";
		new_pin = "654322";
		new_phoneNumber = "0987321654";
		new_email = "Sess" + generateRandomNumber() + "@gmail.com";
		
		// Access to page: https://demo.guru99.com/v4/
		driver.get("https://demo.guru99.com/v4/");
		LoginUrl = driver.getCurrentUrl();
		
		
	}

	@Test
	public void TC_01_Register() {
		//Click link "here" to register
		driver.findElement(By.xpath("//a[text()='here']")).click();
		

		driver.findElement(By.cssSelector("input[name='emailid']")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		
		
		// Get username + password
		UserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Password :']/parent::tr/following-sibling::tr//h3")).getText(), "This access is valid only for 20 days.");
						
	}

	@Test
	public void TC_02_Login() {
		//back to page: https://demo.guru99.com/v4/
		driver.get(LoginUrl);
		//Login 
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();
		
		//Verify
		Assert.assertTrue(driver.getPageSource().contains(UserID));
		

	}

	@Test
	public void TC_03__Create_New_Customer() {
		//click on New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//Input form
		driver.findElement(By.name("name")).sendKeys(CustomerName);
		driver.findElement(By.cssSelector("input[value='m']")).click();
		//Remove thuoc tinh cua DOB roi moi input
		WebElement dateOfBirthTextbox = driver.findElement(By.name("dob"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", dateOfBirthTextbox);
		SleepSecond(2);
		dateOfBirthTextbox.sendKeys(DOB_input);
		driver.findElement(By.name("addr")).sendKeys(Address_input);
		driver.findElement(By.name("city")).sendKeys(City);
		driver.findElement(By.name("state")).sendKeys(State);
		driver.findElement(By.name("pinno")).sendKeys(pinNumber);
		driver.findElement(By.name("telephoneno")).sendKeys(phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("password")).sendKeys(Password);
		SleepSecond(2);
		driver.findElement(By.name("sub")).click();
		
		SleepSecond(2);
		// Verify
		Assert.assertEquals(driver.findElement(By.className("heading3")).getText(),"Customer Registered Successfully!!!");		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DOB_output);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address_output);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phoneNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), emailAddress);
				
	}

	//@Test
	public void TC_04_Edit_Customer() {
		//Get Customer ID
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		//press Edit Customer button
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		SleepSecond(1);
		
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		//Verify Customer Name and Address
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address_output);
		
		//Input new info
		driver.findElement(By.name("city")).sendKeys(new_city);
		driver.findElement(By.name("state")).sendKeys(new_state);
		driver.findElement(By.name("pinno")).sendKeys(new_pin);
		driver.findElement(By.name("telephoneno")).sendKeys(new_phoneNumber);
		driver.findElement(By.name("emailid")).sendKeys(new_email);
		driver.findElement(By.name("sub")).click();
		SleepSecond(1);
		
		//Verify
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
