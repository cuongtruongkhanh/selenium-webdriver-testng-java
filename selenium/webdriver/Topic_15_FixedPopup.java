package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_FixedPopup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor JsExecutor;
	
	
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
		JsExecutor = (JavascriptExecutor) driver;
		
	}

//	@Test
	public void TC_01_() {
		driver.get("https://ngoaingu24h.vn/");
		
		WebElement LoginButton = driver.findElement(By.cssSelector("div#button-login-dialog>button:nth-child(1)"));
		WebElement LoginPopup = driver.findElement(By.xpath("//body//div[@id='modal-login-v1'][1]"));
		WebElement LoginInput = driver.findElement(By.xpath("//body//div[@id='modal-login-v1'][1]//input[@id='account-input']"));
		WebElement LoginPW = driver.findElement(By.xpath("//body//div[@id='modal-login-v1'][1]//input[@id='password-input']"));
		WebElement ConfirmLogin = driver.findElement(By.xpath("//body//div[@id='modal-login-v1'][1]//button[@class='btn-v1 btn-login-v1 buttonLoading']"));
		WebElement ErrorMessage = driver.findElement(By.xpath("//body//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']"));
		
		//Verify LoginPopup not display
		Assert.assertFalse(LoginPopup.isDisplayed());
		
		
		LoginButton.click();
		sleepSecond(1);
		Assert.assertTrue(LoginPopup.isDisplayed());
		
		LoginInput.sendKeys("admin");
		LoginPW.sendKeys("admin");
		ConfirmLogin.click();
		Assert.assertEquals(ErrorMessage.getText(), "Mật khẩu sai!");
		sleepSecond(1);
	
		LoginInput.sendKeys("automationfc");
		LoginPW.sendKeys("automationfc");
		ConfirmLogin.click();
		Assert.assertEquals(ErrorMessage.getText(), "Tài khoản không tồn tại!");
		sleepSecond(1);
	}

//	@Test
	public void TC_02_Random_Fixed_Popup() {
		driver.get("https://www.kmplayer.com");
		
		WebElement welcomePopup = driver.findElement(By.cssSelector("div.pop-layer"));
		
		if(welcomePopup.isDisplayed()) {
			//close popup
			JsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			System.out.println("Popup is closed");
		}
		
		System.out.println("Doing sth else");
	}

	@Test
	public void TC_03_Random_Not_in_DOM() {
		driver.get("https://dehieu.vn/");
		sleepSecond(2);
		
		List<WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));
		
		if(contentPopup.size()>0) {
			WebElement popupName = driver.findElement(By.id("popup-name"));
			WebElement popupEmail = driver.findElement(By.id("popup-email"));
			WebElement popupPhone = driver.findElement(By.id("popup-phone"));
			
			popupName.sendKeys("Khanh Cuong");
			popupEmail.sendKeys("KC@gmail.com");
			popupPhone.sendKeys("0987654321");
			sleepSecond(3);
			
			driver.findElement(By.cssSelector("div.popup-content>button#close-popup")).click();
			
			
			
		}
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepSecond(2);
		
		//verify 
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/dang-nhap");
		
	}

//	@Test
	public void TC_04_() {
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
