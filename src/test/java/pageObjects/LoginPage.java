package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	@FindBy(id="username")
	WebElement userNameTextBox;
	
	@FindBy(id="password")
	WebElement passwordTextBox;
	
	@FindBy(id="submit")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[text()='Your username is invalid!']")
	WebElement invalidTxt;
	
	public void enterUserName(String userName) {
		userNameTextBox.sendKeys(userName);
	}
	
	public void enterPassword(String password) {
		passwordTextBox.sendKeys(password);
	}
	
	public void clickSubmitButton() {
		submitBtn.click();
	}
	
	public boolean checkIfinvalidLoginDisplayed() {
		return invalidTxt.isDisplayed();
	}
}
