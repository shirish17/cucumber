package com.specomm.adidas.pagecomponents;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.specomm.adidas.common.utils.ReusableActions;

import junit.framework.Assert;

import com.specomm.adidas.common.exception.ElementNotVisible;
import com.specomm.adidas.common.utils.Constants;
import com.specomm.adidas.common.utils.GeneralActions;


public class AdidasHomePage
{
	WebDriver driver;
	//final String SING_CURRENCY="S$";
	ReusableActions inAction = new ReusableActions();
	Constants constants=new Constants();
	AdidasCommonPage commonpage = new AdidasCommonPage(driver);
	GeneralActions genAction = new GeneralActions();
	
	//public static String strURL="";
	public AdidasHomePage(WebDriver driver)
	{ 
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	} 
	
	@FindBy(how=How.CLASS_NAME,using="totalCount")
	public WebElement productCount;
	
	//PLP product images
	@FindBy(how = How.XPATH, using ="//*[starts-with(@id,'image-')]")
	 public List<WebElement> pElement;
	
	//homepage main menu
	@FindBy(how=How.XPATH, using ="//span[@class='show_bk']")
	public List<WebElement>megaMenuElements;
	
	//PLP add to bag
	@FindBy(how=How.XPATH,using="//div[contains(@id,'category_bag_')]")
	public List<WebElement> addToBags;
	
	//product price element
	@FindBy(how=How.CLASS_NAME, using="price")
	public  List<WebElement> productprice;
	
	@FindBy(how=How.XPATH, using="//a[starts-with(@id,'sbSelector_')]")
	public List<WebElement> selectSizeText;
	
	//PLP close button for mousehover on product
		@FindBy(how=How.LINK_TEXT,using="close")
		public WebElement closePLPProductPopup;
		
		//Select size drop down values
		@FindBy(how=How.XPATH,using="//ul[starts-with(@id,'sbOptions_')]/li[*]")
		public List<WebElement> selectSizeDrpDwnValues;
		
	//Add to bag success message
	@FindBy(how=How.XPATH,using="//div[starts-with(@id,'cart-success-')]")
	public WebElement cartSuccessMsg;
	
	//cartsuccessicon
	@FindBy(how=How.XPATH,using="//div[starts-with(@id,'cart-success-')]/a[1]")
	public WebElement cartSuccessIcon;
	
	@FindBy(how=How.LINK_TEXT,using="bag")
	public WebElement cartSuccessMsgBagLinkTest;
	
	public void mainMenuRandomSelection() 
	{
		// TODO Auto-generated method stub
		
	}
	public void selectCategoryRandomly(List<WebElement>sectionElemts)
	{
		try
		{
			System.out.println(sectionElemts.size());
			int randNum=ReusableActions.randomSelection(sectionElemts);
			ReusableActions.waitForElementToBeClickable(driver,sectionElemts.get(randNum));
			System.out.println("**********sectionelement"+sectionElemts.get(randNum));
			ReusableActions.waitForpageToLoad(driver);
			inAction.buttonClick(driver,sectionElemts.get(randNum),"Click the product");
		}
		catch(Exception t)
		{
			t.printStackTrace();
		}
	}
	
	public void mainMenuSelection(String mainmenuname) throws InterruptedException
	{
		try{
		ReusableActions.waitForpageToLoad(driver);		
		int menuNum=megaMenuElements.size();
		if(menuNum>0)
		{
			for (int iterator=0;iterator<=menuNum;iterator++)
			{
				if(megaMenuElements.get(iterator).getText().equalsIgnoreCase(mainmenuname))
				{
					megaMenuElements.get(iterator).click();
					break;
					//selectCategory(megaMenuElements,iterator);
				}				
			}
		}		
		
	}
	catch (org.openqa.selenium.NoSuchElementException e)
		{
			String message="Not able to find element "+mainmenuname +"in method 'mainMenuSelection'";
			//log4jlogger.debug(message, e);
		}
	}
	public void checkProductsCountOnCategory()
	{
		boolean blnstatus=false;
		int tempcount=0;
		try
		{
			tempcount=getProductsCountOnCategory();
			if(tempcount>0)
			{
				blnstatus=true;
			}
			else
			{
				blnstatus=false;
			}
			Assert.assertTrue("Failure: Product count on category page is zero." , blnstatus);
		}
		catch(Exception exception)
		{
			System.out.println("Error: Error  in checkProductsCountOnCategory.");
			exception.printStackTrace();
		}
	}
	public int getProductsCountOnCategory()
	{
		
		int productCountonCategoryPage=0;
		String tempvalue="";
		boolean blnstatus=false;
		String strtempresult="";
		try
		{
			inAction.waitForpageToLoad(driver);
			blnstatus=inAction.displayElement(driver, productCount);
			Assert.assertTrue("Products are displayed on PLP page.", blnstatus);
			tempvalue=productCount.getText().trim();
			blnstatus=tempvalue.isEmpty();
			Assert.assertEquals("Failure: Product count on PLP page is empty, as expected 'false' but returns "+blnstatus, false, blnstatus);
			Pattern strpattern = Pattern.compile("[^0-9]"); //checking that string pattern has value other than numbers
			Matcher matcher = strpattern.matcher(tempvalue);
			if(!matcher.matches())
			{
				strtempresult=tempvalue.replaceAll("[\\,]","");  //if it has quoma (,) then replace with null
				productCountonCategoryPage=Integer.parseInt(strtempresult);
				System.out.println("Category product count is: "+productCountonCategoryPage);					
			}	
			
		}
		catch (NumberFormatException ex)          //catching number format exception if received string has values with quoma formating
		{
			System.out.println("Exception: NumberFormatException for element '"+productCount+"', while formating value: "+tempvalue+" in getProductsCountOnCategory method.");
			productCountonCategoryPage=0;			
			
		}
		
		catch (Exception E)
		{
			System.out.println("Error: Error while receiving product count for element: " +productCount +" in getProductsCountOnCategory.");
			productCountonCategoryPage=0;
			E.printStackTrace();
		}
		return productCountonCategoryPage;
	}
	public void CheckProductSubCategoryName(String productname)
	{
		int plpProductIndex=0;
		String strURL="";
		String [] temp;
		String tempvalue="";
		WebElement productsubCategory=null;
		boolean blnstatus=false;
		String strTempValue="";
		try
		{
			inAction.waitForpageToLoad(driver);	
			strURL=getURLFromConfig();
			plpProductIndex=getPLPProductIndex(productname);
			//System.out.println("Debug++ plpProductIndex: "+plpProductIndex);
			if((plpProductIndex<0)&&(strURL.contains("shop")))
			{
				System.out.println("productname on PLP for method 'navigateToPDPfromPLP' has negative index of : "+plpProductIndex);
				plpProductIndex=0; //if live environment then set to first element
			}
			inAction.mouseHover(driver, pElement.get(plpProductIndex));
			inAction.waitForElementToBeClickable(driver, pElement.get(plpProductIndex));
			inAction.mouseHover(driver, addToBags.get(plpProductIndex));
			inAction.waitForElementToBeClickable(driver, addToBags.get(plpProductIndex));
			inAction.linkClick(driver, addToBags.get(plpProductIndex), "Click on Add Bag button failed for PLP");
			
			temp=pElement.get(plpProductIndex).getAttribute("id").trim().split("\\-",2);
			tempvalue=temp[1].trim();
			//runtime creation of subcategory element
			productsubCategory=driver.findElement(By.xpath("//*[starts-with(@id,'product-"+tempvalue+"')]/div/div[3]/div[2]/p"));
			blnstatus=inAction.displayElement(driver, productsubCategory);			
			Assert.assertTrue("Failure: Element '"+productsubCategory+"' 	is not visible.", blnstatus);
			inAction.mouseHover(driver, productsubCategory);
			strTempValue=inAction.getAttributeofElement(driver, productsubCategory, "innerText");
			System.out.println("Subcategory name is "+strTempValue);
			if(!strTempValue.isEmpty())
			{
				blnstatus=true;
			}
			Assert.assertTrue("Failure: Expected element: '"+productsubCategory+"' has name but recieved '" +strTempValue+".", blnstatus);				
			
		}
		catch (Exception exception)
		{
			System.out.println("Error: Error while checking sub category on PLP page using CheckProductSubCategoryName.");
			exception.printStackTrace();
		}
	}
	public int getPLPProductIndex(String productname)
	{
		int ProductIndex=-1;
		boolean blnstatus = false;
		int iterator=0;
		try
		{
			inAction.waitForpageToLoad(driver);
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
				System.out.println("Warning: Not able to locate element with product name as '"+productname +"' on page.");
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
	public String getURLFromConfig()
	{
		String strURL="";
		boolean blanstatus=false;
		try
		{
			strURL=genAction.GetValue_Configfile("url").trim();
			System.out.println("URL" +strURL);
			if(!strURL.isEmpty())
			{
				blanstatus=true;
			}			
			Assert.assertTrue("URL from property file is blank.", blanstatus);
		}
		catch (Exception exception)
		{
			System.out.println("Exception: getURLFromConfig method.");
			strURL="";
			exception.printStackTrace();
			
		}
		return strURL;
		
	}
	public void CheckProductPrice(String productname)
	{
		int plpProductIndex=0;
		boolean blnstatus=false;
		String tempvalue="";
		String strURL="";
		try
		{
			strURL=genAction.GetValue_Configfile("url").trim();
			System.out.println("URL" +strURL);
			plpProductIndex=getPLPProductIndex(productname);
			inAction.mouseHover(driver, pElement.get(plpProductIndex));
			inAction.waitForElementToBeClickable(driver, pElement.get(plpProductIndex));
			blnstatus=inAction.displayElement(driver, productprice.get(plpProductIndex));
			Assert.assertTrue("Failure: Element '"+productprice+"' is not visible.", blnstatus);
			tempvalue=productprice.get(plpProductIndex).getText().trim();
			System.out.println("price value is "+tempvalue);
			if(!tempvalue.isEmpty())
			{
				blnstatus=true;
			}
			Assert.assertTrue("Failure: Expected element: '"+productprice+"' has price text but recieved '" +tempvalue+".", blnstatus);
			if(strURL.contains("sg")) //URL is for singapore
			{
				blnstatus=tempvalue.contains(constants.SG_CURRENCY);				
			}
			else if(strURL.contains("my"))
			{
				blnstatus=tempvalue.contains(constants.MY_CURRENCY);
			}
			else if(strURL.contains("th"))
			{
				blnstatus=tempvalue.contains(constants.TH_CURRENCY);
			}
			else if(strURL.contains("ph"))
			{
				blnstatus=tempvalue.contains(constants.PH_CURRENCY);
			}
			else
			{
				blnstatus=false;
			}			
			Assert.assertTrue("Failure: Element '"+productprice+"' do not contains currency symbol.", blnstatus);
		}
		catch (Exception exception)
		{
			System.out.println("Error: Error while checking product price in CheckProductPrice.");
			exception.printStackTrace();
		} 
	  }
	public void addproductfromPLP(String productname, String size) throws InterruptedException, java.io.FileNotFoundException 
	{
		int plpProductIndex=0;
		String strURL="";
		int iterator=0;
		String tempvalue="";
		boolean blnstatus=false;
		int randomnumber=0;
		String strTempValue="";
		String [] temp;
		try
		{
			inAction.waitForpageToLoad(driver);
			strURL=getURLFromConfig();
			plpProductIndex=getPLPProductIndex(productname);
			//System.out.println("URL & Product index "+strURL+", "+plpProductIndex);
			if((plpProductIndex<0) && (strURL.contains("shop")))  //if URL has shop text it referes to the live site
			{
				System.out.println("productname on PLP for method 'navigateToPDPfromPLP' has negative index of : "+plpProductIndex);
				plpProductIndex=0; //if live environment then set to first element
			}
			inAction.mouseHover(driver, pElement.get(plpProductIndex));
			inAction.waitForElementToBeClickable(driver, pElement.get(plpProductIndex));
			inAction.mouseHover(driver, addToBags.get(plpProductIndex));
			inAction.waitForElementToBeClickable(driver, addToBags.get(plpProductIndex));
			inAction.linkClick(driver, addToBags.get(plpProductIndex), "Click on Add Bag button failed for PLP");
			for (iterator=0;iterator<selectSizeText.size();iterator++)
			{
				tempvalue=selectSizeText.get(iterator).getText().trim();
				
				if(tempvalue.equalsIgnoreCase("Select size"))
				{
					inAction.waitForElementToBeClickable(driver, selectSizeText.get(iterator));										
					blnstatus=true;
					break;
				}
				else if(tempvalue.equalsIgnoreCase("Product is Sold Out"))
				{
					blnstatus=selectSizeText.get(iterator).isDisplayed();
					Assert.assertFalse("Expected size drop down should not display for sold out but received as displaying.", blnstatus);
					blnstatus=addToBags.get(plpProductIndex).isEnabled();
					Assert.assertFalse("Expected Add to Bag disable but received as Enabled.", blnstatus);
					break;
				}
			}
			if(tempvalue.equalsIgnoreCase("Product is Sold Out") && blnstatus==false)
			{
				//This block is executed when sold out product is highlighted, so close this product and select a new
				if(inAction.displayElement(driver, closePLPProductPopup))
				{
					inAction.waitForElementToBeClickable(driver, closePLPProductPopup);
					inAction.linkClick(driver, closePLPProductPopup, "Click on close popup option for PLP failed.");
					if(strURL.contains("shop")) //execute below block if URL is live store and product out of stock
					{
						System.out.println("Warning: Selected product- "+pElement.get(iterator) +"is out of stock. So selected another product.");
						//Call random function to select product randomly
						randomnumber=inAction.randomSelection(pElement);
						if(randomnumber==plpProductIndex && plpProductIndex!=1)
						{
							randomnumber=1; //hardcoded if random number returns the same out of stock product
							plpProductIndex=randomnumber;
							inAction.mouseHover(driver, pElement.get(plpProductIndex));							
							inAction.waitForElementToBeClickable(driver, pElement.get(plpProductIndex));																		
							blnstatus=inAction.displayElement(driver, addToBags.get(plpProductIndex));
							Assert.assertTrue("Failure: Element '"+addToBags.get(plpProductIndex)+"' is not visible.", blnstatus);
							inAction.mouseHover(driver, addToBags.get(plpProductIndex));
							inAction.waitForElementToBeClickable(driver, addToBags.get(plpProductIndex));
							inAction.linkClick(driver, addToBags.get(plpProductIndex), "Click on Add Bag button failed for PLP");
							
						}
					}					
				}
			}			
			inAction.waitForElementToBeClickable(driver, selectSizeText.get(iterator));
			inAction.buttonClick(driver, selectSizeText.get(iterator), "Not able to click on 'Select size' drpdwn");
			blnstatus=false;
			int newiterator=0;
			while(blnstatus==false && newiterator<selectSizeDrpDwnValues.size()) //check for size value
			{
				inAction.mouseHover(driver, selectSizeDrpDwnValues.get(newiterator));
				strTempValue=inAction.getAttributeofElement(driver, selectSizeDrpDwnValues.get(newiterator), "getText");				
				if(size.equalsIgnoreCase(strTempValue))
				{
					System.out.println("size to select is "+size);
					blnstatus=true;
					inAction.waitForElementToBeClickable(driver, selectSizeDrpDwnValues.get(newiterator));
					inAction.buttonClick(driver, selectSizeDrpDwnValues.get(newiterator), "Not able to select a value from size drop down.");
					break;
				}
				newiterator++;
			}
			if((!blnstatus)&& strURL.contains("shop"))  //not able to select required size, may be size is not available or size text is not correct
			{
				System.out.println("Warning: Not able to locate required size '"+size +"' for the product, so selected random size from drop down for live environment.");
				//code to select size randomly				
				//below code is to get the drop down element, please note drop down attribute is changing runtime
				temp=inAction.getAttributeofElement(driver, selectSizeText.get(iterator), "id").trim().split("\\_",2);  //run time id is changing so split based on "_"			temp=inAction.getAttributeofElement(driver, selectSize, "id").trim().split("\\_",2);  //run time id is changing so split based on "_"			temp=inAction.getAttributeofElement(driver, selectSize, "id").trim().split("\\_",2);  //run time id is changing so split based on "_"
				tempvalue=temp[1].trim();
				//below code generate drop down value element at run time
				List<WebElement> selectSizeDrpDwnValues=driver.findElements(By.xpath("//ul[starts-with(@id,'sbOptions_"+tempvalue+"')]/li[*]"));
				randomnumber=inAction.randomSelection(selectSizeDrpDwnValues); //get random number from size dropdown
				System.out.println("randomnumber " +randomnumber);
				if(randomnumber==0)     //select size drop down is having some empty values as well so hardcoded the condition
				{
					randomnumber=1; //first value of size drop down value is "select size" which not required
				}
				newiterator=randomnumber;				
				inAction.waitForElementToBeClickable(driver, selectSizeDrpDwnValues.get(newiterator));
				inAction.buttonClick(driver, selectSizeDrpDwnValues.get(newiterator), "Not able to select a value from size drop down at index "+newiterator+".");
			}
			JavascriptExecutor jse = (JavascriptExecutor)driver; //scrolling page down so that Add to Bag button visible
			jse.executeScript("window.scrollBy(0,250)", "");
			inAction.waitForElementToBeClickable(driver, addToBags.get(plpProductIndex));
			inAction.linkClick(driver, addToBags.get(plpProductIndex), "Click on Add Bag button failed for PLP");
			
		}
		
		catch (Exception E)
		{
			
			E.printStackTrace();
		}
	 }
	public void CheckAddToBag()
	{
		String tempvalue="";
		boolean blnstatus=false;
		try
		{
			tempvalue=cartSuccessMsg.getAttribute("innerText").trim();
			if(tempvalue.equalsIgnoreCase(constants.PLP_CART_SUCCESS_MSG))
			{
				 blnstatus=true;
			}
			Assert.assertTrue("Failure: Expected text is "+constants.PLP_CART_SUCCESS_MSG+" but received text "+tempvalue+".", blnstatus);
			inAction.displayElement(driver, cartSuccessIcon);
			blnstatus=inAction.displayElement(driver, cartSuccessMsgBagLinkTest);
			Assert.assertTrue("Element Display failure: Element -'"+cartSuccessMsgBagLinkTest+"' is not displayed.", blnstatus);
			inAction.waitForElementToBeClickable(driver, cartSuccessMsgBagLinkTest);
			inAction.linkClick(driver, cartSuccessMsgBagLinkTest, "Click Failure for element '"+cartSuccessMsgBagLinkTest+"'.");
		}
		catch (org.openqa.selenium.InvalidSelectorException invalidSelector)
		{
			System.out.println("invalidSelector exception");
		}
		catch (org.openqa.selenium.ElementNotVisibleException elmNotVisible)
		{
			System.out.println("ElementNotVisibleException in 'CheckAddToBag' method");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	}

//note for finding attribute and passing as parameter
//productNameOnPLP=driver.findElement(By.xpath("//img[starts-with(@id,'image-"+tempvalue+"')]"));
//AddToBag=driver.findElement(By.xpath("//button[starts-with(@id,'"+tempvalue+"')]"));  //assigned to value received from id attribute