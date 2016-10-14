package com.specomm.adidas.pagecomponents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.testng.Assert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.GeneralActions;
import com.specomm.adidas.common.utils.ReusableActions;

import junit.framework.Assert;

public class AdidasLoginPage
{
	WebDriver driver;
	String Title,Value;
	ReusableActions inAction = new ReusableActions();
	GeneralActions genAction = new GeneralActions();
	Constants constants=new Constants();
	public Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	
	public AdidasLoginPage(WebDriver driver)
	{ 
	    this.driver = driver; 
	} 
	@FindBy(how = How.ID, using = "email")
	 public WebElement txtbx_UserName;

	@FindBy(how = How.ID, using = "pass")
	 public WebElement txtbx_Password;

	@FindBy(how = How.ID, using = "send2")
	 public WebElement btn_Login ;
	
	@FindBy(how = How.XPATH, using ="html/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li/span")
	public WebElement invalidErrMsg ;	
	
	public void logIn_Action(String sUserName, String sPassword) throws InterruptedException 
	{
		Assert.assertEquals(constants.CUSTOMER_HOME_PAGE_TITLE, driver.getTitle());
		enterUserName(sUserName);
		enterPassword(sPassword);
        Thread.sleep(2000);
        clickLoginButton();
		
	}
	

	// Enter Username
		public void enterUserName(String sUsername) {
			try{
			
			ReusableActions.isElementVisible(txtbx_UserName , "Verify if UserName field exists?");
			inAction.inputText(driver, txtbx_UserName,sUsername , "Enter username : " + sUsername);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
		
		//Enter Password
		public void enterPassword(String sPassword) {
			try{
				
			
			ReusableActions.isElementVisible(txtbx_UserName , "Verify if Password field exists");
			inAction.inputText(driver, txtbx_Password, sPassword, "Enter password : " + sPassword);
	    }
		catch(Exception e){
			e.printStackTrace();
			
		}
		}
		//Click Login button
		public void clickLoginButton() {
			try{
			inAction.buttonClick(driver, btn_Login, "Click button : btn_Login");
	    }
			catch(Exception e){
				
				e.printStackTrace();
			}
			
		}


		public void verifyInvalidPassword() {
			try{
				
				
				System.out.println(invalidErrMsg.getText());
				Assert.assertEquals(constants.INVALID_USERNAME_PASSWORD, invalidErrMsg.getText());    
					
	    }
		
		catch(Throwable t)
		{
			t.printStackTrace();
		}
	}
	}
