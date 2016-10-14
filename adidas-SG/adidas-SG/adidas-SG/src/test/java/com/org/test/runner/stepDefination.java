package com.org.test.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.specomm.adidas.common.utils.GeneralActions;
import com.specomm.adidas.common.utils.ReusableActions;
import com.specomm.adidas.pagecomponents.NewAccountCreationPage;
import com.specomm.adidas.pagecomponents.ProductDetailsPage;
import com.specomm.adidas.pagecomponents.SubCategoriesPage;
import com.specomm.adidas.pagecomponents.AdidasCommonPage;
import com.specomm.adidas.pagecomponents.AdidasHomePage;
import com.specomm.adidas.pagecomponents.MyAccountPage;
import com.specomm.adidas.pagecomponents.AdidasLoginPage;
import com.specomm.adidas.pagecomponents.DeletePage;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class stepDefination {
	WebDriver driver;
	String str_brower;
	String str_url;
	NewAccountCreationPage newAccountCreationPage;
	AdidasHomePage adidasHomePage;
	MyAccountPage myaccountpage;
	AdidasLoginPage adidasLoginPage;
	SubCategoriesPage subCategoriesPage;
	ProductDetailsPage productDetailsPage;
	AdidasCommonPage adidascommonpage;
	DeletePage deletepage;
	Logger log4jlogger = Logger.getLogger("devpinoyLogger");
	GeneralActions genAction = new GeneralActions();

	@Before
	public void setup() throws IOException {
		driver = GeneralActions.getDriver(); // changed from framework
		str_brower = genAction.GetValue_Configfile("browser"); // changed from																// framework
		driver = GeneralActions.launchBrowser(driver, str_brower);
		newAccountCreationPage = PageFactory.initElements(driver, NewAccountCreationPage.class);
		// str_url=genAction.GetValue_Configfile("url");
		// ReusableActions.openUrl(driver, str_url) ;//changed from framework
		adidasHomePage=PageFactory.initElements(driver,AdidasHomePage.class);
		myaccountpage=PageFactory.initElements(driver,MyAccountPage.class);
		adidasLoginPage=PageFactory.initElements(driver,AdidasLoginPage.class);
		subCategoriesPage=PageFactory.initElements(driver, SubCategoriesPage.class);
		productDetailsPage=PageFactory.initElements(driver, ProductDetailsPage.class);
		adidascommonpage=PageFactory.initElements(driver, AdidasCommonPage.class);
		deletepage=PageFactory.initElements(driver, DeletePage.class);

	}

	@After
	public void closeBrowser() throws FileNotFoundException, IOException 
	{
		// Write code to close browser
		//new DriverFactory().destroyDriver();
		//driver.quit();

	}

	@Given("^User is on landing page$")
	public void user_is_on_landing_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		str_url = genAction.GetValue_Configfile("url");
		ReusableActions.openUrl(driver, str_url);// changed from framework

	}
	@Then ("^Check for the common components on the page$")
	public void Check_Common_components()
	{
		adidascommonpage.checkCommoncomponets();
	}
	
	/*
	@Then ("^Close promotion popup if exist$")
	public void Close_promotion_popup()
	{
		adidascommonpage.popUp();
	}
	@Then ("^Close cookie poup if exist$")
	public void Close_cookie_poup_if_exist()
	{
		adidascommonpage.closeCookiePopup();
	}
	
	@Then ("^Click on Login link$")
	public void Click_on_Login_link()
	{
		adidascommonpage.clickLogin();
	}
	@Then ("^Click on adidas logo to navigate homepage$")
	public void Click_on_adidas_logo_to_navigate_homepage()
	{
		adidascommonpage.clickHome();
	}
	@And ("^Click order tracker link$")
	public void Click_order_tracker_link()
	{
		adidascommonpage.ordertracker();
	}
	@And ("^Check for search option$")
	public void Check_for_search_option()
	{
		adidascommonpage.checkSearchOption();
	}
	
	@And ("^Check for minibag$")
	public void Check_for_minibag()
	{
		adidascommonpage.checkMiniBagOption();
	}
	@And ("^Check for free delivery text$")
	public void Check_for_free_delivery_text()
	{
		adidascommonpage.checkfreedeliverytext();
	}
	
	@And ("^Check for customer service text$")
	public void Check_for_customer_service_text()
	{
		adidascommonpage.checkServiceText();
	}
	
	@And ("^Check for return text$")
	public void Check_for_return_text()
	{
		adidascommonpage.checkReturnText();
	}
	*/
	/* implement below commented code
	@Then("^User enters newsletter details by entering \"(.*?)\", \"(.*?)\" and \"(.*?)\"$")
	public void user_enters_newsletter_details_by_entering_and(String email, String dob, String gender) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		adidascommonpage.enterNewsletterDetails(email, dob, gender);
	}
	
	@Then("^User search by providing \"(.*?)\"$")
	public void User_search_by_providing(String searchstring) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		adidascommonpage.searchForContent(searchstring);		
	}*/
	
	@Then("^Click on Create Account$")
	public void click_on_Create_Account() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//adidasHomePage.clickCreateAccount();
	}
	

	@Then("^User creates a new account with \"(.*?)\", \"(.*?)\", \"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\",\"(.*?)\" and \"(.*?)\"$")
	public void user_creates_a_new_account_with_and(String rFName, String rLName, String Day, String Month, String year, String rAddress, String rPword, String rcPword) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		newAccountCreationPage.enterNewAccountDetails(rFName, rLName, Day, Month, year, rAddress, rPword, rcPword);
	    
	}
	@Then ("^User logout$")
	public void User_logout()
	{
		MyAccountPage.clickLogout();
	}
	
	@Then("^User provides Login details by entering \"(.*?)\" and \"(.*?)\"$")
	public void logIn_Action(String email, String password) throws InterruptedException
	{
		adidasLoginPage.logIn_Action(email,password);
	}
	
	@Then ("^Check for invalid login$")
	public void Verify_Invalid_Password()
	{
		adidasLoginPage.verifyInvalidPassword();
	}
	
		
	@Then("^Check customer \"(.*?)\"$")
	public void check_customer(String customername) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		myaccountpage.Verify_LoginSuccess(customername);
	}
	@When ("^Customer selects main menu \"(.*?)\"$")
	public void main_menu_selection(String mainmenuname) throws InterruptedException
	{
		adidasHomePage.mainMenuSelection(mainmenuname);		
	}
	@Then("^Customer checks for the products count$")
	public void customer_checks_for_the_products() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//adidasHomePage.checkProductsOnCategory();
		adidasHomePage.checkProductsCountOnCategory();
	}
	@Then("^Click a \"(.*?)\" from PLP page to open PDP$")
	public void Customer_selects_a_product_from_PLP_page(String productname) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		//adidasHomePage.navigateToPDPfromPLP(productname);
	}
	@Then("^Customer adds a \"(.*?)\" of \"(.*?)\" from PLP page$")
	public void customer_adds_a_from_PLP_page(String productname, String size) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new PendingException();			
		adidasHomePage.addproductfromPLP(productname, size);
		
	}
	@Then("^Check PLP \"(.*?)\" has subcategory name$")
	public void Check_PLP_ProductSubCategoryName(String productname)
	{		
		adidasHomePage.CheckProductSubCategoryName(productname);	
		
	}
	
	@Then("^Check PLP \"(.*?)\" has price with currency symbol$")
	public void Check_PLP_Product_hasPrice(String productname)
	{		
		adidasHomePage.CheckProductPrice(productname);			
		
	}
	
	@Then("^Customer checks product is added to cart$")
	public void Check_PLP_AddtoBag()
	{		
		adidasHomePage.CheckAddToBag();	
		
	}
	
	@And ("^Customer selects product randomly$")
	public void product_Random_Selection()
	{
		subCategoriesPage.productRandomSelection();
	}
	@Then("^Add product to bag from PDP page$")
	public void prodct_add_ToBag()
	{
		productDetailsPage.selectProductToCart();
	}
	@And ("^Check PDP page for \"(.*?)\"$")
	public void Check_PDP_page_for(String productname)
	{
		productDetailsPage.verifyPDPPage(productname);
	}
	@Then("^Customer add \"(.*?)\" to cart and validate minicart$")
	public void Customer_addproduct_to_cart(int qty)
	{
		productDetailsPage.addProductToCart(qty);
	}
	
	@Then("^Delete step Customer selects a \"(.*?)\" from PLP$")
	public void Delete_step(String productname)
	{		
		DeletePage.deletemethod(productname);	
		
	}

}
