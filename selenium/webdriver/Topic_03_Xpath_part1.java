package webdriver;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Register_With_Empty_Data() {
		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify result
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_with_Invalid_Email() {
		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("123456");
		driver.findElement(By.id("txtCEmail")).sendKeys("123456");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify invalid Email
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");

	}

	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("123456");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify invalid Email confirm
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}

	@Test
	public void TC_04_PW_less_than_6_characters() {

		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("12345");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		// Verify Password
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),
				"Mật khẩu phải có ít nhất 6 ký tự");

	}

	@Test
	public void TC_05_Incorrect_Confirm_PW() {
		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567");
		driver.findElement(By.id("txtPhone")).sendKeys("0987654321");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();

		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");

	}

	@Test
	public void TC_06_Incorrect_TextPhone() {
		// Access to registration
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// Fill up information into Registration form
		driver.findElement(By.id("txtFirstname")).sendKeys("Khanh Cuong");
		driver.findElement(By.id("txtEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("1234@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		driver.findElement(By.id("txtCPassword")).sendKeys("123456");

		// less than 10 char
		driver.findElement(By.id("txtPhone")).sendKeys("098765432");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");

		// More than 12 char
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("098765432101234");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		// Wrong format
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("12345");
		driver.findElement(By.xpath("//div[@class='field_btn']/button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
