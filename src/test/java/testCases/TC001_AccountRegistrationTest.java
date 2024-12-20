package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	
	@Test(groups = {"Regression","Master"})
	public void verifyAccountRegistration() throws InterruptedException {
		logger.info("**** Starting TC001_AccountRegistrationTest ****");
		
		try 
		{
		HomePage home = new HomePage(driver);
		logger.info("Clicked on Register link");
		scrollToElement(home.RegisterButton);
		home.ClickOnRegisterButton();
		
		logger.info("Providing customer details");
		AccountRegistrationPage registerationPage = new AccountRegistrationPage(driver);
		registerationPage.enterFirstName(RandomStringUtils.randomAlphabetic(4));
		registerationPage.enterLastName(RandomStringUtils.randomAlphabetic(4));
		registerationPage.setGender("Male");
		registerationPage.setEmail(RandomStringUtils.randomAlphabetic(6)+"@gmail.com");
		scrollToBottom();
		registerationPage.setVerificationText();
		registerationPage.clickSubmitButton();
		
		logger.info("Validating expected message");
		String textMessage = registerationPage.getSucessfullMessage();
		Assert.assertTrue(textMessage.contains("Registration Form is Successfully Submitted."));
		}
		catch(Exception ex) {
			logger.error("Test failed....");
			logger.debug("Debug logs...");
			Assert.fail();
		}
	}
}
