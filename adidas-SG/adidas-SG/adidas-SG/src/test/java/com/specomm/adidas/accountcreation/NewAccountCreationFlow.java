package com.specomm.adidas.accountcreation;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.GeneralActions;
import com.specomm.adidas.common.utils.ReusableActions;
import com.specomm.adidas.pagecomponents.NewAccountCreationPage;

public class NewAccountCreationFlow extends GeneralActions
{
	static WebDriver driver;
	NewAccountCreationPage newAccountCreationPage;
	Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	GeneralActions genAction = new GeneralActions();
	
	/*@BeforeClass
	public void setup() throws IOException
	{
		driver=getDriver();
		String str_brower;    //changed from framework
		String str_url;
		
		str_brower=genAction.GetValue_Configfile("browser"); //changed from framework
		str_url=genAction.GetValue_Configfile("url"); //changed from framework
		driver=launchBrowser(driver,str_brower);
		newAccountCreationPage=PageFactory.initElements(driver,NewAccountCreationPage.class);
		ReusableActions.openUrl(driver, str_url) ;//changed from framework
	}*/
	
}
