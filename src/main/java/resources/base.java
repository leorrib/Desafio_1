package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class base {

public WebDriver driver;
public Properties prop;
String userDir = System.getProperty("user.dir");

public WebDriver initializeDriver() throws IOException
{
	prop = new Properties();
	FileInputStream fis = new FileInputStream(userDir+"\\src\\main\\java\\resources\\data.properties");
	
	prop.load(fis);
	
	//String browserName = System.getProperty("browser");
	String browserName = prop.getProperty("browser");
	
	
if(browserName.contains("chrome"))//.contains could also be replaced by .equalTo
{
	System.setProperty("webdriver.chrome.driver", userDir+"\\drivers\\chromedriver.exe");
	HashMap<String, Object> prefs = new HashMap<String, Object>();
	prefs.put("profile.default_content_setting_values.geolocation", 2);
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", prefs);
	
	if(browserName.contains("headless"))
	{	
		options.addArguments("headless","user-agent=mrbean");
	}
	
	driver = new ChromeDriver(options);
}
else if(browserName.contains("firefox"))
{
System.setProperty("webdriver.gecko.driver", userDir+"\\drivers\\geckodriver.exe");	
driver=new FirefoxDriver();
}
else if(browserName.contains("edge"))
{
System.setProperty("webdriver.edge.driver", userDir+"\\drivers\\msedgedriver.exe");	
driver=new EdgeDriver();
}
else
{
System.out.println("Invalid browser name"); 
}

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
return driver;


}

	
}
