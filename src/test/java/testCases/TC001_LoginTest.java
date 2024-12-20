package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import testBase.BaseClass;

public class TC001_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity","Master"})
	public void TestLogin()
	{
		logger.info("Test for login is started");
		
		try
		{
		LoginPage login = new LoginPage(driver);
		LogoutPage logout = new LogoutPage(driver);
		login.enterUserName(p.getProperty("email"));
		login.enterPassword(p.getProperty("password"));
		login.clickSubmitButton();
		Assert.assertTrue(logout.getsuccessfullloginHeaderText().contains("Logged In Successfully"));
		}
		catch(Exception ex) {
			Assert.fail();
		}
		logger.info("Test ended for login");
	}
}
