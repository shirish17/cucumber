package com.specomm.adidas.pagecomponents;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import junit.framework.Assert;

import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.GeneralActions;
import com.specomm.adidas.common.utils.ReusableActions;


public class NewAccountCreationPage 
{
	WebDriver driver;
	public Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	Constants constants=new Constants();
	ReusableActions inAction = new ReusableActions();
	GeneralActions genAction = new GeneralActions();
	public NewAccountCreationPage(WebDriver driver)
	{
		this.driver = driver; 
	    PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "#firstname")
	public WebElement txtbx_Firstname;
	
	//@FindBy(how = How.CSS, using = "#lastname")
	@FindBy(how = How.ID, using = "lastname")	
	public WebElement txtbx_Lastname;
	
	@FindBy(how = How.CSS, using = "#day")
	public WebElement list_Day;
	
	@FindBy(how = How.CSS, using = "#month")
	 public WebElement list_Month;
	
	@FindBy(how = How.CSS, using = "#year")
	 public WebElement list_Year;
	
	@FindBy(how = How.XPATH, using = "(//input[@id='email_address'])[2]")
	 public WebElement txtbx_EmailAddress;
	
	@FindBy(how = How.CSS, using = "#password")
	 public WebElement txtbx_Password;
	
	@FindBy(how = How.CSS, using = "#confirmation")
	 public WebElement txtbx_ConfirmPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@id='dwfrm_profile_customer_agreeterms']")
	 public WebElement chkbx_Agreement;
	
	@FindBy(how = How.XPATH, using = "//*[@id='advice-required-entry-dwfrm_profile_customer_agreeterms']")
	 public WebElement req_Agreement;
	
	@FindBy(how = How.CSS, using = "button.button")
	public WebElement	btn_Register;	
	
	@FindBy(how = How.XPATH, using = "html/body/div[1]/div/div[2]/div/div/div[1]/ul/li/ul/li/span")
	public WebElement alreadyExists;
	
	@FindBy(how = How.XPATH, using = "//*[@id='header']/div[1]/div[1]/div[1]/a/img")
	public WebElement img;
	
	
	public void enterNewAccountDetails(String rFirstname,String rLastname,String dDay,String dMonth,String dYear,String rEaddress,String rPassword,String rcPassword)
	{
		// TODO Auto-generated method stub
		try
		{
			Assert.assertEquals(driver.getTitle(), constants.REGISTER_NEW_USER_PAGE);
			ReusableActions.waitForpageToLoad(driver);
			enterFirstName(rFirstname);			
			enterLastName(rLastname);
			ReusableActions.selectItemFromDropdown(driver, list_Day, dDay, "Enter day :"+dDay);
			ReusableActions.selectItemFromDropdown(driver, list_Month, dMonth, "Enter month :"+dMonth);
			ReusableActions.selectItemFromDropdown(driver, list_Year, dYear, "Enter year :"+dYear);
			enterEmailAddress(rEaddress);
			inAction.buttonClick(driver, img, "Click the flag image");
			inAction.buttonClick(driver, img, "Click the flag image");
			enterPassword(rPassword);
			enterConfirmPassword(rcPassword);
			Thread.sleep(3000);
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0,450)", "");
			inAction.selectCheckbox(driver, chkbx_Agreement, "check the terms and condition checkbox");
			clickRegisterButton();
			try{
				String agr=driver.findElement(By.id("advice-required-entry-dwfrm_profile_customer_agreeterms")).getText();
				System.out.println(agr);
				if(agr.contains("This is a required field"))
				{
					Thread.sleep(3000);
				inAction.selectCheckbox(driver, chkbx_Agreement, "check the terms and condition checkbox");
					
				clickRegisterButton();
			}
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			if(ReusableActions.displayElement(driver,alreadyExists))
			{
				enterEmailAddress(ReusableActions.getCurrentTimeStamp()+rEaddress);
				enterPassword(rPassword);
				inAction.buttonClick(driver, img, "Click the flag image");
				inAction.buttonClick(driver, img, "Click the flag image");
				enterConfirmPassword(rcPassword);
				Thread.sleep(3000);
				jsx = (JavascriptExecutor)driver;
				jsx.executeScript("window.scrollBy(0,450)", "");
				inAction.selectCheckbox(driver, chkbx_Agreement, "check the terms and condition checkbox");
				clickRegisterButton();
				try{
					
					if(req_Agreement.getText().contains("This is a required field")){
						Thread.sleep(3000);
					inAction.selectCheckbox(driver, chkbx_Agreement, "check the terms and condition checkbox");
						
					clickRegisterButton();
				}
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	//Click Register button
	public void clickRegisterButton()
	{
		try
		{
			inAction.buttonClick(driver, btn_Register, "Click button : btn_Login");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

	//Enter Password
	public void enterConfirmPassword(String rcPassword)
	{
		try
		{
			ReusableActions.isElementVisible(txtbx_ConfirmPassword , "Verify if Password field exists");
			inAction.inputText(driver, txtbx_ConfirmPassword, rcPassword, "Enter confrim password : " + rcPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

	//Enter Password
	public void enterPassword(String rPassword) 
	{
		try
		{
			ReusableActions.isElementVisible(txtbx_Password , "Verify if Password field exists");
			inAction.inputText(driver, txtbx_Password, rPassword, "Enter password : " + rPassword);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
	}

	private void enterEmailAddress(String rEaddress)
	{
		try
		{
			ReusableActions.isElementVisible(txtbx_EmailAddress , "Verify if EmailAddress field exists");
			inAction.inputText(driver, txtbx_EmailAddress, rEaddress, "Enter password : " + rEaddress);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}

	private void enterFirstName(String rFirstname) 
	{
		// TODO Auto-generated method stub
		try
		{
			ReusableActions.isElementVisible(txtbx_Firstname , "Verify if FirstName field exists");
			inAction.inputText(driver, txtbx_Firstname,rFirstname , "Enter Firstname : " + rFirstname);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	// Enter Lastname
	public void enterLastName(String rLastName)
	{
		try
		{
			ReusableActions.isElementVisible(txtbx_Lastname , "Verify if LastName field exists");
			inAction.inputText(driver, txtbx_Lastname,rLastName , "Enter Firstname : " + rLastName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
