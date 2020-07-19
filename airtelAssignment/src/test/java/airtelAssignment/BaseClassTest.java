package airtelAssignment;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClassTest {  

	static WebDriver driver;
	static Logger log = Logger.getLogger(BaseClassTest.class);
	static String appUrl="http://automationpractice.com/";


	@BeforeMethod
	public void setUp() {
        PropertyConfigurator.configure(".//log4j.properties");

		log.info("Opening chome browser");
		driver = new ChromeDriver();
		log.info("Navigating to application Url "+appUrl);
		driver.get(appUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void close()
	{
		log.info("Closing the browser");
		driver.quit();
	}
	
}
