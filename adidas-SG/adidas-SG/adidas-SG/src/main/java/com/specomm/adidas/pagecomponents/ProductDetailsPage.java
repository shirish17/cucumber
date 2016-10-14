package com.specomm.adidas.pagecomponents;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.ReusableActions;

import junit.framework.Assert;

public class ProductDetailsPage 
{
	WebDriver driver;
	boolean blnstatus=false;
	ReusableActions inAction = new ReusableActions();
	public Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	public ProductDetailsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.XPATH, using ="//*[@id='thumb-slider']/li[1]/a/img")
	public WebElement imageSlider;
	
	//Product name
	@FindBy(how=How.CLASS_NAME, using="productname")
	public WebElement productname;
	
	//product price
	@FindBy(how=How.CLASS_NAME, using="price-box")
	public WebElement productprice;
	
	
	//Article color
	@FindBy(how=How.XPATH,using="//*[@id='label_articlecolor']/div/div")
	public WebElement colorLabel;
	
	//Color options
	@FindBy(how=How.CLASS_NAME, using ="swatchanchor")
	public WebElement coloroptions;
	
	//Size drop down
	@FindBy(how=How.XPATH, using =".//*[@id='value_sizesearchvalue']/div[2]/div/div/a")
	public WebElement drpdwn_sizelist;
	
	//Add to Bag button
	@FindBy(how=How.CLASS_NAME,using="add-to-cart")
	public WebElement addToCartBtn;
	
	//PDP large image
	//@FindBy(how=How.CLASS_NAME,using ="product-image")
	@FindBy(how=How.ID,using="zoom1")
	public WebElement productimagelarge;
	
	@FindBy(how=How.XPATH,using="//*[@id='product_addtocart_form']/div[2]/div[2]/div[1]")	
	public WebElement productlogo;
	
	@FindBy(how=How.CLASS_NAME,using="flag-text")
	public WebElement newtext;
	
	@FindBy(how=How.ID, using="check-store-availability")
	public WebElement btnstoreavailability;
	
	@FindBy(how=How.XPATH,using ="//*[@id='write-review-form']/button")
	public WebElement btnwritereview;
	
	@FindBy(how=How.ID,using="cartCount")
	public WebElement minicart;
	
	
	
public void selectProductToCart()
{
	try
	{
		inAction.buttonClick(driver, imageSlider, "Click Image Slider");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}

public void verifyPDPPage(String productname1) 
{
	try
	{
		ReusableActions.waitForpageToLoad(driver);
		if(driver.getTitle().contains(productname1))
		{
			log4jlogger.debug("Success as PDP page contains "+productname1);
			System.out.println("Success "+productname1);
		}
		else
		{
			log4jlogger.debug("Failure as PDP page does not contain "+productname1);
		}
			
		Assert.assertTrue("Text found",productname.getText().contains(productname1));
		log4jlogger.debug("Success as PDP page contains productname as '"+productname1 +"'.");
		Assert.assertTrue("Checking $ symbol in price", productprice.getText().contains("S$"));
		ReusableActions.displayElement(driver, addToCartBtn);
		Assert.assertNotNull("Color Label is null on PDP page.", colorLabel);
		blnstatus=ReusableActions.displayElement(driver, coloroptions);		
		Assert.assertTrue("Element '"+coloroptions + "' is not displayed on PDP",blnstatus);
		if(ReusableActions.displayElement(driver, drpdwn_sizelist))
		{
			System.out.println("displayed " +drpdwn_sizelist.getText());			
			log4jlogger.debug("Displayed element: '" +drpdwn_sizelist.getText() +"' on PDP page.");
		}
		
		if(ReusableActions.displayElement(driver, productimagelarge))	
		{
			System.out.println("displayed " +productimagelarge.getAttribute("href"));
			log4jlogger.debug("Displayed element: '" +productimagelarge.getAttribute("href") +"' on PDP page.");
		}
		
		
		if(ReusableActions.displayElement(driver, productlogo))	
		{
			System.out.println("displayed " +productlogo.getClass());					
			
			log4jlogger.debug("Displayed element: '" +productlogo.getText() +"' on PDP page.");
		}
		
		if(ReusableActions.displayElement(driver, productlogo))
		{
			 System.out.println("Element has "+newtext.getText());
			Assert.assertTrue("New text on PDP page is not displayed.", newtext.getText().equalsIgnoreCase("New"));
		}
		
		if(ReusableActions.displayElement(driver, productlogo))
		{
			System.out.println("Element has "+btnstoreavailability.getText());
			Assert.assertTrue("New text on PDP page is not displayed.", btnstoreavailability.getText().equalsIgnoreCase("Check Store Availability"));
		}
		
		if(ReusableActions.displayElement(driver, btnwritereview))
		{
			 System.out.println("Element has: "+btnwritereview.getText());
			Assert.assertTrue("New text on PDP page is not displayed.", btnwritereview.getText().equalsIgnoreCase("Write a Review"));
		}
		if(ReusableActions.displayElement(driver, minicart))
		{
			 System.out.println("Element has: "+minicart.getText());
			
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

public void addProductToCart(int qty)
{
	int minicartcount=0;
	try
	{
		if(ReusableActions.displayElement(driver, minicart))
		{			
			if (minicart.getText()!=null)
			{
				if(Integer.parseInt(minicart.getText())>0)
				{
					minicartcount=Integer.parseInt(minicart.getText());
				}
			}
			
		}
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
}
}
