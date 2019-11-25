package helper;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
public WebDriver driver;
	@Parameters({"Port"})
	@BeforeClass
	public void StartBrowser(String Port) throws MalformedURLException{			
		/*WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();			
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");*/
		
		if(Port.equalsIgnoreCase("27621")) {
			ChromeOptions options = new ChromeOptions();		
			String hubUrl="http://localhost:4444/wd/hub";
			this.driver = new RemoteWebDriver(new URL(hubUrl), options);
			driver.manage().window().maximize();
			driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		}
		else {
			FirefoxOptions options = new FirefoxOptions();		
			String hubUrl="http://localhost:4444/wd/hub";
			this.driver = new RemoteWebDriver(new URL(hubUrl), options);
			driver.manage().window().maximize();
			driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		}	
		
	  }
	
	@AfterClass
    	public void quitbrowser(){
		driver.quit();
	  }

}
