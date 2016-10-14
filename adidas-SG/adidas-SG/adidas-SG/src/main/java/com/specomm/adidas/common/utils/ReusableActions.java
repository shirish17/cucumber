package com.specomm.adidas.common.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriverException;
import com.specomm.adidas.common.exception.ElementNotEditable;
import com.specomm.adidas.common.exception.ElementNotVisible;

import junit.framework.Assert;


public class ReusableActions
{
	public static WebDriver driver;
	public static String msgText;
	private static final Exception NullPointerException = null;
	public static Logger log4jlogger =Logger.getLogger("devpinoyLogger");
	public static GeneralActions gActions = new GeneralActions();
	public static String fsep=System.getProperty("file.separator");
	public static int timeoutsec=60;
	public static Random rand;
	
	public static void openUrl(WebDriver driver, String url)
	{
		// TODO Auto-generated method stub
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	public static void waitForpageToLoad(WebDriver driver)
	{
		try
		{
			driver.manage().timeouts().pageLoadTimeout(timeoutsec, TimeUnit.SECONDS);
			}
		catch(Throwable t)
		{
			Assert.fail();
			
		}
	}
	
	public void buttonClick(WebDriver driver, WebElement element, String msg){
		log4jlogger.debug(msg);
		try{
			if(element.getText()!=null){
				msgText = element.getText();}
			else{
				msgText = element.getTagName();}				
			
			if(element.isDisplayed() && element.isEnabled()){
					try{
						element.click();
						log4jlogger.debug("	Clicked on " + msgText + " button");
					}
					catch(WebDriverException webex){
						String message = webex.getMessage();
						if(message.toLowerCase().contains("unknown error".toLowerCase())){
							element.click();
						}else{
							log4jlogger.debug("Button can't be Clicked ");
							throw webex;}
					}
				}else{
					
					throw new ElementNotEditable("Button[WebElement] is non Editable : " );
			}
			log4jlogger.debug("In " + driver.getTitle() + " page, Clicked on :" + msgText + " button field.");
			}
			catch(Throwable thr){
			if(thr instanceof ElementNotVisible){
				String message = "In " + driver.getTitle() + " page, " + msgText + " button field is not visible."; 
				log4jlogger.debug(message, thr);
				throw new ElementNotVisible(message, thr);
			}else if(thr instanceof ElementNotEditable){
				String message = "In " + driver.getTitle() + " page, " + msgText + " button field is not editable.";
				log4jlogger.debug(message, thr);
				throw new ElementNotEditable(message, thr);
			
			}
		}		
	}
	
	public  void linkClick(WebDriver driver, WebElement element, String msg){
		log4jlogger.debug(msg);
		try{
			if(element.getText()!=null){
				msgText = element.getText();}
			else{
				msgText = element.getTagName();}				
			
			if(element.isDisplayed() && element.isEnabled()){
					try{
						element.click();
						log4jlogger.debug("	Clicked on " + msgText + " link");
					}
					catch(WebDriverException webex){
						String message = webex.getMessage();
						if(message.toLowerCase().contains("unknown error".toLowerCase())){
							element.click();
						}else{
							log4jlogger.debug("link can't be Clicked ");
							throw webex;}
					}
				}else{
					
					throw new ElementNotEditable("Link[WebElement] is non Clickable : " );
			}
			log4jlogger.debug("In " + driver.getTitle() + " page, Clicked on :" + msgText + " Web Link.");
			}
			catch(Throwable thr){
			if(thr instanceof ElementNotVisible){
				String message = "In " + driver.getTitle() + " page, " + msgText + " Link is not visible."; 
				log4jlogger.debug(message, thr);
				throw new ElementNotVisible(message, thr);
			}else if(thr instanceof ElementNotEditable){
				String message = "In " + driver.getTitle() + " page, " + msgText + " Link is not Clickable.";
				log4jlogger.debug(message, thr);
				throw new ElementNotEditable(message, thr);
			
			}
		}		
	}
	
	public void inputText(WebDriver driver, WebElement element , CharSequence value, String msg)
	{
		// TODO Auto-generated method stub
		log4jlogger.debug(msg);
		try
		{
			if (element.getText()!=null)
			{
				msgText = element.getText();
			}
			
		else
		{
			msgText = element.getTagName();}
			element.clear();
			element.sendKeys(value);
			log4jlogger.debug("In" + driver.getTitle() +"page, entered the value: " +value + "for " +msgText +"textbox field.");
		}
		catch(NoSuchElementException nex)
		{
			String message = "In " + driver.getTitle() + " page, " + msgText + " is not present."; 
			log4jlogger.debug(message, nex);
			
			throw new NoSuchElementException(message, nex);
		}
		catch(Throwable thr)
		{
			if(thr instanceof ElementNotVisible)
			{
				String message = "In " + driver.getTitle() + " page, " + msgText + " textbox field is not visible."; 
				log4jlogger.debug(message, thr);
				throw new ElementNotVisible(message, thr);
			}
			else if(thr instanceof ElementNotEditable)
			{
				String message = "In " + driver.getTitle() + " page, " + msgText + " textbox field is not editable.";
				log4jlogger.debug(message, thr);
				throw new ElementNotEditable(message, thr);
			}
			else
			{
				String message = "In " + driver.getTitle() + " page, Got some exception while performing action " + msgText + " textbox field.";
				log4jlogger.debug(message,  thr);				
				throw new RuntimeException(message, thr);
			}
		}
		
	}
	public static boolean isElementVisible(WebElement element, String msg) throws ElementNotVisibleException
	{
		
		log4jlogger.debug(msg);
		return element.isDisplayed();
		
	}
	public static void selectItemFromDropdown(WebDriver driver, WebElement element , String valToBeSelected, String msg){
		log4jlogger.debug(msg);
		try{
			if(element.getText()!=null){
				msgText = element.getText();}
			else{
				msgText = element.getTagName();}
			List <WebElement> options = driver.findElements(By.tagName("option"));
			Select selectOption = new Select(element);
			if(options!=null){
				for (WebElement option : options) {
			    	if (valToBeSelected.equalsIgnoreCase(option.getText())){
			    		//selectOption.deselectAll(); - To be used only in case of multiselect
			    		selectOption.selectByVisibleText(valToBeSelected);}
			    	}
			log4jlogger.debug("In " + driver.getTitle() + " page, Select : " + valToBeSelected + " for " + msgText + " from dropdown field field.");
			}else{
				log4jlogger.error("In " + driver.getTitle() + " page, No Options are available in the dropdowns");
				throw NullPointerException;
			}
		}catch(NoSuchElementException nex){
			String message = "In " + driver.getTitle() + " page, " + msgText + " is not present."; 
			log4jlogger.error(message, nex);
			
			throw new NoSuchElementException(message, nex);
		}catch(Throwable thr){
			if(thr instanceof ElementNotVisible){
				String message = "In " + driver.getTitle() + " page, " + msgText + " textbox field is not visible."; 
				log4jlogger.error(message, thr);
				throw new ElementNotVisible(message, thr);
			}else if(thr instanceof ElementNotEditable){
				String message = "In " + driver.getTitle() + " page, " + msgText + " textbox field is not editable.";
				log4jlogger.debug(message, thr);
				throw new ElementNotEditable(message, thr);
			}else{
				String message = "In " + driver.getTitle() + " page, Got some exception while performing action " + msgText + " textbox field.";
				log4jlogger.error(message,  thr);
				
				throw new RuntimeException(message, thr);
			}
		}
	}
	public void selectCheckbox(WebDriver driver, WebElement element, String msg){
		log4jlogger.debug(msg);
		try{
			if(element.getText()!=null){
				msgText = element.getText();}
			else{
				msgText = element.getTagName();}				
			if(element.isDisplayed() && element.isEnabled() && !element.isSelected()){
					try{
						element.click();
						log4jlogger.debug("	Clicked on " + msgText + " CheckBox");
					}
					catch(WebDriverException webex){
						String message = webex.getMessage();
						if(message.toLowerCase().contains("unknown error".toLowerCase())){
							element.click();
						}else{
							log4jlogger.debug("CheckBox can't be Selected ");
							throw webex;}
					}
				}else if(element.isDisplayed() && element.isEnabled() && element.isSelected()){
					log4jlogger.debug("CheckBox is already Selected ");
				}else{
					
					throw new ElementNotEditable("CheckBox[WebElement] is non Editable : " );
			}
			log4jlogger.debug("In " + driver.getTitle() + " page, Clicked on :" + msgText + " CheckBox field.");
			}
			catch(Throwable thr){
			if(thr instanceof ElementNotVisible){
				String message = "In " + driver.getTitle() + " page, " + msgText + " CheckBox field is not visible."; 
				log4jlogger.debug(message, thr);
				throw new ElementNotVisible(message, thr);
			}else if(thr instanceof ElementNotEditable){
				String message = "In " + driver.getTitle() + " page, " + msgText + " CheckBox field is not editable.";
				log4jlogger.debug(message, thr);
				throw new ElementNotEditable(message, thr);
			}
			}
	}
	
	
	public static boolean displayElement(WebDriver driver,WebElement e )
	{
		try
		{
			if(e.isDisplayed())
			{
				return true;
			}
			
			System.out.println("Success : Element '" +e+"'is  displayed");  // changed from framework
			//Reporter.log("Success : Element is  displayed");
		}
		catch(Throwable t)
		{
			t.printStackTrace();
//			Assert.fail();
			//Reporter.log("Failed : Element is not displayed");
			System.out.println("Failed : Element '" +e+"'is not displayed");  // changed from framework
		}
		return false;
	}
	//Get the current system time
	 
	 public static String getCurrentTimeStamp()
	    { 
	          SimpleDateFormat CurrentDate = new SimpleDateFormat("MM-dd-yyyy"+"_"+"HH-mm-ss");
	          Date now = new Date(); 
	         String CDate = CurrentDate.format(now); 
	          return CDate; 
	    }
	 
	 public static int randomSelection(List<WebElement> elements) throws InterruptedException
	 {
		    System.out.println("*****size"+elements.size());
		    rand = new Random();
		    
			int randNumber =  rand.nextInt(elements.size());			
			System.out.println("**********randomSelection"+randNumber+"**********");		
			
			return randNumber;
	 }
	 
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element)
	{
		// TODO Auto-generated method stub
		try
		{
			WebDriverWait wait =new WebDriverWait(driver, timeoutsec);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			//System.out.println("Success: Element is clickable.");
			
		}
		catch (Exception E)
		{
			System.out.println("Failed : Element '" +element +"'is not Clickable.");
			E.printStackTrace();
		}
		
	}
	public static void waitForElementToBeInvisible(WebDriver driver, WebElement element)
	{
		
		try
		{
			FluentWait<WebDriver> wait= new FluentWait<WebDriver>(driver);
			wait.withTimeout(10, TimeUnit.SECONDS);		
			wait.pollingEvery(10,TimeUnit.SECONDS);
			wait.ignoring(NoSuchElementException.class);
			wait.until(new ExpectedCondition<Boolean>()
				{
				public Boolean apply(WebDriver driver)
				{
					return (!element.isDisplayed());			
				}
				}
			);
			/*WebDriverWait wait =new WebDriverWait(driver, timeoutsec);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
			blnstatus=true;*/
		}
		catch (Exception E)
		{
			System.out.println("Failed : Element '" +element +"'is still visible.");			
			E.printStackTrace();
		}		
	}
	public static void mouseHover(WebDriver driver,WebElement element) throws InterruptedException
	{
		try
		{
			Actions builder = new Actions(driver);			
			builder.moveToElement(element).build().perform();
			log4jlogger.debug("Success : Mouse Over");
		}
		catch(Throwable thr)
		{
			Thread.sleep(2000);
			Assert.fail();
			log4jlogger.debug("Failed : Mouse Over",thr);
			//Reporter.log("Failed : Mouse Over");
		}
	}
	public String getAttributeofElement(WebDriver driver,WebElement element, String attributename)
	{
		String strAttributeValue="";
		try
		{
			if(attributename.equalsIgnoreCase("getText"))
			{
				strAttributeValue=element.getText().trim();
			}
			else
			{
				strAttributeValue=element.getAttribute(attributename).trim();
			}			
			if(strAttributeValue.isEmpty())
			{
				strAttributeValue="";
				System.out.println("The attribute '"+attributename+"' is blank for the element '"+element+"' in getAttributeofElement." );
			}
		}
		catch (Exception E)
		{
			System.out.println("Failed : Element '" +element +"'is still visible.");			
			E.printStackTrace();
		}
		return strAttributeValue;
		
	}
}
