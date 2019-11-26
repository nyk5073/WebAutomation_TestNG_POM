package helper;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
public class Base {
public WebDriver driver;
	//uncomment the following section if you want to run it in local machine's Google Chrome browser
	/*@BeforeClass
	public void StartBrowser(){			
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");	
	  }*/
	//uncomment the following section if you want to run it in local machine's Mozilla Firefox browser
	/*@BeforeClass
	public void StartBrowser(){			
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");	
	  }*/
	//uncomment the following section if you want to run it in Grid
		//@Parameters({"Port"})
		@Parameters({"os", "Port"})
		@BeforeClass
		public void StartBrowser(String os,String Port) throws MalformedURLException, InterruptedException{	
			Platform platform = Platform.fromString(os.toUpperCase());
			if(Port.equalsIgnoreCase("27532")) {
				ChromeOptions options = new ChromeOptions();
				options.setCapability("platform", platform);
				String hubUrl="http://localhost:4444/wd/hub";
				this.driver = new RemoteWebDriver(new URL(hubUrl), options);
				driver.manage().window().maximize();
				driver.get("https://opensource-demo.orangehrmlive.com");
				Thread.sleep(5000);
			}
			else {
				FirefoxOptions options = new FirefoxOptions();
				options.setCapability("platform", platform);
				String hubUrl="http://localhost:4444/wd/hub";
				this.driver = new RemoteWebDriver(new URL(hubUrl), options);
				driver.manage().window().maximize();
				driver.get("https://opensource-demo.orangehrmlive.com");
				Thread.sleep(5000);
			}		
		  }
	@AfterClass
    	public void quitbrowser(){
		driver.quit();
	  }
}