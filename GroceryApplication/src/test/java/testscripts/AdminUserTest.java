package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationGrocery.TestNgbase;
import constant.Constants;
import page.AdminUsersPage;
import page.HomePage;
import page.Loginspage;
import utilities.ExcelUtility;
import utilities.RandomDataUtility;

public class AdminUserTest extends 	TestNgbase{
HomePage home;
AdminUsersPage userpage;
	@Test(description="user is trying to register new admin user")
	public void isUserIsAbleToRegisterNewUser() throws IOException {
		String usernamevalue = ExcelUtility.readStringData(0,0,"Loginpages");
		String passwordvalue = ExcelUtility.readStringData(0,1,"Loginpages");
		
		Loginspage loginpage = new Loginspage(driver);
		loginpage.verifyValidUsernameonUsernamefield(usernamevalue).verifyValidPasswordonPasswordfield(passwordvalue);
		home=loginpage.clickLoginButton();
		userpage=home.clickOnMoreInfo();
		RandomDataUtility random=new RandomDataUtility();
		//String newusernamevalue = ExcelUtility.readStringData(0,0,"adminuser");
		//String newpasswordvalue = ExcelUtility.readStringData(0,1,"adminuser");
		String newusernamevalue =random.randomusername();
		String newpasswordvalue = random.randomPassword();
		userpage.clickaddButton().enterUsername(newusernamevalue).enterPassword(newpasswordvalue).selectDashboard().clickSubmit();
		Boolean alertdisplay=userpage.isalertDisplayed();
		System.out.println(alertdisplay);
		Assert.assertTrue(alertdisplay,Constants.useraddingerror);
	}
	@Test(description="user is trying to search admin user")
	
	public void isUserIsAbleToSearchtheUser() throws IOException
	{
		String usernamevalue = ExcelUtility.readStringData(0,0,"Loginpages");
		String passwordvalue = ExcelUtility.readStringData(0,1,"Loginpages");
		
		Loginspage loginpage = new Loginspage(driver);
		loginpage.verifyValidUsernameonUsernamefield(usernamevalue).verifyValidPasswordonPasswordfield(passwordvalue);
		home=loginpage.clickLoginButton();
		userpage=home.clickOnMoreInfo();
		String newuser = ExcelUtility.readStringData(0,0,"AdminUser");
		userpage.clickOnSearchButton().serachcurrentuser(newuser).selecttype().clickSearch();
		String expected="Admin Users";
		String actual=userpage.admintext();
		Assert.assertEquals(actual,expected ,Constants.adminusersearcherror);
		
	}
	@Test(description="user is trying to retry")
	public void userisAbleToReset() throws IOException {
		String usernamevalue = ExcelUtility.readStringData(0,0,"Loginpages");
		String passwordvalue = ExcelUtility.readStringData(0,1,"Loginpages");
		
		Loginspage loginpage = new Loginspage(driver);
		loginpage.verifyValidUsernameonUsernamefield(usernamevalue).verifyValidPasswordonPasswordfield(passwordvalue);
		home=loginpage.clickLoginButton();
		userpage=home.clickOnMoreInfo();
		userpage.clickResetButton();
		String expected="Admin Users";
		String actual=userpage.admintext();
		Assert.assertEquals(actual,expected ,Constants.adminpagereseterror);
	}
}
