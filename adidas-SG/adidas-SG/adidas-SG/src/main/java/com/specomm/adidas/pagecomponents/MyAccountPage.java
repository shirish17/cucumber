package com.specomm.adidas.pagecomponents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.ReusableActions;

import junit.framework.Assert;

public class MyAccountPage 
{
	static WebDriver driver;
	String Title,Value;
	static ReusableActions inAction = new ReusableActions();
	Constants constants=new Constants();
	public Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	public MyAccountPage(WebDriver driver)
	{ 
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	} 
	
	@FindBy(how = How.CSS, using = "font > strong")
	public static WebElement click_Logout ;
	
	@FindBy(how=How.CLASS_NAME, using="li-h-logut-link")
	public static WebElement linkusername;
	//html/body/div[1]/div/div[1]/div[1]/div[1]/ul/li[4]/a
	
	@FindBy(how=How.XPATH, using = "html/body/div[1]/div/div[3]/div/div[2]/div[2]/div/div[3]/p[1]/strong")
	public static WebElement welcome_msg ;
	
	public static void clickLogout(){
		try{
			inAction.linkClick(driver, linkusername, "Click on user name link");
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("My Account page is not loaded", driver.getTitle().equalsIgnoreCase("My Account"));
		inAction.linkClick(driver, click_Logout, "Click link : click_Logout");
		inAction.waitForpageToLoad(driver);
		Assert.assertTrue("Logout success page is not loaded after logout.", driver.getTitle().contains("Online Store"));
		
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
	}

	public void Verify_LoginSuccess(String customername) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		inAction.waitForpageToLoad(driver);
		//Thread.sleep(7000);
		System.out.println("Customer welcome message " + welcome_msg.getText());  //implementation pending
		if(welcome_msg.getText().contains(customername))
		{
			System.out.println("Login Success.");
		}
		else
		{
			System.out.println("Login is not Success.");
		}
		
		Assert.assertEquals(constants.MY_ACCOUNT_PAGE,driver.getTitle());
		
	}
	
}
