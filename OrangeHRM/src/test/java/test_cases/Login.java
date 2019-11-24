package test_cases;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import helper.Base;
import pages_object_repository.Dashboardpage;
import pages_object_repository.Landingpage;

public class Login extends Base{
	
	Landingpage landing;
	Dashboardpage dashboard;
	
	String CSV_file=System.getProperty("user.dir")+"/Resources/OrangeHRM_Creds.csv";
	
	@Test(priority=0)
	public void login() throws InterruptedException, CsvValidationException{
		landing = new Landingpage(driver);
		CSVReader reader = null;
		
		try {
			reader = new CSVReader(new FileReader(CSV_file));
			String[] cell;
			
			while((cell=reader.readNext())!=null) {
				String Username = cell[0];
				String Password = cell[1];
				landing.userLogsIn(Username,Password);
				String currentUrl=driver.getCurrentUrl();
				String expectedUrl="https://opensource-demo.orangehrmlive.com/index.php/dashboard";
				if(currentUrl.equals(expectedUrl)) {
					System.out.println("Login Successful!");
					dashboard = new Dashboardpage(driver);
					dashboard.userLogsOut();
				}
				else {
					String Obs_Error_Msg = driver.findElement(By.id("spanMessage")).getText();
					String Exp_Error_Msg = "Invalid credentials";
					Assert.assertEquals(Obs_Error_Msg, Exp_Error_Msg);
					System.out.println("Login is expected to be unsuccessful!");
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
