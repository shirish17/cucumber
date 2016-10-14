package com.org.test.runner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.org.test.pages.SignInPage;
import com.org.test.utils.CommonFunctions;
import com.org.test.utils.DriverFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps
{
	public static String str_brower;	
	static WebDriver driver;
	HomePage homepage_obj=PageFactory.initElements(driver, HomePage.class);
	SignInPage SingInPage_obj =PageFactory.initElements(driver, SignInPage.class);
	@Before
	public static void Driversetup() throws FileNotFoundException, IOException
	{
		// write code to get browser name from propery and then create a browser		
		driver=new DriverFactory().getDriver();
	}

	@After
	public static void closeBrowser() throws FileNotFoundException, IOException 
	{
		// Write code to close browser
		//new DriverFactory().destroyDriver();
		
	}

	@Given("^User is on landing page$")
	public void OpenApp() throws Throwable 
	{
	   //Read the URL from property file
		String URL="";
		URL=CommonFunctions.GetValue_Configfile("url");
		if (URL!=null)
		{
			driver.navigate().to(URL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	    
	}

	@When("^User logins with \"(.*?)\" and \"(.*?)\"$")
	public void user_logins_with_and(String email, String password) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		SingInPage_obj.login(email,password);
	    
	}
	
	
	@Then("^Verify that user is on \"(.*?)\"$")
	public void verify_that_user_is_on(String arg1) throws Throwable 
	{
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	@Then("^provide a \"(.*?)\" criteria$")
	public void provide_a_criteria(String searchtext) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		homepage_obj.Search(String searchtext)
	    
	}
	@Then("^validate the result$")
	public void validate_the_result() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	@Then("^user logout$")
	public void user_logout() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	@Then("^User close the browser$")
	public void user_close_the_browser() throws Throwable {
	    
	}


}
