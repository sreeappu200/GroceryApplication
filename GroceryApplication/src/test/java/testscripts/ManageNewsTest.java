package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationGrocery.TestNgbase;
import constant.Constants;

import page.HomePage;
import page.Loginspage;
import page.ManageNewsPage;
import utilities.ExcelUtility;

public class ManageNewsTest extends TestNgbase {
	HomePage home;
	ManageNewsPage newspage;
	
	@Test(description="user is trying to add new news")
	public void isUserIsAbleToAddNewNews() throws IOException {
		String usernamevalue = ExcelUtility.readStringData(0,0,"Loginpages");
		String passwordvalue = ExcelUtility.readStringData(0,1,"Loginpages");
		
		Loginspage loginpage = new Loginspage(driver);
		loginpage.verifyValidUsernameonUsernamefield(usernamevalue).verifyValidPasswordonPasswordfield(passwordvalue);
		home=loginpage.clickLoginButton();
		newspage=home.clickOnMoreInfoNews();
		String addnews = ExcelUtility.readStringData(0,0,"addnews");
		newspage.clickNewButton().addnewsinfield(addnews).clickOnAdd();
		
		Boolean alertdisplay=newspage.isalertDisplayed();
		System.out.println(alertdisplay);
		Assert.assertTrue(alertdisplay,Constants.newsaddingerror);
		
	}
	@Test(description="user is trying to search news")
	public void isUserIsAbleTosearchNews() throws IOException {
		String usernamevalue = ExcelUtility.readStringData(0,0,"Loginpages");
		String passwordvalue = ExcelUtility.readStringData(0,1,"Loginpages");
		
		Loginspage loginpage = new Loginspage(driver);
		loginpage.verifyValidUsernameonUsernamefield(usernamevalue).verifyValidPasswordonPasswordfield(passwordvalue);
		home=loginpage.clickLoginButton();
		newspage=home.clickOnMoreInfoNews();
		newspage.clickSearch();
		String serachnew = ExcelUtility.readStringData(0,0,"addnews");
		newspage.serachNews(serachnew).clickSearchnews();
		String expected="Search Manage News";
		String actual=newspage.newspage();
		Assert.assertEquals(actual,expected ,Constants.newssearcherror);
		
	}
}
