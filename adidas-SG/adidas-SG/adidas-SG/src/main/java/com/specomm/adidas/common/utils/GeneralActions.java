package com.specomm.adidas.common.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GeneralActions
{
	public static Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	public static String fsep=System.getProperty("file.separator");
	public static WebDriver driver;
	public static String browserVersion; //changed from framework
	
	public static WebDriver getDriver() 
	{
		return driver;
	}
	public static WebDriver launchBrowser(WebDriver driver, String browserType)
	{
		log4jlogger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log4jlogger.debug("Instantiating the driver :" + browserType);
		
		if(browserType.equalsIgnoreCase(Constants.sBrowserFirefox))
		{
			//log4jlogger.debug("Browser to launch is firefox.."); //changed from framework
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			driver = new FirefoxDriver(profile);				
			driver.manage().window().maximize();
			log4jlogger.debug("launching the "+browserType+" browser");  //changed from framework
		}
		else if (browserType.equalsIgnoreCase(Constants.sBrowserChrome))
		{
			System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+fsep+"src" + fsep+ "test" +fsep + "resources"+ fsep + "chromedriver.exe"));
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			log4jlogger.debug("launching the "+browserType+" browser");
		}
		else if (browserType.equalsIgnoreCase(Constants.sBrowserIe))
		{
			System.setProperty("webdriver.ie.driver", (System.getProperty("user.dir")+fsep+"src"+fsep+"test"+fsep+"resources"+fsep+"IEDriverServer.exe"));
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();			
			log4jlogger.debug("launching the "+browserType+" browser");
		}
		else //changed from framework
		{
			FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			driver = new FirefoxDriver(profile);				
			driver.manage().window().maximize();
			log4jlogger.debug("launching the "+browserType+" browser");  
		}
		browserVersion=getBrowserVersion(driver);	//changed from framework
		log4jlogger.debug("Browser version is "+browserVersion);//changed from framework
		return driver;
		
	}
	
	private static String getBrowserVersion(WebDriver driver)
	{
		String browserVersion= 	((RemoteWebDriver)driver).getCapabilities().getVersion();
		return browserVersion;
	}
	
	public String GetValue_Configfile(String key) throws FileNotFoundException, IOException //changed from framework
	{
		
		String var_values="";
		Properties prop = new Properties();
		prop.load(new FileInputStream(System.getProperty("user.dir")+fsep+"src"+fsep+"config.properties"));
		var_values=prop.getProperty(key);																		
		return var_values;
	}
}
