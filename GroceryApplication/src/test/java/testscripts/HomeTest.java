package testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import automationGrocery.TestNgbase;
import constant.Constants;
import page.Loginspage;
import page.HomePage;
import utilities.ExcelUtility;

public class HomeTest extends TestNgbase{
	HomePage home;
@Test(description="user is trying to logout")
public void verifyUserisabletoSuccessfullyLoggedoutCredentials() throws IOException {
	String userNameValue=ExcelUtility.readStringData(0, 0, "Loginpages");
	String passwordValue=ExcelUtility.readStringData(0, 1, "Loginpages");
	Loginspage loginpage=new Loginspage(driver);
	loginpage.verifyValidUsernameonUsernamefield(userNameValue).verifyValidPasswordonPasswordfield(passwordValue);
	home=loginpage.clickLoginButton();
	home.clickOnadminButton();
	loginpage=home.clickOnLoginoutButton();
	String expected="7rmart supermarket";
	String actual=loginpage.isTitleDisplayed();
	Assert.assertEquals(actual,expected ,Constants.logouterror);
}
}
	







