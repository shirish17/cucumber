package com.specomm.adidas.pagecomponents;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.ReusableActions;

public class DeletePage 
{
	static WebDriver driver;
	static ReusableActions inAction = new ReusableActions(); //initialized object of reusable action class	
	
	Constants constant = new Constants();	
	@FindBy(how = How.XPATH, using ="//*[starts-with(@id,'image-')]")
	 public static List<WebElement> pElement;
	
	//category_bag_
	@FindBy(how=How.XPATH,using="//div[contains(@id,'category_bag_')]")
	public static List<WebElement> addToBags;
	
	//select size element
	@FindBy(how=How.XPATH, using="//a[starts-with(@id,'sbSelector_')]")
	public static List<WebElement> selectSizeText;
	
	//Select size drop down values
	@FindBy(how=How.XPATH,using="//ul[starts-with(@id,'sbOptions_')]/li[*]")
	public static List<WebElement> selectSizeDrpDwnValues;
	
	//@FindBy(how=How.XPATH, using ="//div[starts-with(@id,'product-block-close-')]")
	//public static WebElement closePLPProductPopup;
	
	@FindBy(how=How.LINK_TEXT,using="close")
	public static WebElement closePLPProductPopup;
	
	//product price element
	//@FindBy(how=How.XPATH,using="//*[starts-with(@id,'product-price-')/span")
	@FindBy(how=How.CLASS_NAME, using="price")
	public static List<WebElement> productprice;
	
	@FindBy(how = How.XPATH, using ="//*[@id='header']/div[1]/div[1]/div[1]/h1/a")
	public static WebElement adidaslogo;
	
	public DeletePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	public static void deletemethod(String productname)
	{
		int plpProductCount=0;
		int plpProductIndex=0;
		String tempvalue="";
		boolean blnstatus=false;
		try
		{
			tempvalue=getSiteURL();			
			plpProductCount=getProductCountOnPLP(pElement);
			System.out.println("PLP products count is "+plpProductCount);
			plpProductIndex=getPLPProductIndex(productname);
			if((plpProductIndex<0) && (tempvalue.contains("shop")))  //if URL has shop text it referes to the live site
			{
				System.out.println("productname on PLP has negative index of : "+plpProductIndex);
				plpProductIndex=0; //if live environment then set to first element
			}
			System.out.println("productname on PLP has index "+plpProductIndex);
			inAction.mouseHover(driver, pElement.get(plpProductIndex));
			inAction.mouseHover(driver, addToBags.get(plpProductIndex));						
			addToBags.get(plpProductIndex).click();
			//closePLPProductPopup
			inAction.waitForElementToBeClickable(driver, closePLPProductPopup);			
			//System.out.println("size dropdwn size"+selectSizeText.size());
			tempvalue=productprice.get(plpProductIndex).getText().trim();
			System.out.println("price is: "+tempvalue);
			for (int i=0;i<selectSizeText.size();i++)
			{
				tempvalue=selectSizeText.get(i).getText();
				
				if(tempvalue.equalsIgnoreCase("Select size"))
				{
					inAction.waitForElementToBeClickable(driver, selectSizeText.get(i));
					selectSizeText.get(i).click();					
					//blnstatus=true;
					break;
				}
				else if(tempvalue.equalsIgnoreCase("Product is Sold Out"))
				{
					blnstatus=selectSizeText.get(i).isDisplayed();
					Assert.assertFalse("Expected size drop down should not display for sold out but received as displaying.", blnstatus);
					blnstatus=addToBags.get(plpProductIndex).isEnabled();
					Assert.assertFalse("Expected Add to Bag disable but received as Enabled.", blnstatus);
				}
			}
			//selectSizeDrpDwnValues.size();
			//System.out.println("size dropdwn values"+selectSizeDrpDwnValues.size());
			for (int i=0;i<selectSizeDrpDwnValues.size();i++)
			{
				tempvalue=selectSizeDrpDwnValues.get(i).getText();
				System.out.println("Product Size has value "+tempvalue);
				
			}
			//inAction.displayElement(driver, selectSizeText.get(index));
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
	}

	private static int getPLPProductIndex(String productname) 
	{
		int ProductIndex=-1;
		boolean blnstatus = false;
		int iterator=0;
		try
		{
			while(blnstatus==false && iterator<pElement.size())
			{
				if(productname.equalsIgnoreCase(pElement.get(iterator).getAttribute("alt"))) //product name match
				{
					blnstatus=true;
					ProductIndex=iterator;
					break;
				}
				iterator++;					
			}
			if(!blnstatus)
			{
				ProductIndex=-1;
				System.out.println("Warning: Not able to locate element with product name as '"+productname +"' on page. So default to click on first product.");
			}
		}
		
		catch (org.openqa.selenium.InvalidSelectorException invalidSelector)
		{
			ProductIndex=-1;
			System.out.println("invalidSelector exception in 'getPLPProductIndex' method.");
		}
		catch (org.openqa.selenium.ElementNotVisibleException elmNotVisible)
		{
			ProductIndex=-1;
			System.out.println("ElementNotVisibleException in 'getPLPProductIndex' method");
		}
		catch (Exception exception)
		{
			ProductIndex=-1;
			System.out.println("Exception: getPLPProductIndex method.");
			exception.printStackTrace();
		}
		
		return ProductIndex;
	}
	public static String getSiteURL()
	{
		String strTempValue="";
		try
		{
			//inAction.waitForpageToLoad(driver);
			//inAction.displayElement(driver, adidaslogo);
			inAction.mouseHover(driver, adidaslogo);
			strTempValue=adidaslogo.getAttribute("href");
			System.out.println("strTempValue: "+strTempValue);
			//adidaslogo.click();
			//strTempValue=inAction.getAttributeofElement(driver, adidaslogo, "href");
		}
		catch (Exception exception)
		{
			strTempValue="";
			System.out.println("Error: getSiteURL method is failed.");
			exception.printStackTrace();
		}
		return strTempValue;
		
	}

	public static int getProductCountOnPLP(List<WebElement> element)
	{
		int plpProducts=0;
		try
		{
			inAction.waitForpageToLoad(driver);
			plpProducts=element.size();
		}
		catch (org.openqa.selenium.InvalidSelectorException invalidSelector)
		{
			plpProducts=0;
			System.out.println("invalidSelector exception in 'getProductCountOnPLP' method.");
		}
		catch (org.openqa.selenium.ElementNotVisibleException elmNotVisible)
		{
			plpProducts=0;
			System.out.println("ElementNotVisibleException in 'getProductCountOnPLP' method");
		}
		
		catch (java.lang.IndexOutOfBoundsException indOutOfBound)
		{
			plpProducts=0;
			System.out.println("IndexOutOfBoundsException in 'getProductCountOnPLP' method");
		}
		catch (Exception exception)
		{
			plpProducts=0;
			System.out.println("Exception: getProductCountOnPLP method.");
			exception.printStackTrace();
		}
		
		return plpProducts;
	}
	

}
