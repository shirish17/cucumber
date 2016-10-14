package com.specomm.adidas.pagecomponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.ReusableActions;
import junit.framework.Assert;

public class AdidasCommonPage
{
	WebDriver driver;
	ReusableActions inAction = new ReusableActions(); //initialized object of reusable action class
	Constants constant = new Constants();
	public AdidasCommonPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//Popup Window: close button
	@FindBy(how = How.CSS , using = "div.home-pop-up > a.close")
	public WebElement btn_popupclose;
	
	//Login link
	@FindBy(how = How.CSS, using = "a.h-login-link")
	public WebElement click_Login;
	
	//cookienoti-text
	@FindBy(how=How.CLASS_NAME, using= "cookienoti-text")
	public WebElement cookiecontent;
		
	//cookie popup close
	@FindBy(how=How.CLASS_NAME, using="close-notification")
	public WebElement btn_cookieclose;
	
	//adidas logo to take homepage
	@FindBy(how = How.XPATH, using ="//*[@id='header']/div[1]/div[1]/div[1]/h1/a/i")
	public WebElement adidaslogo;
	
	//order tracker link
	@FindBy(how=How.CLASS_NAME, using="h-order-traker-link")
	public WebElement ordertracker;
	
	//newsletter link
	@FindBy(how=How.CLASS_NAME, using="h-newsletter-link")
	public WebElement newsletter;
	
	//search box
	@FindBy(how=How.CLASS_NAME, using="form-search")
	public WebElement searchbox;
	
	//search icon
	@FindBy(how=How.ID, using="top-search")
	public WebElement searchicon;
	
	//search content box
	@FindBy(how=How.ID, using="search")
	public WebElement searchcontentbox;
	
	//minicart option
	@FindBy(how=How.ID,using="cartCount")
	public WebElement minicart;
	
	//free shipping text
	@FindBy(how=How.CLASS_NAME,using="freedelivery")
	public WebElement shippingtext;
	
	//Customer service text
	@FindBy(how=How.CLASS_NAME,using="customerservicon")
	public WebElement customerservicetext;
	
	//returns text
	@FindBy(how=How.CLASS_NAME,using="fastreturns")
	public WebElement reurnstext;
		
	public void checkCommoncomponets() 
	{
		checkPopup();
		checkCookiePopupContent();
		checkLoginLink();
		checkAdidasLogo();
		checkOrderTrackerLink();
		checkNewsletterLink();
		checkSearchOption();
		checkMiniBagOption();
		checkfreedeliverytext();
		checkServiceText();
		checkReturnText();
	}
	
	public void checkPopup()
	{
		try
		{
			if (driver.getTitle().trim().equalsIgnoreCase(constant.URL_LOADING_PAGE_TITLE)) //homepage must have popup other pages optional
			{
				inAction.waitForpageToLoad(driver);
				Assert.assertTrue("Popup on page is not available", inAction.displayElement(driver, btn_popupclose));
			}
			if(inAction.displayElement(driver, btn_popupclose))  //other pages is optional
			{
				inAction.waitForElementToBeClickable(driver, btn_popupclose);
				inAction.buttonClick(driver, btn_popupclose, "Click button : btn_popupclose");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error: Check popup exist is failed.");
			e.printStackTrace();
		}
	}
	public void checkCookiePopupContent()
	{
		try
		{
			if (driver.getTitle().trim().equalsIgnoreCase(constant.URL_LOADING_PAGE_TITLE)) //homepage must have cookie popup, other pages optional
			{
				inAction.waitForpageToLoad(driver);
				Assert.assertTrue("Cookie popup on page is not available", inAction.displayElement(driver, cookiecontent));
			}
			if(ReusableActions.displayElement(driver, cookiecontent))
			{
				Assert.assertTrue("Check cookie popup content failed.",cookiecontent.getText().trim().equalsIgnoreCase(constant.COOKIE_MSG));
				inAction.waitForElementToBeClickable(driver, btn_cookieclose);
				inAction.buttonClick(driver, btn_cookieclose, "Click button : btn_cookieclose");
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Error: Check cookie popup exist is failed.");
			e.printStackTrace();
		}
	}
	public void checkLoginLink()
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Login link on page is not available", inAction.displayElement(driver, click_Login));
		}
		catch(Exception e)
		{
			System.out.println("Error: Check Login link exist is failed.");
			e.printStackTrace();
		}
	}
	
	public void checkOrderTrackerLink()
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Order Tracker link on page is not available", inAction.displayElement(driver, ordertracker));
		}
		catch(Exception e)
		{
			System.out.println("Error: check on order tracker link is failed.");
			e.printStackTrace();
		}
	}
	
	public void checkNewsletterLink()
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Order Tracker link on page is not available", inAction.displayElement(driver,newsletter));
		}
		catch(Exception e)
		{
			System.out.println("Error: check on newsletter link is failed.");
			e.printStackTrace();
		}
	}
	
	public void checkAdidasLogo()
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Login link on page is not available", inAction.displayElement(driver, adidaslogo));
		}
		catch(Exception e)
		{
			System.out.println("Error: Check Login link exist is failed.");
			e.printStackTrace();
		}
	}
	
	public void clickHome()
	{
		try
		{
			ReusableActions.waitForElementToBeClickable(driver, adidaslogo);
			inAction.linkClick(driver, adidaslogo, "Click link : homePage");
		}
		catch (Exception e)
		{
			System.out.println("Error: Click addidas logo is failed.");
			e.printStackTrace();
		}
	}
	public void checkSearchOption() 
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Search option on page is not available", inAction.displayElement(driver, searchbox));//checking for search display
		}
		catch (Exception e)
		{
			System.out.println("Error: Search option is not available on the page.");
			e.printStackTrace();
		}
		
		
	}
	public void checkMiniBagOption() 
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Minicart option on page is not available", inAction.displayElement(driver, minicart));
		}
		catch (Exception e)
		{
			System.out.println("Error: Minibag option is not available on the page.");
			e.printStackTrace();
		}
		
		
	}
	public void checkfreedeliverytext() 
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Shipping text on page is not available", inAction.displayElement(driver, shippingtext));
			Assert.assertTrue("Shipping message is incorrect", shippingtext.getText().trim().equalsIgnoreCase(Constants.FREE_SHIPPING));
		}
		catch (Exception e)
		{
			System.out.println("Error: Shipping text option is not available on the page.");
			e.printStackTrace();
		}
		
	}
	public void checkServiceText() 
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Service Text on page is not available", inAction.displayElement(driver, customerservicetext));
			Assert.assertTrue("Service message is incorrect", customerservicetext.getText().trim().equalsIgnoreCase(Constants.SERVICE_TEXT));
		}
		catch (Exception e)
		{
			System.out.println("Error: Service Text is not available on the page.");
			e.printStackTrace();
		}
		
	}
	public void checkReturnText() 
	{
		try
		{
			inAction.waitForpageToLoad(driver);
			Assert.assertTrue("Return Text on page is not available", inAction.displayElement(driver, reurnstext));
			Assert.assertTrue("Return message is incorrect", reurnstext.getText().trim().equalsIgnoreCase(Constants.RETURNS_TEXT));
		}
		catch (Exception e)
		{
			System.out.println("Error: Return Text is not available on the page.");
			e.printStackTrace();
		}
		
	}
	
	
	}
