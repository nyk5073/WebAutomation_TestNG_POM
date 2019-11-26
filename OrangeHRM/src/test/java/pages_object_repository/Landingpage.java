package pages_object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Landingpage {
	
	WebDriver driver;
	
	public Landingpage (WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="txtUsername") static WebElement userName;
	@FindBy(id="txtPassword") static WebElement password;
	@FindBy(id="btnLogin") static WebElement loginBtn;
	
	
	public void userLogsIn(String Username, String Password) {
		userName.sendKeys(Username);
		password.sendKeys(Password);
		loginBtn.click();		
	}
}
