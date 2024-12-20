package testCases;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.LogoutPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_002_LoginDDT extends BaseClass {

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "DataDriven") // getting data provider from different
																				// class
	public void TestLogin(String email, String pwd, String exp) {
		logger.info("Test for login is started");

		try {
			LoginPage login = new LoginPage(driver);
			LogoutPage logout = new LogoutPage(driver);
			login.enterUserName(email);
			login.enterPassword(pwd);
			login.clickSubmitButton();
			Thread.sleep(Duration.ofSeconds(5));
			boolean exist = logout.succesfullTextExist();

			if (exp.equalsIgnoreCase("Valid")) {
				if (exist == true) {
					Assert.assertTrue(true);
					logout.clickLogoutButton();
				} else {
					Assert.assertFalse(false);
				}
			} else {
				if (exist == false) {
					Assert.assertTrue(login.checkIfinvalidLoginDisplayed());
				} else {
					Assert.assertFalse(true);
				}
			}
		} catch (Exception ex) {
			Assert.fail();
			logger.info("test failed due to exception -> " + ex);
		}
		logger.info("Test ended for login");
	}
}
