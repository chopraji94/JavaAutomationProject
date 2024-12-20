package pageObjects;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//label[text()='First Name  ']//following-sibling::input")
	WebElement firstNameTxtBox;
	
	@FindBy(xpath="//label[text()='Last Name  ']//following-sibling::input")
	WebElement lastNameTxtBox;
	
	@FindBy(xpath="//li//label[text()='Male']")
	WebElement maleRadioBtn;
	
	@FindBy(xpath="//li//label[text()='Female']")
	WebElement femaleRadioBtn;
	
	@FindBy(xpath="//label[text()='Email  ']//following-sibling::input")
	WebElement emailTxtBox;
	
	@FindBy(xpath="//fieldset[contains(@class,'verification')]//input[@type='text']//following-sibling::label")
	WebElement verificationTxt;
	
	@FindBy(xpath="//fieldset[contains(@class,'verification')]//input[@type='text']")
	WebElement verificationTxtBox;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[@id='messageContainer']")
	WebElement sucessfullMessage;
	
	public String getSucessfullMessage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		return wait.until(ExpectedConditions.visibilityOf(sucessfullMessage)).getText();
	}
	
	public void clickSubmitButton() {
		submitBtn.click();
	}
	
	public void setVerificationText() {
		String verificationText = Arrays.asList(verificationTxt.getText().split(" ")).get(1);
		verificationTxtBox.sendKeys(verificationText);
	}
	
	public void setEmail(String email) {
		emailTxtBox.sendKeys(email);
	}
	
	public void setGender(String gender) {
		if(gender == "Male") {
			maleRadioBtn.click();
		}
		else {
			femaleRadioBtn.click();
		}
	}
	
	public void enterLastName(String lastName) {
		lastNameTxtBox.sendKeys(lastName);
	}
	
	public void enterFirstName(String firstName) {
		firstNameTxtBox.sendKeys(firstName);
	}
}
