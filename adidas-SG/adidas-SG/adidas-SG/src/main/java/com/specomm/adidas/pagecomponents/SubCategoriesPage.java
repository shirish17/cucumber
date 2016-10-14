package com.specomm.adidas.pagecomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.ReusableActions;

public class SubCategoriesPage 
{
WebDriver driver;
AdidasHomePage adidasHomePage=new AdidasHomePage(driver);
public SubCategoriesPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
@FindBy(how = How.XPATH, using ="//*[@id='top']/body/div[1]/div/div[3]/div[1]/div[2]/div[2]/div/div/div")
public WebElement noProductsAvailable;

//subCateElements1
@FindBy(how=How.XPATH,using="//*[starts-with(@id,'image-')]")
public List<WebElement> subCateElements1;

@FindBy(how=How.XPATH,using="//*[starts-with(@id,'image-')]")
public WebElement subCateE1;

public void productRandomSelection()
{
	adidasHomePage=new AdidasHomePage(driver);
	boolean status = false;
	try
	{
		if(ReusableActions.displayElement(driver,driver.findElement(By.className("col-main-loader"))))
			if(ReusableActions.displayElement(driver, driver.findElement(By.className("message"))))
			{
				System.out.println("There are no products matching the selection.");
				status= true;
				while(status)
				{
					adidasHomePage.mainMenuRandomSelection();
					ReusableActions.waitForpageToLoad(driver);
					productRandomSelection();
					if(!ReusableActions.displayElement(driver, noProductsAvailable))
					{
						status= false;
					}
				}
			}
			else
			{
				if(ReusableActions.displayElement(driver, subCateE1))
				{
					ReusableActions.waitForpageToLoad(driver);
					Thread.sleep(3000);
					adidasHomePage.selectCategoryRandomly(subCateElements1);
				}
			}
	}
	catch(Exception e){
		//e.printStackTrace();
	}
}
}
